package domain.db;

import domain.model.Order;
import domain.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDbInMemory implements PersonDB {
	private Map<String, Person> persons = new HashMap<>();

	// CONSTRUCTOR
	public PersonDbInMemory () {
		Person administrator = new Person("admin", "admin@ucll.be", "t", "Ad", "Ministrator");
		add(administrator);
	}
	
	@Override
    public Person get(String personId) throws DbException{
		if(personId == null){
			throw new DbException("No id given");
		}
		return persons.get(personId);
	}

	@Override
	public Person getPersonByEmail(String email) throws DbException {
		return null;
	}

	@Override
    public List<Person> getAll() throws DbException {
		return new ArrayList<Person>(persons.values());	
	}

	@Override
    public void add(Person person) throws DbException{
		if(person == null){
			throw new DbException("No person given");
		}
		if (hasUser(person)) {
			throw new DbException("User already exists");
		}
		persons.put(person.getUserid(), person);
	}

	private boolean hasUser(Person newPerson) {
		for (Person person : persons.values()) {
			if (person.equals(newPerson)) {
				return true;
			}
		}
		return false;
	}

	@Override
    public void update(Person person) throws DbException{
		if(person == null){
			throw new DbException("No person given");
		}
		if(!persons.containsKey(person.getUserid())){
			throw new DbException("No person found");
		}
		persons.put(person.getUserid(), person);
	}
	
	@Override
    public void delete(String personId) throws DbException{
		if(personId == null){
			throw new DbException("No id given");
		}
		persons.remove(personId);
	}


	@Override
    public int getNumberOfPersons() {
		return persons.size();
	}

	@Override
	public void addPersonOrders(Person person, List<Order> orders) {

	}

	@Override
	public List<Order> getPersonOrders(Person person) {
		return null;
	}
}
