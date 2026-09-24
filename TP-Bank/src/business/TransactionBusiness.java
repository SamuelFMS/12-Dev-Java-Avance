package business;

import config.DataBaseConfig;
import dao.ClientDao;
import dao.TransactionDao;
import exceptions.SoldeInsuffisant;
import models.DepotModel;
import models.RetraitModel;
import models.TransactionDaoModel;
import models.TransactionModel;
import validation.Validator;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionBusiness {
    private final TransactionDao transactionDao;
    private final ClientDao clientDao;

    public TransactionBusiness() {
        this.transactionDao = new TransactionDao();
        this.clientDao = new ClientDao();
    }

    public List<TransactionModel> getAllRelated(String numeroCompte){
        List<TransactionModel> list = new ArrayList<>();
        for(TransactionDaoModel tdm : transactionDao.getAllRelatedTransaction(numeroCompte)){
            if(tdm.getNumeroDestinataire() != null && tdm.getNumeroDestinataire().equalsIgnoreCase(numeroCompte)){
                list.add(new DepotModel(tdm.getNumeroOrigin(), tdm.getMontant(), tdm.getDescription(), tdm.getDate()));
            }
            else {
                list.add(new RetraitModel(tdm.getNumeroDestinataire(), tdm.getMontant(), tdm.getDescription(), tdm.getDate()));
            }
        }
        return list;
    }

    public boolean withdrawMoney(String numeroCompte, BigDecimal value) throws SoldeInsuffisant {
        if(Validator.isAValidDecimal(value.toString())){
            if(clientDao.getClient(numeroCompte).getSolde().compareTo(value)>=0) {
                try (Connection connection = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USER, null)) {
                    connection.setAutoCommit(false);
                    if (transactionDao.withdrawMoney(connection, numeroCompte, value)) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        return false;
                    }
                } catch (SQLException e){
                    e.printStackTrace();
                    return false;
                }
            } else {
                throw new SoldeInsuffisant();
            }
        } else{
            throw new RuntimeException("Invalid withdraw montant " + value);
        }
    }

    public boolean depositMoney(String numeroCompte, BigDecimal value){
        if(Validator.isAValidDecimal(value.toString())){
            try (Connection connection = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USER, null)) {
                connection.setAutoCommit(false);
                if (transactionDao.depositMoney(connection, numeroCompte, value)) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            } catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }
        else {
            throw new RuntimeException("Invalid deposit montant " + value);
        }
    }
}
