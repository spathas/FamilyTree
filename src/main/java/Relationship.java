public class Relationship {

    String firstName;
    String lastName;
    String relationship;

    public Relationship(String firstName, String relationship, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationship = relationship;
    }

    @Override
    public String toString() {
        return "Person{" + "First name=" + firstName + ", relationship=" + relationship +  ", Last name=" + lastName + "\n";
    }
}
