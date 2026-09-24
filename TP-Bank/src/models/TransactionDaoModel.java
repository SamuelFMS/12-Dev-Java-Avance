package models;

import utils.DisplayTableInterface;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDaoModel {
    private final String numeroOrigin;
    private final String numeroDestinataire;
    private final BigDecimal montant;
    private final String description;
    private final Date date;

    public TransactionDaoModel(String numeroOrigin, String numeroDestinataire, BigDecimal montant, String description, Date date) {
        this.numeroOrigin = numeroOrigin;
        this.numeroDestinataire = numeroDestinataire;
        this.montant = montant;
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction " + numeroOrigin + " -> " + numeroDestinataire + " (" + montant + " euros)" + "\ndescription: " + description;
    }

    public String getNumeroOrigin() {
        return numeroOrigin;
    }

    public String getNumeroDestinataire() {
        return numeroDestinataire;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }
}
