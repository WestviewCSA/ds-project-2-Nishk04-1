import java.util.LinkedList;

public class OptimalSolver {
    private Map maze;
    private boolean found;
    private int[] endCoord = new int[2]; // Coordinates of the goal
    private boolean isCoordinateBased;

    public OptimalSolver(Map maze, boolean isCoordinateBased) {
        this.maze = maze;
        this.found = false;
        this.endCoord[0] = 0;
        this.endCoord[1] = 0;
        this.isCoordinateBased = isCoordinateBased;
    }

    // Solve method
    public void solve() {
        if (maze == null) {
            System.out.println("No map found.");
            return;
        }

        Tile start = maze.getStartTile();
        LinkedList<Tile> openList = new LinkedList<>();


        start.setVisited(true);
        start.setCost(0); // Start with 0 cost
        start.setParent(null);
        openList.add(start);

        while (!openList.isEmpty()) {
            // Get the tile with the lowest cost
            Tile currTile = getLowestCostTile(openList);
            openList.remove(currTile);

            if (currTile.getType() == '$') {
                System.out.println("Found the Diamond Wolverine Buck!");
                endCoord[0] = currTile.getRow();
                endCoord[1] = currTile.getCol();
                found = true;
                tracePath(currTile); // Backtrack to reconstruct the path
                break;
            }

           
            for (Tile neighbor : maze.getNeighbors(maze, currTile)) {
                if (!neighbor.getVisited()) {
                    double gCost = currTile.getCost() + 1; // Simplified cost (1 for each move)

                    // Apply the heuristic (Manhattan distance) only if the goal has been found because otherwise it wouldn't work since we don't know where the end goal is
                    double hCost = 0;
                    if (found) {
                        hCost = heuristic(neighbor, maze.getTile(endCoord[0], endCoord[1], 0));
                    }

                    neighbor.setCost(gCost + hCost); // f(n) = g(n) + h(n)
                    neighbor.setParent(new int[]{currTile.getRow(), currTile.getCol()});
                    openList.add(neighbor);
                    neighbor.setVisited(true);
                }
            }
        }

        if (!found) {
            System.out.println("The Wolverine Store is closed.");
        } else {
            System.out.println(maze.returnMaze(isCoordinateBased));
        }
    }

    private Tile getLowestCostTile(LinkedList<Tile> list) {
        Tile lowestCostTile = list.getFirst();
        for (Tile tile : list) {
            if (tile.getCost() < lowestCostTile.getCost()) {
                lowestCostTile = tile;
            }
        }
        return lowestCostTile;
    }

    // Heuristic function for A* (Manhattan distance)
    private double heuristic(Tile current, Tile goal) {
        return Math.abs(current.getRow() - goal.getRow()) + Math.abs(current.getCol() - goal.getCol());
    }

    // Backtrack the path from goal to start and mark it with '+'
    private void tracePath(Tile endTile) {
        Tile current = endTile;
        while (current.getParent() != null) {
            int[] parentCoords = current.getParent();
            Tile parentTile = maze.getTile(parentCoords[0], parentCoords[1], 0);
            if (current.getType() != '$' && current.getType() != 'S') {
                current.setType('+');
            }
            current = parentTile;
        }
    }
}
