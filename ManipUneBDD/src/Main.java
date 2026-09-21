import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Article> articles = ArticleDB.getAll();


        // Affichages de tous les articles
        ArticleDB.getAll().forEach(article -> System.out.println(article));
        scanner.nextLine();

        // Insertion dun article
        System.out.println("Insertion d'un nouveau Article");
        Article newArticle = new Article("Description", "Marque", 10);
        ArticleDB.updateOrCreate(newArticle);
        ArticleDB.getAll().forEach(article -> System.out.println(article));
        scanner.nextLine();

        // Suppression
        System.out.println("Suppression de cet article");
        ArticleDB.deleteArticle(newArticle);
        ArticleDB.getAll().forEach(article -> System.out.println(article));
        scanner.nextLine();

        // Edition de l'article 0
        if(articles.get(0).getRsBrand().equals("Logic")) {
            articles.get(0).setRsBrand("Logitoch");
        }else {
            articles.get(0).setRsBrand("Logic");
        }
        ArticleDB.updateOrCreate(articles.get(0));
        ArticleDB.getAll().forEach(article -> System.out.println(article));
    }
}