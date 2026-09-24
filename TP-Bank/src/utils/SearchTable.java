package utils;

import exceptions.EmptyArrayException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SearchTable<T extends DisplayTableInterface> extends DisplayTable<T>{
    private static final int ID_COLUMN_TABLE = 0;

    private void check(){
        Set<String> checked = new HashSet<>();
        for(T currentToCheck: this.data){
            if(!checked.add(currentToCheck.getRowData()[ID_COLUMN_TABLE].toUpperCase())){
                throw new IllegalArgumentException("Duplicate item: " + currentToCheck.getRowData()[ID_COLUMN_TABLE]);
            }
        }
    }

    private boolean findKey(String id){
        for(T currentToCheck: this.data) {
            if (currentToCheck.getRowData()[ID_COLUMN_TABLE].equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String show(Scanner scanner, String text) throws EmptyArrayException {
        String s = "";
        do {
            s = super.show(scanner, text);
        } while (!findKey(s));
        return s;

    }

    public SearchTable(T[] data) {
        super(data);
        check();
    }

    public SearchTable(List<T> data) {
        super(data);
        check();
    }
}
