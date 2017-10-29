package MyGraphs.chapter4dot1;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Graph<T> {
    private Map<String, Vertex> vertices = new HashMap<>();

    public Graph(T vertexData) {
        addVertex(new Vertex(UUID.randomUUID().toString(), vertexData));
    }

    public Map<String, Vertex> getVertices() {
        return vertices;
    }

    public void addVertex(Vertex vertex) {
       vertices.put(vertex.getUuid(), vertex);
    }

    public void connectVertices(Vertex u, Vertex v, Edge e) {
        u.connectVertex(v, e);
        v.connectVertex(u, e);
    }

    public Vertex getVertex(String uuid) {
        return vertices.get(uuid);
    }

}
