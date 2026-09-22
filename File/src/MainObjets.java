import package_objet.InputUtil;
import package_objet.Menu;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class MainObjets {

    public static void main(String[] args) {
        readMenu();
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
        saveMenuToFile(menus);
    }

    public static void readMenu(){
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("objets.txt"))));
            while(true) {
                System.out.println((Menu) ois.readObject());
            }
        }
        catch (EOFException e){
            System.out.println("Fin de la lecture");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void saveMenuToFile(Menu[] menus) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("objets.txt"))));
            for(Menu menu : menus) {
                oos.writeObject(menu);
            }
            oos.close();
            System.out.println("Saved in objets.txt");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
