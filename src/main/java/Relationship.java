import java.util.ArrayList;

public class Relationship {

    Person firstPerson;
    Person lastPerson;
    String relationship;

    public Relationship(Person firstPerson, String relationship, Person lastPerson) {
        this.firstPerson = firstPerson;
        this.lastPerson = lastPerson;
        this.relationship = relationship;
    }

    //Getters
    public Person getFirstPerson() {
        return firstPerson;
    }
    public Person getLastPerson() {
        return lastPerson;
    }
    public String getRelationship() {
        return relationship;
    }

    @Override
    public String toString() {
        return "First name=" + firstPerson.getName() + ", relationship=" + relationship +  ", Last name=" + lastPerson.getName() + "\n";
    }
}
