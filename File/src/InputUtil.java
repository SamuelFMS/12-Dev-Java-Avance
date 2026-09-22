import java.util.Scanner;

public class InputUtil {
    public static Integer inputInteger(Scanner scan, int min, int max) {
        Integer number = null;
        Boolean isInputValid = false;
        do {
            String numberString = scan.next();
            try {
                number = Integer.parseInt(numberString.trim());
                if (number >= min && number <= max) {
                    isInputValid = true;
                } else System.out.println("Veuillez saisir un nombre entre " + min + " et " + max);
            } catch (NumberFormatException e) {
                // Gérer l'erreur si le string n'est pas un nombre
                System.out.println("Ce n'est pas un nombre valide !");
            }
        } while(!isInputValid);
        return number;
    }
}
