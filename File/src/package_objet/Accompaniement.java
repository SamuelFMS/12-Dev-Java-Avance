package package_objet;

import java.math.BigDecimal;

public class Accompaniement extends Categorie {
    protected static Product[] proposition = {    new Product("Riz", BigDecimal.valueOf(2.50)),
            new Product("Pates", BigDecimal.valueOf(2.50)),
            new Product("Frites", BigDecimal.valueOf(3.50)),
            new Product("Légumes", BigDecimal.valueOf(3.80)),
            new Product("Aucun", BigDecimal.ZERO)};

    @Override
    public String getName() {
        return "accompagnements";
    }

    @Override
    public Product[] getProposition() {
        return proposition;
    }
}
