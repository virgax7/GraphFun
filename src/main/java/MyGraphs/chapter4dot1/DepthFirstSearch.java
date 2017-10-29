package MyGraphs.chapter4dot1;

import java.util.List;
import java.util.Map;

public class DepthFirstSearch {
    private final Graph graph;

    public DepthFirstSearch(Graph graph) {
        this.graph = graph;
    }

    public void dfs() {
        Vertex vertex = ((Map.Entry<String, Vertex>) graph.getVertices().entrySet().iterator().next()).getValue();
        for (Connection c : (List<Connection>) vertex.getConnections()) {
            
        }
    }

    public void dfs(Vertex v) {

    }
}
