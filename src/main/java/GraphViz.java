import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.attribute.Rank.RankDir.*;
import static guru.nidi.graphviz.model.Factory.*;

public class GraphViz implements IRelationshipsFinding{

    ArrayList<Relationship> relationships;

    public GraphViz(ArrayList<Relationship> relationships) {
        this.relationships = relationships;
    }

//    private static Graph addByPerson(Graph graph, Person person) {
//
//        for(Family fam : person.getFamilies()) {
//            for(Person per : fam.parents) {
//                if (!per.equals(person)) graph = addSpouse(graph, person, per);
//            }
//                for(Person child : fam.children) {
//                    if (!child.equals(person)) graph = addSpouse( graph, person, child);
//                }
//
//
//        }
//        return graph;
//    }

    private static Graph addRelative(Graph graph, Relationship rel) {
        Boolean isCoupleRelationship = rel.getRelationship().equals("husband") || rel.getRelationship().equals("Wife");

        if(isCoupleRelationship) {
            return addSpouse(graph, rel);
        } else {
            return addChild(graph, rel);
        }
    }

    private static Graph addChild(Graph graph, Relationship rel) {
        return
//                graph("example1").directed().graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
//                .nodeAttr().with(Font.name("Arial"), Color.LIGHTGREY,Style.FILLED).with(
//                        node(rel.getFirstPerson().getName()).with(Style.FILLED, Color.ANTIQUEWHITE, Shape.HEXAGON)
//                        .link(to(node(rel.getLastPerson().getName())).with(Color.FORESTGREEN, Style.DOTTED, Label.of("child")))
//        );

                graph.with(
                node(
                        rel.getFirstPerson().getName()
                ).with(
                        Style.FILLED, Color.LIGHTGREY, Shape.DOUBLE_OCTAGON
                ).link(
                        to(
                                node(
                                        rel.getLastPerson().getName()
                                ).with(Shape.DIAMOND, Color.LAVENDER)
                        ).with(

                                Color.BLUE1,
                                Style.DOTTED,
                                Arrow.VEE,
                                //Rank.dir(LEFT_TO_RIGHT),
                                Label.of("child")
                        )
                )
        );
    }
//add(Color.WHITE.gradient(Color.rgb("888888")).background().angle(90)) mono perigramma me col k periexomeno

    private static Graph addSpouse(Graph graph, Relationship rel) {
        return
                graph.with(
                        node(rel.getFirstPerson().getName() + " Family")
                         .link(to(node(rel.firstPerson.getName()).with(
                                 Color.LIGHTBLUE2
                         ))),
                        node(rel.getFirstPerson().getName() + " Family")
                                .link(to(node(rel.lastPerson.getName())
                                        .with(
                                        Color.ANTIQUEWHITE2
                                     //   Shape.MDiamond
                                ).with(Shape.HEXAGON))
                                .with(
                                        Color.VIOLETRED,
                                    Style.BOLD,
                                    Label.of("couple")
                                ))

                                );
//                                .with(
//                                        Color.LIGHTBLUE1,
//                                        Shape.MDiamond)

//                    node(
//                        rel.getFirstPerson().getName()
//                    )
//                        graph("example1").directed().graphAttr().with(Rank.dir(LEFT_TO_RIGHT)).nodeAttr()
//                .with(Font.name("Arial"), Color.LIGHTGREY,Style.FILLED)
//                                .with(
//                node(
//                        rel.getFirstPerson().getName()
//                )
//                        .link(
//                                to(
//                                        node(rel.getLastPerson().getName())
//                                               // .with(attr("weight", 5), Style.FILLED, Color.BLUE1)
//                                ).with(
//                                    Color.VIOLETRED,
//                                    Style.BOLD,
//                                    Label.of("married to")
//                                )
 //.with(Color.BLUE1)//).with(Style.DOTTED , Color.BLUE1)
//                        )
//                        .with(
//                                Color.LIGHTGREY,
//                                Shape.DOUBLE_OCTAGON
//                        )
//        );
    }

//    private static Graph addSpouse(Graph graph, Person firstPerson, Person lastPerson) {
//
//
//            return graph.with(
//                    node(firstPerson.getName()).with(Style.FILLED)
//                            .link(to(node(lastPerson.getName()))).with(Style.FILLED)
//                        //.link(to(node(lastPerson.getName()))).with(Style.DOTTED, Label("couple"), Color.RED)
//            );
//
//    }

    public void graphVizMethod() throws IOException {
        Graph g = graph("example1").directed()
                .graphAttr().with(Rank.dir(TOP_TO_BOTTOM)).nodeAttr().with(Font.name("Pristina"), Shape.PENTAGON, Color.GOLD, Style.SOLID)
                .linkAttr().with("class", "link-class"); //Castellar, Vivaldi, Pristina

        for(Relationship relationship : relationships) {
            g = addRelative(g, relationship);
        }

//        g = addByPerson(g, per);

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