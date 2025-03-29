import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeReader {
    private Map maze;

    public MazeReader() {
        this.maze = null;
    }

    public Map getMaze() {
        return this.maze;
    }

    public void readMaze(String filename, boolean isCoordinateBased) {
        if (isCoordinateBased) {
            readCoordinateMap(filename);
        } else {
            readTextMap(filename);
        }
    }

    private void readTextMap(String filename) {
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
            scanner.nextLine(); // Move to the next line

            // Create the map
            maze = new Map(numRows, numCols, numRooms);

            int rowIndex = 0;
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                if (!row.isEmpty()) {
                    for (int col = 0; col < numCols && col < row.length(); col++) {
                        char element = row.charAt(col);
                        Tile obj = new Tile(rowIndex, col, element, 0);
                        maze.setTile(obj);
                    }
                    rowIndex++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading text map: " + e.getMessage());
        }
    }

    private void readCoordinateMap(String filename) {
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
            scanner.nextLine(); // Move to the next line

            maze = new Map(numRows, numCols, numRooms);

            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                if (!row.isEmpty() && row.length() >= 4) {
                    char mapElement = row.charAt(0);
                    int rowIndex = Character.getNumericValue(row.charAt(1));
                    int colIndex = Character.getNumericValue(row.charAt(2));
                    int mazeLevel = Character.getNumericValue(row.charAt(3));

                    Tile obj = new Tile(rowIndex, colIndex, mapElement, mazeLevel);
                    maze.setTile(obj);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading coordinate map: " + e.getMessage());
        }
    }
}
