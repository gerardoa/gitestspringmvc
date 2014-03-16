package springapp.service;

import springapp.domain.Product;

import java.util.List;

/**
 * Created by Gerardo on 16/03/14.
 */
public class SimpleProductManager implements ProductManager {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void increasePrice(int percentage) {
        if (products != null) {
            for (Product product : products) {
                double newPrice = product.getPrice().doubleValue() *
                        (100 + percentage) / 100;
                product.setPrice(newPrice);
            }
        }
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
