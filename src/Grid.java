import java.util.Scanner;
public class Grid {
	
	private GridSpace[][] grid;
	private int bombsLeft;
	
	public Grid(int height, int width, int bombs) {
		grid = new GridSpace[height][width];
		bombsLeft = bombs < height*width ? bombs : height*width - 1;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				grid[i][j] = new GridSpace(j, i);
			}
		}
		Randp p = new Randp(height*width);
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(p.nextInt() <= bombsLeft) {
					grid[i][j].setState(3);
				}
			}
		}
		setNumb();
	}
	
	public void run() {
		Scanner input = new Scanner(System.in);
		String s = input.nextLine();
		if(s.compareTo("r") == 0) {
			int x = input.nextInt();
			int y = input.nextInt();
			reveal(x, y);
		} else if(s.compareTo("f") == 0) {
			int x = input.nextInt();
			int y = input.nextInt();
			flag(x, y);
		} else {
			System.out.println("Error invalid option, please choose r or f");
			run();
		}
	}
	
	public void flag(int x, int y) {
		if(y < 0 || y > grid.length - 1 || x < 0 || x > grid[0].length - 1) {
			System.out.println("Coordinates out of bounds");
			//this.show();
		} else {
			bombsLeft += grid[y][x].flag();
			//this.show();
		}
		//this.run();
	}
	
	public GridSpace get(int x, int y) {
		if(y < 0 || y > grid.length - 1 || x < 0 || x > grid[0].length - 1) {
			System.out.println("Coordinates out of bounds");
			GridSpace v = new GridSpace(-5, -5);
			v.setState(-5);
			return v;
		} else {
			return grid[y][x];
			//this.show();
		}
		//this.run();
	}
	
	public void revealAll() {
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				if(grid[i][j].getState() > 3)
					grid[i][j].setState(grid[i][j].getState() - 2);
				grid[i][j].reveal();
			}
		}
	}
	
	public boolean revealZeros() {
		boolean retur = false;
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[y].length; x++) {
				if(grid[y][x].getState() == 0 && grid[y][x].getbombs() == 0) {
					for(GridSpace a: getNeighbors(x, y)) {
						if(a.getState() == 2) {
							retur = true;
							a.reveal();
						}
					}
				}
			}
		}
		return retur;
	}
	
	public void reveal(int x, int y) {
		if(y < 0 || y > grid.length - 1 || x < 0 || x > grid[0].length - 1) {
			System.out.println("Coordinates out of bounds");
			//this.show();
		} else {
			grid[y][x].reveal();
//			if(grid[y][x].getState() == 1) {
//				System.out.println("YOU LOSE :(");
//				revealAll();
//				//this.show();
//				//return;
//			}
//			} else if(grid[y][x].getState() == 0 && grid[y][x].getbombs() == 0) {
//				reveal(x + 1, y + 1);
//				reveal(x + 1, y);
//				reveal(x + 1, y - 1);
//				reveal(x, y + 1);
//				reveal(x, y - 1);
//				reveal(x - 1, y + 1);
//				reveal(x - 1, y);
//				reveal(x - 1, y - 1);
//			}
			//this.show();
		}
		//this.run();
	}
	
