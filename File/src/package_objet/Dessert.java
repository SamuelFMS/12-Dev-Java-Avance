package package_objet;

public class Dessert extends Categorie {
    protected static String[] proposition = {"Tarte maison", "Mousse au chocolat", "Tiramisu", "Aucun"};

    @Override
    public String getName() {
        return "desserts";
    }

    @Override
    public String[] getProposition() {
        return proposition;
    }

}
