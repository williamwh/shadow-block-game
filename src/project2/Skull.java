package project2;

import org.newdawn.slick.Input;
/**
 * Skull class is a subclass of a Unit class
 * it is used to implement a skeleton
 * @author Wei He
 *
 */
public class Skull extends Unit{
	/**type of this class*/
	public static final String TYPE = "skeleton";
	// image file path
	private static final String IMAGE = "res/skull.png";
	// the moving speed of a skeleton
	private final int SKU_SPEED = 1;
	// the moving direction of a skeleton
	private static int dir = DIR_UP;
	// indicates if the time has been set
	private static boolean set = false;
	// time at when it was set
	private int setTime;
	/**
	 * initialize a Skull 
	 * @param x x-coordinate of a Skull
	 * @param y y-coordinate of a Skull
	 */   
	public Skull(float x, float y) {
		super(IMAGE, x, y);
	}
	@Override
	public void update(Input input, int delta) {
		/*record the time*/
		if (!set) {
			setTime = App.getTime();
			set = true;
		}
		/*skull moves up or down at a rate of once per second*/
		if (set && App.getTime()-setTime > SKU_SPEED*TO_MILLI) {
			/*reverse direction when it is blocked, and reset time*/
			if (Loader.isWall(getx(), gety()+dir*App.TILE_SIZE)
					|| Loader.isBlock(getx(), gety()+dir*App.TILE_SIZE)) {
				if (dir == DIR_UP) {
					dir = DIR_DOWN;
					set = false;
				}else {
					dir = DIR_UP;
					set = false;
				}
			}else {
				/*if the move is valid, move to the new position*/
				if (!Loader.isWall(getx(), gety()+dir*App.TILE_SIZE)
						&& !Loader.isBlock(getx(), gety()+dir*App.TILE_SIZE)) {
					float newy = gety()+dir*App.TILE_SIZE;
					setxy(getx(), newy, TYPE);
					set = false;
				}
			}
		}
	}
	/**
	 * initialize the variables for this class
	 * */
	public static void initSkull() {
		set = false;
		dir = DIR_UP;
	}
}
