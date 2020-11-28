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
            for(Person per : fam.parents) {
                if (!per.equals(person)) graph = addSpouse(graph, person, per);
            }
                for(Person child : fam.children) {
                    if (!child.equals(person)) graph = addSpouse( graph, person, child);
                }


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
    private static Graph addSpouse(Graph graph, Person firstPerson, Person lastPerson) {
            return graph.with(
                    node(firstPerson.getName()).with(Style.FILLED)
                            .link(to(node(lastPerson.getName()))).with(Style.FILLED)
            );

    }

    // public static void main(String[] args) throws IOException {
    public void graphVizMethod() throws IOException {
        Graph g = graph("GraphViz").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT)).nodeAttr().with(Font.name("Arial"))
                .linkAttr().with("class", "link-class");

        for(Relationship relationship : relationships) {
            g = addSpouse(g, relationship);
        }
//        g = addByPerson(g, per);
        guru.nidi.graphviz.engine.Graphviz
                .fromGraph(g)
                .height(500)
                .render(Format.DOT).toFile(new File("src/output/ex1.dot"));

        guru.nidi.graphviz.engine.Graphviz
                .fromGraph(g)
                .height(5000)
                .render(Format.PNG).toFile(new File("src/output/ex1.png"));
    }

    public void graphVizMethodForPerson(Person per) throws IOException {
        Graph g = graph("GraphVizFor").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT)).nodeAttr().with(Font.name("Arial"))
                .linkAttr().with("class", "link-class");

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