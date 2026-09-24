import business.ClientBusiness;
import business.TransactionBusiness;
import exceptions.EmptyArrayException;
import exceptions.IdenticalAccountsException;
import exceptions.SoldeInsuffisant;
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


    public static void createAccountBank() {
        System.out.println("Veuillez entrez un numéro de compte ex: (FR-0001-0004): ");
        String numberAccount = InputUtils.inputNextWithRegex(scanner, Validator.ACCOUNT_NUMBER, "Format incorrect. Exemple attendu : FR-0001-0100");
        System.out.println("Veuillez entrez le titulaire du compte bancaire");
        String holder = InputUtils.inputNextLine(scanner);
        System.out.println("Récapitulatif");
        System.out.println("Numéro de compte: " + numberAccount);
        System.out.println("Titulaire: " + holder);
        System.out.print("Confirmer création du compte (y/n): ");
        if (InputUtils.inputBoolean(scanner)) {
            String res = clientBusiness.createClient(numberAccount, holder);
            if (res == null) {
                System.out.println("Echec de la création d'un compte");
            } else {
                System.out.println("Création du compte bancaire " + res + " avec succès");
            }
        } else {
            System.out.println("Annulation de la création d'un compte");
        }

    }

    public static void viewAccountBank() {
        List<ClientModel> listClient = clientBusiness.getAllClients();
        SearchTable<ClientModel> searchTable = new SearchTable<>(listClient);
        try {
            String numeroCompte = searchTable.show(scanner, "Entrez un numéro de compte pour voir l'historique: ");
            if (numeroCompte != null) {
                List<TransactionModel> transactions = transactionBusiness.getAllRelated(numeroCompte);
                DisplayTable<TransactionModel> displayTable = new DisplayTable<>(transactions);
                try {
                    displayTable.show(scanner, null);
                }
                catch (EmptyArrayException e){
                    System.out.println("Aucune opération n'a été éffectués sur ce compte");
                }
            }
        } catch (EmptyArrayException e) {
            System.out.println("Aucun client trouvé");
        }
    }

    public static void depositMoney() {
        List<ClientModel> listClient = clientBusiness.getAllClients();
        SearchTable<ClientModel> searchTable = new SearchTable<>(listClient);
        try {
            String numeroCompte = searchTable.show(scanner, "Entrez un numéro de compte: ");
            if (numeroCompte != null) {
                System.out.println("Combien d'argent souhaitez vous ajoutez au compte? ");
                BigDecimal depositMoney = InputUtils.inputMoney(scanner);
                if (transactionBusiness.depositMoney(numeroCompte, depositMoney)) {
                    System.out.println("Le dépot a été confirmé");
                } else {
                    System.out.println("Une erreur s'est produite lors du dépot");
                }
            }
        } catch (EmptyArrayException e) {
            System.out.println("Aucun client trouvé");
        }
    }

    public static void withdrawMoney() {
        List<ClientModel> listClient = clientBusiness.getAllClients();
        SearchTable<ClientModel> searchTable = new SearchTable<>(listClient);
        try {
            String numeroCompte = searchTable.show(scanner, "Entrez un numéro de compte: ");
            if (numeroCompte != null) {
                System.out.println("Combien d'argent souhaitez vous retirez au compte? ");
                BigDecimal withdrawMoney = InputUtils.inputMoney(scanner);
                System.out.println("Etes vous sur de voulour retirer " + withdrawMoney + "€? (y/n)");
                if (InputUtils.inputBoolean(scanner)) {
                    try {
                        if(transactionBusiness.withdrawMoney(numeroCompte, withdrawMoney)) {
                            System.out.println("Succes l'argent a bien été retiré");
                        } else {
                            System.out.println("Une erreur s'est produite");
                        }
                    } catch (SoldeInsuffisant e) {
                        System.out.println("Le solde est insuffisant pour faire cet opération");
                    }
                }
            }
        } catch (EmptyArrayException e) {
            e.printStackTrace();
        }
    }

    public static void transferMoney() {
        List<ClientModel> listClient = clientBusiness.getAllClients();
        SearchTable<ClientModel> searchTable = new SearchTable<>(listClient);
        try {
            String numberAccountTransmitter = searchTable.show(scanner, "Entrez un numéro de compte qui donne: ");
            String numberAccountBeneficiary = searchTable.show(scanner, "Entrez le numéro de compte qui recoit l'argent: ");
            System.out.println("Combien d'argent souhaitez vous transferez? ");
            BigDecimal transferMoney = InputUtils.inputMoney(scanner);
            System.out.println("Etes vous sur de voulour transferer " + transferMoney + "€");
            System.out.println("De " + numberAccountTransmitter + " vers " + numberAccountBeneficiary + " ? (y/n)");
            if (InputUtils.inputBoolean(scanner)) {
                try {
                    if(transactionBusiness.transferMoney(numberAccountTransmitter, numberAccountBeneficiary, transferMoney)) {
                        System.out.println("Succes l'argent a bien été transféré");
                    } else {
                        System.out.println("Une erreur s'est produite");
                    }
                } catch (SoldeInsuffisant e) {
                    System.out.println("Le solde est insuffisant pour faire cet opération");
                } catch (IdenticalAccountsException e) {
                    System.out.println("Vous ne pouvez pas envoyer sur le meme compte");
                }
            }

        } catch (EmptyArrayException e) {
            e.printStackTrace();
        }
    }

    public static void mainMenu() {
        boolean applicationRunning = true;
        while (applicationRunning) {
            System.out.println("1- Créer un compte bancaire");
            System.out.println("2- Consulter compte bancaire");
            System.out.println("3- Deposer de l'argent");
            System.out.println("4- Retirer de l'argent");
            System.out.println("5- Effectuer un virement");
            System.out.println("0- Stop");
            System.out.println("Votre choix:");
            int choice = InputUtils.inputInteger(scanner, 0, 5);
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
                case 4:
                    withdrawMoney();
                    break;
                case 5:
                    transferMoney();
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