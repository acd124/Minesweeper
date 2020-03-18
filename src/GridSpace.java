
public class GridSpace {
	private int xPos;
	private int yPos;
	private int gridState; //0revealed, 1boom, 2hidden, 3bomb, 4flagempty, 5flagbomb
	private int numb; //not set at construction; number of bombs adjacent

	public GridSpace(int x, int y) {
		xPos = x;
		yPos = y;
		gridState = 2;
	}
	
	public int getX() { return xPos; }
	public int getY() { return yPos; }
	public int getState() { return gridState; }
	public int getbombs() { return numb; }
	
	public void setState(int n) { gridState = n; }
	public void setnumb(int n) { numb = n; }
	public void incnumb() { numb += 1; }
	
	public void reveal() {
		if(gridState == 2 || gridState == 3)
			gridState -= 2;
	}
	
	public int flag() {
		if(this.gridState == 2) {
			setState(4);
			return -1;
		} else if(gridState == 3) {
			setState(5);
			return -1;
		} else if(gridState == 4) {
			setState(2);
			return 1;
		} else if(gridState == 5) {
			setState(3);
			return 1;
		}
		return 0;
	}
	
	public String toString() { //fix display to hide properly
		if(gridState == 4 || gridState == 5)
			return "p";
		if(gridState == 1)
			return "@";
		if(gridState == 2 || gridState == 3)
			return "o";
		return "" + numb;
	}
}
