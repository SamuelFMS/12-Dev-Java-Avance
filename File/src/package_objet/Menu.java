package package_objet;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Scanner;

public class Menu implements Serializable {
    public Entry entry;
    public MainMenu mainMenu;
    public Accompaniement accompaniement;
    public Drink drink;
    public Dessert dessert;

    public Menu() {
        entry = new Entry();
        mainMenu = new MainMenu();
        accompaniement = new Accompaniement();
        drink = new Drink();
        dessert = new Dessert();
    }

    public void inputSelection(Scanner scanner) {
        this.entry.inputSelection(scanner);
        this.mainMenu.inputSelection(scanner);
        this.accompaniement.inputSelection(scanner);
        this.drink.inputSelection(scanner);
        this.dessert.inputSelection(scanner);
    }

    public BigDecimal getPrice() {
        return this.entry.getPrice().add(this.mainMenu.getPrice()).add(this.accompaniement.getPrice()).add(this.drink.getPrice()).add(this.dessert.getPrice());
    }

    @Override
    public String toString() {
        return "[" + entry + ", " + mainMenu + ", " + accompaniement + ", " + drink + ", " + dessert + "]";
    }
}
