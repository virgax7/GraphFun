package MyGraphs.chapter4dot1;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class Vertex<T> {
    private final String uuid;
    private List<Connection> connections = new ArrayList<>();
    private final T data;

    public Vertex(String uuid, T data) {
        this.uuid = uuid;
        this.data = data;
    }

    public void connectVertex(Vertex vertex, Edge e) {
        connections.add(new Connection(vertex, e));
    }

    public String getUuid() {
        return uuid;
    }

    public T getData() {
        return data;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(uuid).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof  Vertex)) {
            return false;
        }
        return obj == this || ((Vertex) obj).uuid == this.uuid;
    }
}
