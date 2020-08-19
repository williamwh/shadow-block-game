package project2;
/**
 * Block class is a subclass of a sprite class
 * it is used as a parent class for all blocks
 * @author Wei He
 *
 */
public abstract class Block extends Sprite{
	/** 
	 * initialize a block
	 * @param image image's source path
	 * @param x x-coordinate of a block
	 * @param y y-coordinate of a block
	 */
	public Block(String image, float x, float y) {
		super(image, x, y);
	}
	/** 
	 * check whether a move is valid for block
	 * @param x x-coordinate of a block
	 * @param y y-coordinate of a block
	 * @return  true if the move is valid, otherwise false*/
	public boolean canMove(float x, float y) {
		if (Loader.isWall(x, y) || Loader.isBlock(x, y) || Loader.isUnit(x, y)) {
			return false;
		}
		return true;
	}
}
