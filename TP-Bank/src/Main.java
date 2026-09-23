import business.ClientBusiness;
import dao.ClientDao;
import exceptions.EmptyArrayException;
import jdk.internal.util.xml.impl.Input;
import models.ClientModel;
import utils.DisplayTable;
import utils.InputUtils;
import utils.SearchTable;
import validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static ClientBusiness clientBusiness;
    static Scanner scanner;


    public static void createAccountBank(){
        System.out.println("Veuillez entrez un numéro de compte ex: (FR-0001-0004): ");
        String numberAccount = InputUtils.inputNextWithRegex(scanner, Validator.ACCOUNT_NUMBER, "Format incorrect. Exemple attendu : FR-0001-0100");
        System.out.println("Veuillez entrez le titulaire du compte bancaire");
        String holder = InputUtils.inputNextLine(scanner);
        System.out.println("Récapitulatif");
        System.out.println("Numéro de compte: " + numberAccount);
        System.out.println("Titulaire: " + holder);
        System.out.print("Confirmer création du compte (y/n): ");
        if(InputUtils.inputBoolean(scanner)) {
            String res = clientBusiness.createClient(numberAccount, holder);
            if(res == null) {
                System.out.println("Echec de la création d'un compte");
            }
            else {
                System.out.println("Création du compte bancaire " + res + " avec succès");
            }
        } else{
            System.out.println("Annulation de la création d'un compte");
        }

    }

    public static void viewAccountBank(){

    }

    public static void mainMenu(){
        boolean applicationRunning = true;
        while (applicationRunning) {
            System.out.println("1- Créer un compte bancaire");
            System.out.println("2- Consulter compte bancaire");
            System.out.println("3- Deposer de l'argent");
            System.out.println("4- Effectuer un virement");
            System.out.println("0- Stop");
            System.out.println("Votre choix:");
            int choice = InputUtils.inputInteger(scanner, 0, 4);
            switch (choice) {
                case 1:
                    createAccountBank();
                    break;
                case 2:
                    viewAccountBank();
                    break;
                default:
                    System.out.println("OK");
                    applicationRunning = false;

            }
        }
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        clientBusiness = new ClientBusiness();
        mainMenu();
        scanner.close();
       /* mesClients.add(new ClientModel("FR-XXXX-XXXX", "Samuel Curran", 1000));
        mesClients.add(new ClientModel("IE-XXXX-1445", "PAPA", 10000000));
        for(int i = 0; i< 30;i++){
            mesClients.add(new ClientModel("IE-XXXX-1445", "Test", 10000000));
        }
        mesClients.set(12, new ClientModel("IE-XXXX-1445TESTETESTSTES", "Test", 10000000));
        DisplayTable displayTable = new DisplayTable(mesClients);
        try {
            displayTable.show(scanner);
        } catch (EmptyArrayException e) {
            throw new RuntimeException(e);
        }
        SearchTable searchTable = new SearchTable(mesClients);
        try {
            searchTable.show(scanner);
        } catch (EmptyArrayException e) {
            throw new RuntimeException(e);
        }*/
    }
}