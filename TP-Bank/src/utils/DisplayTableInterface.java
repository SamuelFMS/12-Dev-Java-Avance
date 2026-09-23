package utils;

public interface DisplayTableInterface {
    /**
     * Renvoie les entêtes des colonnes
     */
    String[] getColumnNames();

    /**
     * Renvoie les données de la ligne pour l'instance actuelle
     */
    String[] getRowData();
}
