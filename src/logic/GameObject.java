package logic;

/** GameObject class, base class from which all game objects inherit */
public class GameObject {

	Pair pos;
	Pair size;
	
	/**
	 * Parameterized constructor
	 * @param pos position of the game object
	 * @param size size of the game object
	 */
	public GameObject(Pair pos, Pair size) {
		this.pos = pos;
		this.size = size;
	}
	
	/**
	 * Position getter
	 * @return the position of the game object
	 */
	public Pair getPos() {
		return pos;
	}
	
	/**
	 * Position setter
	 * @param pos new game object position
	 */
	public void setPos(Pair pos) {
		this.pos = pos;
	}
	
	/**
	 * Size getter
	 * @return size of game object
	 */
	public Pair getSize() {
		return size;
	}
	
	/**
	 * Checks whether game object collides with the other passed object<br>
	 * Collision based on Axis Aligned Bounding Boxes
	 * @param object the object to check for collision
	 * @return true if there is collision, otherwise false
	 */
	public boolean isColliding(GameObject object) {
		if (pos.x < object.pos.x + object.size.x &&
			pos.x + size.x > object.pos.x &&
			pos.y < object.pos.y + object.size.y &&
			pos.y + size.y > object.pos.y) {
			return true;
		}
		return false;
	}
	
	/**
	 * Moves object from current position by given delta
	 * @param dx move units in the x axis
	 * @param dy move units in the y axis
	 */
	public void move(float dx, float dy) {
		pos.x += dx;
		pos.y += dy;
	}

}
