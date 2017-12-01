package MyGraphs.chapter4dot1;

import java.util.Random;
import java.util.stream.IntStream;

public class GraphGenerator {

    public static Graph getTinyIntegerGraph() {
        final Graph<Integer> tinyGraph = new Graph<>();
        IntStream.range(0, 12).forEach(i -> tinyGraph.addVertex(new Vertex<>(i)));

        for (int i = 0; i < 16; i++) {
            final Random random = new Random();
            final Object[] values = tinyGraph.getVertices().values().toArray();
            final Object randomValue1 = values[random.nextInt(values.length)];
            final Object randomValue2 = values[random.nextInt(values.length)];
            tinyGraph.connectVertices((Vertex<Integer>) randomValue1, (Vertex<Integer>)  randomValue2, new Edge(1));
        }

        return tinyGraph;
    }

}
