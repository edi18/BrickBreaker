package logic;

import java.util.Stack;
import java.util.Vector;

/**
 * Main game class<br>
 * Processes all of the games logic<br>
 * Returns status of the game
 */
public class BrickBreaker {
	
	/** Input enum, specifies the type of input that logic can process */
	public enum Input {
		MOVE_PLATFORM_RIGHT,
		MOVE_PLATFORM_LEFT
	}
	
	Field field; // represents the size of the playing field, where the game takes place
	
	Vector<Brick> bricks;
	Platform platform;
	Ball ball;
	
	Vector<GameObject> gameObjects;  // Collection of all game objects
	
	boolean running; // status flag, true if game is still running
	
	/** Default constructor, has constant size for game objects and the playing field */
	public BrickBreaker() {
		
		final Pair fieldSize = new Pair(40, 40);
		
		final Pair brickSize = new Pair(4, 1);
		
		final Pair platformSize = new Pair(6, 1);
		
		field = new Field(fieldSize);
		
		// Creates five rows of bricks, spanning the entire width of the playing field, with the first row offset by 4 brick heights
		Stack<Brick.Type> brickTypes = new Stack<Brick.Type>();
		brickTypes.add(Brick.Type.BLUE);
		brickTypes.add(Brick.Type.GREEN);
		brickTypes.add(Brick.Type.YELLOW);
		brickTypes.add(Brick.Type.ORANGE);
		brickTypes.add(Brick.Type.RED);
		
		bricks = new Vector<Brick>();
		for (int i = 4; i < 9; i++) {
			Brick.Type currentBrickType = brickTypes.pop();
			for (int j = 0; j < 10; j++) {
				bricks.add(new Brick(new Pair(j*brickSize.x, i*brickSize.y), brickSize, currentBrickType));
			}
		}
		
		platform = new Platform(new Pair(fieldSize.x / 2 - platformSize.x / 2, fieldSize.y - platformSize.y), platformSize, new Pair(1, 0));
		
		ball = new Ball(new Pair(fieldSize.x / 2 - 1, fieldSize.y / 2 - 1), new Pair(1, 1), new Pair(0, 1));
		
		running = true;
		
		// add game objects to vector
		gameObjects = new Vector<GameObject>();
		for (int i = 0; i < bricks.size(); i++) {
			gameObjects.add(bricks.get(i));
		}
		
		// add platform to game objects
		gameObjects.add(platform);
		
		// add ball to game objects
		gameObjects.add(ball);
	}
	
	/**
	 * Parameterized constructor
	 * @param fieldSize size of the playing field
	 * @param platformSize size of the player platform
	 * @param platformVelocity x-velocity of player platform
	 * @param brickSize size of the bricks
	 * @param ballSize size of the ball
	 * @param ballVelocity initial moving ball velocity
	 */
	public BrickBreaker(Pair fieldSize, Pair platformSize, Pair platformVelocity, Pair brickSize, Pair ballSize, Pair ballVelocity) {
		
		field = new Field(fieldSize);
				
		Stack<Brick.Type> brickTypes = new Stack<Brick.Type>();
		brickTypes.add(Brick.Type.BLUE);
		brickTypes.add(Brick.Type.GREEN);
		brickTypes.add(Brick.Type.YELLOW);
		brickTypes.add(Brick.Type.ORANGE);
		brickTypes.add(Brick.Type.RED);
		
		bricks = new Vector<Brick>();
		for (int i = 4; i < 9; i++) {
			Brick.Type currentBrickType = brickTypes.pop();
			for (int j = 0; j < 10; j++) {
				bricks.add(new Brick(new Pair(j*brickSize.x, i*brickSize.y), brickSize, currentBrickType));
			}
		}
		
		// create platform with params and in the bottom middle of the playing field
		platform = new Platform(new Pair(fieldSize.x / 2 - platformSize.x / 2, fieldSize.y - platformSize.y), platformSize, platformVelocity);
		
		// create ball with params and in the center of the playing field
		ball = new Ball(new Pair(fieldSize.x / 2 - ballSize.x / 2, fieldSize.y / 2 - ballSize.y / 2), ballSize, ballVelocity);
		
		running = true;
		
		// add game objects to vector
		gameObjects = new Vector<GameObject>();
		for (int i = 0; i < bricks.size(); i++) {
			gameObjects.add(bricks.get(i));
		}
		
		// add platform to game objects
		gameObjects.add(platform);
		
		// add ball to game objects
		gameObjects.add(ball);
		
	}
	
