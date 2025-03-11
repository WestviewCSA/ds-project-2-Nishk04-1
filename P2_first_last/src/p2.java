import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p2 {

	private static Scanner scanner;
	private int rows, cols, maps;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    String filename = "H:\\git\\ds-project-2-Nishk04-1\\P2_first_last\\src\\TestMaze01";
		readMap("TestMaze01");
	}
	
	public static void readMap(String filename) {
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			
			int numRows = scanner.nextInt();
			int numCols = scanner.nextInt();
			int numRooms = scanner.nextInt();
			
			int rowIndex = 0;
			//process the map!
			while(scanner.hasNextLine()) {
				String row = scanner.nextLine();
				
				if(row.length()>0) {
					for(int i = 0; i < numCols && i < row.length(); i++) {
						char element = row.charAt(i);
						Tile obj = new Tile(rowIndex, i, element);
					}
				}
			}
			
		} catch (FileNotFoundException e){
			System.out.println(e);
		}
	}
	
}
