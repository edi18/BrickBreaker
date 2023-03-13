package logic;

/**
 * Player platform class<br>
 * Has two methods for manual movement
 */
public class Platform extends MovingGameObject {
	
	/**
	 * Parameterized constructor
	 * @param pos initial position of platform
	 * @param size size of platform
	 * @param xvel x-velocity of platform
	 */
	public Platform(Pair pos, Pair size, Pair xvel) {
		super(pos, size, xvel);
	}
	
	/**
	 * Move platform to the left by x-velocity
	 */
	public void moveLeft() {
		pos.x -= vel.x;
	}
	
	/**
	 * Move platform to the right by x-velocity
	 */
	public void moveRight() {
		pos.x += vel.x;
	}
	
}
