package dao;

import config.DataBaseConfig;
import models.TransactionDaoModel;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao extends Dao<TransactionDaoModel> {
    public TransactionDao() {
        super("vue_details_transferts");
    }

    public boolean withdrawMoney(Connection connection, String numero_compte, BigDecimal money, Integer id_depot){
        String sqlRequest = "INSERT INTO retrait(date_retrait, somme, numero_compte, id_depot) VALUES (?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setObject(1, LocalDateTime.now());
            preparedStatement.setBigDecimal(2, money);
            preparedStatement.setString(3, numero_compte);
            if(id_depot == null){
                preparedStatement.setString(4, null);
            } else {
                preparedStatement.setInt(4, id_depot);
            }

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted== 1) {
                return true;
            } else {
                return false;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Integer depositMoney(Connection connection, String numero_compte, BigDecimal money){
        String sqlRequest = "INSERT INTO depot(date_depot, somme, numero_compte, description) VALUES (?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, LocalDateTime.now());
            preparedStatement.setBigDecimal(2, money);
            preparedStatement.setString(3, numero_compte);
            preparedStatement.setString(4, null);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted== 1) {
                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if(generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                    else{
                        return null;
                    }
                }
            } else {
                return null;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<TransactionDaoModel> getAllRelatedTransaction(String numero_compte) {
        List<TransactionDaoModel> list = new ArrayList<>();
        String sqlRequest = "SELECT * FROM " + vueNameTable + " " +
                "WHERE numero_destinataire = ? OR numero_origin = ?";
        try (Connection connection = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USER, null)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
            preparedStatement.setString(1, numero_compte);
            preparedStatement.setString(2, numero_compte);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(mapRow(resultSet));
                }
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected TransactionDaoModel mapRow(ResultSet rs) throws SQLException {
        return new TransactionDaoModel(rs.getString("numero_origin"),
                rs.getString("numero_destinataire"),
                rs.getBigDecimal("montant"),
                rs.getString("description"),
                rs.getDate("date_operation"));
    }
}
