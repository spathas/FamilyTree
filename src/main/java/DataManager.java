import com.opencsv.CSVReader;

//import guru.nidi.graphviz.attribute.Color;
//import guru.nidi.graphviz.attribute.Style;
//import static guru.nidi.graphviz.attribute.Attributes.attr;
//import static guru.nidi.graphviz.model.Factory.node;

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class DataManager {

    public static void exportTXT(TreeSet<Person> person) {
        try {
            FileWriter myWriter = new FileWriter("names.txt");
            for(Person per : person) {
                myWriter.write("Name: " + per.getName() + ", Gender: " + per.getGender() + ",\n");
            }
            myWriter.write("\n\n The length of list is: " + person.size());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static boolean checkRelationship(Person firstPerson, String relationship) {
        if( relationship.equals("father") || relationship.equals("mother") || relationship.equals("husband") || relationship.equals("wife") )
            //Check for logical fail
            if( (relationship.equals("father") || relationship.equals("husband")) && firstPerson.getGender().equals("man") )
                return true;
        return (relationship.equals("mother") || relationship.equals("wife")) && firstPerson.getGender().equals("woman");
    }

    public static void main(String[] args) {


//        String csvFile = "/home/cspathas/Desktop/BaratheonTreeWithRels.csv";
        String csvFile = "C:\\Users\\emi-1\\Projects\\FamilyTree\\src\\main\\java\\BaratheonTreeWithRels.csv";

        CSVReader familyTree;
        TreeSet<Person> people = new TreeSet<>();
        ArrayList<Relationship> relationships = new ArrayList<>();
        ArrayList<Family> families = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        ////////////CSV READER///////////////////////////////////////
        try {

            familyTree = new CSVReader(new FileReader(csvFile));
            String[] line;
            int lineCount = 0;

            while ((line = familyTree.readNext()) != null) {
                lineCount++;

                if(line[line.length -1].equals("") || line[line.length -1] == null) {
                    String name = line[0];
                    String gender = line[1];

                    Person.createPerson(people, name, gender, lineCount);
                }
                else {
                    //Find people objects base on 2 names by CSV line and relationship between them.
                    Person firstPerson = Person.findPerson(people, line[0]);
                    Person lastPerson = Person.findPerson(people, line[2]);
                    String rel = line[1];

                    if (!DataManager.checkRelationship(firstPerson, rel)) {
                        System.out.println("There is a bad relationship in CSV file.\nPlease check your relationship stack.");
                        System.exit(-1);
                    }else {
                        //Create a stack of all relationships
                        assert firstPerson != null && lastPerson != null;
                        relationships.add( new Relationship(firstPerson, rel, lastPerson) );

                    }


                }
                //Create a family object with existing data
            }
            Family.CreateNewIfPartners(relationships, families);
            Family.insertChild(relationships, families);

            //Insert families in person objects.
            for(Person person : people) {
                person.families.addAll(Family.addFamiliesToPerson(families, person));
                System.out.println();
                System.out.println("Families of " + person.getName() + " are: \n");
                person.printFamilies();
                System.out.println("-----------------------------------------------");
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
//            Family.calcRelationship(families, personFirst, personLast);


            //////////TXT GENERATE FILE////////////////////////////////
            // Make a scanner to give a choice.
            System.out.println("If you wanna export a TXT file with all names of this tree press true");
            boolean exportFile = scanner.nextBoolean();
            if(exportFile) exportTXT(people);

        } catch (IOException e) {
            System.out.println("\"Check the path or type of CSV file.\"");
            e.printStackTrace();
        }


        ///////////////GRAPHVIZ/////////////////////////////////////////
//        Graph g = graph("Baratheon").directed()
//                .graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
//                .nodeAttr().with(Font.name("Arial"))
//                .linkAttr().with("class", "link-class")
//                .with(
//                        //
//                );
//
//        Graphviz
//                .fromGraph(g)
//                .height(500)
//                .render(Format.DOT).toFile(new File("./ex1.dot"));
//
//        Graphviz
//                .fromGraph(g)
//                .height(500)
//                .render(Format.PNG).toFile(new File("./ex1.png"));


    }

}