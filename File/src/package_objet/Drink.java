package package_objet;

public class Drink extends Categorie {
    protected static String[] proposition = {"Eau plate", "Eau gazeuze", "Soda", "Vin", "Aucune"};

    @Override
    public String getName() {
        return "boissons";
    }

    @Override
    public String[] getProposition() {
        return proposition;
    }
}
