package project2;

import org.newdawn.slick.Input;
/**
 * Mage class is a subclass of a Unit class
 * it is used to implement a mage
 * @author Wei He
 *
 */
public class Mage extends Unit{
	/** type of this class*/
	public static final String TYPE = "mage";
	// image file path
	private static final String IMAGE = "res/mage.png";
	// stores the horizontal distance between a player and a mage
	private float distX;
	// stores the vertical distance between a player and a mage
	private float distY;
	/**
	 * initialize a mage 
	 * @param x x-coordinate of a mage
	 * @param y y-coordinate of a mage
	 */
	public Mage(float x, float y) {
		super(IMAGE, x, y);
	}
	@Override
	public void update(Input input, int delta) {
		/*calculate the distance between player and mage, then apply the 
		 * algorithm on mage*/
		findDistance();
		/*algorithm runs every time when player makes a move*/
		if (Player.getMovex() != DIR_NONE || Player.getMovey() != DIR_NONE) {
			if (Math.abs(distX) > Math.abs(distY)) {
				float newx = getx()+App.TILE_SIZE*sgn(distX);
				/*check whether a move is valid*/
				if (!Loader.isWall(newx, gety()) && !Loader.isBlock(newx, gety())) {
					setxy(newx, gety(), TYPE);
				}
			}else {
				float newy = gety()+App.TILE_SIZE*sgn(distY);
				/*check whether a move is valid*/
				if (!Loader.isWall(getx(), newy) && !Loader.isBlock(getx(), newy)) {
					setxy(getx(), newy, TYPE);
				}
			}
		}	
	}
	/** 
	 * find the distance between player and mage
	 */
	public void findDistance() {
		/*loop over to find player*/
		for (Sprite sprite : World.getSprites()) {
			if (sprite instanceof Player) {
				distX = sprite.getx()-getx();
				distY = sprite.gety()-gety();
			}
		}
	}
	/** 
	 * a function used to determine which direction should the mage move
	 * @param dist x or y distance between player and mage
	 * @return -1 when dist is less than 0, otherwise 1
	 */
	public int sgn(float dist) {
		if (dist < 0) {
			return -1;
		}
		return 1;
	}
}
