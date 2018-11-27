package service;

import domain.db.DbException;
import domain.db.ProductDB;
import domain.db.ProductDBSQL;
import domain.model.Product;

import java.util.List;
import java.util.Properties;

public class ProductService {

    private ProductDB db; // = new ProductDBSQL();

    public ProductService(Properties properties) {
        this.db = new ProductDBSQL(properties);
    }


    public Product get(String productId) throws DbException {
        return db.get(productId);
    }

    public List<Product> getAll() {
        return db.getAll();
    }

    public void add(Product product) throws DbException {
        db.add(product);
    }

    public void update(Product product) throws DbException {
        db.update(product);
    }

    public void delete(String productId) throws DbException {
        db.delete(productId);
    }

    public int getNumberOfProducts() {
        return db.getNumberOfProducts();
    }

    public String getNextProductId() { return db.getNextProductId(); }

}
