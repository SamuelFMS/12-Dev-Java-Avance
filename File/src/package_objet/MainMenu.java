package package_objet;

public class MainMenu extends Categorie {
    protected static String[] proposition = {"Poulet", "Boeuf", "Poisson", "Végétarien", "Végan", "Aucun"};

    @Override
    public String getName() {
        return "plats";
    }

    @Override
    public String[] getProposition() {
        return proposition;
    }
}
