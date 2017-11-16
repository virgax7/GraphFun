package MyGraphs.chapter4dot1;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Vertex<T> {
    private final String uuid;
    private final List<Connection<T>> connections = new ArrayList<>();
    private final T data;

    public Vertex(T data) {
        this.uuid = UUID.randomUUID().toString();
        this.data = data;
    }

    public void connectVertex(Vertex<T> vertex, Edge e) {
        connections.add(new Connection<T>(vertex, e));
    }

    public String getUuid() {
        return uuid;
    }

    public T getData() {
        return data;
    }

    public List<Connection<T>> getConnections() {
        return connections;
    }

    public List<Vertex<T>> getConnectedVertices() {
        return connections.stream()
                .map(connection -> connection.getVertex())
                .collect(Collectors.toList());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(uuid).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null || !(obj instanceof  Vertex)) {
            return false;
        }
        return obj == this || ((Vertex<T>) obj).uuid.equals(this.uuid);
    }

    public String printData() {
        return getData() + "";
    }

    @Override
    public String toString() {
        return "[" + getUuid().substring(0,4) + ", " + data + "]";
    }
}
