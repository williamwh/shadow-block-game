package project2;

import org.newdawn.slick.Input;

/**
 * Tnt class is a subclass of a Block class
 * it is used to implement a TNT block
 * @author Wei He
 *
 */
public class Tnt extends Block {
	/**type of this class*/
	public static final String TYPE = "tnt";
	private final double EXPLO_TIME = 0.4;
	// TNT image file path
	private static final String TNT_IMAGE = "res/tnt.png";
	// Explosion effect image file path
	private static final String EXPLO_IMAGE = "res/explosion.png";
	// indicates whether the time is set
	private static boolean set = false;
	// the time at when it was set
	private int setTime;
	/**
	 * initialize a Tnt 
	 * @param x x-coordinate of a Tnt
	 * @param y y-coordinate of a Tnt
	 */
	public Tnt(float x, float y) {
		super(TNT_IMAGE, x, y);
	}
	@Override
	public void update(Input input, int delta) {
		//calculate new x and new y
		float newx = getx()+Player.getMovex();
		float newy = gety()+Player.getMovey();
		// explosion effect disappears after a certain period of time
		if (set && App.getTime()-setTime > EXPLO_TIME*TO_MILLI) {
			//remove the tnt sprite
		    World.setRemoveSprite(this.getClass().getSimpleName());
			Loader.updateTypes(newx, newy, EMPTY);
		}
		// move tnt when pushed by player, and record positions
		if (Loader.getTypes()[Loader.convertX(getx())][Loader.convertY(gety())].equals(Player.TYPE)) {
			addPos(getx(), gety());
			// when tnt is exploded, put up the explosion effect image 
			if (isExploded(Loader.convertX(newx), Loader.convertY(newy))){
				setImage(EXPLO_IMAGE);
				setTime = App.getTime();
				set = true;
				setxy(newx, newy, TYPE);
			}
			// update the new position of tnt if it is not exploded
			if (!set) {
				setxy(newx, newy, TYPE);
			}
		}
	}
	/**
	 * determine if the tnt is exploded
	 * @param x x-coordinate of a Tnt
	 * @param y y-coordinate of a Tnt
	 * @return true if exploded, otherwise false
	 * */
	public boolean isExploded(int x, int y) {
		if (Loader.getTypes()[x][y].equals(Cracked.TYPE)) {
			return true;
		}
		return false;
	}
	/**
	 * getter for set
	 * */
	public static boolean getSet() {
		return set;
	}
	/**
	 * initialize the set
	 * */
	public static void initTnt() {
		set = false;
	}
}
