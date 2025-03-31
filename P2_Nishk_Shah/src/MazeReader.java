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
            int curRoomIndex = 0;
            
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                if (!row.isEmpty()) {
                    for (int col = 0; col < numCols && col < row.length(); col++) {
                        char element = row.charAt(col);
                        Tile obj = new Tile(rowIndex, col, element, curRoomIndex);
                        maze.setTile(obj);
                    }
                    rowIndex++;
                }
                
                // If we've filled a full room we want to increment the room index
                if (rowIndex >= numRows) {
                    rowIndex = 0;  // Reset for next room
                    curRoomIndex++; // Move to next room
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
    
            // Read the first line to get the size of the maze and number of mazes
            int numRows = scanner.nextInt();
            int numCols = scanner.nextInt();
            int numRooms = scanner.nextInt();
            scanner.nextLine(); // Move to the next line to start reading coordinates
    
            // Initialize the maze with '.' for all tiles
            maze = new Map(numRows, numCols, numRooms);
    
            // Fill all the tiles with '.'
            for (int room = 0; room < numRooms; room++) {
                for (int row = 0; row < numRows; row++) {
                    for (int col = 0; col < numCols; col++) {
                        maze.setTile(new Tile(row, col, '.', room)); // Default to '.'
                    }
                }
            }
    
            // Read the map elements and place them in the maze
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine().trim();
                if (!row.isEmpty()) {
                    String[] parts = row.split("\\s+"); // Split by whitespace
    
                    // Ensure the row has exactly 4 parts (element, row, column, maze level)
                    if (parts.length == 4) {
                        char mapElement = parts[0].charAt(0); // Map element (W, $, @, etc.)
                        int rowIndex = Integer.parseInt(parts[1]);
                        int colIndex = Integer.parseInt(parts[2]);
                        int mazeLevel = Integer.parseInt(parts[3]);
    
                        // Ensure the indices are within bounds
                        if (rowIndex >= 0 && rowIndex < numRows && colIndex >= 0 && colIndex < numCols && mazeLevel >= 0 && mazeLevel < numRooms) {
                            // Set the tile at the specified location
                            Tile tile = new Tile(rowIndex, colIndex, mapElement, mazeLevel);
                            maze.setTile(tile);
                        } else {
                            System.out.println("Error: Invalid coordinates at (" + rowIndex + ", " + colIndex + ", " + mazeLevel + ")");
                        }
                    } else {
                        System.out.println("Error: Invalid row format: " + row);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading coordinate map: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format in file.");
        }
    }
    

    // private void readCoordinateMap(String filename) {
    //     try {
    //         File file = new File(filename);
    //         if (!file.exists()) {
    //             System.out.println("File not found: " + filename);
    //             return;
    //         }
    //         Scanner scanner = new Scanner(file);

    //         int numRows = scanner.nextInt();
    //         int numCols = scanner.nextInt();
    //         int numRooms = scanner.nextInt();
    //         scanner.nextLine(); // Move to the next line

    //         maze = new Map(numRows, numCols, numRooms);

    //         while (scanner.hasNextLine()) {
    //             String row = scanner.nextLine();
    //             if (!row.isEmpty() && row.length() >= 4) {
    //                 char mapElement = row.charAt(0);
    //                 int rowIndex = Character.getNumericValue(row.charAt(1));
    //                 int colIndex = Character.getNumericValue(row.charAt(2));
    //                 int mazeLevel = Character.getNumericValue(row.charAt(3));

    //                 Tile obj = new Tile(rowIndex, colIndex, mapElement, mazeLevel);
    //                 maze.setTile(obj);
    //             }
    //         }

    //             for (int room = 0; room < numRooms; room++) {
    //                 for (int row = 0; row < numRows; row++) {
    //                     for (int col = 0; col < numCols; col++) {
    //                         if(maze.getTile(row, col, room) == null){
    //                             maze.setTile(new Tile(row, col, '.', room)); // Default to '.'
    //                         }
    //                     }
    //                 }
    //             }
    
    //         scanner.close();
    //     } catch (FileNotFoundException e) {
    //         System.out.println("Error reading coordinate map: " + e.getMessage());
    //     }
    // }
}
