package project2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Loader class is used to read a level file, 
 * and use the information from it to create an array list of sprite
 * @author Wei He
 *
 */
public class Loader {
	// define comma as a final variable
	private static final String COMMA = ",";
	// record the positions of sprites
	private static String[][] types;
	// width of the game world
	private static int world_width;
	// height of the game world
	private static int world_height;
	// the starting x and y coordinates of a game world
	private static int offset_x;
	private static int offset_y;
	/**
	 * Create the appropriate sprite given a name and location.
	 * @param name	the name of the sprite
	 * @param x		the x position
	 * @param y		the y position
	 * @return		the sprite object
	 */
	private static Sprite createSprite(String name, float x, float y) {
		switch (name) {
		    case Player.TYPE:
			    return new Player(x, y);
		    case Skull.TYPE:
			    return new Skull(x, y);
		    case Mage.TYPE:
			    return new Mage(x, y);
		    case Rogue.TYPE:
			    return new Rogue(x, y);
			case Tnt.TYPE:
				return new Tnt(x, y);
			case Stone.TYPE:
				return new Stone(x, y);
			case Ice.TYPE:
				return new Ice(x, y);
			case Floor.TYPE:
				return new Floor(x, y);
			case Wall.TYPE:
				return new Wall(x, y);
			case Target.TYPE:
				return new Target(x, y);
			case Door.TYPE:
				return new Door(x, y);
			case Cracked.TYPE:
				return new Cracked(x, y);
			case Switch.TYPE:
				return new Switch(x, y);
		}
		return null;
	}
	
	/** 
	 * Converts a world coordinate to a tile coordinate,
	 * and returns if that location is a wall or door tile
	 * @param x x-coordinate of a sprite
	 * @param y y-coordinate of a sprite
	 * @return  true if it is one of wall, cracked or door, otherwise false
	 */
	public static boolean isWall(float x, float y) {
		int newx = convertX(x);
		int newy = convertY(y);
		// Do bounds checking!
		if (newx >= 0 && newx < world_width && newy >= 0 && newy < world_height) {
			return types[newx][newy].equals(Wall.TYPE)
					|| types[newx][newy].equals(Cracked.TYPE)
					|| types[newx][newy].equals(Door.TYPE);
		}
		// Default to false
		return false;
	}
	/** 
	 * Converts a world coordinate to a tile coordinate,
	 * and returns if that location is a block tile
	 * @param x x-coordinate of a sprite
	 * @param y y-coordinate of a sprite
	 * @return  true if it is one of the blocks, otherwise false
	 */
	public static boolean isBlock(float x, float y) {
		int newx = convertX(x);
		int newy = convertY(y);
		// Do bounds checking!
		if (newx >= 0 && newx < world_width && newy >= 0 && newy < world_height) {
			return (types[newx][newy].equals(Stone.TYPE) 
					|| types[newx][newy].equals(Ice.TYPE) 
					|| types[newx][newy].equals(Tnt.TYPE));
			
		}
		// Default to false
		return false;
	}
	
	/** 
	 * Converts a world coordinate to a tile coordinate,
	 * and returns if that location is a unit tile
	 * @param x x-coordinate of a sprite
	 * @param y y-coordinate of a sprite
	 * @return  true if it is one of the units, otherwise false
	 */
	public static boolean isUnit(float x, float y) {
		int newx = convertX(x);
		int newy = convertY(y);
		// Do bounds checking!
		if (newx >= 0 && newx < world_width && newy >= 0 && newy < world_height) {
			return (types[newx][newy].equals(Mage.TYPE) || 
					types[newx][newy].equals(Skull.TYPE) ||
					types[newx][newy].equals(Rogue.TYPE) ||
					types[newx][newy].equals(Player.TYPE));
			
		}
		// Default to false
		return false;
	}
	/**
	 * Converts a world coordinate to a tile coordinate, 
	 * and update the types array
	 * @param x x-coordinate of a sprite
	 * @param y y-coordinate of a sprite
	 * @param type type of the sprite
	 * */
	public static void updateTypes(float x, float y, String type) {
		int newx = convertX(x);
		int newy = convertY(y);
		// Do bounds checking!
		if (newx >= 0 && newx < world_width && newy >= 0 && newy < world_height) {
			types[newx][newy] = type;
		}
	}
		
	/**
	 * Loads the sprites from a given file.
	 * @param filename name of a file
	 * @return ArrayList<Sprite>
	 */
	public static ArrayList<Sprite> loadSprites(String filename) {
		ArrayList<Sprite> list = new ArrayList<>();
		
		// Open the file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			
			// Find the world size
			line = reader.readLine();
			String[] parts = line.split(COMMA);
			world_width = Integer.parseInt(parts[0]);
			world_height = Integer.parseInt(parts[1]);
			
			// Create the array of object types
			types = new String[world_width][world_height];
			
			// Calculate the top left of the tiles so that the level is
			// centred
			offset_x = App.SCREEN_WIDTH/2 - world_width/2 * App.TILE_SIZE;
			offset_y = App.SCREEN_HEIGHT/2 - world_height/2 * App.TILE_SIZE;

			// Loop over every line of the file
			while ((line = reader.readLine()) != null) {
				String name;
				float x, y;
				
				// Split the line into parts
				parts = line.split(COMMA);
				name = parts[0];
				x = Integer.parseInt(parts[1]);
				y = Integer.parseInt(parts[2]);
				types[(int)x][(int)y] = name;
				
				// Adjust for the grid
				x = offset_x + x * App.TILE_SIZE;
				y = offset_y + y * App.TILE_SIZE;
				
				// Create the sprite
				list.add(createSprite(name, x, y));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * convert world x-coordinate
	 * @param x x-coordinate of a sprite
	 * @return converted x
	 * */
	public static int convertX(float x) {
		x -= offset_x;
		x /= App.TILE_SIZE;
		// Rounding is important here
		x = Math.round(x);
		return (int)x;
	}
	/**
	 * convert world y-coordinate
	 * @param y y-coordinate of a sprite
	 * @return converted y
	 * */
	public static int convertY(float y) {
		y -= offset_y;
		y /= App.TILE_SIZE;
		// Rounding is important here
		y = Math.round(y);
		return (int)y;
	}
	/**
	 * getter of types array
	 * @return types array
	 * */
	public static String[][] getTypes() {
		return types;
	}
}
