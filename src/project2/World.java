package project2;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * World class is for the entire game implementation
 * @author Wei He
 *
 */
public class World {
	/**total number of levels*/
	public final int TOTAL_LEVELS = 5;
	
	// prefix of level file path
	private final String PREFIX = "res/levels/";
	// suffix of level file path
	private final String SUFFIX = ".lvl";
	// a caption used to show number of moves 
	private final String MOVES = "Moves: ";
	// an array list stores all sprites
	private static ArrayList<Sprite> sprites;
	// a variable used to record the number of moves
	private static int moveCount;
	// record the type of a sprite which is needed to be removed
	private static String removeSprite = null;
	// record the player's x and y coordinates
	private float playerx;
	private float playery;
	// a integer variable indicates the current level 
	private int currentLevel = 0;
	
	/**
	 * initialize the world of game
	 */
	public World() {
		// initialize the count of move
		moveCount = 0;
		// create an array of level source paths
		//levels = new String[]{LEVEL1, LEVEL2, LEVEL3, LEVEL4, LEVEL5, LEVEL6};
		// load the sprites in current level
		sprites = Loader.loadSprites(PREFIX + currentLevel + SUFFIX);
	}
	/** 
	 * update the world of game
	 * @param input references to Slick library
	 * @param delta time elapsed since last call
	 */
	public void update(Input input, int delta) {
		/*update player first, then others, 
		  and leave the units except player till last
		 */
		updatePlayer(input, delta);
		updateOthers(input, delta);
		updateUnits(input, delta);
		/*restart game when player is contacted with other units or 
		  R is pressed*/
		if (isContacted() || input.isKeyPressed(Input.KEY_R)) {
			restartLevel();
		}
		// remove sprite when needed
		if (removeSprite != null && findIndex(removeSprite) != 0) {
			sprites.remove(findIndex(removeSprite));
			removeSprite = null;
		}
		/* when targets are all covered by blocks or N is pressed,
		   go to next level */
		if (targetAchieved() || input.isKeyPressed(Input.KEY_N)) {
			loadNextLevel();
		}
		/* when Z is pressed, roll back player and all blocks*/
		if (input.isKeyPressed(Input.KEY_Z)) {
			rollBack();
		}
	}
    /** Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
	public void render(Graphics g) {
		g.drawString(MOVES + moveCount, 0, 0);
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				sprite.render(g);
			}
		}
	}
	
	private int findIndex(String type) {
		//get the index of an array list for a specific type of sprite
		for (Sprite sprite : sprites) {
			if (sprite.getClass().getSimpleName().equals(type)) {
				return sprites.indexOf(sprite);
			}
		}
		return 0;
	}
	private void updatePlayer(Input input, int delta) {
		//loop over and update the player only
		for (Sprite sprite : sprites) {
			if (sprite instanceof Player) {
				sprite.update(input, delta);
				//store the player's position in two variables
				playerx = sprite.getx();
				playery = sprite.gety();
			}
		}
	}
	private void updateOthers(Input input, int delta) {
		//loop over to update the all sprites except units
		for (Sprite sprite : sprites) {
			if (!(sprite instanceof Player) && !(sprite instanceof Skull)
					&& !(sprite instanceof Mage) && !(sprite instanceof Rogue)) {
				sprite.update(input, delta);
			}
		}
	}

	private void updateUnits(Input input, int delta) {
		// iterate through sprites, and update the all units except player
		for (Sprite sprite : sprites) {
			if (sprite instanceof Skull || sprite instanceof Mage 
					|| sprite instanceof Rogue) {
				sprite.update(input, delta);
			}
		}
	}
	
	private void restartLevel() {
		// initialize variables
		moveCount = 0;
		App.setTime(0);
		Rogue.initDir();
		Skull.initSkull();
		Tnt.initTnt();
		Ice.initIce();
		initOldPositions();
		removeSprite = null;
		// reload the current level
		sprites = Loader.loadSprites(PREFIX + currentLevel + SUFFIX);
	}
	
	private void loadNextLevel() {
		// initialize move count
		moveCount = 0;
		//increase current level number by one, and load new level
		if (currentLevel < TOTAL_LEVELS) {
			currentLevel++;
			sprites = Loader.loadSprites(PREFIX + currentLevel + SUFFIX);
		}
	}

	private boolean targetAchieved() {
		// loop over to find targets
		for (Sprite spriteA : sprites) {
			if (spriteA instanceof Target) {
				int count = 0;
				/* loop over to check if target is covered by a block
				 * if not, increase count by one*/
				for (Sprite spriteB : sprites) {
					if (Float.compare(spriteB.getx(), spriteA.getx()) != 0
							|| Float.compare(spriteB.gety(), spriteA.gety()) != 0
							|| !(spriteB instanceof Stone || spriteB instanceof Ice)) {
						count++;
					}
				}
				/* if there is any target that is not covered by any block, 
				   targets are not achieved
				 */
				if (count == sprites.size()) {
					return false;
				}
			}
		}
		// default to true
		return true;
	}
	private void rollBack() {
		for (Sprite sprite : sprites) {
			//make sure that there is at least one old position
			if (sprite.getoldPositions().size() >= 1) {
				//get last position, and assign the sprite's position to it
				Position position = sprite.getLastPos();
				sprite.setxy(position.getx(), position.gety(),
						     sprite.getClass().getSimpleName().toLowerCase());
				// reduce the count of move by one when player undoes a move
				if (sprite instanceof Player) {
					moveCount--;
				// initialize variables in Ice after being roll back
				}else if (sprite instanceof Ice) {
					Ice.initIce();
				// initialize variables in Tnt after being roll back
			    }else if (sprite instanceof Tnt) {
					Tnt.initTnt();
			    }
			}
		}
	}
	private boolean isContacted() {
		//convert world x-coordinate of player
		int convertedX = Loader.convertX(playerx);
		for (Sprite sprite : sprites) {
			//check if player's position is same as the positions of other units
			if (sprite instanceof Skull || sprite instanceof Mage || sprite instanceof Rogue) {
				if (Float.compare(sprite.getx(), playerx) == 0
						&& Float.compare(sprite.gety(), playery) == 0) {
					return true;
				}
			/* check if player moves directly towards a rogue, and in the meantime,
			 *  the rogue moves directly towards the player.
			 * in this case, the game should restart*/
			// player moves to the right, and rogue moves to the left
			}else if (currentLevel < TOTAL_LEVELS
					&& Loader.getTypes()[convertedX-1][Loader.convertY(playery)].equals(Rogue.TYPE)
					&& Player.getMovex() == App.TILE_SIZE && Rogue.getDir() == Sprite.DIR_LEFT) {
				return true;
			// player moves to the left, and rogue moves to the right
			}else if (currentLevel < TOTAL_LEVELS
					&& Loader.getTypes()[Loader.convertX(playerx)+1][Loader.convertY(playery)].equals(Rogue.TYPE)
					&& Player.getMovex() == -App.TILE_SIZE && Rogue.getDir() == Sprite.DIR_RIGHT) {
				return true;
			}
		}
		return false;
	}

	private void initOldPositions() {
		//initialize the array list of old positions for every sprite
		for (Sprite sprite : sprites) {
			sprite.setoldPositions();
		}
	}
	/**
	 * getter for sprites
	 * */
	public static ArrayList<Sprite> getSprites() {
		return sprites;
	}
	/**
	 * getter for moveCount
	 */
	public static int getMoveCount() {
		return moveCount;
	}
	/**
	 * increase count of move by one every time when this function is called
	 */
	public static void addMoveCount() {
		World.moveCount++;
	}
	/**
	 * setter for removeSprite
	 * */
	public static void setRemoveSprite(String type) {
		removeSprite = type;
	}
}
