package project2;

import org.newdawn.slick.Input;
/**
 * Stone class is a subclass of a block class
 * it is used to implement a stone block
 * @author Wei He
 *
 */
public class Stone extends Block {
	/** type of this class*/
	public static final String TYPE = "stone";
	// image file path
	private static final String IMAGE = "res/stone.png";
	/**
	 * initialize a stone 
	 * @param x x-coordinate of a stone
	 * @param y y-coordinate of a stone
	 */
	public Stone(float x, float y) {
		super(IMAGE, x, y);
	}
	@Override
	public void update(Input input, int delta) {
		String[][] types = Loader.getTypes();
		//record old position
		if (Player.getMovex() != DIR_NONE || Player.getMovey() != DIR_NONE) {
			addPos(getx(), gety());
		}
		// move stone when pushed by player
		if (types[Loader.convertX(getx())][Loader.convertY(gety())].equals(Player.TYPE)) {
			float newx = getx()+Player.getMovex();
			float newy = gety()+Player.getMovey();
			setxy(newx, newy, TYPE);
		}
		// move stone when pushed by rogue
		if (types[Loader.convertX(getx())][Loader.convertY(gety())].equals(Rogue.TYPE)) {
			float newx = getx()+Rogue.getDir()*App.TILE_SIZE;
			setxy(newx, gety(), TYPE);
		}
	}
}
