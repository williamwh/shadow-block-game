package project2;

import org.newdawn.slick.Input;

/**
 * Switch class is a subclass of a sprite class
 * it is used to implement a switch tile
 * @author Wei He
 *
 */
public class Switch extends Sprite{
	/**type of this class*/
	public static final String TYPE = "switch";
	// indicates if the switch is covered
	private static boolean covered;
	// image file path
	private static final String IMAGE = "res/switch.png";
	/**
	 * initialize a switch 
	 * @param x x-coordinate of a switch
	 * @param y y-coordinate of a switch
	 */
	public Switch(float x, float y) {
		super(IMAGE, x, y);
	}
	@Override
	public void update(Input input, int delta) {
		// set the "covered" variable on the condition of whether a switch is covered
		// by a block
		if (Loader.isBlock(getx(), gety())) {
			covered = true;
		}else {
			covered = false;
		}
	}
	/**
	 * getter for "covered" variable
	 * */
	public static boolean getCovered() {
		return covered;
	}
}
