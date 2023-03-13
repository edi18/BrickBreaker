package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import logic.GameObject;

/** Game frame class */
public class BrickBreakerFrame extends JFrame {

	BrickBreakerPanel brickBreakerPanel;
	
	/**
	 * 
	 * @param width frame width
	 * @param height frame height
	 */
	public BrickBreakerFrame(int width, int height) {
		super("Brick breaker");
		
		setVisible(true);
		
		brickBreakerPanel = new BrickBreakerPanel();
		
		brickBreakerPanel.setPreferredSize(new Dimension(width, height)); // adjust for client frame size
		this.getContentPane().add(brickBreakerPanel);
		
		pack();
		
		//add(brickBreakerPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	/** Draws rectangle to buffer(panel) */
	public void drawRect(Rect rect) {
		brickBreakerPanel.drawRect(rect);
	}
	
	/** Clears entire buffer(panel) */
	public void clear() {
		brickBreakerPanel.clear();
	}
	
	/** Presents(draws) entire buffer to frame */
	public void present() {
		brickBreakerPanel.present();
	}

}
