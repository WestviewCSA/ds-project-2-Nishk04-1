
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

	public Tile getStartTile() {
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
