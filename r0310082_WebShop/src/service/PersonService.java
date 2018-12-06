package service;

import domain.db.PersonDB;
import domain.db.PersonDBSQL;
import domain.model.Person;

import java.util.List;
import java.util.Properties;

public class PersonService {


    private PersonDB db; // = new PersonDBSQL();

    public PersonService(Properties properties) {
        this.db = new PersonDBSQL(properties);
    }

    public Person get(String userId) {
        return db.get(userId);
    }

    public Person getPersonByEmail(String email) { return db.getPersonByEmail(email); }

    public List<Person> getAll() {
        return db.getAll();
    }

    public void add(Person person) {
        db.add(person);
    }

    public void update(Person person) {
        db.update(person);
    }

    public void delete(String userId) {
        db.delete(userId);
    }

    public int getNumberOfPersoens() {
        return db.getNumberOfPersons();
    }

}
