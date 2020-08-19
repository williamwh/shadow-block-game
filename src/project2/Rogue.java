package project2;

import org.newdawn.slick.Input;
/**
 * Rogue class is a subclass of a Unit class
 * it is used to implement a rogue
 * @author Wei He
 *
 */
public class Rogue extends Unit{
	/**type of this class*/
	public static final String TYPE = "rogue";
	// image file path
	private static final String IMAGE = "res/rogue.png";
	// moving direction of a rogue
	private static int dir = DIR_LEFT;
	/**
	 * initialize a rogue 
	 * @param x x-coordinate of a rogue
	 * @param y y-coordinate of a rogue
	 */           
	public Rogue(float x, float y) {
		super(IMAGE, x, y);
	}
	@Override
	public void update(Input input, int delta) {
		/*moves when player makes a move*/
		if (Player.getMovex() != DIR_NONE || Player.getMovey() != DIR_NONE) {
			/*reverse direction when it is blocked*/
			if (!canMove(getx()+dir*App.TILE_SIZE, gety(), dir*App.TILE_SIZE, DIR_NONE)) {
				if (dir == DIR_LEFT) {
					dir = DIR_RIGHT;
				}else {
					dir = DIR_LEFT;
				}
			}
			// Move to our destination
			float newx = getx()+dir*App.TILE_SIZE;
			if (canMove(newx, gety(), dir*App.TILE_SIZE, DIR_NONE)) {
				setxy(newx, gety(), TYPE);
			}
		}
		
	}
	/**
	 * getter for horizontal move of Rogue
	 * */
	public static int getDir() {
		return dir;
	}
	/**
	 * initialize the moving direction of Rogue
	 * */
	public static void initDir() {
		dir = DIR_LEFT;
	}
}
