package project2;

import org.newdawn.slick.Input;
/**
 * Ice class is a subclass of a block class
 * it is used to implement a ice block
 * @author Wei He
 *
 */
public class Ice extends Block{
	/** type of this class*/
	public static final String TYPE = "ice";
	/** ice moves at 0.25s per tile*/
	public final double ICE_SPEED = 0.25;
	// image file path
	private static final String IMAGE = "res/ice.png";
	// indicates whether the time is set
	private static boolean set = true;
	// the time at when it was set
	private int setTime;
	// determines if a ice needs to move
	private static boolean needMove = false;
	// variables used to store the movement of player at when it pushes the ice
	private float movex = 0;
	private float movey = 0;
	/**
	 * initialize an ice 
	 * @param x x-coordinate of an ice
	 * @param y y-coordinate of an ice
	 */
	public Ice(float x, float y) {
		super(IMAGE, x, y);
	}
	@Override
	public void update(Input input, int delta) {
		/* record time*/
		if (!set) {
			setTime = App.getTime();
			set = true;
		}
		/* ice moves every 0.25 seconds until hit an object*/
		if (needMove && set && App.getTime()-setTime > ICE_SPEED*TO_MILLI) {
			/*calculate new x and y*/
			float newx = getx() + movex;
			float newy = gety() + movey;
			/*move if it is valid*/
			if (canMove(newx, newy)) {
				setxy(newx, newy, TYPE);
				set = false;
			}else {
				needMove = false;
			}
		}
		/*when player has pushed the ice, record the direction of the ice being pushed*/
		if (Loader.getTypes()[Loader.convertX(getx())][Loader.convertY(gety())].equals("player")) {
			movex = Player.getMovex();
			movey = Player.getMovey();
			needMove = true;
		}
		/* record the position of ice each time when player makes a move*/
		if (Player.getMovex() != DIR_NONE || Player.getMovey() != DIR_NONE) {
			addPos(getx(), gety());
		}
	}
	/*initialize variables*/
	public static void initIce() {
		set = true;
		needMove = false;
	}
	/*return whether a ice is moving*/
	public static boolean isMoving() {
		return needMove;
	}
}
