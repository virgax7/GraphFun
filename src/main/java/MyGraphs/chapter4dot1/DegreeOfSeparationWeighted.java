package MyGraphs.chapter4dot1;

import java.util.Random;

public class DegreeOfSeparationWeighted {
    public static void main(String[] args) {
        final Graph<Integer> graph = GraphGenerator.getTinyIntegerGraph();
        final Vertex<Integer> source = graph.getVerticesList().get((new Random()).nextInt(graph.getVerticesList().size()));
        System.out.println("source is " + source);
        queryFiveRandomConnections(new BreadthFirstPathsWeighted<>(source), graph);
        GraphPrinter.printGraph(graph);
    }

    public static <T> void queryFiveRandomConnections(final BreadthFirstPaths<T> breadthFirstPaths, final Graph<T> graph) {
        for (int i = 0; i < 5; i++) {
            final Vertex<T> destination = graph.getVerticesList().get((new Random()).nextInt(graph.getVerticesList().size()));
            System.out.println("destination is " + destination);
            if (breadthFirstPaths.hasPathTo(destination)) {
                breadthFirstPaths.getPathTo(destination).stream().forEach(vertex -> System.out.println("\t" + vertex));
            } else {
                System.out.println("Not connected\n");
            }
        }
    }
}
