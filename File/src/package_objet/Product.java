package package_objet;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
    protected String nameProduct;
    protected BigDecimal price;

    public Product(String nameProduct, BigDecimal price) {
        this.nameProduct = nameProduct;
        this.price = price;
    }

    @Override
    public String toString() {
        return nameProduct + (!price.equals(BigDecimal.ZERO) ? " (" + price + "€)" : "");
    }
}
