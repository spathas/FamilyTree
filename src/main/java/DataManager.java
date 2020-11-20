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

    public static void main(String[] args) {

//        Wrong type of file.
//        String csvFile = "/home/cspathas/Desktop/testfile.ods";

        String csvFile = "/home/cspathas/Desktop/BaratheonTreeWithRels.csv";
        CSVReader familyTree;
        TreeSet<Person> people = new TreeSet<>();
        ArrayList<Family> families = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        ////////////CSV READER///////////////////////////////////////
        try {
            familyTree = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = familyTree.readNext()) != null) {
                if(line[line.length -1].equals("") || line[line.length -1] == null) {
                    people.add(new Person(line[0], line[1]));
                }
                else {
                    if(Person.isPerson(people, line[0]) && Person.isPerson(people, line[2])) {
                        System.out.println("Person with name: " + line[0] + ", or Person with name: " + line[2] + "does not exist as people object");
                    } else {

                        //Find people objects base on 2 names by CSV line
                        Person firstPerson = Person.findPerson(people, line[0]);
                        Person lastPerson = Person.findPerson(people, line[2]);
                        String rel = line[1];

                        Family.isPartners(firstPerson, lastPerson, rel, families);
                        Family.isParent(firstPerson, lastPerson, rel, families);
                    }

                }
            }

            System.out.println("People");
            System.out.println(people.toString());
            System.out.println("________________________________________________________________\n");
            System.out.println("Relationships");
            System.out.println(families.toString());

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