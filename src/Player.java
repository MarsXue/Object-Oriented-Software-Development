/**
 * Project skeleton for SWEN20003: Object Oriented Software Development 2017
 * Implemented by Wenqing XUE
 * 
 * Player class is created for player, which can be moved
 * by arrows input. Child class extends from Sprite class.
 * input isKeyPressed for determine the input. Also check
 * if there is a wall blocked.
 */

import java.util.*;
import org.newdawn.slick.*;

/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Player class for "Player", inherited from Unit class.
 * Get direction input to control Player object.
 * Player object counts movement and displays on screen.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class Player extends Unit implements Undoable {
	
	/** Player's move count starts from 0 */
	private int moves = 0;
	/** Record stack of history position */
	private Stack<int[]> record;
	
	/**
	 * Constructor.
	 * @param x		Position x
	 * @param y		Position y
	 * @param name	Spriet type
	 */
	public Player(int x, int y, String name) {
		super("res/player_left.png", x, y, name);
		record = new Stack<>();
	}
	
	/**
	 * Override makeMove method in Unit class.
	 * Make move by corresponding input.
	 * @param dir   Direction of Player object
	 * @param world Object of World class
	 */
	@Override
	public void makeMove(int dir, World world) {
		int posX = this.getX();
		int posY = this.getY();
		
		switch (dir) {
			case DIR_LEFT:
				posX--;
				break;
			case DIR_RIGHT:
				posX++;
				break;
			case DIR_UP:
				posY--;
				break;
			case DIR_DOWN:
				posY++;
				break;
		}
		// push block if next position exists Block object
		world.pushBlock(dir, posX, posY);
		// set position if it is not blocked
		if (!world.isBlocked(posX, posY)) {
			setXY(posX, posY);
		}
		// each move, check restart condition
		if (dir != DIR_NONE) {
			checkRestart(world, this.getX(), this.getY());
		}
	}
	
	/**
	 * Check restart function.
	 * When Player object attaches any other Unit objects,
	 * Restart the current level immediately.
	 * @param world Object of World class
	 * @param posX  Position x
	 * @param posY  Position y
	 * @return
	 */
	public void checkRestart(World world, int posX, int posY) {
		ArrayList<Sprite> sprites = world.findSprite(posX, posY);
		
		for (Sprite sprite : sprites) {
			if ((sprite instanceof Skeleton) 
			 || (sprite instanceof Rogue)
			 || (sprite instanceof Mage)) {
				world.loadLevel();
			}
		}
	}
	
	/**
	 * Record the position (x, y), and push into record stack.
	 */
	public void record() {
		int[] position = new int[SIZE];
		
		position[POS_X] = this.getX();
		position[POS_Y] = this.getY();
		record.push(position);
	}
	
	/**
	 * Undo the moves, set the position to last record in stack.
	 */
	public void undo() {
		// make undo if history position exists
		if (!record.empty()) {
			moves--;
			// pop the last history position in record stack.
			int[] lastRecord = record.pop();
			this.setXY(lastRecord[POS_X], lastRecord[POS_Y]);
		}
	}
	
	/**
	 * Override update method from Sprite class.
	 * Player object make move when input a direction.
	 * Increase move count each time when make move.
	 * Check restart if condition satisfies.
	 * @param dir   Direction of player object
	 * @param delta Delta
	 * @param world Object of World class
	 */
	@Override
	public void update(int dir, int delta, World world) {
		if (dir != DIR_NONE) {	
			moves++;
			makeMove(dir, world);			
		}
		checkRestart(world, this.getX(), this.getY());
	}
	
	/**
	 * Override render method from Sprite class.
	 * Render move count at the left up corner.
	 * @param g Graphics
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
		g.drawString("Moves: " + moves, 10, 32);
	}
	
}