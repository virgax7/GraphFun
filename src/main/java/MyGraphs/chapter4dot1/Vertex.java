package MyGraphs.chapter4dot1;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
    private final List<Connection<T>> connections = new ArrayList<>();
    private final int primaryKey;
    private final T data;

    public Vertex(final T data, Graph<T> graph) {
        this.data = data;
        graph.addVertex(this);
        this.primaryKey = graph.getPrimaryKey();
    }

    public void connectVertex(final Vertex<T> vertex, final Edge e) {
        connections.add(new Connection<T>(vertex, e));
    }

    public T getData() {
        return data;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public List<Connection<T>> getConnections() {
        return connections;
    }

    @Override
    public int hashCode() {
        return primaryKey;
    }

    @Override
    public boolean equals(Object obj) {
       if (obj == null || !(obj instanceof Vertex)) {
           return false;
       }
       return obj == this || ((Vertex) obj).getPrimaryKey() == getPrimaryKey();
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
