import java.util.ArrayList;

public class Family {

    Person[] parents = new Person[2];
    ArrayList<Person> children = new ArrayList<>();

    //Check the relationships



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
        return "Family{" + "Parents='" + parents[0].getName() + parents[1].getName() +
                "\n, children" + printChildrenNames() +"}\n";
    }

}
