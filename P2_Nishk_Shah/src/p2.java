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
        String filename = "../TEST/Test10";

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

		//ERROR CHECKING:
		if ((Stack && Queue) || (Queue && Opt) || (Stack && Opt)) {
            System.out.println("Error: Please only use ONE of --Stack, --Queue, or --Opt at a time.");
            System.exit(-1);
        }
		if (!Stack && !Queue && !Opt) {
			System.out.println("Error: Please specify one of --Stack, --Queue, or --Opt.");
			System.exit(-1);
		}
		//READ MAP:
		mazeReader = new MazeReader();
		if (Incoordinate) { //Reads the coordinate map
	        mazeReader.readMaze(filename, true);
	    } else { //Reads the text-based map
	        mazeReader.readMaze(filename, false);
	    }
		maze = mazeReader.getMaze();
		System.out.print(maze.returnMaze(Incoordinate));
		
		//SOLVE THE MAZE:
		// long startTime = System.nanoTime();
        // long endTime = System.nanoTime();
        // System.out.println("Total Runtime: " + (endTime - startTime) + " nanoseconds");
		StackSolver stackSolver = new StackSolver(maze, Outcoordinate);
		QueueSolver queueSolver = new QueueSolver(maze, Outcoordinate);
		OptimalSolver optimalSolver = new OptimalSolver(maze, Outcoordinate);
		if (Time) { 
	        if (Stack) { 
	            long startStack = System.currentTimeMillis();
				stackSolver.solve();	            
				long endStack = System.currentTimeMillis();
	            double sDur = (endStack - startStack) / 1000.0;
	            System.out.println("Total Runtime: " + sDur + " seconds");
	        } else if (Queue) {
	            long startQueue = System.currentTimeMillis();
	            queueSolver.solve();
	            long endQueue = System.currentTimeMillis();
	            double qDur = (endQueue - startQueue) / 1000.0;
	            System.out.println("Total Runtime: " + qDur + " seconds");
	        } else if (Opt) {  
	            long optstart = System.currentTimeMillis();
	            optimalSolver.solve();
	            long optend = System.currentTimeMillis();
	            double optDur = (optend - optstart) / 1000.0;
	            System.out.println("Total Runtime: " + optDur + " seconds");
	        }
	    } else {
	        if (Stack) { 
	        	 //stackSolver.solve();
	        } else if (Queue) { 
	        	 queueSolver.solve();
	        } else if (Opt) {  
	            //
	        }
	    }

		// mazeReader = new MazeReader();
		// mazeReader.readMaze(filename, false);
		// maze = mazeReader.getMaze();
		// System.out.print(maze.returnMaze(false));

		// QueueSolver queueSolver = new QueueSolver(maze, false);
		// queueSolver.solve();

		// StackSolver stackSolver = new StackSolver(maze, false);
		// stackSolver.solve();
	}
}
