package project2;

import org.newdawn.slick.Input;
/**
 * Floor class is a subclass of a sprite class
 * it is used to implement a floor tile
 * @author Wei He
 *
 */
public class Floor extends Sprite{
	/** type of this class*/
	public static final String TYPE = "floor";
	// image file path
	private static final String IMAGE = "res/floor.png";
	/**
	 * initialize a floor
	 * @param x x-coordinate of a floor
	 * @param y y-coordinate of a floor
	 */
	public Floor(float x, float y) {
		super(IMAGE, x, y);
	}
	@Override
	public void update(Input input, int delta) {
		
	}
}
