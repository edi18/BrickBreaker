package console;

import logic.Ball;
import logic.Brick;
import logic.GameObject;
import logic.Platform;

/** Character buffer class for printing to console */
public class ConsoleBuffer {
	
	int width;
	int height;
	char[][] buffer; 
	
	/**
	 * Parameterized constructor
	 * @param width width of the console buffer
	 * @param height height of the console buffer
	 */
	public ConsoleBuffer(int width, int height) {
		this.width = width;
		this.height = height;
		
		buffer = new char[this.height][this.width];
		
		clear();
	}
	
	/** Clears entire character buffer to '.' character */
	public void clear() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				buffer[i][j] = '.';
			}
		}
	}
	
	/**
	 * Width getter
	 * @return width of the console buffer
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Height getter
	 * @return height of the console buffer
	 */
	public int getHeight() {
		return height;
	}
	
	/** Draws object based on the type of the object */
	public void draw(GameObject object) {
		if (object instanceof Brick) {
			for (int y = (int) Math.floor(object.getPos().y); y < (int) Math.floor(object.getPos().y + object.getSize().y); y++) {
				for (int x = (int) Math.floor(object.getPos().x); x < (int) Math.floor(object.getPos().x + object.getSize().x); x++) {
					buffer[y][x] = '#';
				}
			}
		}
		else if (object instanceof Platform) {
			for (int y = (int) Math.floor(object.getPos().y); y < (int) Math.floor(object.getPos().y + object.getSize().y); y++) {
				for (int x = (int) Math.floor(object.getPos().x); x < (int) Math.floor(object.getPos().x + object.getSize().x); x++) {
					buffer[y][x] = 'P';
				}
			}
		}
		else if (object instanceof Ball) {
			for (int y = (int) Math.floor(object.getPos().y); y < (int) Math.floor(object.getPos().y + object.getSize().y); y++) {
				for (int x = (int) Math.floor(object.getPos().x); x < (int) Math.floor(object.getPos().x + object.getSize().x); x++) {
					buffer[y][x] = 'O';
				}
			}
		}
	}
	
	/** Outputs the current buffer state to the console */
	public void present() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(buffer[i][j]);
			}
			System.out.println();
		}
	}
	
}
