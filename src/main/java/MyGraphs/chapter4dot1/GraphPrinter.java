package MyGraphs.chapter4dot1;

import java.util.function.Function;

public class GraphPrinter {

    private static <T> Function<Vertex<T>, Void> regularVertexPrintStrategy(final Function<Vertex<T>, ? extends String> vertexToString) {
        return vertex -> {
            final StringBuilder connectedVertices = new StringBuilder();
            vertex.getConnections().stream().forEach(s -> connectedVertices.append(vertexToString.apply(s.getVertex()) + ", "));
            if (connectedVertices.length() > 1) {
                connectedVertices.setLength(connectedVertices.length() - 2);
            }
            System.out.println(vertexToString.apply(vertex) + ": " + connectedVertices);
            return null;
        };
    }

    public static <T> void printGraph(final Graph<T> graph) {
        graphPrintAspect(graph, regularVertexPrintStrategy(vertex -> {return vertex.toString();}));
    }

    public static <T> void printGraphWithOnlyData(final Graph<T> graph) {
        graphPrintAspect(graph, regularVertexPrintStrategy(vertex -> {return vertex.printData();}));
    }

    public static <T> void graphPrintAspect(final Graph<T> graph, final Function<Vertex<T>, Void> vertexPrintStrategy) {
        System.out.println("Now printing Graph");
        System.out.println("------------------------------------------------------------");
        for (final Vertex<T> vertex : graph.getVerticesList()) {
            vertexPrintStrategy.apply(vertex);
        }
        System.out.println("------------------------------------------------------------");
    }
}
