import javax.swing.JApplet;

import java.awt.*;
import java.awt.event.*;

public class MineMain extends JApplet implements MouseListener, KeyListener, MouseMotionListener{

	Grid G = new Grid(25, 25, 99);
	int mouseX;
	int mouseY;
	
	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Grid G = new Grid(5, 5, 5);
//		G.show();
//		G.run();
//		Grid E = new Grid(5, 5, 5);
//		E.show();
//		E.run();
//	}
	
	public void init() {
		setSize(640, 640);
		setBackground(Color.black);
		
		addMouseListener(this);
		addKeyListener(this);
		addMouseMotionListener(this);
		
		repaint();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.red);
		g.drawString("" + G.getBombs(), 5, 15);
		
		//from 20, 20 to getWidth() - 20, getHeight() - 20
		//each square is (getWidth() - 40)/a.length, (getHeight() - 40)/G.getGrid().length
		
		for(GridSpace[] a : G.getGrid()) {
			for(GridSpace b : a) {
				if(b.getState() > 1 && b.getState() < 4) {
					g.setColor(Color.gray);
					g.fillRect(20 + b.getX()*((getWidth() - 40)/a.length), 20 + b.getY()*((getHeight() - 40)/G.getGrid().length),
							(getWidth() - 40)/a.length, (getHeight() - 40)/G.getGrid().length);
					g.setColor(Color.black);
					g.drawRect(20 + b.getX()*((getWidth() - 40)/a.length), 20 + b.getY()*((getHeight() - 40)/G.getGrid().length),
							(getWidth() - 40)/a.length, (getHeight() - 40)/G.getGrid().length);
				}
				if(b.getState() > 3) {
					g.setColor(Color.blue);
					g.fillRect(20 + b.getX()*((getWidth() - 40)/a.length), 20 + b.getY()*((getHeight() - 40)/G.getGrid().length),
							(getWidth() - 40)/a.length, (getHeight() - 40)/G.getGrid().length);
				}
				if(b.getState() == 1) {
					g.setColor(Color.red);
					g.fillOval(20 + b.getX()*((getWidth() - 40)/a.length), 20 + b.getY()*((getHeight() - 40)/G.getGrid().length),
							(getWidth() - 40)/a.length, (getHeight() - 40)/G.getGrid().length);
				}
				if(b.getState() == 0) {
					g.setColor(Color.white);
					g.drawString("" + b.getbombs(), 20 + (int)((b.getX() + .4)*((getWidth() - 40)/a.length)), 20 + (int)((b.getY() + .7)*((getHeight() - 40)/G.getGrid().length)));
				}
			}
		}
		
	}
	
	public static void printArrays(Object[][] ob) {
		for(int i = 0; i < ob.length; i++) {
			System.out.print("{");
			for(int j = 0; j < ob[i].length; j++) {
				System.out.print(ob[i][j] + ", ");
			}
			System.out.println("}");
		}
		System.out.println();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("click " + e.getButton() +  " " + 
		e.getX() + " " + e.getY());
		if(e.getButton() == 1) {
			if(!(e.getX() < 20 || e.getX() > getWidth() - 20 || e.getY() < 20 || e.getY() > getHeight() - 20))
				G.reveal((e.getX() - 20)/((getWidth() - 40)/G.getGrid()[0].length), (e.getY() - 20)/((getHeight() - 40)/G.getGrid().length));
			while(G.revealZeros()) {
			}
		} else if(e.getButton() == 3) {
			if(!(e.getX() < 20 || e.getX() > getWidth() - 20 || e.getY() < 20 || e.getY() > getHeight() - 20))
				G.flag((e.getX() - 20)/((getWidth() - 40)/G.getGrid()[0].length), (e.getY() - 20)/((getHeight() - 40)/G.getGrid().length));
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent c) {
		//Point e = MouseInfo.getPointerInfo().getLocation();
		if((c.getKeyChar() + "").equals(" ")) {
			System.out.println("space" + " " + mouseX + " " + mouseY);
			if(!(mouseX < 20 || mouseX > getWidth() - 20 || mouseY < 20 || mouseY > getHeight() - 20)) {//not out of bounds
				if(G.get((mouseX - 20)/((getWidth() - 40)/G.getGrid()[0].length), (mouseY - 20)/((getHeight() - 40)/G.getGrid().length)).getState() > 1) {//not reveal yet
					G.flag((mouseX - 20)/((getWidth() - 40)/G.getGrid()[0].length), (mouseY - 20)/((getHeight() - 40)/G.getGrid().length));//flag it
				} else if(G.get((mouseX - 20)/((getWidth() - 40)/G.getGrid()[0].length), (mouseY - 20)/((getHeight() - 40)/G.getGrid().length)).getState() == 0 &&//it's been revealed
						G.getNeighborflags((mouseX - 20)/((getWidth() - 40)/G.getGrid()[0].length), (mouseY - 20)/((getHeight() - 40)/G.getGrid().length)) == //neighborflags
								G.get((mouseX - 20)/((getWidth() - 40)/G.getGrid()[0].length), (mouseY - 20)/((getHeight() - 40)/G.getGrid().length)).getbombs()){ //same as num bombs
					for(GridSpace u : G.getNeighbors((mouseX - 20)/((getWidth() - 40)/G.getGrid()[0].length), (mouseY - 20)/((getHeight() - 40)/G.getGrid().length))) { //reveal all neighbors
						G.reveal(u.getX(), u.getY());
						//System.out.println(u.getX() + " " + u.getY());
						//u.reveal();
					}
				}
				while(G.revealZeros()) { }
			}
		} else if((c.getKeyChar() + "").equals("r")) {
			G = new Grid(25, 25, 99);
		}
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

}
