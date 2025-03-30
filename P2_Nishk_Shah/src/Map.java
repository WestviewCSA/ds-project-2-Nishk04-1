import java.util.ArrayList;

public class Map {
	 private Tile[][][] map;
	int rows, cols, roomNum;
	
	public Map(int rows, int cols, int rooms) {
		this.rows = rows;
		this.cols = cols;
		this.roomNum = rooms;
        map = new Tile[rows][cols][rooms]; // Only allocate once

	}
	
	public void setTile(Tile obj) {
		int row = obj.getRow();
		int col = obj.getCol();
		int room = obj.getLevel();
	    if (row >= 0 && row < rows && col >= 0 && col < cols && room >= 0 && room < roomNum) {
	        map[row][col][room] = obj; 
	    } else {
	        System.out.println("Error: Index out of bounds! row=" + row + ", col=" + col + ", room=" + room);
	    }
	}
	
	public Tile getTile(int row, int col, int room) {
		return map[row][col][room];
	}

	public ArrayList<Tile>  getNeighbors(Map maze, Tile tile){
		ArrayList<Tile> neighbors = new ArrayList<Tile>();
		//North, South, East, West - format: {row, col}
		int[][] directions = {{-1,0}, {1, 0},{0,1},{0,-1}};
		for(int i = 0; i < directions.length; i++){
			int curRow = tile.getRow() + directions[i][0];
			int curCol = tile.getCol() + directions[i][1];
			int curRoom = tile.getLevel();

			//the cur index has to be smaller than the length
			if(curRow >= 0 && curRow < rows && curCol >= 0 && curCol < cols && curRoom >= 0 && curRoom < roomNum) {
				Tile neighbor = maze.getTile(curRow, curCol, curRoom);
            	// only add the neighbor if it's not visited and not marked with '+'
				if (neighbor.getType() != '+' && !neighbor.getVisited() && neighbor.getType() != '@') {
					neighbors.add(neighbor);
				}

			} else{
				continue; // skip if out of bounds
			}

		}
		return neighbors;
	}

	public Tile getStartTile() {
		// Gets the first W it finds
		for(int room = 0; room < this.roomNum; room++) {
			for(int row = 0; row < rows; row++) {
				for(int col = 0; col < cols; col++) {
					if(map[row][col][room] != null && map[row][col][room].getType() == 'W') {
						return map[row][col][room];
					}
				}
			}
		}
		return null;
	}

	// Will find the NEXT W after the starting W
	public Tile getNextLevelStart(int startRow, int prevLevel) {
		int nextLevel = prevLevel + 1;
		if (nextLevel >= roomNum) return null; // No more rooms
		
		// Start searching from the row after the initial W
		for (int row = startRow + 1; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (map[row][col][nextLevel] != null && map[row][col][nextLevel].getType() == 'W') {
					return map[row][col][nextLevel]; // Return the first W found but after the starting W
				}
			}
		}
		return null;
	}

	public String returnMaze() {
		String maze = "";
		for(int room = 0; room < this.roomNum; room++) {
			for(int row = 0; row < rows; row++) {
				for(int col = 0; col < cols; col++) {
					maze += map[row][col][room];
					
				}
				maze+= "\n";
			}
		}
		return maze;
	}
}
