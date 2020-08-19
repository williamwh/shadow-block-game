package project2;

import org.newdawn.slick.Input;

/**
 * Target class is a subclass of a Sprite class
 * it is used to implement a target tile
 * @author Wei He
 *
 */
public class Target extends Sprite {
	public static final String TYPE = "target";
	private static final String IMAGE = "res/target.png";
	/**
	 * initialize a target 
	 * @param x x-coordinate of a target
	 * @param y y-coordinate of a target
	 */
	public Target(float x, float y) {
		super(IMAGE, x, y);
	}
	@Override
	public void update(Input input, int delta) {
	}
}
