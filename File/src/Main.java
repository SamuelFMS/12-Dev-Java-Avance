import package_objet.InputUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static String[] entrys = {"Salade", "Soupe", "Quiche", "Aucune"};
    static String[] mainMenus = {"Poulet", "Boeuf", "Poisson", "Végétarien", "Végan", "Aucun"};
    static String[] accompaniements = {"Riz", "Pates", "Frites", "Légumes","Aucun"};
    static String[] drinks = {"Eau plate", "Eau gazeuze", "soda", "Vin", "Aucune"};
    static String[] desserts = {"Tarte maison", "Mousse au chocolat", "Tiramisu", "Aucun"};

    public static void displayItemMenu(String[] elements){
        for(int i = 0; i < elements.length; i++){
            System.out.print("["+(i+1)+" - " + elements[i] + "]");
        }
        System.out.println();
    }

    public static String displayMenuForFile(int[][] menu) {
        StringBuilder menuString = new StringBuilder();

        for (int numeroMenu = 0; numeroMenu < menu.length; numeroMenu++){
            menuString.append("*************** ");
            menuString.append("Résumé de la commande N°").append(numeroMenu+1);
            menuString.append(" ***************\n");
            menuString.append(entrys[menu[numeroMenu][0]]).append("\n");
            menuString.append(mainMenus[menu[numeroMenu][1]]).append("\n");
            menuString.append(accompaniements[menu[numeroMenu][2]]).append("\n");
            menuString.append(drinks[menu[numeroMenu][3]]).append("\n");
            menuString.append(desserts[menu[numeroMenu][4]]).append("\n");
            menuString.append("\n");
            menuString.append("\n");
        }
        return menuString.toString();
    }

    public static void saveMenuToFile(int[][] choices){
        FileWriter menuFileOut;
        try {
            menuFileOut = new FileWriter(new File("menu.txt"));
            menuFileOut.write(displayMenuForFile(choices));
            menuFileOut.close();
            System.out.println("Save to file");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        /**
         * Debut du programme
         */
        System.out.println("Bonjour, combien de menus souhaitez vous ?");
        Scanner scanner = new Scanner(System.in);
        int numberOfOrder = InputUtil.inputInteger(scanner, 0, 10);
        int[][] savedChoices = new int[numberOfOrder][5];
        for(int numberMenu = 0; numberMenu < numberOfOrder; numberMenu++){
            int[] currentChoice = new int[5];

            System.out.println("============================================");
            System.out.println("Commande numéro " + (numberMenu+1));
            System.out.println("============================================");

            // MenuObjet.Entry
            int entry = askItem(entrys,"Que souhaitez vous comme entrée ? [Saisir le chiffre correspondant]", scanner)-1;
            int mainMenu =  askItem(mainMenus,"Que souhaitez vous comme plats ? [Saisir le chiffre correspondant]", scanner)-1;
            int accompaniement = askItem(accompaniements,"Que souhaitez vous comme accompagnements ? [Saisir le chiffre correspondant]", scanner)-1;
            int drink = askItem(drinks,"Que souhaitez vous comme boissons ? [Saisir le chiffre correspondant]", scanner)-1;
            int dessert = askItem(desserts,"Que souhaitez vous comme desserts ? [Saisir le chiffre correspondant]", scanner)-1;
            System.out.println("Résumé de la commande " + (numberMenu+1));
            System.out.print("["+entrys[entry]+", ");
            System.out.print(mainMenus[mainMenu] + ", ");
            System.out.print(accompaniements[accompaniement] + ", ");
            System.out.print(drinks[drink] + ", ");
            System.out.println(desserts[dessert] + "]");
            System.out.println("============================================");

            currentChoice[0] = entry;
            currentChoice[1] = mainMenu;
            currentChoice[2] = accompaniement;
            currentChoice[3] = drink;
            currentChoice[4] = dessert;
            savedChoices[numberMenu] = currentChoice;
        }
        saveMenuToFile(savedChoices);
        scanner.close();

    }

    private static int askItem(String[] listItem,String question, Scanner scanner) {
        displayItemMenu(listItem);
        System.out.println(question);
        return InputUtil.inputInteger(scanner, 1, listItem.length);
    }
}