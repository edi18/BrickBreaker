package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Vector;

import javax.swing.JPanel;

/** Frame pixel buffer(implemented as a panel) */
public class BrickBreakerPanel extends JPanel {
	
	Vector<Rect> rects; // collection of current rectangles to present
	
	/**
	 * Default constructor
	 */
	public BrickBreakerPanel() {
		this.setBackground(Color.BLACK);
		
		rects = new Vector<Rect>();
	}
	
	/** Main paint method for drawing the rectangles with specified color and size, overridden from JPanel */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		for (int i = 0; i < rects.size(); i++) {
			g2.setColor(rects.get(i).color);
			g2.fillRect(rects.get(i).x, rects.get(i).y, rects.get(i).w, rects.get(i).h);
		}
	}

	/** Draw rectangle to buffer */
	public void drawRect(Rect rect) {
		rects.add(rect);
	}
	
	/** Clears the buffer */
	public void clear() {
		rects.clear();
	}
	
	/** Draws buffer to frame */
	public void present() {
		repaint();
	}
	
}
