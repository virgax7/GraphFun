package MyGraphs.chapter4dot1;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private final String uuid;
    private List<Connection> connections = new ArrayList<>();

    public Vertex(String uuid) {
        this.uuid = uuid;
    }

    public void connectVertex(Vertex vertex, Edge e) {
        connections.add(new Connection(vertex, e));
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof  Vertex)) {
            return false;
        }
        return obj == this || ((Vertex) obj).uuid == this.uuid;
    }
}
