package package_objet;

import java.math.BigDecimal;

public class Dessert extends Categorie {
    protected static Product[] proposition = {new Product("Tarte maison", BigDecimal.valueOf(4)),
            new Product("Mousse au chocolat",BigDecimal.valueOf(2)),
            new Product("Tiramisu", BigDecimal.valueOf(3)),
            new Product("Tiramisu", BigDecimal.ZERO)};

    @Override
    public String getName() {
        return "desserts";
    }

    @Override
    public Product[] getProposition() {
        return proposition;
    }

}
