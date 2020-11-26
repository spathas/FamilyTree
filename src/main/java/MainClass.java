import java.io.IOException;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) throws IOException {

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
        GraphViz graph = new GraphViz(data.getRelationships());


        ////////////SEARCHING RELATIONSHIPS////////////////////////////

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Insert firstRelName");
//        Person personFirst = Person.findPerson(data.people, scanner.nextLine());
//        System.out.println("Insert lastRelName");
//        Person personLast = Person.findPerson(data.people, scanner.nextLine());
//
//        System.out.println("The relationship is:");
//        assert personFirst != null;
//        IRelationshipsFinding.calcRelationship(personFirst, personLast);
//        System.out.println();


        //////////TXT GENERATE FILE////////////////////////////////
        // Make a scanner to give a choice.
//        System.out.println("If you wanna export a TXT file with all names of this tree press true");
//        boolean exportFile = scanner.nextBoolean();
//        if(exportFile) CSVClass.exportTXT(data.people);

        graph.graphVizMethod();

    }
}
