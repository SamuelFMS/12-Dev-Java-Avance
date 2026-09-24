package models;

import java.math.BigDecimal;
import java.util.Date;

public class DepotModel extends TransactionModel{
    public DepotModel(String numeroDestinataire, BigDecimal montant, String description, Date date) {
        super(numeroDestinataire, montant, description, date);
    }

    @Override
    public String getType() {
        return this.numeroDestinataire == null?"Dépot":"Virement";
    }

    @Override
    public String getOrigineOrDestination() {
        return this.numeroDestinataire == null?"---":numeroDestinataire;
    }

    @Override
    public String getMontant() {
        return "+" + montant.toString();
    }
}
