package MyGraphs.chapter4dot1;

import org.apache.commons.lang3.mutable.MutableInt;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Bridge {

    public static void main(String[] args) {
        Graph<Integer> graph;
        while (hasParallelEdges(graph = GraphGenerator.getTinyIntegerGraph()));
        printBridges(graph);
        GraphPrinter.printGraphWithOnlyData(graph);
    }

    public static <T> boolean hasParallelEdges(final Graph<T> graph) {
        return graph.getVerticesList().stream()
                .map(vertex -> vertex.getConnectedVertices())
                .anyMatch(connectedVertices -> connectedVertices.stream()
                        .anyMatch(tVertex -> Collections.frequency(connectedVertices, tVertex) > 1)
                );
    }

    public static <T> void printBridges(final Graph<T> graph) {
        final Map<String, Integer> pre = new HashMap<>();
        final Map<String, Integer> low = new HashMap<>();
        final MutableInt count = new MutableInt(0);
        graph.getVerticesList().stream()
                .forEach(vertex -> {pre.put(vertex.getUuid(), -1); low.put(vertex.getUuid(), -1);});

        for (final Vertex<T> vertex : graph.getVerticesList()) {
            if (pre.get(vertex.getUuid()) == -1) {
                dfs(vertex, vertex, count, pre, low);
            }
        }
    }

   private static <T> void dfs(final Vertex<T> prevVertex, final Vertex<T> curVertex, final MutableInt count,
                               final Map<String, Integer> pre, final Map<String, Integer> low) {
        pre.put(curVertex.getUuid(), count.addAndGet(1));
        low.put(curVertex.getUuid(), count.getValue());

        for (final Connection<T> c : curVertex.getConnections()) {
           final Vertex<T> adjVertex = c.getVertex();
           if (pre.get(adjVertex.getUuid()) == -1) {
              dfs(curVertex, adjVertex, count, pre, low);
              low.put(curVertex.getUuid(), low.get(curVertex.getUuid()) < low.get(adjVertex.getUuid())
                      ? low.get(curVertex.getUuid())
                      : low.get(adjVertex.getUuid()));
              if (low.get(adjVertex.getUuid()) == pre.get(adjVertex.getUuid())) {
                  System.out.println("bridge exists between " + curVertex.printData() + " and " + adjVertex.printData());
              }
           } else if (!adjVertex.equals(prevVertex)) {
               low.put(curVertex.getUuid(), low.get(curVertex.getUuid()) < pre.get(adjVertex.getUuid())
                   ? low.get(curVertex.getUuid())
                   : pre.get(adjVertex.getUuid()));
           }
        }
   }

}
