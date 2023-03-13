package logic;

/** Base class for all moving game objects, objects with a specified velocity */
public class MovingGameObject extends GameObject {
	
	Pair vel;
	
	/**
	 * Parameterized constructor
	 * @param pos position of moving object
	 * @param size size of moving object
	 * @param vel velocity of moving object
	 */
	public MovingGameObject(Pair pos, Pair size, Pair vel) {
		super(pos, size);
		
		this.vel = vel;
	}

	/**
	 * Velocity getter
	 * @return the objects velocity
	 */
	public Pair getVel() {
		return vel;
	}
	
	/**
	 * Velocity setter
	 * @param vel objects new velocity
	 */
	public void setVel(Pair vel) {
		this.vel = vel;
	}
	
	/** Adjusts position based on current velocity of object */
	public void update() {
		pos.x += vel.x;
		pos.y += vel.y;
	}
	
}
