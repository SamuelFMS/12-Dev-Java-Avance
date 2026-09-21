import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArticleDB {

    public static boolean create(Article article){
        String strSql = "INSERT INTO t_articles (Description, Brand, UnitaryPrice) VALUES(?, ?, ?) ";
        try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrl(), ConnectionDB.getLogin(), null)) {
            PreparedStatement preparedStatement = connection.prepareStatement(strSql);
            preparedStatement.setString(1, article.getRsDescription());
            preparedStatement.setString(2, article.getRsBrand());
            preparedStatement.setDouble(3, article.getRsPrice());

            int rowsInserted = preparedStatement.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("Insertion avec success");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateOrCreate(Article article) {
        boolean result = true;
        if(article.getRsIdUser() == null) {
            System.out.println("Creating article ");
            result = create(article);
        }
        else {
            System.out.println("Update");
        }

        return result;
    }

    public static boolean deleteArticle(int idArticle){
        String strSql = "DELETE FROM t_articles WHERE idArticle = ?";
        try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrl(), ConnectionDB.getLogin(), null)) {
            PreparedStatement preparedStatement = connection.prepareStatement(strSql);
            preparedStatement.setInt(1, idArticle);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Suppression avec success");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Article> getAll() {
        ArrayList<Article> articles = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(ConnectionDB.getUrl(), ConnectionDB.getLogin(), null)) {
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
        return articles;
    }
}
