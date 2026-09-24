package dao;

import config.DataBaseConfig;
import models.ClientModel;
import models.TransactionDaoModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao extends Dao<TransactionDaoModel> {
    public TransactionDao() {
        super("vue_details_transferts");
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
