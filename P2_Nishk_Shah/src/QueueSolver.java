
public class QueueSolver {
    private Map maze;
    private boolean found;
    private int[] endCoord = new int[2]; //Index of the WV Buck
    private boolean isCoordinateBased;

    // Uses BFS + Queues
    public QueueSolver(Map maze, boolean isCoordinateBased) {
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
        Queue<Tile> queue = new Queue<Tile>();
        queue.enqueue(start);
        start.setVisited(true);
        start.setParent(null); // Start has no parent

        while (!queue.empty()) {
            Tile currTile = queue.dequeue();

            if (currTile.getType() == '$') {
                System.out.println("Found the Diamond Wolverine Buck!");
                endCoord[0] = currTile.getRow();
                endCoord[1] = currTile.getCol();
                //System.out.println("End coordinates: " + endCoord[0] + ", " + endCoord[1]);
                found = true;
                tracePath(currTile); // Backtrack
                break;
            }

            for (Tile neighbor : maze.getNeighbors(maze, currTile)) {
                if ((neighbor.getType() == '.' || neighbor.getType() == 'W' || neighbor.getType() == '$') && !neighbor.getVisited()) {
                    neighbor.setVisited(true);
                    neighbor.setParent(currTile.getLocation());
                    neighbor.setAction(getAction(currTile, neighbor)); 
                    queue.enqueue(neighbor);
                }
            }
        }

        if (!found) {
            System.out.println("The Wolverine Store is closed.");
        } else {
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
