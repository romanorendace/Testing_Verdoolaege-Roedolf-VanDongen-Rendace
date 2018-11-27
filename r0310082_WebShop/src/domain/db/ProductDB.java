package domain.db;

import domain.model.Product;

import java.util.List;

public interface ProductDB {
    Product get(String id);

    List<Product> getAll();

    void add(Product product);

    void update(Product product);

    void delete(String id);

    int getNumberOfProducts();

    String getNextProductId();
}
