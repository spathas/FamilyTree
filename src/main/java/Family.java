import java.util.HashSet;
import java.util.Set;

public class Family implements IRelationshipsFinding {

    Person[] parents = new Person[2];
    Set<Person> children = new HashSet<>();

    public Family(Person father, Person mother) {
        this.parents[0] = father;
        this.parents[1] = mother;
    }

    public Family(Person father, Person mother, Person child) {
        this.parents[0] = father;
        this.parents[1] = mother;
        this.children.add(child);
    }

    public Person[] getParents() {
        return parents;
    }

    public Set<Person> getChildren() {
        return children;
    }
// To String Object printing

    private String printChildrenNames() {
        String child = "\n";
        if (children.size() > 0) {
            for (Person person : children) {
                child += "\t" + person.getName() + "\n";
            }
        } else {
            return "There are no children.";
        }
        return child;
    }

    private String printParent(int index) {
        if (this.parents[index] != null)
            return parents[index].getName();
        return "Unknown";
    }

    @Override
    public String toString() {
        return "Father is: " + printParent(0) + "\nMother is: " + printParent(1) +
                "\nChildren: " + printChildrenNames() + "\n\n";
    }
}

    //    public static Family findFamilyByParents(ArrayList<Family> families, Person firstPerson, Person lastPerson) {
//        for(Family family: families) {
//            if(isParent(family, firstPerson, lastPerson))
//                return family;
//        }
//        return null;
//    }
