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
		if(Help){
			System.out.print("--Stack (boolean) If this switch is set, use the stack-based approach\r\n" + //
								"--Queue (boolean) If this switch is set, use the queue-based approach\r\n" + //
								"--Opt (boolean) If this switch is set, find the shortest path\r\n" + //
								"--Time (boolean) If this switch is set, print the runtime of the program as described above.\r\n" + //
								"--Incoordinate (boolean) If this switch is set, the input file is in the coordinate-based system. If the switch is not set, the input file is in the text-map format.\r\n" + //
								"--Outcoordinate (boolean) If this switch is set, the output file is in the coordinate-based system. If the switch is not set, the output file is in the text-map format.");
			System.exit(0);
		}
		if(Time){

		}
		 // Define the filename
		 String filename = "P2_Nishk_Shah\\TEST\\Test05"; // Adjust path if needed
		 boolean isCoordinateBased = false; // Decide format based on input flag
 
		 mazeReader = new MazeReader();
		 mazeReader.readMaze(filename, isCoordinateBased);
		 maze = mazeReader.getMaze();
 
		 System.out.println(maze.returnMaze(false));
 
		StackSolver solver = new StackSolver(maze);
        solver.solve();
		
		// QueueSolver queueSolver = new QueueSolver(maze);
		// queueSolver.solve();
		
	}
}
