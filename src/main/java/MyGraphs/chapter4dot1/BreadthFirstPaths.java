package MyGraphs.chapter4dot1;

import java.util.*;
import java.util.stream.Collectors;

// all edges have uniform weight
public class BreadthFirstPaths <T> {
    final Map<Vertex<T>, Boolean> marked = new HashMap<>();
    final Map<Vertex<T>, Vertex<T>> edgeTo = new HashMap<>();
    final Vertex<T> source;

    public BreadthFirstPaths(final Vertex<T> source) {
        this.source = source;
        bfs(source);
    }

    private void bfs(final Vertex<T> source) {
        final Deque<Vertex<T>> deque = new ArrayDeque<>();
        deque.add(source);
        while (!deque.isEmpty()) {
           final Vertex<T> vertex = deque.removeFirst();
           for (final Vertex<T> adjVertex : vertex.getConnectedVertices()) {
               if (marked.get(adjVertex) == null) {
                    edgeTo.put(adjVertex, vertex);
                    marked.put(adjVertex, true);
                    deque.addLast(adjVertex);
               }
           }
        }
    }

    public boolean hasPathTo(final Vertex<T> destination) {
       return marked.get(destination) != null;
    }

    public List<Vertex<T>> getPathTo(final Vertex<T> destination) {
       final Deque<Vertex<T>> path = new ArrayDeque<>();
       for (Vertex<T> curVertex = destination; !curVertex.equals(source); curVertex = edgeTo.get(curVertex)) {
            path.addFirst(curVertex);
       }
       path.addFirst(source);
       return path.stream().collect(Collectors.toList());
    }
}
