import java.time.Instant;
import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Date date = null;
        Date today = Date.from(Instant.now()); // new Date() est correcte mais sonarqube me met une erreur pas content
        try() {
            System.out.println(date.getClass().getName()); // Un Objet null n'est pas instancié et donc ne possede pas les classes meres
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        System.out.println(today.getClass().getName());
    }
}