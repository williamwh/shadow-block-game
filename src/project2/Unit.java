package project2;

/**
 * Unit class is a subclass of a sprite class
 * and a superclass of all units like player, rogue, ...
 * it shares all common methods for its subclasses
 * @author Wei He
 *
 */
public abstract class Unit extends Sprite {
	/**
	 * initialize a unit 
	 * @param image image's source path
	 * @param x x-coordinate of a unit
	 * @param y y-coordinate of a unit
	 */
	public Unit(String image, float x, float y) {
		super(image, x, y);
	}
	/**
	 * determine whether a move is valid for a unit
	 * @param x x-coordinate of a unit
	 * @param y y-coordinate of a unit
	 * @param move_x distance of movement for x
	 * @param move_x distance of movement for y
	 * @return true if move is valid, otherwise false
	 * */
	public static boolean canMove(float x, float y, float move_x, float move_y) {
		// get the position array
		String[][] types = Loader.getTypes();
		
		// if next position is a wall
		if (Loader.isWall(x, y)) {
			return false;
			
		// tnt can move into a cracked wall, and so the unit can move when encounter
		// a tnt with a cracked wall behind it
		}else if (types[Loader.convertX(x)][Loader.convertY(y)].equals(Tnt.TYPE) &&
			types[Loader.convertX(x+move_x)][Loader.convertY(y+move_y)].equals(Cracked.TYPE)) {
			return true;
			
		// when ice is moving, unit should not move through it
		}else if (types[Loader.convertX(x)][Loader.convertY(y)].equals(Ice.TYPE)
				&& Ice.isMoving()) {
			return false;
			
		// if next position is one of the blocks and the blocks cannot be pushed,
		// unit should not move
		}else if (Loader.isBlock(x, y) && !canPush(x+move_x, y+move_y)) {
			return false;
		}
		
		// default to true
		return true;
	}
	/**
	 * check whether a unit can push the block
	 * @param x x-coordinate of a sprite behind the block
	 * @param y y-coordinate of a sprite behind the block
	 * @return true if can be pushed, otherwise false
	 * */
	public static boolean canPush(float x, float y) {
		if (Loader.isBlock(x, y) || Loader.isWall(x, y) || Loader.isUnit(x, y)) {
			return false;
		}
		return true;
	}
}
