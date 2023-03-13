package gui;

import java.awt.Color;

/** Rectangle class for drawing to frame */
public class Rect {

	public int x, y;
	public int w, h;
	public Color color;
	
	/**
	 * Parameterized constructor
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param w rect width
	 * @param h rect height
	 * @param color rect color
	 */
	public Rect(int x, int y, int w, int h, Color color) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.color = color;
	}

}
