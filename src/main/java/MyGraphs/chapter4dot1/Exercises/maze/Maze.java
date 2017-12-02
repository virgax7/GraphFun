package MyGraphs.chapter4dot1.Exercises.maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static MyGraphs.chapter4dot1.Exercises.maze.DirectionProber.*;

class Maze {
    final Location[][] locations;
    final int n;

    Maze(final int n) {
        locations = new Location[n][n];
        this.n = n;
        IntStream.range(0, this.n).forEach(i -> Arrays.setAll(locations[i], j -> new Location(new XyCoordinate(i, j))));
        dfs(new XyCoordinate(0, 0), new XyCoordinate(-1, -1));
    }

    private void dfs(final XyCoordinate xyCoordinate, final XyCoordinate prevXyCoordinate) {
        final Location curLocation = locations[xyCoordinate.getX()][xyCoordinate.getY()];
        curLocation.setVisited(true);
        final List<DirectionProber> directions = new ArrayList<>(Arrays.asList(LEFT_PROBER, RIGHT_PROBER, UP_PROBER, DOWN_PROBER));
        Collections.shuffle(directions);

        for (final DirectionProber directionProber : directions) {
            if (directionProber.directionIsValid(locations, curLocation)) {
                dfs(directionProber.getNextCoordinate(curLocation), xyCoordinate);
            } else if (directionProber.getNextCoordinate(curLocation).getX() != prevXyCoordinate.getX()
                        || directionProber.getNextCoordinate(curLocation).getY() != prevXyCoordinate.getY()) {
                    directionProber.closeDirection(curLocation);
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder maze = new StringBuilder();
        IntStream.range(0, n).forEach($ -> maze.append("....."));
        maze.append("\n");
        IntStream.range(0, n).forEach(i -> fillLeftAndDownWalls(locations[i], new StringBuilder(), new StringBuilder(), maze));
        return maze.toString();
    }

    private void fillLeftAndDownWalls(final Location[] location, final StringBuilder leftWall, final StringBuilder downWall, final StringBuilder maze) {
        IntStream.range(0, n).boxed().map(i -> location[i]).forEach(curLocation -> {
            leftWall.append(curLocation.isLeftClosed() ? "|    " : "     ");
            downWall.append(curLocation.isDownClosed() ? "....." : "     ");
        });
        maze.append(leftWall.toString() + "|\n");
        maze.append(downWall.toString() + "\n");
    }

}
