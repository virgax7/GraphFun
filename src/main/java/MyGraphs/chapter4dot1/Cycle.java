package MyGraphs.chapter4dot1;

import java.util.*;

public class Cycle {
    public static void main(String[] args) {
        final Deque<Vertex<Integer>> stack = new ArrayDeque<>();
        Graph<Integer> graph = GraphGenerator.getTinyIntegerGraph();
        System.out.println(hasCycle(graph, stack) ? "yes" : "no");
    }

    public static <T> boolean hasCycle(final Graph<T> graph, final Deque<Vertex<T>> stack) {
        if (hasSelfLoop(graph, stack) || hasParallelEdge(graph, stack)) {
            return true;
        }
        final Set<Integer> visited = new HashSet<>();
        final Map<Integer, Integer> edgeMap = new LinkedHashMap<>();
        for (final Vertex<T> vertex : graph.getVertices()) {
            if (visited.add(vertex.getPrimaryKey())) {
                if (dfs(edgeMap, visited, vertex, null, stack)) {
                    System.out.println(edgeMap);
                    return true;
                }
            }
        }
        return false;
    }

    private static <T> boolean dfs(Map<Integer, Integer> edgeMap, Set<Integer> visited, Vertex<T> curVertex, Vertex<T> prevVertex, Deque<Vertex<T>> stack) {
        visited.add(curVertex.getPrimaryKey());
        for (final Connection<T> c : curVertex.getConnections()) {
            final Vertex<T> adjVertex = c.getVertex();
            if (visited.add(adjVertex.getPrimaryKey())) {
               edgeMap.put(adjVertex.getPrimaryKey(), curVertex.getPrimaryKey());
               if (dfs(edgeMap, visited, adjVertex, curVertex, stack)) {
                   return true;
               }
            } else if (!adjVertex.equals(prevVertex)) {
                edgeMap.put(adjVertex.getPrimaryKey(), curVertex.getPrimaryKey());
                return true;
            }
        }
        return false;
    }

    private static <T> boolean hasSelfLoop(final Graph<T> graph, final Deque<Vertex<T>> stack) {
        for (Vertex<T> vertex : graph.getVertices()) {
           if (vertex.getConnections().contains(vertex)) {
               stack.add(vertex);
               stack.add(vertex);
               return true;
           }
        }
        return false;
    }

    private static <T> boolean hasParallelEdge(Graph<T> graph, Deque<Vertex<T>> stack) {
       for (final Vertex<T> vertex : graph.getVertices()) {
           final Set<Vertex<T>> set = new HashSet<>();
           for (Connection<T> c : vertex.getConnections()) {
               if (!set.add(c.getVertex())) {
                   stack.add(c.getVertex());
                   stack.add(vertex);
                   stack.add(c.getVertex());
               }
           }
       }
       return false;
    }
}
