//import guru.nidi.graphviz.attribute.Color;
//import guru.nidi.graphviz.attribute.Style;
//import static guru.nidi.graphviz.attribute.Attributes.attr;
//import static guru.nidi.graphviz.model.Factory.node;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeSet;

public class DataManager {

    private static boolean isPartners(Family family, Person firstPerson, Person lastPerson) {
        int firstGender, lastGender;
        firstGender = IRelationshipsFinding.translateGender(firstPerson);
        lastGender = IRelationshipsFinding.translateGender(lastPerson);

        return IRelationshipsFinding.isPartners(family, firstPerson, lastPerson, firstGender, lastGender);
    }

    private static boolean isParent(Family family, Person firstPerson, Person lastPerson) {
        int firstGender;
        firstGender = IRelationshipsFinding.translateGender(firstPerson);

        return IRelationshipsFinding.isParent(family, firstPerson, lastPerson, firstGender);
    }

    //Check for double generation
    private static Family findFamilyByRelationship(Relationship relationship, ArrayList<Family> families) {
        for(Family family: families) {
            if (isPartners(family, relationship.getFirstPerson(), relationship.getLastPerson()))
                return family;
            //Check for parents
            if (isParent(family, relationship.getFirstPerson(), relationship.getLastPerson()))
                return family;
        }
        return null;
    }

    public static Family findFamilyByPartners(ArrayList<Family> families, Person firstPerson, Person lastPerson) {
        for(Family family: families) {
            if(isPartners(family, firstPerson, lastPerson))
                return family;
        }
        return null;
    }

    //This is a bad idea, don't handle null values because is risky.
    //Γενικά να απόφεύγουμε τετοιο κώδικα που διαχειρίζεται null τιμές μπορεί να σκάσει πολύ εύκολα.
    public static Family findFamilyByNullParents(ArrayList<Family> families, Person nullPerson, Person lastPerson) {
        if(nullPerson == null) {
            if (IRelationshipsFinding.translateGender(lastPerson) == 0) nullPerson = new Person("woman");
            if (IRelationshipsFinding.translateGender(lastPerson) == 1) nullPerson = new Person("man");

            int nullGender = IRelationshipsFinding.translateGender(nullPerson);
            int lastGender = IRelationshipsFinding.translateGender(lastPerson);

            for(Family family: families) {
                if( family.parents[nullGender].getName().equals(nullPerson.getName()) && family.parents[lastGender].getName().equals(lastPerson.getName()) )
                    return family;
            }
        }
        return null;
    }


    public static ArrayList<Family> addFamiliesToPerson(ArrayList<Family> families, Person firstPerson) {
        ArrayList<Family> fam = new ArrayList<>();
        for(Family family: families) {
            int index = -1;
            if(firstPerson.getGender().equals("man")) index = 0;
            if(firstPerson.getGender().equals("woman")) index = 1;

            if(family.parents[index].getName().equals(firstPerson.getName())) {
                fam.add(family);
            }
            for (Person person : family.children) {
                if(person.getName().equals(firstPerson.getName())) fam.add(family);
            }
        }
        return fam;
    }

    public static void CreateNewIfPartners(ArrayList<Relationship> relationships, ArrayList<Family> families) {
        for(Relationship rel : relationships) {
            //Create new family object and insert into family array.
            switch (rel.getRelationship()) {
                case "husband":
                    if (findFamilyByRelationship(rel, families) == null) {
                        families.add(new Family(rel.getFirstPerson(), rel.getLastPerson()));
                    }
                    else System.out.println("There is a duplicate here: " + rel.toString() + "\nCheck your CSV file for duplicates");
                    break;
                case "wife":
                    if (findFamilyByRelationship(rel, families) == null)
                        families.add(new Family(rel.getLastPerson(), rel.getFirstPerson()));
                    else System.out.println("There is a duplicate here: " + rel.toString() + "\nCheck your CSV file for duplicates");
                    break;
            }
        }
    }
    private static void insertChildToNullFamilies(ArrayList<Family> families, Relationship rel, String parentType) {
        String parentGender = "";
        if (parentType.equals("father")) parentGender = "man";
        if (parentType.equals("mother")) parentGender = "woman";

        Family newFam;
        newFam = findFamilyByNullParents(families, null, rel.getFirstPerson());
        if (newFam != null) newFam.children.add(rel.getLastPerson());
        if (newFam == null && parentGender.equals("man")) families.add(new Family(new Person(parentGender), rel.getFirstPerson(), rel.getLastPerson()));
        if (newFam == null && parentGender.equals("woman")) families.add(new Family(rel.getFirstPerson(), new Person(parentGender), rel.getLastPerson()));
    }

    private static void checkForCompletedFamily(ArrayList<Relationship> relationships, ArrayList<Family> families, Relationship rel, String parentType) {
        for(Relationship relationship : relationships) {
            if (!(rel.getFirstPerson().equals(relationship.getFirstPerson())) && relationship.getLastPerson().equals(rel.getLastPerson()) && relationship.getRelationship().equals(parentType)) {
                Family fam = findFamilyByPartners(families, rel.getFirstPerson(), relationship.getFirstPerson());
                if (fam != null) {
                    fam.children.add(relationship.getLastPerson());
                    return;
                }
            }
        }
        insertChildToNullFamilies(families, rel, parentType);
    }

    public static void insertChild(ArrayList<Relationship> relationships, ArrayList<Family> families) {
        for (Relationship rel : relationships) {
            if(rel.getRelationship().equals("mother"))
                checkForCompletedFamily(relationships, families, rel, "father");
            if(rel.getRelationship().equals("father"))
                checkForCompletedFamily(relationships, families, rel, "mother");
        }

    }



    public static void main(String[] args) {

        TreeSet<Person> people = new TreeSet<>();
        ArrayList<Relationship> relationships = new ArrayList<>();
        ArrayList<Family> families = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        IFileManager.readFile(people, relationships);

        //Create a family object with existing data
        CreateNewIfPartners(relationships, families);
        insertChild(relationships, families);

        //Insert families in person objects.
        for(Person person : people) {
            person.families.addAll(addFamiliesToPerson(families, person));
            person.printFamilies();
        }

        System.out.println("People");
        System.out.println(people.toString());
        System.out.println("________________________________________________________________\n");

        System.out.println("Relationships");
        System.out.println(relationships.toString());
        System.out.println("________________________________________________________________\n");

        System.out.println("Families");
        System.out.println(families.toString());
        System.out.println("________________________________________________________________\n");


        ////////////SEARCHING RELATIONSHIPS////////////////////////////

        System.out.println("Insert firstRelName");
        Person personFirst = Person.findPerson(people, scanner.nextLine());
        System.out.println("Insert lastRelName");
        Person personLast = Person.findPerson(people, scanner.nextLine());

        System.out.println("The relationship is:");
        assert personFirst != null;
        IRelationshipsFinding.calcRelationship(personFirst, personLast);
        System.out.println();


        //////////TXT GENERATE FILE////////////////////////////////
        // Make a scanner to give a choice.
        System.out.println("If you wanna export a TXT file with all names of this tree press true");
        boolean exportFile = scanner.nextBoolean();
        if(exportFile) IFileManager.exportTXT(people);



    }

}
