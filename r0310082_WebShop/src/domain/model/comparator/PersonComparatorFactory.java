package domain.model.comparator;

import domain.model.Person;

import java.util.Comparator;

public class PersonComparatorFactory {

    public Comparator<Person> createPersonComparator(String sortKey) {
        Comparator<Person> comparator = null;
        if (sortKey.equals("email")) {
            comparator = new ComparatorByEmail();
        }
        else if (sortKey.equals("firstname")) {
            comparator = new ComparatorByFirstName();
        }
        else if (sortKey.equals("lastname")) {
            comparator = new ComparatorByLastName();
        }
        return comparator;
    }
}
