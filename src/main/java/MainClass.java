import java.io.IOException;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CSVClass csvFile = new CSVClass();
        DataManager data = new DataManager();

        csvFile.readFile(data.getPeople(), data.getRelationships());

        //Create a family object with existing data
        data.CreateNewIfPartners();
        data.checkForCompletedFamily();
        //Add those families to every person
        data.insertFamiliesToPerson();
        //Generate the graph
        GraphViz graph = new GraphViz(data.getRelationships());

        int countMain = 0;
        while (countMain == 0) {

            String val = "";

            System.out.println("Choose functionality from menu.");
            System.out.println("1. Print all relationships from CSV file.");
            System.out.println("2. Print all people from CSV file.");
            System.out.println("3. Print all families according the data from CSV file.");
            System.out.println("4. Print all data from CSV file with families.");
            System.out.println("5. Print families by person name.");
            System.out.println("6. Find the relationship between two deference people.");
            System.out.println("7. Export a txt file with all people.");
            System.out.println("8. Export dot file and graph from GraphViz for data.");
            System.out.println("9. Exit from app. (Press exit or number of function)");

            System.out.println("\nEnter your choice.");
            val = scanner.nextLine();

            switch (val) {


                case "1":
                    data.printListOfRelationships();
                    break;

                case "2":
                    data.printListOfPeople();
                    break;

                case "3":
                    data.printListOfFamilies();
                    break;

                case "4":
                    data.printListOfRelationships();
                    data.printListOfPeople();
                    data.printListOfFamilies();
                    break;

                case "5":
                    int counter = 0;

                    while (counter == 0) {
                        counter = 1;
                        System.out.println("Insert name of person to return families");
                        Person personName = Person.findPerson(data.people, scanner.nextLine());
                        if (personName != null) {
                            data.printFamiliesPerPerson(personName);
                        } else {
                            System.out.println("Name does not exist.");
                            counter = 0;
                        }
                    }
                    break;

                case "6":
                    ////////////SEARCHING RELATIONSHIPS////////////////////////////
                    int count = 0;
                    System.out.println("Please put deference name and if course null values not accepted.");

                    while (count == 0) {
                        count = 1;
                        System.out.println("Insert firstRelName");
                        Person personFirst = Person.findPerson(data.people, scanner.nextLine());
                        System.out.println("Insert lastRelName");
                        Person personLast = Person.findPerson(data.people, scanner.nextLine());
                        assert personFirst != null;
                        if (!personFirst.equals(personLast)) {
                            System.out.println("The relationship is:");
                            IRelationshipsFinding.printRelationship(personFirst, personLast);
                            System.out.println();
                        } else {
                            System.out.println("You can not put same name as choices.");
                            count = 0;
                        }
                    }
                    break;

                case "7":
                    //////////TXT GENERATE FILE////////////////////////////////
                    CSVClass.exportTXT(data.people);
                    break;

                case "8":
                    try {
                        graph.graphVizMethod();
                    } catch (IOException e) {
                        System.out.println("There is an issue with GraphViz API.");
                    }
                    break;

                case "9":
                    countMain = 1;
                    break;

                case "exit":
                    countMain = 1;
                    break;

                case "EXIT":
                    countMain = 1;
                    break;

                case "Exit":
                    countMain = 1;
                    break;

                default:
                    System.out.println("You must choose a functionality. Write a number base on function you want.");
                    countMain = 0;
            }
        }
    }
}
