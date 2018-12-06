package domain.db;

import domain.model.Person;

import java.util.List;

public interface PersonDB {
    Person get(String personId) throws DbException;

    Person getPersonByEmail(String email) throws DbException;

    List<Person> getAll() throws DbException;

    void add(Person person) throws DbException;

    void update(Person person) throws DbException;

    void delete(String personId) throws DbException;

    int getNumberOfPersons();

}