	/**
	 * Playing field getter
	 * @return the playing field (it's just a dimension, width and height)
	 */
	public Field getField() {
		return field;
	}
	
	/**
	 * Game objects getter
	 * @return all of the game objects in a vector
	 */
	public Vector<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	/**
	 * Game running status getter
	 * @return true if game over conditions are not met, otherwise returns false
	 */
	public boolean getStatus() {
		return running;
	}
	
	/**
	 * Takes user input and if input is valid performs the passed input action
	 * @param input 
	 */
	public void processInput(Input input) {
		switch (input) {
		case MOVE_PLATFORM_RIGHT:
			platform.moveRight();
			break;
		case MOVE_PLATFORM_LEFT:
			platform.moveLeft();
			break;
		}
	}
	
	/**
	 * Update method, updates all of the game objects
	 */
	public void update() {
		
		// Move platform within bounds
		if (platform.getPos().x < 0) {
			platform.setPos(new Pair(0, platform.getPos().y));
		}
		else if (platform.getPos().x + platform.getSize().x >= field.getSize().x) {
			platform.setPos(new Pair(field.getSize().x - platform.getSize().x, platform.getPos().y));
		}
		
		// Move ball
		ball.update();
		
		// Ball bounces off walls
		if (ball.getPos().x < 0) {
			// return ball to position before a wall was hit
			ball.setPos(new Pair(0, ball.getPos().y));
			
			ball.setVel(new Pair(-1 * ball.getVel().x, ball.getVel().y));
		}
		else if (ball.getPos().x + ball.getSize().x >= field.getSize().x) {
			ball.setPos(new Pair(field.getSize().x - ball.getSize().x, ball.getPos().y));
			
			ball.setVel(new Pair(-1 * ball.getVel().x, ball.getVel().y));
		}
		
		// Ball bounces off the platform
		if (ball.isColliding(platform)) {
			// return ball to position before collision
			ball.move(-ball.getVel().x, -ball.getVel().y);
			
			//float dx = (ball.getPos().x - (platform.getPos().x + platform.getSize().x / 2));
			float dx = Math.abs(ball.getVel().y) * (ball.getPos().x + ball.getSize().x / 2 - (platform.getPos().x + platform.getSize().x / 2)) / (platform.getSize().x / 2);
			
			ball.setVel(new Pair(dx, -1 * ball.getVel().y));
		}
		
		// if there are no more bricks (game over)
		running = false;
		for (int i = 0; i < gameObjects.size(); i++) {
			if (gameObjects.get(i) instanceof Brick) {
				running = true;
				break;
			}
		}
		
		// Ball falls off (game over)
		if (ball.getPos().y >= field.getSize().y) {
			ball.move(-ball.getVel().x, -ball.getVel().y);
			// Set running to false to indicate game over
			running = false;
		}
		
		// Ball bounces off the ceiling
		if (ball.getPos().y < 0) {
			// return ball to position before hitting the ceiling
			ball.move(-ball.getVel().x, -ball.getVel().y);
			
			ball.setVel(new Pair(ball.getVel().x, -1 * ball.getVel().y));
		}
		
		// If ball hit a brick
		for (int i = 0; i < gameObjects.size(); i++) {
			if (gameObjects.get(i) instanceof Brick && ball.isColliding(gameObjects.get(i))) {
				
				gameObjects.remove(i);
				
				ball.move(-ball.getVel().x, -ball.getVel().y);
				
				ball.setVel(new Pair(ball.getVel().x, -1 * ball.getVel().y));
				
				break;
			}
		}
	}
	
}
