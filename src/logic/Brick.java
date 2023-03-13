package logic;

/** Represents rectangular brick game object that breaks. */
public class Brick extends GameObject {
	
	/** Brick type enum, signifies what color is a given brick  */
	public enum Type {
		RED,
		ORANGE,
		YELLOW,
		GREEN,
		BLUE
	}

	Type type;
	
	/**
	 * Basic constructor
	 * @param pos the brick coordinates
	 * @param size the brick size
	 * @param type the brick type, instance of Brick.Type enumeration
	 */
	public Brick(Pair pos, Pair size, Type type) {
		super(pos, size);
		
		this.type = type;
	}
	
	/**
	 * Getter
	 * @return the brick type
	 */
	public Type getType() {
		return type;
	}

}