//	private void setNumbs() {
//		for(int i = 0; i < grid.length; i++) {
//			for(int j = 0; j < grid[i].length; j++) {
//				if(i == 0) {
//					if(j == 0) {
//						if(grid[i][j+1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i+1][j+1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i+1][j].getState() == 3)
//							grid[i][j].incnumb();
//					} else if(j == grid[i].length - 1) {
//						if(grid[i][j-1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i+1][j-1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i+1][j].getState() == 3)
//							grid[i][j].incnumb();
//					} else {
//						if(grid[i][j+1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i+1][j+1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i+1][j].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i+1][j-1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i][j-1].getState() == 3)
//							grid[i][j].incnumb();
//					}
//				} else if(i == grid.length - 1) {
//					if(j == 0) {
//						if(grid[i-1][j].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i-1][j+1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i][j+1].getState() == 3)
//							grid[i][j].incnumb();
//					} else if(j == grid[i].length - 1) {
//						if(grid[i][j-1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i-1][j-1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i-1][j].getState() == 3)
//							grid[i][j].incnumb();
//					} else {
//						if(grid[i][j-1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i-1][j-1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i-1][j].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i-1][j+1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i][j+1].getState() == 3)
//							grid[i][j].incnumb();
//					}
//				} else {
//					if(j == 0) {
//						if(grid[i-1][j].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i-1][j+1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i][j+1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i+1][j+1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i+1][j].getState() == 3)
//							grid[i][j].incnumb();
//					} else if(j == grid[i].length - 1) {
//						if(grid[i-1][j].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i-1][j-1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i][j-1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i+1][j-1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i+1][j].getState() == 3)
//							grid[i][j].incnumb();
//					} else {
//						if(grid[i+1][j+1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i+1][j].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i+1][j-1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i][j-1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i-1][j-1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i-1][j].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i-1][j+1].getState() == 3)
//							grid[i][j].incnumb();
//						if(grid[i][j+1].getState() == 3)
//							grid[i][j].incnumb();
//					}
//				}
//			}
//		}
//	}
	
	private void setNumb() {
		for(GridSpace[] a : grid) {
			for(GridSpace b : a) {
				for(int i = -1; i < 2; i++) {
					for(int j = -1; j < 2; j++) {
						if(!((i == j && i == 0) || 
								b.getX() + i < 0 ||
								b.getX() + i > a.length - 1 ||
								b.getY() + j < 0 ||
								b.getY() + j > grid.length - 1)
								&& grid[b.getY() + j][b.getX() + i].getState() == 3) {
							b.incnumb();
						}
					}
				}
			}
		}
	}
	
	public GridSpace[][] getGrid() {
		return grid;
	}
	
	public int getBombs() {
		return bombsLeft;
	}
	
	public GridSpace[] getNeighbors(int x, int y) {
		int len = 0;
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if(!(i == j && i == 0 || x + i < 0 || y + j < 0 || y + j > grid.length - 1 || x + i > grid[0].length - 1))
					len++;
			}
		}
		if(len == 0)
			return new GridSpace[0];
		GridSpace[] a = new GridSpace[len];
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if(!(i == j && i == 0 || x + i < 0 || y + j < 0 || y + j > grid.length - 1 || x + i > grid[0].length - 1))
					a[--len] = grid[y+j][x+i];
			}
		}
		return a;
	}
	
	public int getNeighborflags(int x, int y) {
		int r = 0;
		for(GridSpace u : getNeighbors(x, y)) {
			if(u.getState() > 3)
				r++;
		}
		return r;
	}
	
	
	public void show() {
		System.out.println(bombsLeft);
		MineMain.printArrays(grid);
	}
	
	public boolean winState() {
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[y].length; x++) {
				if(grid[y][x].getState() == 1 || grid[y][x].getState() == 2 || grid[y][x].getState() == 4) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean failState() {
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[y].length; x++) {
				if(grid[y][x].getState() == 1) {
					return true;
				}
			}
		}
		return false;
	}
	public void autoWin() {
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[y].length; x++) {
				if(grid[y][x].getState() == 2 || grid[y][x].getState() == 4) {
					grid[y][x].setState(0);
				}
			}
		}
	}
	public void autoFlag() {
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[y].length; x++) {
				if(grid[y][x].getState() == 3) {
					grid[y][x].setState(3);
					flag(x, y);
				}
			}
		}
	}
	public void autoLose() {
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[y].length; x++) {
				if(grid[y][x].getState() == 3 || grid[y][x].getState() == 5) {
					grid[y][x].setState(3);
					reveal(x, y);
					return;
				}
			}
		}
	}
	public void autoCascade() {
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[y].length; x++) {
				if(grid[y][x].getState() == 2 && grid[y][x].getbombs() == 0) {
					reveal(x, y);
					while(revealZeros()) { }
				}
			}
		}
	}
	public void startCascade() {
		for(int y = 0; y < grid.length; y++) {
			for(int x = 0; x < grid[y].length; x++) {
				if(grid[y][x].getState() == 2 && grid[y][x].getbombs() == 0) {
					reveal(x, y);
					return;
				}
			}
		}
	}
	public void auto(int x, int y) {
		if(this.get(x, y).getState() == 2) {
			reveal(x, y);
		} else if(this.get(x, y).getState() == 3) {
			flag(x, y);
		} else if(this.get(x, y).getState() == 4) {
			flag(x, y);
			reveal(x, y);
		} else if(this.get(x, y).getState() == 0) {
			//if(this.getNeighborflags(x, y) == get(x, y).getbombs()) {
				for(GridSpace u:this.getNeighbors(x, y)) {
					if(u.getState() == 2)
						u.reveal();
					if(u.getState() == 4) {
						flag(u.getX(), u.getY());
						u.reveal();
					}
					if(u.getState() == 3) {
						flag(u.getX(), u.getY());
					}
				}
			//}
		}
	}
	
}
