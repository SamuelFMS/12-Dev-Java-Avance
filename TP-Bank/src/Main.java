import dao.ClientDao;
import exceptions.EmptyArrayException;
import models.ClientModel;
import utils.DisplayTable;
import utils.SearchTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<ClientModel> mesClients = new ArrayList<>();
        ClientDao clientDao = new ClientDao();
        mesClients = clientDao.getAll();
        SearchTable clientBD = new SearchTable(mesClients);
        try {
            clientBD.show(scanner);
        } catch (EmptyArrayException e) {
            e.printStackTrace();
        }
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