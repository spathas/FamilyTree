import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Family {

    Person[] parents = new Person[2];
    Set<Person> children = new HashSet<>();

    public Family(Person father, Person mother) {
        this.parents[0] = father;
        this.parents[1] = mother;
    }

    public Family(Person father, Person mother, Person child) {
        if(father == null) {
            father = new Person();
        }
        if(mother == null) {
            mother = new Person();
        }
        this.parents[0] = father;
        this.parents[1] = mother;
        this.children.add(child);
    }

    //Check for double generation
    private static Family findFamily(Relationship relationship, ArrayList<Family> families) {
        for(Family family: families) {
            //Check for partners
            if (relationship.getFirstPerson().equals(family.parents[0]) || relationship.getFirstPerson().equals(family.parents[1]))
                if (relationship.getLastPerson().equals(family.parents[0]) || relationship.getLastPerson().equals(family.parents[1]))
                    return family;
            //Check for parents
            if (relationship.getFirstPerson().equals(family.parents[0]) || relationship.getFirstPerson().equals(family.parents[1]))
                for (Person child : family.children) {
                    if (relationship.getLastPerson().getName().equals(child.getName()))
                        return family;
                }
        }
        return null;
    }

    private static Family findFamily(ArrayList<Family> families, String firstName, String secondName) {
        for(Family family: families) {
            if( ( family.parents[0].getName().equals(firstName) && family.parents[1].getName().equals(secondName) ) || family.parents[1].getName().equals(firstName) && family.parents[0].getName().equals(secondName) )
                return family;
        }
        return null;
    }

    // If there are partners create a new obj of Family
    public static void isPartners(Stack<Relationship> relationships, ArrayList<Family> families) {
        for(Relationship rel : relationships) {
            //Create new family object and insert into family array.
            switch (rel.getRelationship()) {
                case "husband":
                    if (Family.findFamily(rel, families) == null)
                        families.add(new Family(rel.getFirstPerson(), rel.getLastPerson()));
                    else System.out.println("There is a duplicate here: " + rel.toString() + "\nCheck your CSV file for duplicates");
                    break;
                case "wife":
                    if (Family.findFamily(rel, families) == null)
                        families.add(new Family(rel.getLastPerson(), rel.getFirstPerson()));
                    else System.out.println("There is a duplicate here: " + rel.toString() + "\nCheck your CSV file for duplicates");
                    break;
            }
        }
    }

    private static void checkForCompletedFamily(Stack<Relationship> relationships, ArrayList<Family> families, Relationship rel, String parentType) {
        for(Relationship relationship : relationships) {
            if (relationship.getLastPerson().getName().equals(rel.getLastPerson().getName()) && !relationship.getFirstPerson().getName().equals(rel.getFirstPerson().getName())) {
                Family fam = Family.findFamily(families, relationship.getFirstPerson().getName(), rel.getFirstPerson().getName());
                if (fam != null) {
                        fam.children.add(rel.getLastPerson());
                        return;
                }
            }
        }
        if (parentType.equals("father")) {
            Family fam = findFamily(families, rel.getFirstPerson().getName(), "Unknown");
            if(fam == null) families.add(new Family(null, rel.getFirstPerson(), rel.getLastPerson()));
            else fam.children.add(rel.getLastPerson());
        }
        else {
            Family fam = findFamily(families, rel.getFirstPerson().getName(), "Unknown");
            if(fam == null) families.add(new Family(rel.getFirstPerson(), null, rel.getLastPerson()));
            else fam.children.add(rel.getLastPerson());
        }
    }

    // Insert children
    public static void insertChild(Stack<Relationship> relationships, ArrayList<Family> families) {
        for (Relationship rel : relationships) {
            if(rel.getRelationship().equals("mother"))
                Family.checkForCompletedFamily(relationships, families, rel, "father");
            if(rel.getRelationship().equals("father"))
                Family.checkForCompletedFamily(relationships, families, rel, "mother");
        }

    }

//
//    public static void isParent(Person firstPerson, Person lastPerson, String rel, ArrayList<Family> families) {
//        if(rel.equals("father") || rel.equals("mother")) {
//
//
//            Family specificFamily = null;
//            for(Family fam: families) {
//                //Find specific families and add the child
//                if( firstPerson.getName().equals(fam.parents[0].getName()) || firstPerson.equals(fam.parents[1]) )
//                specificFamily = fam;
//            }
//
//            assert( specificFamily != null);
//            for(Person child: specificFamily.children)
//                if (lastPerson.getName().equals(child.getName())) {
//                    return;
//                }
//
//            specificFamily.children.add(lastPerson);
//
//        }
//    }
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

    private String printParent(int index) {
        if(this.parents[index] != null)
            return parents[index].getName();
        return "Unknown";
    }

    @Override
    public String toString() {
        return "Father is: " + printParent(0) + "\nMother is: " + printParent(1) +
                "\nChildren: " + printChildrenNames() + "\n\n";
    }

}
