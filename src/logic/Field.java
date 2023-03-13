package logic;

/** Playing field class, contains width and height */
public class Field {
	
	Pair size;
	
	/**
	 * Parameterized constructor
	 * @param size the dimensions of the playing field
	 */
	public Field(Pair size) {
		this.size = size;
	}
	/**
	 * Playing field dimensions getter
	 * @return dimensions of the playing field
	 */
	public Pair getSize() {
		return size;
	}
	
}
