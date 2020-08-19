package project2;
/**
 * Position class is used to store the position of a sprite
 * it is useful especially for storing past positions of a sprite into an array list of Position
 * @author Wei He
 *
 */
public class Position {
	// x and y coordinates of a position
	private float x;
	private float y;
	/**
	 * initialize a position 
	 * @param x x-coordinate of a position
	 * @param y y-coordinate of a position
	 */
	public Position(float x, float y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * getter for x variable
	 * @return  x
	 * */
	public float getx() {
		return x;
	}
	/**
	 * getter for y variable
	 * @return  y
	 * */
	public float gety() {
		return y;
	}
}
