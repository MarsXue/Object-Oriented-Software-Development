import java.util.*;
import org.newdawn.slick.*;

/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * World class for loading all the sprite objects in current file,
 * And runs the game in rule by calling specific sprite in order,
 * Rule function including isBlocked, levelCompleted and so on.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class World {	
	/** maximum level file is 5 */
	private static final int MAX_FILE = 5;
	/** level file starts from 0 */
	private int currentFile = 0;
	/** ArrayList of sprites loaded in current file */
	private ArrayList<Sprite> sprites;
	
	/*
	 * Constructor.
	 */
	public World() {
		loadLevel();	
	}
	
	/**
	 * Load current level.
	 */
	public void loadLevel() {
		sprites = Loader.loadSprites(currentFile + ".lvl");
	}
	 
	/**
	 * Load next level if valid.
	 */
	public void nextLevel() {
		if (currentFile < MAX_FILE) {
			currentFile++;
		}
		loadLevel();
	}
	
	/**
	 * Check current level is completed.
	 * If all targets are covered by blocks,
	 * Then load next level.
	 */
	public void levelCompleted() {
		int countTarget = 0, countBlock = 0;
		for (Sprite sprite : sprites) {
			if (sprite instanceof Target) {
				countTarget++;
				ArrayList<Sprite> list = 
						findSprite(sprite.getX(), sprite.getY());
				for (Sprite item : list) {
					if (item instanceof Block) {
						countBlock++;
					}
				}
			}
		}
		if (countBlock == countTarget) {
			nextLevel();
		}
	}

	/**
	 * If block object is attached in next move,
	 * Call makePush function in Block class.
	 * @param dir  Direction of Player object
	 * @param posX Position x (next move)
	 * @param posY Position y (next move)
	 */
	public void pushBlock(int dir, int posX, int posY) {
		ArrayList<Sprite> list = findSprite(posX, posY);
		for (Sprite sprite : list) {
			if (sprite instanceof Block) {			
				((Block)sprite).makePush(this, dir, posX, posY);
			}
		}
	}
	
	/**
	 * Check if the (x, y) coordinate is blocked by
	 * Wall, Cracked Wall, Block and Door (closed).
	 * @param x Position x
	 * @param y Position y
	 * @return Boolean type, true if is blocked
	 */
	public boolean isBlocked(int x, int y) {
		ArrayList<Sprite> list = findSprite(x, y);
		for (Sprite sprite : list) {
			// Wall, Cracked wall, Block can block
			if ((sprite instanceof Wall) || (sprite instanceof Cracked)
			 || (sprite instanceof Block)) {
				return true;
			// Door (closed) can block
			} else if (sprite instanceof Door) {
				return (!((Door)sprite).getActive());
			}
		}
		return false;
	}
	
	/**
	 * Find all sprites on (x, y) coordinate.
	 * @param x Position x
	 * @param y Position y
	 * @return ArrayList of sprite object on (x, y)
	 */
	public ArrayList<Sprite> findSprite(int x, int y) {
		ArrayList<Sprite> list = new ArrayList<>();
		for (Sprite sprite : sprites) {
			if ((sprite.getX() == x) && (sprite.getY() == y)) {
				// exclude player object
				if (!(sprite instanceof Player) && !(sprite instanceof Floor)) {
					list.add(sprite);
				}
			}
		}
		return list;
	}
	
	/**
	 * Add particular sprite in sprite list.
	 * @param sprite Specific Sprite object
	 */
	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}
	
	/**
	 * Remove particular sprite from sprite list.
	 * @param sprite Specific Sprite object
	 */
	public void destroySprite(Sprite sprite) {
		sprites.remove(sprite);
	}
	
	/**
	 * Getter function.
	 * Find the specific sprite, without accessing sprites.
	 * @return Sprite with the required type.
	 */
	public Sprite getSpriteOfType(String spriteType) {
		Sprite tmp = null;
		for (Sprite sprite : sprites) {
			if ((sprite.getType()).equals(spriteType)) {
				tmp = sprite;
			}
		}
		return tmp;
	}
	
	/**
	 * Check if there is an Actionable sprite,
	 * Make action if it satisfies condition,
	 * Check explosion effect at the end.
	 */
	public void checkAction() {
		Sprite swt = getSpriteOfType(Sprite.SWITCH);
		Sprite tnt = getSpriteOfType(Sprite.TNT);
		// call makeAction if sprite is non-null
		if (tnt != null) {
			((Actionable)tnt).makeAction(this);
		} else if (swt != null) {
			((Actionable)swt).makeAction(this);
		}
		// destroy Explosion sprite after displays 4s
		Sprite exp = getSpriteOfType(Sprite.EXPLOSION);
		// if explosion sprite exists and active, destroy it
		if (exp != null && ((Explosion)exp).getActive()) {
			destroySprite(exp);
		}
	}
	
	/**
	 * Update function.
	 * @param input Input information
	 * @param delta Delta
	 */
	public void update(Input input, int delta) {
		// "R": restart level
		if (input.isKeyPressed(Input.KEY_R)) {
			loadLevel();
		}
		// "N": next level
		if (input.isKeyPressed(Input.KEY_N)) {
			nextLevel();
		}
		int dir = getDir(input);
		// record the position of sprite
		recordSprite(dir);
		// "Z": undo the move
		if (input.isKeyPressed(Input.KEY_Z)) {
			undoSprite();
		}		
		// update player sprite first
		for (Sprite sprite : sprites) {
			if (sprite instanceof Player) {
				sprite.update(dir, delta, this);
			}
		}
		// check Actionable sprite
		checkAction();
		// update the rest sprites
		for (Sprite sprite : sprites) {
			if (sprite != null && !(sprite instanceof Player)) {
				sprite.update(dir, delta, this);
			}
		}
		// check whether the current level is completed
		levelCompleted();
	}
	
	/**
	 * Render function.
	 * @param g Graphics
	 */
	public void render(Graphics g) {
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				sprite.render(g);
			}
		}
	}
	
	/**
	 * Convert the input into direction integer,
	 * Since input can only be read once.
	 * @param input Input information
	 * @return Corresponding integer of direction
	 */
	public int getDir(Input input) {
		int dir = Sprite.DIR_NONE;
		
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			dir = Sprite.DIR_LEFT;
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			dir = Sprite.DIR_RIGHT;
		}
		if (input.isKeyPressed(Input.KEY_UP)) {
			dir = Sprite.DIR_UP;
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			dir = Sprite.DIR_DOWN;
		}
		return dir;
	}
	
	/**
	 * Call each Undoable sprite to record itself,
	 * if there is a valid player input.
	 * @param dir Direction of input
	 */
	public void recordSprite(int dir) {
		if (dir != Sprite.DIR_NONE) {
			for (Sprite sprite : sprites) {
				if (sprite instanceof Undoable) {
					((Undoable)sprite).record();
				}
			}
		}
	}
	
	/**
	 * Call each Undoable sprite to undo the move.
	 */
	public void undoSprite() {
		for (Sprite sprite : sprites) {
			if (sprite instanceof Undoable) {
				((Undoable)sprite).undo();
			}
		}
	}
	
}