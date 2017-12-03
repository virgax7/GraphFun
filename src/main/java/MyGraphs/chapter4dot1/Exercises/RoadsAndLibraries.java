package MyGraphs.chapter4dot1.Exercises;

import java.util.*;
import java.util.stream.IntStream;

public class RoadsAndLibraries {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int queries = in.nextInt();
        for(int i = 0; i < queries; i++){
            final List<Integer>[] graph = IntStream.range(0, in.nextInt() + 1)
                    .mapToObj(vertex -> new ArrayList<>(Arrays.asList(vertex))).toArray(List[]::new);
            final int numberOfRoads = in.nextInt();
            final long libraryCost = in.nextLong();
            final long roadCost = in.nextLong();
            IntStream.range(0, numberOfRoads).mapToObj($ -> new int[] {in.nextInt(), in.nextInt()})
                    .forEach(cities -> {graph[cities[0]].add(cities[1]); graph[cities[1]].add(cities[0]);});
            final Set<Integer> visited = new HashSet<>();
            long result = 0;
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
                  result += libraryCost + (libraryCost < roadCost ? libraryCost * (subGraphSize - 1) : roadCost * (subGraphSize - 1));
               }
            }
            System.out.println(result);
        }
    }
}
