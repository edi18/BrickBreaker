package gui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import java.util.EnumMap;

import logic.Ball;
import logic.Brick;
import logic.BrickBreaker;
import logic.BrickBreaker.Input;
import logic.GameObject;
import logic.Pair;
import logic.Platform;

/** Main game class for the GUI interface */
public class GUIBrickBreaker {

	BrickBreaker brickBreaker;
	
	BrickBreakerFrame brickBreakerFrame;
	
	/** Enum for user input keys */
	private enum Key {
		A,
		D
	}
	
	// Key status map
	EnumMap<Key, Boolean> keys; 
	
	public GUIBrickBreaker() {
		
		brickBreaker = new BrickBreaker(new Pair(400, 400), new Pair(60, 10), new Pair(10, 0), new Pair(40, 10), new Pair(10, 10), new Pair(0, 10));
		
		brickBreakerFrame = new BrickBreakerFrame((int)brickBreaker.getField().getSize().x, (int)brickBreaker.getField().getSize().y);
		
		keys = new EnumMap<Key, Boolean>(Key.class);
		keys.put(Key.A, false);
		keys.put(Key.D, false);
		
		// Define key listener for user input keys
		brickBreakerFrame.addKeyListener(new KeyListener() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	        }

	        @Override
	        public void keyPressed(KeyEvent e) {
	            switch (e.getKeyChar()) {
	            case 'd':
	            	keys.put(Key.D, true);
	            	break;
	            case 'a':
	            	keys.put(Key.A, true);
	            	break;
	            }
	        }

	        @Override
	        public void keyReleased(KeyEvent e) {
	        	switch (e.getKeyChar()) {
	            case 'd':
	            	keys.put(Key.D, false);
	            	break;
	            case 'a':
	            	keys.put(Key.A, false);
	            	break;
	            }
	        }
	    });
		
	}
	
	/** Start main game loop */
	public void start() {
		while (brickBreaker.getStatus()) {
			
			handleInput();
			
			update();
			
			draw();
			
			// Wait certain number of milliseconds
			try {
				Thread.sleep(39);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/** Handles user key input, sends input message to game logic */
	private void handleInput() {
		
		if (keys.get(Key.A)) {
			brickBreaker.processInput(Input.MOVE_PLATFORM_LEFT);
		}
		
		if (keys.get(Key.D)) {
			brickBreaker.processInput(Input.MOVE_PLATFORM_RIGHT);
		}
		
	}
	
	/** Calls update on game logic */
	private void update() {
		brickBreaker.update();
	}
	
	/** Draws graphics to frame from current game logic status */
	private void draw() {
		brickBreakerFrame.clear();
		
		Vector<GameObject> gameObjects = brickBreaker.getGameObjects();
		for (int i = 0; i < gameObjects.size(); i++) {
						
			if (gameObjects.get(i) instanceof Brick) {
				
				Brick brick = (Brick)gameObjects.get(i);
				
				Color color = null;
				
				if (brick.getType() == Brick.Type.RED) {
					color = Color.RED;
				}
				else if (brick.getType() == Brick.Type.ORANGE) {
					color = Color.ORANGE;
				}
				else if (brick.getType() == Brick.Type.YELLOW) {
					color = Color.YELLOW;
				}
				else if (brick.getType() == Brick.Type.BLUE) {
					color = Color.BLUE;
				}
				else if (brick.getType() == Brick.Type.GREEN) {
					color = Color.GREEN;
				}
				
				brickBreakerFrame.drawRect(new Rect((int)gameObjects.get(i).getPos().x, (int)gameObjects.get(i).getPos().y, (int)gameObjects.get(i).getSize().x, (int)gameObjects.get(i).getSize().y, color));	
			}			
			else if (gameObjects.get(i) instanceof Platform) {
				brickBreakerFrame.drawRect(new Rect((int)gameObjects.get(i).getPos().x, (int)gameObjects.get(i).getPos().y, (int)gameObjects.get(i).getSize().x, (int)gameObjects.get(i).getSize().y, Color.DARK_GRAY));
			}
			else if (gameObjects.get(i) instanceof Ball) {
				brickBreakerFrame.drawRect(new Rect((int)gameObjects.get(i).getPos().x, (int)gameObjects.get(i).getPos().y, (int)gameObjects.get(i).getSize().x, (int)gameObjects.get(i).getSize().y, Color.WHITE));
			}
		
		}
		
		brickBreakerFrame.present();
	}

}
