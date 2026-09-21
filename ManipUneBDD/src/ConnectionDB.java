import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static String url = "jdbc:mariadb://localhost:3306/shop";
    private static String login = "root";

    public static String getUrl() {
        return url;
    }

    public static String getLogin() {
        return login;
    }
}
