package models;

import utils.DisplayTableInterface;

import java.math.BigDecimal;
import java.util.Date;

public abstract class TransactionModel implements DisplayTableInterface {
    protected final String numeroDestinataire;
    protected final BigDecimal montant;
    protected final String description;
    protected final Date date;

    protected TransactionModel(String numeroDestinataire, BigDecimal montant, String description, Date date) {
        this.numeroDestinataire = numeroDestinataire;
        this.montant = montant;
        this.description = description;
        this.date = date;
    }

    public abstract String getType();
    public abstract String getOrigineOrDestination();
    public abstract String getMontant();


    @Override
    public String[] getColumnNames() {
        return new String[]{"Type", "Origine / Destination", "Montant", "date", "description"};
    }
    @Override
    public String[] getRowData() {
        return new String[]{getType(), getOrigineOrDestination(), getMontant(), date.toString(), description==null?"---": description};
    }
}
