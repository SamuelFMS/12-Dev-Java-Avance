package models;

import java.math.BigDecimal;
import java.util.Date;

public class RetraitModel extends TransactionModel{

    public RetraitModel(String numeroDestinataire, BigDecimal montant, String description, Date date) {
        super(numeroDestinataire, montant, description, date);
    }

    @Override
    public String getType() {
        return this.numeroDestinataire == null?"Retrait":"Virement";
    }

    @Override
    public String getOrigineOrDestination() {
        return this.numeroDestinataire == null?"---":numeroDestinataire;
    }

    @Override
    public String getMontant() {
        return "-" +montant.toString();
    }
}
