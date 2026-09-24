package business;

import config.DataBaseConfig;
import dao.ClientDao;
import models.ClientModel;
import validation.Validator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ClientBusiness {
    private final ClientDao clientDao;

    public ClientBusiness() {
        clientDao = new ClientDao();
    }

    public List<ClientModel> getAllClients() {
        return clientDao.getAll();
    }

    public ClientModel getClient(String numberAccount){
        return clientDao.getClient(numberAccount);
    }

    public String createClient(String numberAccount, String holder) {
        if (!Validator.isValidAccountNumber(numberAccount)) {
            throw new IllegalArgumentException("Numéro de compte invalide : " + numberAccount);
        }

        try (Connection connection = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USER, null)) {
            connection.setAutoCommit(false);

            try {
                String res = clientDao.createClient(connection, numberAccount, holder);

                if (res == null) {
                    connection.rollback();
                } else {
                    connection.commit();
                }
                return res;
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
