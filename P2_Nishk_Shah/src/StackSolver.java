import java.util.Stack;

public class StackSolver {
    private Map maze;
    private boolean found;
    private int[] endCoord = new int[2]; //Index of the WV Buck
    private boolean isCoordinateBased; // This can be used to determine if the input is coordinate based or not
 
    //Uses DFS + Stacks
    public StackSolver(Map maze, boolean isCoordinateBased) {
        this.maze = maze;
        this.found = false;
        this.endCoord[0] = 0;
        this.endCoord[1] = 0;
        this.isCoordinateBased = isCoordinateBased;
    }

    public void solve() {
        if (maze == null) {
            System.out.println("No map found.");
            return;
        }
    
        Tile start = maze.getStartTile();
        Stack<Tile> stack = new Stack<>();
        stack.add(start);
        start.setVisited(true);
        start.setParent(null); // Start has no parent
    
        while (!stack.isEmpty()) {
            Tile currTile = stack.pop();
    
            // If we find the destination, trace the path and stop
            if (currTile.getType() == '$') {
                System.out.println("Found the Diamond Wolverine Buck!");
                endCoord[0] = currTile.getRow();
                endCoord[1] = currTile.getCol();
                //System.out.println("End coordinates: " + endCoord[0] + ", " + endCoord[1]);
                found = true;
                tracePath(currTile); // Backtrack
                break;
            }
    
            // Explore neighbors
            for (Tile neighbor : maze.getNeighbors(maze, currTile)) {
                // Explore '.' (open path), 'W' (start), and '$' (destination)
                if ((neighbor.getType() == '.' || neighbor.getType() == 'W' || neighbor.getType() == '$') && !neighbor.getVisited()) {
                    neighbor.setVisited(true);
                    neighbor.setParent(currTile.getLocation()); // Set parent for backtracking
                    neighbor.setAction(getAction(currTile, neighbor)); 
                    stack.push(neighbor);
                }
            }
        }
    
        // If no solution is found, print a message
        if (!found) {
            System.out.println("The Wolverine Store is closed.");
        } else {
            // Print the maze with the path marked
            System.out.println(maze.returnMaze(isCoordinateBased)); 
        }
    }
    

    // It finds the action taken to move from parent to child
    private int[] getAction(Tile parent, Tile child) {
        return new int[]{child.getRow() - parent.getRow(), child.getCol() - parent.getCol()};
    }

    // It traces back to the parent tile (kind of like LinkedLists with nodes except implemented slightly differently)
    private void tracePath(Tile endTile) {
        Tile current = endTile;
    
        // Backtrack to the starting point
        while (current.getParent() != null) {
            // Get the parent tile
            int[] parentCoords = current.getParent();
            Tile parentTile = maze.getTile(parentCoords[0], parentCoords[1], current.getLevel());
    
            // Mark the path with '+'
            if (current.getType() != '$' && current.getType() != 'W' && current.getType() != '|') {
                current.setType('+');
            }
    
            // Move to the parent tile to continue backtracking
            current = parentTile;
        }
    }
    

}
