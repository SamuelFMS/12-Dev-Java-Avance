package utils;

import exceptions.EmptyArrayException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DisplayTable <T extends DisplayTableInterface>{
    protected final List<T> data;
    private List<T> searchData;
    private final int numberOfItemPerPage = 10;
    private int currentPage = 0;

    public DisplayTable(T[] data) {
        this.data = java.util.Arrays.asList(data);
        searchData = this.data ;
    }

    public DisplayTable(List<T> data){
        this.data = data;
        searchData = this.data;
    }

    public void searchInData(String search){
        currentPage = 0;
        searchData = new ArrayList<>();
        for(T ligne : data){
            boolean find = false;
            for (String s : ligne.getRowData()){
                if (s.toUpperCase().contains(search.toUpperCase())){
                    find=true;
                    break;
                }
            }
            if(find){
                searchData.add(ligne);
            }
        }
        if(searchData.isEmpty()){
            System.out.println("Aucun résultat trouvée pour la recherche");
            searchData = data;
        }
    }

    public int getTotalNumberPage(){
        return (int)Math.ceil((double) searchData.size() / numberOfItemPerPage);
    }

    public int[] getSizeColumn(){
        List<T> currentData = getCurrentData();
        String[] columnName = currentData.get(0).getColumnNames();

        int[] result = new int[columnName.length];
        for (int columnNumber =0; columnNumber<columnName.length; columnNumber++){
            result[columnNumber] = columnName[columnNumber].length();
            for(int rowNumber = 0; rowNumber < currentData.size(); rowNumber++) {
                result[columnNumber] = Math.max(result[columnNumber], currentData.get(rowNumber).getRowData()[columnNumber].length());
            }
        }
        return result;
    }

    public List<T> getCurrentData(){
        return searchData.subList(currentPage*10, Math.min((currentPage+1)*10, searchData.size()));
    }

    private void displayTable() throws EmptyArrayException {
        if(searchData.isEmpty()){
            throw new EmptyArrayException();
        }
        int[] sizeColumn = getSizeColumn();
        String[] columnName = searchData.get(0).getColumnNames();

        /*
            Display First line +----+-------+------+
         */
        StringBuilder delimiter = new StringBuilder();
        for(int columnIndex = 0; columnIndex < columnName.length; columnIndex++) {
            delimiter.append("+");
            delimiter.append(StringUtils.repeat("-", sizeColumn[columnIndex]+2));
        }
        delimiter.append("+");
        System.out.println(delimiter);

        /*
            Display Header
         */
        System.out.print("| ");
        for(int columnIndex = 0; columnIndex < columnName.length; columnIndex++){
            System.out.print(StringUtils.padOrTrunc(columnName[columnIndex], sizeColumn[columnIndex]) + " | ");
        }
        System.out.println();
        System.out.println(delimiter);

        for(T line: getCurrentData()){
            String[] colRowData = line.getRowData();
            System.out.print("| ");
            for(int columnIndex = 0; columnIndex < colRowData.length; columnIndex++)
                System.out.print(StringUtils.padOrTrunc(colRowData[columnIndex], sizeColumn[columnIndex]) + " | ");
            System.out.println();
        }
        System.out.println(delimiter);
        System.out.println();
    }

    public void show(Scanner scanner) throws EmptyArrayException {
        displayTable();
        System.out.printf("Page %d sur %d  Total : %d éléments  %n", currentPage+1, getTotalNumberPage(), searchData.size());
        if(currentPage+1 < getTotalNumberPage()){
            System.out.print("[N]ext ");
        }
        if(currentPage > 0) {
            System.out.print("[P]rev ");
        }
        System.out.print("[S]earch ");
        if(data.size() != searchData.size()) {
            System.out.print("[R]eset ");
        }
        System.out.println("[Q]uit");
        String s = scanner.next();
        if(s.equalsIgnoreCase("N")){
            if(currentPage+1 < getTotalNumberPage()) {
                currentPage++;
                show(scanner);
            }
        } else if (s.equalsIgnoreCase("P")) {
            if(currentPage > 0) {
                currentPage--;
                show(scanner);
            }
        } else if (s.equalsIgnoreCase("S")) {
            System.out.println("Entrez votre recherche: ");
            String search = scanner.next();
            searchInData(search);
            show(scanner);
        } else if (s.equalsIgnoreCase("R")) {
            if(searchData.size() != data.size()) {
                searchData = data;
                currentPage = 0;
                show(scanner);
            }
        }
    }
}
