package dao;

import models.ClientModel;

import java.sql.Connection;
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

    public String createClient(Connection connection, String numberAccount, String holder) throws SQLException {
        String sql = "INSERT INTO " + creationNameTable + "(numero_compte, titulaire) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, numberAccount);
        preparedStatement.setString(2, holder);

        int rowsInserted = preparedStatement.executeUpdate();

        if(rowsInserted == 1) {
            return numberAccount;
        }
        else {
            return null;
        }
    }
}