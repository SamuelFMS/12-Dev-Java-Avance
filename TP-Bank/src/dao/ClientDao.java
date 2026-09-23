package dao;

import models.ClientModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDao extends Dao<ClientModel> {

    public ClientDao() {
        super("vue_solde_utilisateurs", "numero_compte");
    }

    @Override
    protected ClientModel mapRow(ResultSet rs) throws SQLException {
        return new ClientModel(rs.getString("numero_compte"), rs.getString("titulaire"), rs.getBigDecimal("total"));
    }
}
