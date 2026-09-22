import package_objet.InputUtil;
import package_objet.Menu;

import java.util.Scanner;

public class MainObjets {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bonjour, combien de menus souhaitez vous ?");
        int numberOfOrder = InputUtil.inputInteger(scanner, 0, 10);
        Menu[] menus = new Menu[numberOfOrder];
        for(int numeroMenu = 0; numeroMenu < numberOfOrder; numeroMenu++) {
            System.out.println("=================================");
            System.out.printf("Commande numéro %d%n", numeroMenu+1);
            System.out.println("=================================");
            menus[numeroMenu] = new Menu();
            menus[numeroMenu].inputSelection(scanner);
            System.out.printf("Résumé de la commande %d%n", numeroMenu+1);
            System.out.println(menus[numeroMenu]);
            System.out.println("=================================");
        }
    }
}
