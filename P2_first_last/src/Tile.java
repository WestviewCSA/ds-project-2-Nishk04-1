
public class Tile {

		private int row, col, mapLevel;
		private char type;
		private boolean visited;
		
		public Tile(int row, int col, char type, int level) { // be able to set a tile 
			this.row = row;
			this.col = col;
			this.type = type;
			this.visited = false;
			this.mapLevel = level;
		}
		
//		public Tile(int row, int col, char type, int mapLevel) { // you can set the mapLevel if it's on another level
//			this.row = row;
//			this.col = col;
//			this.type = type;
//			this.mapLevel = mapLevel;
//			this.visited = false;
//		} 

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
		
		public String toString() {
			return "" + type;
		}

}
