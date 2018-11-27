package domain.model.comparator;

import domain.model.Person;

import java.util.Comparator;

public class ComparatorByEmail implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2)
    {
        if (p1 == null || p2 == null) { return 1; }
        return p1.getEmail().compareTo(p2.getEmail());
    }
}
