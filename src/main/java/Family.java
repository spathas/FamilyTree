import java.util.ArrayList;

public class Family {

    Person[] parents = new Person[2];
    ArrayList<Person> children = new ArrayList<>();

    public Family(Person father, Person mother) {
        this.parents[0] = father;
        this.parents[1] = mother;
    }

    //Functions
    public static void isPartners(Person firstPerson, Person lastPerson, String rel, ArrayList<Family> families) {
        //Create new family object and insert into family array.
        if(rel.equals("husband") || rel.equals("wife")) {
            if(firstPerson.getGender().equals("man") && lastPerson.getGender().equals("woman")) {
                //First man, Second woman
                families.add(new Family(firstPerson, lastPerson));
            }else{
                //First woman, Second man
                families.add(new Family(lastPerson, firstPerson));
            }
        }
    }

    public static void isParent(Person firstPerson, Person lastPerson, String rel, ArrayList<Family> families) {
        if(rel.equals("father") || rel.equals("mother")) {

            Family specificFamily = null;
            for(Family fam: families) {
                //Find specific families and add the child
                if( firstPerson.getName().equals(fam.parents[0].getName()) || firstPerson.equals(fam.parents[1]) )
                specificFamily = fam;
            }

            assert specificFamily != null : "There is an error in method isParent with insert ArrayList family";
            for(Person child: specificFamily.children)
                if (lastPerson.getName().equals(child.getName())) {
                    return;
                }

            specificFamily.children.add(lastPerson);

        }
    }
// End of functions
    //Check the relationships
    //Input people to right array-list.
    //If there is no relationship generate a new Family obj


    // To String Object printing
    private String printChildrenNames() {
        String child = "\n";
        if(children.size() > 0) {
            for (Person person : children) {
                child += "\t" + person.getName() + "\n";
            }
        }else {
            return "There are no children.";
        }
        return child;
    }

    @Override
    public String toString() {
        return "Father is: " + parents[0].getName() + "\nMother is: " + parents[1].getName() +
                "\nChildren: " + printChildrenNames() + "\n\n";
    }

}
