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

public class GraphViz implements IRelationshipsFinding{

    ArrayList<Relationship> relationships;

    public GraphViz(ArrayList<Relationship> relationships) {
        this.relationships = relationships;
    }

    private static Graph addByPerson(Graph graph, Person person) {

        for(Family fam : person.getFamilies()) {
//            for(Person per : fam.parents) {
//                if (!per.equals(person)) graph = addSpouse(graph, person, per, IRelationshipsFinding.calcRelationship(per, person));
//            }
//                for(Person child : fam.children) {
//                    graph = addSpouse( graph, person, child, IRelationshipsFinding.calcRelationship(child, person) );
//                }


        }
        return graph;
    }

    private static Graph addSpouse(Graph graph, Relationship rel) {

        if(rel.getRelationship().equals("husband") || rel.getRelationship().equals("Wife")) {
            return graph.with(
                    node(rel.getFirstPerson().getName()).with(Style.FILLED)
                            .link(to(node(rel.getLastPerson().getName()))).with(Style.FILLED)
            );
        }else {
            return graph.with(
                    node(rel.getFirstPerson().getName()).with(Style.FILLED)
                            .link(to(node(rel.getLastPerson().getName())))
            );
        }
    }

    private static Graph addSpouse(Graph graph, Person per, Person per2, String rel) {

        switch (rel) {
            case "Husband":
            return graph.with(
                    node(per.getName()).with(Color.BROWN)
                            .link(to(node(per2.getName()))).with(Style.FILLED)
            );
            case "Wife":
                return graph.with(
                        node(per.getName()).with(Color.YELLOW)
                                .link(to(node(per2.getName()))).with(Style.FILLED)
                );
            case "Father":
                return graph.with(
                        node(per.getName()).with(Color.BLUE)
                                .link(to(node(per2.getName()))).with(Style.FILLED)
                );
            case "Mother":
                return graph.with(
                        node(per.getName()).with(Color.RED)
                                .link(to(node(per2.getName()))).with(Style.FILLED)
                );
            case "Brother":
                return graph.with(
                        node(per.getName()).with(Color.BISQUE1)
                                .link(to(node(per2.getName()))).with(Style.FILLED)
                );
            case "Sister":
                return graph.with(
                        node(per.getName()).with(Color.BISQUE)
                                .link(to(node(per2.getName()))).with(Style.FILLED)
                );
            case "Daughter":
                return graph.with(
                        node(per.getName()).with(Color.PERU)
                                .link(to(node(per2.getName()))).with(Style.FILLED)
                );
            case "Son":
                return graph.with(
                        node(per.getName()).with(Color.BLACK)
                                .link(to(node(per2.getName()))).with(Style.FILLED)
                );

        }
        return graph.with(
                node(per.getName()).with(Color.RED)
                        .link(to(node(per2.getName()))).with(Style.FILLED)
        );
    }

    // public static void main(String[] args) throws IOException {
    public void graphVizMethod(Person per) throws IOException {
        Graph g = graph("example1").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT)).nodeAttr().with(Font.name("Arial"))
                .linkAttr().with("class", "link-class");

//        for(Relationship relationship : relationships) {
//
//            g = addSpouse(g, relationship);
//        }

        g = addByPerson(g, per);

        guru.nidi.graphviz.engine.Graphviz
                .fromGraph(g)
                .height(500)
                .render(Format.DOT).toFile(new File("src/output/ex1.dot"));

        guru.nidi.graphviz.engine.Graphviz
                .fromGraph(g)
                .height(5000)
                .render(Format.PNG).toFile(new File("src/output/ex1.png"));



    }

}