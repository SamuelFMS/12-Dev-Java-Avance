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
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Article> articles = ArticleDB.getAll();


        // Affichages de tous les articles
        articles.forEach(article -> System.out.println(article));
        // Insertion dun article
        Article newArticle = new Article("Description", "Marque", 10);
        ArticleDB.deleteArticle(13);
    }
}