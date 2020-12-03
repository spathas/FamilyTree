
import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.model.Graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.*;

public class GraphViz implements IRelationshipsFinding {

    ArrayList<Relationship> relationships;

    public GraphViz(ArrayList<Relationship> relationships) {
        this.relationships = relationships;
    }

    private static Graph addRelative(Graph graph, Relationship rel) {
        Boolean isCoupleRelationship = rel.getRelationship().equals("husband") || rel.getRelationship().equals("Wife");

        if (isCoupleRelationship) {
            return addSpouse(graph, rel);
        }
        else {
            return addChild(graph, rel);
        }
    }

    private static Graph addChild(Graph graph, Relationship rel) {
        return graph.with(
                node(
                        rel.getFirstPerson().getName()
                ).with(
                        Style.FILLED,
                        Color.LIGHTGREY,
                        Shape.DOUBLE_OCTAGON
                ).link(
                        to(
                                node(
                                        rel.getLastPerson().getName()
                                ).with(
                                        Shape.DIAMOND,
                                        Color.LAVENDER)
                        ).with(
                                Color.BLUE1,
                                Style.DOTTED,
                                Arrow.VEE,
                                Label.of("child")
                        )
                )
        );
    }

    private static Graph addSpouse(Graph graph, Relationship rel) {
        return
                graph.with(
                        node(rel.getFirstPerson().getName() + " Family")
                                .link(
                                        to(
                                                node(
                                                        rel.firstPerson.getName())

                                                        .with(
                                                                Color.LIGHTBLUE2)
                                        )
                                ),
                        node(rel.getFirstPerson().getName() + " Family")
                                .link(to(node(
                                        rel.lastPerson.getName())
                                        .with(Color.ANTIQUEWHITE2)
                                        .with(Shape.HEXAGON)
                                )
                                        .with(
                                                Color.VIOLETRED,
                                                Style.BOLD,
                                                Label.of("couple")
                                        ))

                );
    }

    public void graphVizMethod() throws IOException {
        Graph g = graph("example1").directed()
                .graphAttr().with(Rank.dir(TOP_TO_BOTTOM)).nodeAttr().with(Font.name("Pristina"), Shape.PENTAGON, Color.GOLD, Style.SOLID)
                .linkAttr().with("class", "link-class"); //Castellar, Vivaldi, Pristina

        for (Relationship relationship : relationships) {
            g = addRelative(g, relationship);
        }

        guru.nidi.graphviz.engine.Graphviz
                .fromGraph(g)
                .height(500)
                .render(Format.DOT).toFile(new File("src/output/ex1.dot"));

        guru.nidi.graphviz.engine.Graphviz
                .fromGraph(g)
                .height(800)
                .render(Format.PNG).toFile(new File("src/output/ex1.png"));
    }
}