package MyGraphs.chapter4dot1;

import org.apache.commons.lang3.mutable.MutableInt;

import java.util.HashMap;
import java.util.Map;

public class BiConnected {

    public static void main(String[] args) {
        Graph<Integer> graph = GraphGenerator.getTinyIntegerGraph();
        printArticulationPoints(graph);
        GraphPrinter.printGraphWithOnlyData(graph);
    }

    private static <T> void printArticulationPoints(Graph<T> graph) {
        final Map<String, Integer> pre = new HashMap<>();
        final Map<String, Integer> low = new HashMap<>();
        final MutableInt count = new MutableInt(0);
        graph.getVerticesList().stream()
                .forEach(vertex -> {pre.put(vertex.getUuid(), -1); low.put(vertex.getUuid(), -1); });

        for (final Vertex<T> vertex : graph.getVerticesList()) {
            if (pre.get(vertex.getUuid()) == -1) {
                dfs(vertex, vertex, count, pre, low);
            }
        }
    }

    private static <T> void dfs(final Vertex<T> prevVertex, final Vertex<T> curVertex, final MutableInt count,
                                final Map<String, Integer> pre, final Map<String, Integer> low) {
        int children = 0;
        pre.put(curVertex.getUuid(), count.addAndGet(1));
        low.put(curVertex.getUuid(), count.getValue());

        for (final Vertex<T> adjVertex : curVertex.getConnectedVertices()) {
            if (pre.get(adjVertex.getUuid()) == -1) {
               children++;
               dfs(curVertex, adjVertex, count, pre, low);
               putMinValue(low, low, curVertex, adjVertex);
               if (!prevVertex.equals(curVertex) && low.get(adjVertex.getUuid()) >= pre.get(curVertex.getUuid())) {
                   System.out.println(curVertex.printData() + " is an articulation point");
               }
            } else if (!adjVertex.equals(prevVertex)) {
               putMinValue(pre, low, curVertex, adjVertex);
            }
        }
        if (prevVertex.equals(curVertex) && children > 1) {
            System.out.println(curVertex.printData() + " is an articulation point");
        }
    }

    private static <T> void putMinValue(final Map<String, Integer> map1, final Map<String, Integer> map2, final Vertex<T> curVertex, final Vertex<T> adjVertex) {
        map2.put(curVertex.getUuid(), map2.get(curVertex.getUuid()) < map1.get(adjVertex.getUuid())
                ? map2.get(curVertex.getUuid())
                : map1.get(adjVertex.getUuid()));
    }
}
