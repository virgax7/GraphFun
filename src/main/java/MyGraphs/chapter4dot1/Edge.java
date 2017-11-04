package MyGraphs.chapter4dot1;

public class Edge {
    private final int weight;

    public Edge(final int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return weight + "";
    }
}
