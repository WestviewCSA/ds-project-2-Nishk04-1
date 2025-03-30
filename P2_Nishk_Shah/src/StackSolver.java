import java.util.Stack;

public class StackSolver {
    private Map maze;
    private boolean found;
    private int[] endCoord = new int[2]; //Index of the WV Buck

    public StackSolver(Map maze) {
        this.maze = maze;
        this.found = false;
        this.endCoord[0] = 0;
        this.endCoord[1] = 0;
    }

    public void solve() {
        if (maze == null) {
            System.out.println("No map found.");
            return;
        }

        long startTime = System.nanoTime();
        Tile start = maze.getStartTile();
        Stack<Tile> stack = new Stack<>();
        stack.add(start);
        start.setVisited(true);
        start.setParent(null); // Start has no parent

        while (!stack.isEmpty()) {
            Tile currTile = stack.pop();

            if (currTile.getType() == '$') {
                System.out.println("Found the Diamond Wolverine Buck!");
                endCoord[0] = currTile.getRow();
                endCoord[1] = currTile.getCol();
                System.out.println("End coordinates: " + endCoord[0] + ", " + endCoord[1]);
                found = true;
                tracePath(currTile); // Backtrack
                break;
            }

            for (Tile neighbor : maze.getNeighbors(maze, currTile)) {
                if ((neighbor.getType() == '.' || neighbor.getType() == 'W' || neighbor.getType() == '$') && !neighbor.getVisited()) {
                    neighbor.setVisited(true);
                    neighbor.setParent(currTile.getLocation());
                    neighbor.setAction(getAction(currTile, neighbor)); 
                    stack.push(neighbor);
                }
            }
        }

        if (!found) {
            System.out.println("The Wolverine Store is closed.");
        } else {
            System.out.println(maze.returnMaze());
        }

        long endTime = System.nanoTime();
        System.out.println("Runtime: " + (endTime - startTime) + " nanoseconds");
    }

    // It finds the action taken to move from parent to child
    private int[] getAction(Tile parent, Tile child) {
        return new int[]{child.getRow() - parent.getRow(), child.getCol() - parent.getCol()};
    }

    // It traces back to the parent tile (kind of like LinkedLists with nodes except implemented slightly differently)
    private void tracePath(Tile endTile) {
        Tile current = endTile;

        while (current.getParent() != null) {
            int[] parentCoords = current.getParent();
            Tile parentTile = maze.getTile(parentCoords[0], parentCoords[1], current.getLevel());
        
            if (current.getType() != '$' && current.getType() != 'W') {
                current.setType('+');
            }
            current = parentTile;
        }
    }

}
