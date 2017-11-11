package MyGraphs.chapter4dot1;

import java.util.LinkedHashSet;
import java.util.Set;

public class DepthFirstSearch {

    public static void main(String[] args) {
        System.out.println("Now running dfs");
        final Graph<Integer> graph = GraphGenerator.getTinyIntegerGraph();
        dfs(graph);
        GraphPrinter.printGraph(graph);
    }

    public static <T> void dfs(final Graph<T> graph) {
        for (final Vertex<T> vertex : graph.getVertices()) {
            final Set<Vertex<T>> connectedPath = new LinkedHashSet<Vertex<T>>(){{add(vertex);}};
            dfs(vertex, connectedPath);
            System.out.println(connectedPath);
        }
    }

    private static <T> void dfs(final Vertex<T> vertex, Set<Vertex<T>> visited) {
        for (final Connection<T> c : vertex.getConnections()) {
            final Vertex<T> curVertex = c.getVertex();
            if (visited.add(curVertex)) {
                dfs(curVertex, visited);
            }
        }
    }

}
