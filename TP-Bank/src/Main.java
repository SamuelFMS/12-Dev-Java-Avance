import business.ClientBusiness;
import business.TransactionBusiness;
import com.sun.security.ntlm.Client;
import exceptions.EmptyArrayException;
import models.ClientModel;
import models.TransactionModel;
import utils.DisplayTable;
import utils.InputUtils;
import utils.SearchTable;
import validation.Validator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static ClientBusiness clientBusiness;
    static TransactionBusiness transactionBusiness;
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
        List<ClientModel> listClient = clientBusiness.getAllClients();
        SearchTable<ClientModel> searchTable = new SearchTable<>(listClient);
        try {
            String numeroCompte = searchTable.show(scanner);
            if(numeroCompte != null){
                List<TransactionModel> transactions = transactionBusiness.getAllRelated(numeroCompte);
                DisplayTable<TransactionModel> displayTable = new DisplayTable<>(transactions);
                displayTable.show(scanner);
            }
        } catch (EmptyArrayException e){
            System.out.println("Aucun client trouvé");
        }
    }

    public static void depositMoney(){
        List<ClientModel> listClient = clientBusiness.getAllClients();
        SearchTable<ClientModel> searchTable = new SearchTable<>(listClient);
        try {
            String numeroCompte = searchTable.show(scanner);
            if(numeroCompte != null){
                System.out.println("Combien d'argent souhaitez vous ajoutez au compte? ");
                BigDecimal depositMoney = InputUtils.inputMoney(scanner);
                if(transactionBusiness.depositMoney(numeroCompte, depositMoney)){
                    System.out.println("Le dépot a été confirmé");
                } else {
                    System.out.println("Une erreur s'est produite lors du dépot");
                }
            }
        } catch (EmptyArrayException e){
            System.out.println("Aucun client trouvé");
        }
    }

    public static void mainMenu(){
        boolean applicationRunning = true;
        while (applicationRunning) {
            System.out.println("1- Créer un compte bancaire");
            System.out.println("2- Consulter compte bancaire");
            System.out.println("3- Deposer de l'argent");
            System.out.println("4- Retirer de l'argent");
            System.out.println("5- Effectuer un virement");
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
                case 3:
                    depositMoney();
                    break;
                default:
                    System.out.println("OK");
                    applicationRunning = false;

            }
        }
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        transactionBusiness = new TransactionBusiness();
        clientBusiness = new ClientBusiness();
        mainMenu();
        scanner.close();
    }
}