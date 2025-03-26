import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;

public class p2 {
	static Map maze;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    String filename = "P2_Nishk_Shah\\src\\TestMaze01";
		readMap(filename);
		String output = maze.returnMaze();
		System.out.println(output);
		
		}
	
	public static void stackSolver() {
		if(maze == null) {
			System.out.println("No map found bro");
			System.exit(-1);
		}
		Tile start = maze.getStartTile();
		Stack<Tile> stack = new Stack<Tile>();
		stack.add(start);
	
		// add the next stuff to the stack until we reach 'w'
		while(!stack.isEmpty() ) {
			// Gets the tile that we need to process first
			Tile currTile = stack.pop();

			if (currTile.getType() == '$') {
				System.out.println("Found the Diamond Wolverine Buck!");
				// TODO: add the code to print the path it took - maybe implement this in the map class
			}
			
			if ((currTile.getType() == '.' || currTile.getType() == 'W') && !currTile.getVisited()) {
				currTile.setVisited(true);
				currTile.setType('+');
				for(Tile neighbors : maze.getNeighbors(maze, currTile)) {
					if(neighbors.getType() == '.' || neighbors.getType() == 'W') {
						stack.push(neighbors);
					}
				}
			}

		}	
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
