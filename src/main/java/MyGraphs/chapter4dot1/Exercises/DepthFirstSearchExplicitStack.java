package MyGraphs.chapter4dot1.Exercises;

import MyGraphs.chapter4dot1.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.Set;

public class DepthFirstSearchExplicitStack {
    public static void main(String[] args) {
        System.out.println("Now running dfs");
        final Graph<Integer> graph = GraphGenerator.getTinyIntegerGraph();
        dfs(graph);
        GraphPrinter.printGraph(graph);
    }

    public static <T> void dfs(final Graph<T> graph) {
        for (final Vertex<T> vertex : graph.getVerticesList()) {
            final Set<Vertex<T>> connectedPath = new LinkedHashSet<Vertex<T>>();
            dfs(vertex, connectedPath);
            System.out.println(connectedPath);
        }
    }

    private static <T> void dfs(final Vertex<T> vertex, Set<Vertex<T>> visited) {
        final Deque<Vertex<T>> deque = new ArrayDeque<>();
        deque.add(vertex);
        while (!deque.isEmpty()) {
            final Vertex<T> curVertex = deque.removeLast();
            if (visited.add(curVertex)) {
               for (final Vertex<T> adjVertex : curVertex.getConnectedVertices()) {
                   deque.add(adjVertex);
               }
            }
        }
    }
}
