package MyGraphs.chapter4dot1;

import java.util.function.Consumer;
import java.util.function.Function;

public class GraphPrinter {

    private static <T> Consumer<Vertex<T>> regularVertexPrintStrategy(final Function<Vertex<T>, ? extends String> vertexToString) {
        return vertex -> {
            final StringBuilder connectedVertices = new StringBuilder();
            vertex.getConnections().stream().forEach(s -> connectedVertices.append(vertexToString.apply(s.getVertex()) + ", "));
            if (connectedVertices.length() > 1) {
                connectedVertices.setLength(connectedVertices.length() - 2);
            }
            System.out.println(vertexToString.apply(vertex) + ": " + connectedVertices);
        };
    }

    public static <T> void printGraph(final Graph<T> graph) {
        graphPrintAspect(graph, regularVertexPrintStrategy(vertex -> vertex.toString()));
    }

    public static <T> void printGraphWithOnlyData(final Graph<T> graph) {
        graphPrintAspect(graph, regularVertexPrintStrategy(vertex -> vertex.printData()));
    }

    public static <T> void graphPrintAspect(final Graph<T> graph, final Consumer<Vertex<T>> vertexPrintStrategy) {
        System.out.println("Now printing Graph");
        System.out.println("------------------------------------------------------------");
        graph.getVerticesList().stream().forEach(vertex -> vertexPrintStrategy.accept(vertex));
        System.out.println("------------------------------------------------------------");
    }
}
