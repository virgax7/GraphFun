package MyGraphs.chapter4dot1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class BreadthFirstPathsWeighted<T> extends BreadthFirstPaths<T> {

    public BreadthFirstPathsWeighted(final Vertex<T> source) {
        super(source);
    }

    @Override
    protected void bfs(final Vertex<T> source) {
        final Map<Vertex<T>, Integer> minMap = new HashMap<>();
        marked.add(source);
        minMap.put(source, 0);
        final Deque<Vertex<T>> deque = new ArrayDeque<>();
        deque.add(source);
        while (!deque.isEmpty()) {
            final Vertex<T> curVertex = deque.removeFirst();
            for (final Connection<T> connection : curVertex.getConnections()) {
                final Vertex<T> adjVertex = connection.getVertex();
                if (marked.add(adjVertex)) {
                    updateMaps(deque, curVertex, connection, adjVertex, minMap);
                } else if (minMap.get(adjVertex) > minMap.get(curVertex) + connection.getEdge().getWeight()) {
                    updateMaps(deque, curVertex, connection, adjVertex, minMap);
                }
            }
        }
    }

    private void updateMaps(final Deque<Vertex<T>> deque, final Vertex<T> curVertex, final Connection<T> connection,
                            final Vertex<T> adjVertex, final  Map<Vertex<T>, Integer> minMap) {
        edgeTo.put(adjVertex, curVertex);
        minMap.put(adjVertex, minMap.get(curVertex) + connection.getEdge().getWeight());
        deque.add(adjVertex);
    }
}
