package package_objet;

import java.math.BigDecimal;

public class Drink extends Categorie {
    protected static Product[] proposition = {
            new Product("Eau plate", BigDecimal.valueOf(2.00)),
            new Product("Eau gazeuze", BigDecimal.valueOf(2.50)),
            new Product("Soda", BigDecimal.valueOf(3.50)),
            new Product("Vin", BigDecimal.valueOf(4.50)),
            new Product("Aucune", BigDecimal.ZERO)
    };

    @Override
    public String getName() {
        return "boissons";
    }

    @Override
    public Product[] getProposition() {
        return proposition;
    }
}
