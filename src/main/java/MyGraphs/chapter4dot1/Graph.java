package MyGraphs.chapter4dot1;

import java.util.ArrayList;
import java.util.List;

public class Graph<T> {
    private int primaryKey = 0;
    private final List<Vertex<T>> vertices = new ArrayList<>();

    public int getPrimaryKey() {
        return primaryKey;
    }

    public List<Vertex<T>> getVertices() {
        return vertices;
    }

    public void addVertex(final Vertex<T> vertex) {
        primaryKey++;
        vertices.add(vertex);
    }

    public void connectVertices(final Vertex<T> u, final  Vertex<T> v, final Edge e) {
        u.connectVertex(v, e);
        v.connectVertex(u, e);
    }

    public Vertex<T> getVertex(int primaryKey) {
        return vertices.get(primaryKey);
    }

}
