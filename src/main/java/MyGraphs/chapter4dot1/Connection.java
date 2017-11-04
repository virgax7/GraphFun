package MyGraphs.chapter4dot1;

public class Connection {
    private final Vertex vertex;
    private final Edge edge;

    public Connection(final Vertex vertex,final Edge edge) {
       this.vertex = vertex;
       this.edge = edge;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public Edge getEdge() {
        return edge;
    }

    public String getConnectedVertex() {
        return vertex + "";
    }

    @Override
    public String toString() {
        return "[" + vertex + ", " + edge + "]";
    }

}
