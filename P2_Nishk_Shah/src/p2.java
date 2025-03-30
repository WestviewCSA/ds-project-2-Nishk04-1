import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;

public class p2 {
	static Map maze;
	static MazeReader mazeReader;
	public static void main(String[] args) {
		// TODO - create the exception stuff
		boolean Stack = false;
        boolean Queue = false;
        boolean Opt = false;
        boolean Time = false;
        boolean Incoordinate = false;
        boolean Outcoordinate = false;
        boolean Help = false;
        String fileName = "";

		for (String arg : args) {
			switch (arg) {
				case "--Stack":
					Stack = true;
					break;
				case "--Queue":
					Queue = true;
					break;
				case "--Opt":
					Opt = true;
					break;
				case "--Time":
					Time = true;
					break;
				case "--Incoordinate":
					Incoordinate = true;
					break;
				case "--Outcoordinate":
					Outcoordinate = true;
					break;
				case "--Help":
					Help = true;
					break;
			}
		}

		 // Define the filename
		 String filename = "P2_Nishk_Shah\\TEST\\Test06"; // Adjust path if needed
		 boolean isCoordinateBased = false; // Decide format based on input flag
 
		 mazeReader = new MazeReader();
		 mazeReader.readMaze(filename, isCoordinateBased);
		 maze = mazeReader.getMaze();
 
		 System.out.println(maze.returnMaze());
 
		 StackSolver solver = new StackSolver(maze);
         solver.solve();
		
	}
	
	public static void stackSolver() {
		if(maze == null) {
			System.out.println("No map found");
			System.exit(-1);
		}
		long startTime = System.nanoTime();

		Tile start = maze.getStartTile();
		Stack<Tile> stack = new Stack<Tile>();
		stack.add(start);
		start.setVisited(true);
		
		boolean found = false;

		// add the next stuff to the stack until we reach 'w'
		while(!stack.isEmpty() ) {
			// Gets the tile that we need to process first
			Tile currTile = stack.pop();

			if (currTile.getType() == '$') {
				System.out.println("Found the Diamond Wolverine Buck!");
				found = true;
				break;
			}

			if(currTile.getType() != 'W'){
				currTile.setType('+');
			}	
			for (Tile neighbor : maze.getNeighbors(maze, currTile)) {
				if ((neighbor.getType() == '.' || neighbor.getType() == 'W'|| neighbor.getType() == '$') && !neighbor.getVisited()) {
					neighbor.setVisited(true); // Mark as visited before pushing
					stack.push(neighbor);
				}
			}
	

		}	
		if (!found) {
            System.out.println("The Wolverine Store is closed.");
        } else {
            String completedMaze = maze.returnMaze();
            System.out.println(completedMaze);
        }

        // Measure the runtime
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  // Convert to milliseconds
        System.out.println("Runtime: " + duration + " nanoseconds");
	}
}
