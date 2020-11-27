import java.io.IOException;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        CSVClass csvFile = new CSVClass();
        DataManager data = new DataManager();

        csvFile.readFile(data.getPeople(), data.getRelationships());

        //Create a family object with existing data
        data.CreateNewIfPartners();
        data.checkForCompletedFamily();
        //Add those families to every person
        data.insertFamiliesToPerson();

        //Print data of DataManager obj
        data.printListOfRelationships();
        data.printListOfPeople();
        data.printListOfFamilies();

        int counter = 0;

        while (counter == 0) {
            counter = 1;
            System.out.println("Insert name of person to return families");
            Person personName = Person.findPerson(data.people, scanner.nextLine());
            if(personName != null) {
            data.printFamiliesPerPerson(personName);
            } else {
                System.out.println("Name does not exist.");
                counter = 0;
            }
        }


        GraphViz graph = new GraphViz(data.getRelationships());


        ////////////SEARCHING RELATIONSHIPS////////////////////////////



        int count = 0;

        while (count == 0) {
            count = 1;
            System.out.println("Insert firstRelName");
            Person personFirst = Person.findPerson(data.people, scanner.nextLine());
            System.out.println("Insert lastRelName");
            Person personLast = Person.findPerson(data.people, scanner.nextLine());
            assert personFirst != null;
            if(!personFirst.equals(personLast)) {
                System.out.println("The relationship is:");
                IRelationshipsFinding.printRelationship(personFirst, personLast);
                System.out.println();
            } else {
                System.out.println("You can not put same name as choices.");
                count = 0;
            }
        }


        //////////TXT GENERATE FILE////////////////////////////////
        // Make a scanner to give a choice.
//        System.out.println("If you wanna export a TXT file with all names of this tree press true");
//        boolean exportFile = scanner.nextBoolean();
//        if(exportFile) CSVClass.exportTXT(data.people);

        //////////GRAPH VIZ////////////////////////////////
//        System.out.println("Insert a name for graph");
//        Person personFirst = Person.findPerson(data.people, scanner.nextLine());
        graph.graphVizMethod();

    }
}
