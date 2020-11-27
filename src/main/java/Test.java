import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.*;

public class Test {
    //Ti thelw:
    //input pou xreiazomai gia to sxedio
    //array apo relationshipsA XRISTOF tha erxetai
    //na valw kai suzugos deksia
    //for gia na trexei gia child/spouse
//    Relationship [] exampleRelationshipsInput = {
//            new Relationship(
//                    new Person("Giorgos","man"),
//                    "spouse",
//                    new Person("Katerina","woman")
//            ),
//            new Relationship(
//                    new Person("Giorgos","man"),
//                    "parent",
//                    new Person("Xristina","woman")
//            )
//    };
//    private static Graph createNode(Graph graph) {
//        return graph.with(
//                node("GrandmaEmily").with(Color.RED).link(node("Andreas")),
//                node("GrandmaEmily").link(node("Euthumia")),
//


//                node("Andreas").link(
//                        to(node("Nikos")).with(attr("weight", 5), Style.DASHED)
//                ));
//}

//    Human h1= new Human();
//    h1.setNameSurname("Stefon Baratheon");
//    Human h2= new Human();
//    h2.setNameSurname("Cassana Estermont");
    private static Graph addFamilly(Graph graph, String parentNameFst, String parentNameSnd,String childName) {
        return graph.with(
                node(parentNameFst).with(Color.GREEN).link(node(childName)),
                node(parentNameSnd).with(Color.GREEN).link(node(childName))
        );
    }
    private static Graph addSpouse(Graph graph, String parentNameFst, String parentNameSnd) {
        return graph.with(
                node(parentNameFst).with(Color.RED).link(to(node(parentNameSnd)))
        );
//        private static graph(directed = true) {
//            edge["color" eq "red", Arrow.TEE]
//            node[Color.GREEN]
//            graph[Rank.dir(LEFT_TO_RIGHT)]
//    }
//class Human{
//        private String name;
//
//        public Human(){
//            name ="d.e.";
//        }
//        public Human(String someonesName){
//            name = someonesName;
//            //setName(nameFromSomeone);
//        }
//
//    public String getNameSurname() {
//        return name;
//    }
//
//    public void setNameSurname(String nameInp) {
//        name = nameInp;
//    }
//}
    }
// public static void main(String[] args) throws IOException {
        public void testaki() throws IOException {
        Graph g = graph("example1").directed()
                .graphAttr().with(Rank.dir(TOP_TO_BOTTOM)).nodeAttr().with(Font.name("Arial"))
                .linkAttr().with("class", "link-class");

        g = addFamilly(g, "Stefon Baratheon", "Cassana Estermont","Robert Baratheon");
        g = addFamilly(g, "Stefon Baratheon", "Cassana Estermont","Stannis Baratheon");
        g = addFamilly(g, "Stefon Baratheon", "Cassana Estermont","Renly Baratheon");
        g = addFamilly(g, "Stannis Baratheon", "Selyse Estermont","Shireen Baratheon");
      //  g = addSpouse(g, "Stefon Baratheon", "Cassana Estermont");


        Graph g1 = graph("example2").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT)).nodeAttr().with(Font.name("Arial"))
                .linkAttr().with("class", "link-class");

        g1 = addSpouse(g, "Stefon Baratheon", "Cassana Estermont");
        Graphviz
                .fromGraph(g)
                .height(500)
                .render(Format.DOT).toFile(new File("src/output/ex1.dot"));

        Graphviz
                .fromGraph(g)
                .height(500)
                .render(Format.PNG).toFile(new File("src/output/ex1.png"));



    }
}
