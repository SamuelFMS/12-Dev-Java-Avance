package utils;

import java.util.Scanner;

public interface InputUtils {
    static boolean inputBoolean(Scanner scanner){
        boolean result = false;
        boolean isInputValid = false;
        while (!isInputValid) {
            String input = scanner.next();
            if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")){
                isInputValid = true;
                result = true;
            }
            else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                isInputValid = true;
            }
            else{
                System.out.println("Saisie incorrecte (y/n) attendus");
            }
        }
        return result;
    }

    static Integer inputInteger(Scanner scan, int min, int max) {
        Integer number = null;
        boolean isInputValid = false;
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
        } while (!isInputValid);
        return number;
    }

    static String inputNextWithRegex(Scanner scanner, String regex, String errorMessage){
        boolean isInputValid = false;
        String res = "";
        while(!isInputValid){
            res = scanner.next();
            if(res.matches(regex)){
                isInputValid = true;
            } else {
                System.out.println(errorMessage);
            }
        }
        return res;
    }

    static String inputNextLine(Scanner scanner) {
        String res = scanner.nextLine();
        while (res.isEmpty()) {
            res = scanner.nextLine();
            if(res.isEmpty()) {
                System.out.println("La chaine ne peux pas etre vide");
            }
        }
        return res;
    }
}
