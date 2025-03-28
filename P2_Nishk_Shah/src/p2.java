import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;

public class p2 {
	static Map maze;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    String filename = "P2_Nishk_Shah\\src\\TEST\\Test05";
		readMap(filename);
		String output = maze.returnMaze();
		System.out.println(output);
		stackSolver();

		
		}
	
	public static void stackSolver() {
		if(maze == null) {
			System.out.println("No map found bro");
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
        long duration = (endTime - startTime) / 1000000;  // Convert to milliseconds
        System.out.println("Runtime: " + duration + " ms");
	}

	
	public static void readMap(String filename) {
		try {
			File file = new File(filename);
			if (!file.exists()) {
				System.out.println("File not found: " + filename);
				return;
			}
			Scanner scanner = new Scanner(file);
			
			int numRows = scanner.nextInt();
			int numCols = scanner.nextInt();
			int numRooms = scanner.nextInt();
			
			//Create the map 
			maze = new Map(numRows, numCols, numRooms);

			int rowIndex = 0;
			
			//process the map!
			while(scanner.hasNextLine()) {
				String row = scanner.nextLine();
				
				if(row.length()>0) {
					for(int col = 0; col < numCols && col < row.length(); col++) {
						char element = row.charAt(col);
						Tile obj = new Tile(rowIndex, col, element, 0);
						
						maze.setTile(obj);
					}
					rowIndex++;
				}
			}
			scanner.close();
			
		} catch (FileNotFoundException e){
			System.out.println(e);
		}
	}
	
	public static void readCoordinateMap(String filename) {
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			
			int numRows = scanner.nextInt();
			int numCols = scanner.nextInt();
			int numRooms = scanner.nextInt();
			
			//process the map!
			while(scanner.hasNextLine()) {
				String row = scanner.nextLine();
				
				if(row.length()>0) {
					char mapElement = row.charAt(0);
					int rowIndex = row.charAt(1);
					int colIndex = row.charAt(2);
					int mazeLevel = row.charAt(3);

					Tile obj = new Tile(rowIndex, colIndex, mapElement, 0);
				}
			}
			scanner.close();
			
		} catch (FileNotFoundException e){
			System.out.println(e);
		}
	}
	
}
