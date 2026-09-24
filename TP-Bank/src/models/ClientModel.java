package models;

import utils.DisplayTableInterface;

import java.math.BigDecimal;

public class ClientModel implements DisplayTableInterface {

    private String numero;
    private String titulaire;
    private BigDecimal solde;

    public ClientModel(String numero, String titulaire, BigDecimal solde) {
        this.numero = numero;
        this.titulaire = titulaire;
        this.solde = solde;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitulaire() {
        return titulaire;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    @Override
    public String[] getColumnNames() {
        return new String[]{"Numéro","Titulaire", "Solde"};
    }

    @Override
    public String[] getRowData() {
        return new String[]{numero, titulaire, String.valueOf(solde)};
    }
}
