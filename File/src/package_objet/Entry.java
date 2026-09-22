package package_objet;

import java.io.Serializable;
import java.math.BigDecimal;

public class Entry extends Categorie implements Serializable {
    protected static Product[] proposition = {
            new Product("Salade", BigDecimal.valueOf(4.50)),
            new Product("Soupe", BigDecimal.valueOf(5.00)),
            new Product("Quiche", BigDecimal.valueOf(5.50)),
            new Product("Aucune", BigDecimal.ZERO)
    };

    @Override
    public String getName() {
        return "entrée";
    }

    @Override
    public Product[] getProposition() {
        return proposition;
    }
}
