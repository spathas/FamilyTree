import com.opencsv.CSVReader;
//import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Rank;
//import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

//import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.*;
//import static guru.nidi.graphviz.model.Factory.node;

public class CSVReaderExample {

    public static void main(String[] args) throws IOException {

        String csvFile = "C:\\Users\\spath\\Desktop\\BaratheonTreeWithRels.csv";
        CSVReader reader;
        Set<Human> man = new HashSet<>();
        Set<Relation> rel = new HashSet<>();

        //CSV to Objects
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                if(line[line.length -1].equals("") || line[line.length -1] == null) {
                    man.add(new Human(line[0], line[1]));
                }
                else {
                    rel.add(new Relation(line[0], line[1], line[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Humans");
        System.out.println(man.toString());
        System.out.println("________________________________________________________________\n");
        System.out.println("Relationships");
        System.out.println(rel.toString());

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
