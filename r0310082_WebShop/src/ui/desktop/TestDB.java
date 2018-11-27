package ui.desktop;

import domain.model.Person;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
//import domain.db.Secret;

public class TestDB {
    public static void main(String[] args) {
        Properties properties = new Properties();
        String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX32?currentSchema=r0310082";
        properties.setProperty("user", "local_r0310082");
        properties.setProperty("password", "7=\"lOào²G:0R8J");
        //Secret.setPass(properties);	// implements line 17 and 18
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode","prefer");

        // UI
        String menu = "\n1. Show person overview\n2. Add person\n0. Quit";
        int choice = -1;

        while (choice != 0) {
            choice = Integer.parseInt(JOptionPane.showInputDialog(menu));

            if (choice == 1) {
                try {
                    Connection connection = DriverManager.getConnection(url,properties);
                    Statement statement = connection.createStatement();
                    ResultSet result = statement.executeQuery( "SELECT * FROM person" );

                    while(result.next()){
                        String firstName = result.getString("firstname");
                        String lastName = result.getString("lastname");
                        String userid = result.getString("userid");
                        String email = result.getString("email");

                        System.out.println(firstName + " " + lastName + ": " + userid + ", " + email);
                    }
                    statement.close();
                    connection.close();
                }
                catch (SQLException exc) {
                    exc.printStackTrace();
                }
            }
            else if (choice == 2) {
                Person person =  new Person();
                try {
                    person.setUserid(JOptionPane.showInputDialog("UserId ?"));
                    person.setFirstName(JOptionPane.showInputDialog("First name ?"));
                    person.setLastName(JOptionPane.showInputDialog("Last name ?"));
                    person.setEmail(JOptionPane.showInputDialog("Email ?"));
                    String password = JOptionPane.showInputDialog("Password ?");
                    person.setPassword(password);

                    Connection connection = DriverManager.getConnection(url, properties);
                    Statement statement = connection.createStatement();
                    String insertQuery = "INSERT INTO r0310082.person VALUES ('"
                            + person.getUserid() + "', '"
                            + person.getFirstName() + "', '"
                            + person.getLastName() + "', '"
                            + person.getEmail() + "', '"
                            + password +"');";
                    statement.execute(insertQuery);

                }
                catch (Exception exc) {
                    exc.printStackTrace();
                }
            }

        }
    }

}
