package dao;

import config.DataBaseConfig;
import models.ClientModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDao extends Dao<ClientModel> {
    String creationNameTable = "utilisateur";

    public ClientDao() {
        super("vue_solde_utilisateurs");
    }

    @Override
    protected ClientModel mapRow(ResultSet rs) throws SQLException {
        return new ClientModel(rs.getString("numero_compte"), rs.getString("titulaire"), rs.getBigDecimal("total"));
    }

    public ClientModel getClient(String numberAccount) {
        ClientModel clientModel = null;
        String sql = "SELECT * FROM " + vueNameTable + " WHERE numero_compte=?";
        try (Connection connection = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USER, null)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, numberAccount);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        clientModel = mapRow(resultSet);
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return clientModel;
    }

    public String createClient(Connection connection, String numberAccount, String holder) throws SQLException {
        String sql = "INSERT INTO " + creationNameTable + "(numero_compte, titulaire) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, numberAccount);
        preparedStatement.setString(2, holder);

        int rowsInserted = preparedStatement.executeUpdate();

        if (rowsInserted == 1) {
            return numberAccount;
        } else {
            return null;
        }
    }
}