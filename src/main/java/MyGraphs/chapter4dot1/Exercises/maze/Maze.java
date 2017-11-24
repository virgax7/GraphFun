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
        initializeLocations();
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
            } else {
                if (directionProber.getNextCoordinate(curLocation).getX() != prevXyCoordinate.getX()
                        || directionProber.getNextCoordinate(curLocation).getY() != prevXyCoordinate.getY()) {
                    directionProber.closeDirection(curLocation);
                }
            }
        }
    }

    private void initializeLocations() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                locations[i][j] = new Location(new XyCoordinate(i, j));
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder maze = new StringBuilder();
        IntStream.range(0, n).forEach($ -> maze.append("....."));
        maze.append("\n");

        for (int i = 0; i < n; i++) {
            final StringBuilder leftWall = new StringBuilder();
            final StringBuilder downWall = new StringBuilder();
            fillLeftAndDownWalls(locations[i], leftWall, downWall);
            maze.append(leftWall.toString() + "|\n");
            maze.append(downWall.toString() + "\n");
        }
        return maze.toString();
    }

    private void fillLeftAndDownWalls(final Location[] location, final StringBuilder leftWall, final StringBuilder downWall) {
        for (int j = 0; j < n; j++) {
            final Location curLocation = location[j];
            leftWall.append(curLocation.isLeftClosed() ? "|    " : "     ");
            downWall.append(curLocation.isDownClosed() ? "....." : "     ");
        }
    }

}
