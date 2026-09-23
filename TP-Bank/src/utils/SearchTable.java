package utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchTable<T extends DisplayTableInterface> extends DisplayTable<T>{
    private static final int ID_COLUMN_TABLE = 0;

    private void check(){
        Set<String> checked = new HashSet<>();
        for(T currentToCheck: this.data){
            if(!checked.add(currentToCheck.getRowData()[ID_COLUMN_TABLE])){
                throw new IllegalArgumentException("Duplicate item: " + currentToCheck.getRowData()[ID_COLUMN_TABLE]);
            }
        }

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
