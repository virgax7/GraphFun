package MyGraphs.chapter4dot1;

import java.util.Random;

public class GraphGenerator {
    public static Graph getTinyIntegerGraph() {
        final Graph<Integer> tinyGraph = new Graph<>();
        for (int i = 0; i < 12; i++) {
            tinyGraph.addVertex(new Vertex(i, tinyGraph));
        }
        for (int i = 0; i < 16; i++) {
            final Random random = new Random();
            tinyGraph.connectVertices(tinyGraph.getVertex(random.nextInt(12)), tinyGraph.getVertex(random.nextInt(12)), new Edge(1));
        }
        return tinyGraph;
    }
}
