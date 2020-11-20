import java.util.ArrayList;

public class Relationship {

    String firstName;
    String lastName;
    String relationship;

    public Relationship(String firstName, String relationship, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationship = relationship;
    }

    public static boolean checkRelationship(String relationship) {
        if(relationship == "father" || relationship == "mother" || relationship == "husband" || relationship == "wife")
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Person{" + "First name=" + firstName + ", relationship=" + relationship +  ", Last name=" + lastName + "\n";
    }
}
