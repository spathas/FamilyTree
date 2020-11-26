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

public class GraphViz {

    ArrayList<Relationship> relationships;

    public GraphViz(ArrayList<Relationship> relationships) {
        this.relationships = relationships;
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

    // public static void main(String[] args) throws IOException {
    public void graphVizMethod() throws IOException {
        Graph g = graph("example1").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT)).nodeAttr().with(Font.name("Arial"))
                .linkAttr().with("class", "link-class");

        for(Relationship relationship : relationships) {

            g = addSpouse(g, relationship);
        }

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