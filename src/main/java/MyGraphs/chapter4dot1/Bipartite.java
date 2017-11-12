package MyGraphs.chapter4dot1;

import java.util.*;

public class Bipartite {
    public static void main(String[] args) {
        Graph<Integer> graph;
        while (!isBipartite(graph = GraphGenerator.getTinyIntegerGraph()));
        GraphPrinter.printGraph(graph);
    }

    private static <T> boolean isBipartite(final Graph<T> graph) {
        final Set<String> visited = new HashSet<>();
        final Map<String, Boolean> colorMap = new HashMap<>();

        for (final Vertex<T> vertex : graph.getVerticesList()) {
            if (visited.add(vertex.getUuid())) {
                colorMap.put(vertex.getUuid(), true);
                if (hasOddCycle(vertex, visited, colorMap)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static <T> boolean hasOddCycle(final Vertex<T> vertex, final Set<String> visited, final Map<String, Boolean> colorMap) {
       for (final Connection<T> c : vertex.getConnections()) {
            final Vertex<T> adjVertex = c.getVertex();
            if (visited.add(adjVertex.getUuid())) {
                colorMap.put(adjVertex.getUuid(), !colorMap.get(vertex.getUuid()));
                if (hasOddCycle(adjVertex, visited, colorMap)) {
                    return true;
                }
            } else if (colorMap.get(adjVertex.getUuid()) == colorMap.get(vertex.getUuid())) {
               return true;
            }
       }
       return false;
    }


}
