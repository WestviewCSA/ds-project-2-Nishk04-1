
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

		map[rows][cols][roomNum] = obj;
		
	}
	
	public String returnMaze() {
		String maze = "";
		for(int room = 0; room < this.roomNum; room++) {
			for(int row = 0; row < rows; row++) {
				for(int col = 0; col < cols; col++) {
					maze += map[row][col][room];
				}
			}
		}
		return maze;
	}
}
