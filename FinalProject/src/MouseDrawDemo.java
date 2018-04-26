import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class MouseDrawDemo extends JPanel implements MouseListener, MouseMotionListener {

	public ArrayList<Point> points = new ArrayList<Point>();



	public int lastx=0;
	public int lasty=0;
	public Color drawingColor = Color.black;

	public static void main(String[] args) {
		JFrame window = new JFrame("MouseDrawDemo");
		JPanel content = new MouseDrawDemo();
		window.setContentPane(content);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(120,70);
		window.setSize(400,300);
		window.setVisible(true);
	}

	public MouseDrawDemo() {
		setBackground(Color.BLACK);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0,0,getWidth(),getHeight());

		g.setColor(this.drawingColor);
		if (points.size()==0)
			return;

		Point lastPoint = points.get(0);
		for(Point p: points){
			g.drawLine(lastPoint.x,lastPoint.y, p.x, p.y);
			lastPoint = p;
		}
	}

	public void mousePressed(MouseEvent evt) {

		if ( evt.isShiftDown() ) {
			points.clear();
			repaint();
			return;
		}
	} 

	public void mouseEntered(MouseEvent evt) { }
	public void mouseExited(MouseEvent evt) { }
	public void mouseClicked(MouseEvent evt) { }
	public void mouseReleased(MouseEvent evt) {

		points.clear();

	}

	public void mouseMoved(MouseEvent evt){ }
	public void mouseDragged(MouseEvent evt){
		int x = evt.getX();  
		int y = evt.getY(); 
		points.add(new Point(x,y));
		repaint();
	}


	static class Point{
		int x;
		int y;
		Point(int x,int y){
			this.x=x; this.y=y;
		}
	}

} 