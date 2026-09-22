package package_objet;

import java.io.Serializable;

public class Entry extends Categorie implements Serializable {
    protected static String[] proposition = {"Salade", "Soupe", "Quiche", "Aucune"};

    @Override
    public String getName() {
        return "entrée";
    }

    @Override
    public String[] getProposition() {
        return proposition;
    }
}
