package logic;

/** Class that represents the rectangular moving ball object. */
public class Ball extends MovingGameObject {
	
	/**
	 * Basic constructor
	 * @param pos the initial ball position
	 * @param size the size of the ball
	 * @param vel the initial velocity of the ball
	 */
	public Ball(Pair pos, Pair size, Pair vel) {
		super(pos, size, vel);		
	}
	
}
