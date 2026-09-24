package dao;

import config.DataBaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao<T> {
    protected final String vueNameTable;

    protected Dao(String vueNameTable) {
        this.vueNameTable = vueNameTable;
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
