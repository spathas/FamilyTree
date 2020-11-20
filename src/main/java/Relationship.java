public class Relationship {

    String firstName;
    String lastName;
    String relationship;

    public Relationship(String firstName, String relationship, String lastName) {
        firstName = firstName;
        lastName = lastName;
        relationship = relationship;
    }

    @Override
    public String toString() {
        return "Person{" + "First name=" + firstName + ", relationship=" + relationship +  ", Last name=" + lastName + "\n";
    }
}
