package dao;

import config.DataBaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao<T> {
    protected final String vueNameTable;
    protected final String creationNameTable;
    protected final String nameIdColumn;

    protected Dao(String vueNameTable, String creationNameTable, String nameIdColumn) {
        this.vueNameTable = vueNameTable;
        this.creationNameTable = creationNameTable;
        this.nameIdColumn = nameIdColumn;
    }

    protected abstract T mapRow(ResultSet rs) throws SQLException;

    public List<T> getAll(){
        List<T> list = new ArrayList<>();
        String sqlRequest = "SELECT * FROM " + vueNameTable;
        try (Connection connection = DriverManager.getConnection(DataBaseConfig.URL, DataBaseConfig.USER, null)) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sqlRequest)) {
                    while (resultSet.next()) {
                        list.add(mapRow(resultSet));
                    }
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
