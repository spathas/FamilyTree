import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Font;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import java.io.File;
import java.io.IOException;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.attribute.Rank.RankDir.*;
import static guru.nidi.graphviz.model.Factory.*;

public class GraphViz {
    public static void main(String[] args) throws IOException {
        Graph g = graph("example1").directed()
                .graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
                .nodeAttr().with(Font.name("Arial"))
                .linkAttr().with("class", "link-class")
                .with(
                        node("GrandmaEmily").with(Color.RED).link(node("Andreas")),
                        node("GrandmaEmily").link(node("Euthumia")),

                        node("Andreas").link(
                                to(node("Nikos")).with(attr("weight", 5), Style.DASHED)
                        )
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
