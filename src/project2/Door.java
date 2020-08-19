package project2;

import org.newdawn.slick.Input;
/**
 * Door class is a subclass of a sprite class
 * it is used to implement a door tile
 * @author Wei He
 *
 */
public class Door extends Sprite {
	/** type of this class*/
	public static final String TYPE = "door";
	// image file path
	private static final String IMAGE = "res/door.png";
	// count the number of times a method has been called
	private boolean needRemove = true;
	/** 
	 * initialize a door 
	 * @param x x-coordinate of a door
	 * @param y y-coordinate of a door
	 */
	public Door(float x, float y) {
		super(IMAGE, x, y);
	}
	@Override
	public void update(Input input, int delta) {
		/*disappear the door while switch is covered*/
		if (Switch.getCovered()) {
			setcanRender(false);
			/* remove the door from "types" array at the first
			 * time when the switch is detected as uncovered. To avoid other
			 * sprites being removed from the array due to the update sequence of sprites*/
			if (needRemove) {
				Loader.updateTypes(getx(), gety(), EMPTY);
				needRemove = false;
			}
		}else {
			/*show the door while the switch is not covered*/
			setcanRender(true);
			Loader.updateTypes(getx(), gety(), TYPE);
			needRemove = true;
		}
	}
}
