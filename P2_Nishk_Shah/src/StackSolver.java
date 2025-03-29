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

        while (!stack.isEmpty()) {
            Tile currTile = stack.pop();

            if (currTile.getType() == '$') {
                System.out.println("Found the Diamond Wolverine Buck!");
                endCoord[0] = currTile.getRow();
                endCoord[1] = currTile.getCol();
                System.out.println("End coordinates: " + endCoord[0] + ", " + endCoord[1]);
                found = true;
                break;
            }

            if (currTile.getType() != 'W') {
                currTile.setType('+');
            }

            for (Tile neighbor : maze.getNeighbors(maze, currTile)) {
                if ((neighbor.getType() == '.' || neighbor.getType() == 'W' || neighbor.getType() == '$') && !neighbor.getVisited()) {
                    neighbor.setVisited(true);
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
}
