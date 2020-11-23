//import guru.nidi.graphviz.attribute.Color;
//import guru.nidi.graphviz.attribute.Style;
//import static guru.nidi.graphviz.attribute.Attributes.attr;
//import static guru.nidi.graphviz.model.Factory.node;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeSet;

public class DataManager {

    public static void main(String[] args) {

        TreeSet<Person> people = new TreeSet<>();
        ArrayList<Relationship> relationships = new ArrayList<>();
        ArrayList<Family> families = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        ////////////CSV READER///////////////////////////////////////
        IFileManager.readFile(people, relationships);

        //Create a family object with existing data
        Family.CreateNewIfPartners(relationships, families);
        Family.insertChild(relationships, families);

        //Insert families in person objects.
        for(Person person : people) {
            person.families.addAll(Family.addFamiliesToPerson(families, person));
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
