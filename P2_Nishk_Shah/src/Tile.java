import java.util.ArrayList;

public class Tile {

		private int row, col, mapLevel;
		private char type;
		private boolean visited;
		private int[] action; // This can be used to store the action taken on this tile to get to the NEXT tile
		private int[] parent;

		public Tile(int row, int col, char type, int level, int[] action, int[] parent) { // be able to set a tile 
			this.row = row;
			this.col = col;
			this.type = type;
			this.visited = false;
			this.mapLevel = level;
			this.parent = parent;
			this.action = action; // This can be used to store the action taken on this tile to get to the NEXT tile
			// Example: action could be {1, 0} for moving south, {-1, 0} for moving north, etc. This can be used to trace back the path taken.
		}
		
		public Tile(int row, int col, char type, int level) { // be able to set a tile 
			this.row = row;
			this.col = col;
			this.type = type;
			this.visited = false;
			this.mapLevel = level;
			this.parent = null;
			this.action = null; 
		}

//		public Tile(int row, int col, char type, int mapLevel) { // you can set the mapLevel if it's on another level
//			this.row = row;
//			this.col = col;
//			this.type = type;
//			this.mapLevel = mapLevel;
//			this.visited = false;
//		} 

		public int[] getAction() {
			return action; // returns the action taken to get to this tile
		}

		public void setAction(int[] action) { // set the action taken to get to this tile
			this.action = action; // Example: {1, 0} for moving south, {-1, 0} for moving north, etc. This can be used to trace back the path taken.
		}

		public int[] getParent() { // get the parent tile of this tile
			return parent;
		}

		public void setParent(int[] parent) { // set the parent tile of this tile
			this.parent = parent; // This can be used to trace back the path taken to get to this tile
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getCol() {
			return col;
		}

		public void setCol(int col) {
			this.col = col;
		}

		public char getType() {
			return type;
		}

		public void setType(char type) {
			this.type = type;
		}
		
		public boolean getVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}
		
		public int getLevel() {
			return mapLevel;
		}

		public int[] getLocation() {
			int[] location = {this.row, this.col};
			return location;
		}
		
		public String toString() {
			return "" + type;
		}

}
