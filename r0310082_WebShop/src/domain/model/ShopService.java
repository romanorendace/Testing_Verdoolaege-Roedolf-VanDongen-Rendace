package domain.model;

import domain.db.*;
import service.PersonService;
import service.ProductService;

import java.util.List;
import java.util.Properties;

public class ShopService {

    private PersonService personService; // = new PersonService();
    private ProductService productService; // = new ProductService();

    public ShopService(Properties properties) {
        this.personService = new PersonService(properties);
        this.productService = new ProductService(properties);
    }

    // PERSON METHODS
    public Person getPerson(String personId) throws DbException {
        return personService.get(personId);
    }

    public Person getPersonByEmail(String email) throws DbException {
        return personService.getPersonByEmail(email);
    }

    public List<Person> getPersons() {
        return personService.getAll();
    }

    public void addPerson(Person person) throws DbException {
        personService.add(person);
    }

    public void updatePerson(Person person) throws DbException {
        personService.update(person);
    }

    public void deletePerson(String id) throws DbException {
        personService.delete(id);
    }

    public void addPersonOrders(Person person, List<Order> orders) throws DbException {
        personService.addPersonOrders(person, orders);
    }

    public List<Order> getPersonOrders(Person person) throws DbException {
        return personService.getPersonOrders(person);
    }



    // PRODUCT METHODS
    public List<Product> getProducts() {
        return productService.getAll();
    }

    public void addProduct(Product product) throws DbException {
        productService.add(product);
    }

    public Product getProduct(String productId) throws DbException {
        return productService.get(productId);
    }

    public void updateProduct(Product product) throws DbException {
        productService.update(product);
    }

    public void deleteProduct(String productId) throws DbException { productService.delete(productId);}

    public String getNextProductId() {
        return productService.getNextProductId();
    }

}
