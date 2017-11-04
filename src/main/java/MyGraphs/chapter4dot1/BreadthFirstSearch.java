package MyGraphs.chapter4dot1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.Set;

public class BreadthFirstSearch {

    public static void main(String[] args) {
        System.out.println("Now running bfs");
        final Graph<Integer> graph = GraphGenerator.getTinyIntegerGraph();
        bfs(graph);
        GraphPrinter.printGraph(graph);
    }

    private static <T> void bfs(final Graph<T> graph) {
        for (final Vertex<T> vertex : graph.getVertices()) {
            final Set<Vertex<T>> connectedPath = new LinkedHashSet<Vertex<T>>(){{add(vertex);}};
            final Deque<Vertex<T>> deque = new ArrayDeque<Vertex<T>>(){{addLast(vertex);}};
            bfs(connectedPath, deque);
            System.out.println(connectedPath);
        }
    }

    private static <T> void bfs(final Set<Vertex<T>> visited, final  Deque<Vertex<T>> deque) {
        while (!deque.isEmpty()) {
           final Vertex<T> vertex = deque.removeFirst();
           for (final Connection c : vertex.getConnections()) {
               final Vertex<T> curVertex = c.getVertex();
               if (visited.add(curVertex)) {
                    deque.addLast(curVertex);
               }
           }
        }
    }

}
