package business;

import dao.TransactionDao;
import models.DepotModel;
import models.RetraitModel;
import models.TransactionDaoModel;
import models.TransactionModel;

import java.util.ArrayList;
import java.util.List;

public class TransactionBusiness {
    private final TransactionDao transactionDao;

    public TransactionBusiness() {
        this.transactionDao = new TransactionDao();
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
}
