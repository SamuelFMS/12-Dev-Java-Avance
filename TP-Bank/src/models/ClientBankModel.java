package models;

import utils.DisplayTableInterface;

public class ClientBankModel implements DisplayTableInterface {

    private String numero;
    private String titulaire;
    private int solde;

    public ClientBankModel(String numero, String titulaire, int solde) {
        this.numero = numero;
        this.titulaire = titulaire;
        this.solde = solde;
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
