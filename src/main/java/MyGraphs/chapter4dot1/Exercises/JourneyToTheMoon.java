package MyGraphs.chapter4dot1.Exercises;

import java.util.*;
import java.util.stream.IntStream;

public class JourneyToTheMoon {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final List<Integer>[] graph = IntStream.range(0, in.nextInt()).boxed()
                .map(vertex -> new ArrayList<>(Arrays.asList(vertex))).toArray(List[]::new);
        IntStream.range(0, in.nextInt()).boxed().map($ -> new int[] {in.nextInt(), in.nextInt()})
                .forEach(cities -> {graph[cities[0]].add(cities[1]); graph[cities[1]].add(cities[0]);});
        final Set<Integer> visited = new HashSet<>();
        final List<Integer> subGraphSizes = new ArrayList<>();
        for (int j = 1; j < graph.length; j++) {
            if (!visited.contains(j)) {
                final Deque<Integer> stack = new ArrayDeque<>();
                stack.add(j);
                int subGraphSize = 0;
                while (!stack.isEmpty()) {
                    final int curVertex = stack.removeLast();
                    if (visited.add(curVertex)) {
                        subGraphSize++;
                        graph[curVertex].stream().forEach(connectedVertex -> stack.add(connectedVertex));
                    }
                }
                subGraphSizes.add(subGraphSize);
            }
        }
        long result = 0;
        int astronautsTaken = 0;
        for(final int subGraphSize : subGraphSizes) {
            result += subGraphSize * (graph.length - (astronautsTaken += subGraphSize));
        }
        System.out.println(result);
    }
}
