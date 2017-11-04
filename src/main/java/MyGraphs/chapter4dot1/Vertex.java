package MyGraphs.chapter4dot1;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
    private final List<Connection> connections = new ArrayList<>();
    private final T data;

    public Vertex(final T data) {
        this.data = data;
    }

    public void connectVertex(final Vertex vertex, final Edge e) {
        connections.add(new Connection(vertex, e));
    }

    public T getData() {
        return data;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
