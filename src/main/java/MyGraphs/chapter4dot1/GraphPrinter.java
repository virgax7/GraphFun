package MyGraphs.chapter4dot1;

public class GraphPrinter {
    public static <T> void printGraph(final Graph<T> g) {
        System.out.println("Now printing Graph");
        System.out.println("------------------------------------------------------------");
        for (final Vertex<T> vertex : g.getVertices()) {
            final StringBuilder connectedVertices = new StringBuilder();
            vertex.getConnections().stream().forEach(s -> connectedVertices.append(s.getConnectedVertex() + ", "));
            if (connectedVertices.length() > 1) {
                connectedVertices.setLength(connectedVertices.length() - 2);
            }
            System.out.println(vertex +  ": " + connectedVertices);
        }
        System.out.println("------------------------------------------------------------");
    }
}
