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

    public static boolean checkRelationship(Person firstPerson, String relationship) {
        //Check for non recommended String in relationship slot of csv file
        if( relationship.equals("father") || relationship.equals("mother") || relationship.equals("husband") || relationship.equals("wife") )
            //Check for logical fail
            if( (relationship.equals("father") || relationship.equals("husband")) && firstPerson.getGender().equals("man") )
                return true;
        return (relationship.equals("mother") || relationship.equals("wife")) && firstPerson.getGender().equals("woman");
    }

    @Override
    public String toString() {
        return "Person{" + "First name=" + firstName + ", relationship=" + relationship +  ", Last name=" + lastName + "\n";
    }
}
