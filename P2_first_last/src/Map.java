
public class Map {
	
	private Tile[][][] map; //rows, cols, # number of rooms/levels
	int rows, cols, roomNum;
	public Map(int rows, int cols, int rooms) {
		map = new Tile[rows][cols][rooms];
		this.rows = rows;
		this.cols = cols;
		this.roomNum = rooms;
		
	}
	
	public void setTile(Map map, int row, int col, int room, Tile obj) {
		//map[row][col][room] = obj;
		
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
