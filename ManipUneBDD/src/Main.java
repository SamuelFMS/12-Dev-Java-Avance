import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ArrayList<Article> articles = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mariadb://localhost:3306/shop";
        String login = "root";

        try (Connection connection = DriverManager.getConnection(url, login, null)) {
            String strSql = "SELECT * FROM T_Articles";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(strSql)) {
                    while (resultSet.next()) {
                        int rsIdUser = resultSet.getInt(1); //soit index(de 1 à n) de la colonne, soit le nom de la co
                        String rsDescription = resultSet.getString(2);
                        String rsBrand = resultSet.getString(3);
                        double rsPrice = resultSet.getDouble(4);
                        articles.add((new Article(rsIdUser, rsDescription, rsBrand, rsPrice)));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Affichages de tous les articles
        articles.forEach(article -> System.out.println(article));
    }
}