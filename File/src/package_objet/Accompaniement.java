package package_objet;

public class Accompaniement extends Categorie {
    protected static String[] proposition = {"Riz", "Pates", "Frites", "Légumes", "Aucun"};

    @Override
    public String getName() {
        return "accompagnements";
    }

    @Override
    public String[] getProposition() {
        return proposition;
    }
}
