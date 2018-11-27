package domain.db;

import domain.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDBSQL implements ProductDB {

    private Properties properties = new Properties();
    private String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX32?currentSchema=r0310082";

    public ProductDBSQL(Properties properties) {
        //properties.setProperty("user", "local_r0310082");
        //properties.setProperty("password", "7=\"lOào²G:0R8J");
        //Secret.setPass(properties);	// implements line 17 and 18
        //properties.setProperty("ssl", "true");
        //properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        //properties.setProperty("sslmode","prefer");
        try {
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
        }
        catch (ClassNotFoundException exc) {
            throw new DbException(exc.getMessage(), exc);
        }
    }

    @Override
    public Product get(String productId) throws DbException {
        if (productId == null) {
            throw new DbException("ProductId cannot be Null.");
        }
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM product "
                    + "WHERE productid = " + productId + ";";
            ResultSet result = statement.executeQuery(sql);

            Product product = new Product();
            while(result.next()) {
                product.setProductId(Integer.toString(result.getInt("productid")));
                product.setName(result.getString("name"));
                product.setDescription(result.getString("description"));
                product.setPrice(result.getDouble("price"));
            }

            return product;
        }
        catch (SQLException exc) {
            throw new DbException(exc.getMessage(), exc);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM product";
            ResultSet results = statement.executeQuery(sql);

            while (results.next()) {
                Product product = new Product();
                product.setProductId(Integer.toString(results.getInt("productid")));
                product.setName(results.getString("name"));
                product.setDescription(results.getString("description"));
                product.setPrice(results.getDouble("price"));

                products.add(product);
            }
            return products;
        }
        catch (SQLException exc) {
            throw new DbException(exc.getMessage(), exc);
        }
    }

    @Override
    public void add(Product product) throws DbException {
        if (product == null) {
            throw new DbException("Product cannot be Null.");
        }
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()) {

            String sql = "INSERT INTO product VALUES ("
                    + product.getProductId() +", '"
                    + product.getName() + "', '"
                    + product.getDescription() + "', "
                    + product.getPrice() +");";

            statement.execute(sql);
        }
        catch (SQLException exc) {
            throw new DbException(exc.getMessage(), exc);
        }
    }

    @Override
    public void update(Product product) throws DbException {
        if (product == null) {
            throw new DbException("Product cannot be Null.");
        }
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()) {

            String sql = "UPDATE product "
                    + "SET name = '" + product.getName() + "', "
                    + "description = '" + product.getDescription() + "', "
                    + "price = " + product.getPrice() + " "
                    + "WHERE productid = " + product.getProductId() + ";";

            statement.execute(sql);
        }
        catch (SQLException exc) {
            throw new DbException(exc.getMessage(), exc);
        }
    }

    @Override
    public void delete(String productId) throws DbException {
        if (productId == null) {
            throw new DbException("ProductId cannot be Null.");
        }
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()) {

            String sql = "DELETE FROM product "
                    + "WHERE productid = " + productId +";";

            statement.execute(sql);
        }
        catch (SQLException exc) {
            throw new DbException(exc.getMessage(), exc);
        }
    }

    @Override
    public int getNumberOfProducts() {
        return getAll().size();
    }

    @Override
    public String getNextProductId() {
        int maximum = Integer.MIN_VALUE;
        for (Product product : getAll()) {
            if (product.getProductId() > maximum) {
                maximum = product.getProductId();
            }
        }
        int nextProductId = maximum + 1;
        return String.valueOf(nextProductId);
    }


}
