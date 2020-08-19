package project2;

import org.newdawn.slick.Input;

/**
 * Wall class is a subclass of a sprite class
 * it is used to implement a wall tile
 * @author Wei He
 *
 */
public class Wall extends Sprite {
	/**type of this class*/
	public static final String TYPE = "wall";
	// image file path
	private static final String IMAGE = "res/wall.png";
	/**
	 * initialize a wall 
	 * @param x x-coordinate of a wall
	 * @param y y-coordinate of a wall
	 */
	public Wall(float x, float y) {
		super(IMAGE, x, y);
	}
	@Override
	public void update(Input input, int delta) {
	}
}
