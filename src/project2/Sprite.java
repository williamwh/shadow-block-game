package project2;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
/**
 * Sprite class is used as a parent class of all entities in this game
 * @author Wei He
 *
 */
public abstract class Sprite {
	/** Used to decide what direction an object is moving*/
	public static final int DIR_NONE = 0;
	/** Used to decide what direction an object is moving*/
	public static final int DIR_LEFT = -1;
	/** Used to decide what direction an object is moving*/
	public static final int DIR_RIGHT = 1;
	/** Used to decide what direction an object is moving*/
	public static final int DIR_UP = -1;
	/** Used to decide what direction an object is moving*/
	public static final int DIR_DOWN = 1;
	/** ratio for converting a second to a millisecond*/
	public static final float TO_MILLI = 1000;
	/** an empty string */
	public static final String EMPTY = "";
	
	// an array list used to store all past positions of a sprite
	private ArrayList<Position> oldPositions = new ArrayList<>();
	// indicates whether to render a sprite
	private boolean canRender = true;
	// stores an image of a sprite
	private Image image = null;
	// x and y coordinates of a sprite
	private float x;
	private float y;
	/**
	 * initialize a sprite 
	 * @param x x-coordinate of a sprite
	 * @param y y-coordinate of a sprite
	 * @param image_src image source path of a sprite
	 */
	public Sprite(String image_src, float x, float y) {
		try {
			image = new Image(image_src);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.x = x;
		this.y = y;
	}
	/** 
	 * update a sprite
	 * @param input references to Slick library
	 * @param delta time elapsed since last call
	 */
	public abstract void update(Input input, int delta);
	
    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
	public void render(Graphics g) {
		// variable used to control when to render
		if (canRender) {
			image.drawCentered(x, y);
		}
	}
	/**
	 * getter for x variable
	 * */
	public float getx() {
		return x;
	}
	/**
	 * getter for y variable
	 * */
	public float gety() {
		return y;
	}
	/**
	 * setter for both x and y variables
	 * @param x x-coordinate of a sprite
	 * @param y y-coordinate of a sprite
	 * @param type the type of a sprite
	 * */
	public void setxy(float x, float y, String type) {
		// update the position array
		Loader.updateTypes(this.x, this.y, EMPTY);
		Loader.updateTypes(x, y, type);
		this.x = x;
		this.y = y;
	}
	/**
	 * replace current image source to a new image source path
	 * @param image_src image source path
	 * */
	public void setImage(String image_src) {
		try {
			image = new Image(image_src);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	/**
	 * setter for canRender variable
	 * this variable is used to determine when to render and when to not render
	 * @param canRender either true or false
	 * */
	public void setcanRender(boolean canRender) {
		this.canRender = canRender;
	}
	/**
	 * record the last position
	 * @param x x-coordinate of a sprite
	 * @param y y-coordinate of a sprite
	 * */
	public void addPos(float x, float y) {
		Position pos = new Position(x, y);
		oldPositions.add(pos);
	}
	/**
	 * get the last position from the array list
	 * @return an object with Position class
	 * */
	public Position getLastPos() {
		Position position = oldPositions.get(oldPositions.size()-1);
		remLastPos();
		return position;
	}
	/**
	 * remove the last position from the array list
	 * */
	public void remLastPos() {
		oldPositions.remove(oldPositions.size()-1);
	}
	/**
	 * getter for the array list of old positions
	 * */
	public ArrayList<Position> getoldPositions(){
		return oldPositions;
	}
	/**
	 * initialize the array list of old positions
	 * */
	public void setoldPositions(){
		oldPositions =  new ArrayList<Position>();
	}
}
