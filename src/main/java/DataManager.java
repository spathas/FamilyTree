import com.opencsv.CSVReader;

//import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Rank;
//import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
//import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.*;
//import static guru.nidi.graphviz.model.Factory.node;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class DataManager {

    public static void main(String[] args) throws IOException {

        String csvFile = "/home/cspathas/Desktop/BaratheonTreeWithRels.csv";
        CSVReader familyTree;
        TreeSet<Person> person = new TreeSet<>();
        ArrayList<Family> rel = new ArrayList<>();

        ////////////CSV READER///////////////////////////////////////
        try {
            familyTree = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = familyTree.readNext()) != null) {
                if(line[line.length -1].equals("") || line[line.length -1] == null) {
                    person.add(new Person(line[0], line[1]));
                }
//                else {
//                    rel.add(new Family(line[0], line[1], line[2]));
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("People");
        System.out.println(person.toString());
        System.out.println("________________________________________________________________\n");
        System.out.println("Relationships");
        System.out.println(rel.toString());

        //////////TXT GENERATE FILE////////////////////////////////
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

        ///////////////GRAPHVIZ/////////////////////////////////////////
        Graph g = graph("Baratheon").directed()
                .graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
                .nodeAttr().with(Font.name("Arial"))
                .linkAttr().with("class", "link-class")
                .with(
                        //
                );

        Graphviz
                .fromGraph(g)
                .height(500)
                .render(Format.DOT).toFile(new File("./ex1.dot"));

        Graphviz
                .fromGraph(g)
                .height(500)
                .render(Format.PNG).toFile(new File("./ex1.png"));


    }

}