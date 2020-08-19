package project2;

import org.newdawn.slick.Input;
/**
 * Player class is a subclass of a Unit class
 * it is used to implement a player
 * @author Wei He
 *
 */
public class Player extends Unit {
	/**type of this class*/
	public static final String TYPE = "player";
	// the movement of x-coordinate of a player
	private static int move_x;
	// the movement of y-coordinate of a player
	private static int move_y;
	// image file path
	private static final String IMAGE = "res/player_left.png";
	/**
	 * initialize a player 
	 * @param x x-coordinate of a player
	 * @param y y-coordinate of a player
	 */
	public Player(float x, float y) {
		super(IMAGE, x, y);
	}

	@Override
	public void update(Input input, int delta) {
		/*initialize the move variables*/
		move_x = DIR_NONE;
		move_y = DIR_NONE;
		/*change the value of move variables when some keys are pressed*/
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			move_x = -App.TILE_SIZE;
		}
		else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			move_x = App.TILE_SIZE;
		}
		else if (input.isKeyPressed(Input.KEY_UP)) {
			move_y = -App.TILE_SIZE;
		}
		else if (input.isKeyPressed(Input.KEY_DOWN)) {
			move_y = App.TILE_SIZE;
		}
		/*if the player has moved, record its last position*/
		if (move_x != DIR_NONE || move_y != DIR_NONE) {
			addPos(getx(), gety());
			// Move to our destination
			if (canMove(getx()+move_x, gety()+move_y, move_x, move_y)) {
				float newx = getx()+move_x;
				float newy = gety()+move_y;
				setxy(newx, newy, TYPE);
			}
			/*increase move count*/
			World.addMoveCount();
		}
	}
	/**
	 * getter for move_x variable
	 * @return  the x-distance that the player has moved
	 * */
	public static float getMovex() {
		return move_x;
	}
	/**
	 * getter for move_y variable
	 * @return  the y-distance that the player has moved
	 * */
	public static float getMovey() {
		return move_y;
	}
}
