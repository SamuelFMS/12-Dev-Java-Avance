package package_objet;

import java.math.BigDecimal;

public class MainMenu extends Categorie {
    protected static Product[] proposition = {
            new Product("Poulet", BigDecimal.valueOf(12.50)),
            new Product("Boeuf", BigDecimal.valueOf(15.00)),
            new Product("Poisson", BigDecimal.valueOf(14.00)),
            new Product("Végétarien", BigDecimal.valueOf(11.50)),
            new Product("Végan", BigDecimal.valueOf(11.50)),
            new Product("Aucun", BigDecimal.ZERO)
    };

    @Override
    public String getName() {
        return "plats";
    }

    @Override
    public Product[] getProposition() {
        return proposition;
    }
}
