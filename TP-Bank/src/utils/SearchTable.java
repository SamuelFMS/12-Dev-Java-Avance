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
        this.data.forEach(currentToCheck -> {
            String value = currentToCheck.getRowData()[ID_COLUMN_TABLE].toUpperCase();
            if (!checked.add(value)) {
                throw new IllegalArgumentException("Duplicate item: " + value);
            }
        });
    }

    private boolean findKey(String id){
        return this.data.stream()
                .anyMatch(maData -> maData.getRowData()[ID_COLUMN_TABLE].equalsIgnoreCase(id));
    }

    @Override
    public String show(Scanner scanner, String text) throws EmptyArrayException {
        String s = "";
        do {
            s = super.show(scanner, text);
            if(s == null) {
                break;
            }
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
