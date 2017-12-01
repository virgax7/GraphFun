package MyGraphs.chapter4dot1.Exercises;

import java.util.*;
import java.util.stream.IntStream;

public class RoadsAndLibraries {
    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        final Scanner in = new Scanner(System.in);
        final int queries = in.nextInt();
        for(int i = 0; i < queries; i++){
            final int numberOfCities = in.nextInt();
            final int numberOfRoads = in.nextInt();
            final long libraryCost = in.nextLong();
            final long roadCost = in.nextLong();
            final List<Integer>[] graph = IntStream.range(0, numberOfCities + 1)
                    .boxed().map(vertex -> new ArrayList<>(Arrays.asList(vertex))).toArray(List[]::new);
            for(int j = 0; j < numberOfRoads; j++) {
                final int city1 = in.nextInt();
                final int city2 = in.nextInt();
                graph[city1].add(city2);
                graph[city2].add(city1);
            }
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
