package project2;

import org.newdawn.slick.Input;
/**
 * Cracked class is a subclass of a sprite class
 * it is used to implement a cracked wall tile
 * @author Wei He
 *
 */
public class Cracked extends Sprite {
	/** type of this class*/
	public static final String TYPE = "cracked";
	// image file path
	private static final String IMAGE = "res/cracked_wall.png";
	/**
	 * initialize a cracked door 
	 * @param x x-coordinate of a cracked door
	 * @param y y-coordinate of a cracked door
	 */
	public Cracked(float x, float y) {
		super(IMAGE, x, y);
	}
	@Override
	public void update(Input input, int delta) {
		/*remove cracked door when Tnt is exploded*/
		if (Tnt.getSet()) {
			World.setRemoveSprite(this.getClass().getSimpleName());
		}
	}
}
