import java.util.ArrayList;

public class Family {

    //Variables
    String FirstName;
    String LastName;
    String relationship;

    Person[] grandparents = new Person[2];
    Person[] parents = new Person[2];
    ArrayList<Person> children = new ArrayList<>();

    public Family(String firstName, String lastName, String relationship) {
        FirstName = firstName;
        LastName = lastName;
        this.relationship = relationship;
    }

    //Set input variables to right place in obj

    //Check the relationship

    //Input people to right array-list.

    //If there is no relationship generate a new Family obj


    // To String Object printing
    private String printChildrenNames() {
        String per = "";
        for(Person person : children) {
            per.concat(person.getName()).concat(", ");
        }
        return per;
    }

    @Override
    public String toString() {
        return "Family{" + "Grandparents=" + grandparents[0].getName() + grandparents[1].getName() +
                "\n, Parents='" + parents[0].getName() + parents[1].getName() +
                "\n, children" + printChildrenNames() +"}\n";
    }

}
