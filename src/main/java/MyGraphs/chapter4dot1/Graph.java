package MyGraphs.chapter4dot1;

import java.util.*;
import java.util.stream.Collectors;

public class Graph<T> {
    private final Map<String, Vertex<T>> vertices = new LinkedHashMap<>();

    public Map<String, Vertex<T>> getVertices() {
        return vertices;
    }

    public List<Vertex<T>> getVerticesList() {
        return vertices.entrySet().stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    public void addVertex(final Vertex<T> vertex) {
        vertices.put(vertex.getUuid(), vertex);
    }

    public void connectVertices(final Vertex<T> u, final  Vertex<T> v, final Edge e) {
        u.connectVertex(v, e);
        v.connectVertex(u, e);
    }

    public Vertex<T> getVertex(final String uuid) {
        return vertices.get(uuid);
    }

    public boolean hasParallelEdge(){
        return getVerticesList().stream()
                .map(vertex -> vertex.getConnectedVertices())
                .anyMatch(connectedVertices -> connectedVertices.stream()
                        .anyMatch(tVertex -> Collections.frequency(connectedVertices, tVertex) > 1)
                );
    }
}
