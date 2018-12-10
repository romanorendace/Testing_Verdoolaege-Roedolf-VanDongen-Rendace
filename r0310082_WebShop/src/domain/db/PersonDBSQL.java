package domain.db;

import domain.model.Order;
import domain.model.Person;
import domain.model.Role;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDBSQL implements PersonDB {

    private Properties properties; // = new Properties();
    private String url; // = "jdbc:postgresql://databanken.ucll.be:51819/2TX32?currentSchema=r0310082";

    public PersonDBSQL(Properties properties) {
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
    public Person get(String personId) throws DbException {
        if(personId == null){
            throw new DbException("No id given");
        }

        try (Connection connection = DriverManager.getConnection(url, properties);
            Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM person WHERE userid = '" + personId + "'";
            ResultSet result =  statement.executeQuery(sql);

            Person person = new Person();
            while (result.next()) {
                person.setUserid(result.getString("userid"));
                person.setFirstName(result.getString("firstname"));
                person.setLastName(result.getString("lastname"));
                person.setEmail(result.getString("email"));
                person.setPassword(result.getString("password"));
                person.setRole(Role.valueOf(result.getString("role")));
            }
            return person;
        }
        catch (SQLException exc) {
            throw new DbException(exc.getMessage(), exc);
        }
    }

    @Override
    public Person getPersonByEmail(String email) throws DbException {
        if(email == null){
            throw new DbException("No id given");
        }

        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM person WHERE email = '" + email.toLowerCase() + "'";
            ResultSet result =  statement.executeQuery(sql);

            Person person = new Person();
            while (result.next()) {
                person.setUserid(result.getString("userid"));
                person.setLastName(result.getString("lastname"));
                person.setFirstName(result.getString("firstname"));
                person.setEmail(result.getString("email"));
                person.setPassword(result.getString("password"));
                person.setRole(Role.valueOf(result.getString("role")));
            }
            return person;
        }
        catch (SQLException exc) {
            throw new DbException(exc.getMessage(), exc);
        }
    }

    @Override
    public List<Person> getAll() throws DbException {
        List<Person> persons = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, properties);
            Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM person";
            ResultSet results =  statement.executeQuery(sql);

            while (results.next()) {
                Person person = new Person();
                person.setUserid(results.getString("userid"));
                person.setFirstName(results.getString("firstname"));
                person.setLastName(results.getString("lastname"));
                person.setEmail(results.getString("email"));
                person.setPassword(results.getString("password"));
                person.setRole(Role.valueOf(results.getString("role")));

                persons.add(person);
            }
        }
        catch (SQLException exc) {
            throw new DbException(exc.getMessage(), exc);
        }
        return persons;
    }

    @Override
    public void add(Person person) throws DbException {
        if (person == null) {
            throw new DbException("Person cannot be Null.");
        }
        String sql = "INSERT INTO person (userid, firstname, lastname, email, password, role)"
                + "VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)){
            //Statement statement = connection.createStatement()) {

            //String sql = "INSERT INTO person VALUES ('"
            //        + person.getUserid() + "', '"
            //        + person.getFirstName() + "', '"
            //        + person.getLastName() + "', '"
            //        + person.getEmail() + "', '"
            //        + person.getPassword() +"');";

            statement.setString(1, person.getUserid());
            statement.setString(2, person.getFirstName());
            statement.setString(3, person.getLastName());
            statement.setString(4, person.getEmail());
            statement.setString(5, person.getPassword());
            statement.setString(6, person.getRole().toString());

            statement.execute();
        }
        catch (SQLException exc) {
            throw  new DbException(exc.getMessage(), exc);
        }
    }

    // NEVER CALLED
    @Override
    public void update(Person person) throws DbException {
        if (person == null) {
            throw new DbException("Person cannot be Null.");
        }
        String sql = "UPDATE person "
                + "SET userid = ?, firstname = ?, lastname = ?, email = ?, password = ? "
                + "WHERE userid = ?";

        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement =  connection.prepareStatement(sql)) {
            //Statement statement = connection.createStatement()) {

            //String sql = "UPDATE person "
            //        + "SET userid = '" + person.getUserid() +"', "
            //        + "firstname = '" + person.getFirstName() + "', "
            //        + "lastname = '" + person.getLastName() + "', "
            //        + "email = '" + person.getEmail() + "', "
            //        + "password = '" + person.getPassword() +"' "
            //        + "WHERE userid = '" + person.getUserid() + "';";

            statement.setString(1, person.getUserid());
            statement.setString(2, person.getFirstName());
            statement.setString(3, person.getLastName());
            statement.setString(4, person.getEmail());
            statement.setString(5, person.getPassword());
            statement.setString(6, person.getUserid());

            statement.execute();
        }
        catch (SQLException exc) {
            throw new DbException(exc.getMessage(), exc);
        }
    }

    @Override
    public void delete(String personId) throws DbException {
        if (personId == null) {
            throw new DbException("PersonId cannot be Null.");
        }
        try (Connection connection = DriverManager.getConnection(url, properties);
            Statement statement = connection.createStatement()) {

            String sql = "DELETE FROM person "
                    + "WHERE userid = '" + personId + "';";

            statement.execute(sql);
        }
        catch (SQLException exc) {
            throw new DbException(exc.getMessage(), exc);
        }
    }

    @Override
    public int getNumberOfPersons() {
        return getAll().size();
    }

    @Override
    public void addPersonOrders(Person person, List<Order> orders) {

        String sql = "INSERT INTO orders (email, productid, amount, datum)"
                + " VALUES(?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            for (Order order : orders) {

                statement.setString(1, person.getEmail());
                statement.setString(2, String.valueOf(order.getProductId()));
                statement.setInt(3, order.getAmount());
                statement.setString(4, getCurrentDate());

                statement.execute();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    private String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.now();
        String date = dateTime.format(formatter);
        return date;
    }

    @Override
    public List<Order> getPersonOrders(Person person) {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM orders"
                    + " WHERE email = '" + person.getEmail().trim() + "'";

            ResultSet results =  statement.executeQuery(sql);

            while (results.next()) {
                Order order = new Order();

                order.setProductId(results.getString("productid"));
                order.setAmount(results.getInt("amount"));
                order.setDate(results.getString("datum"));

                orders.add(order);
            }
        }
        catch (SQLException exc) {
            throw new DbException(exc.getMessage(), exc);
        }
        return orders;
    }
}
