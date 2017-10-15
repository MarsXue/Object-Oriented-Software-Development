/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Rogue class for "Rogue", inherited from Unit class.
 * Rogue object moves each time when Player object moves.
 * During movement, Rogue object can push block if attaches.
 * Direction starts from left, turn right if blocked.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class Rogue extends Unit {
	
	/** Rogue's initial direction starts from left */
	private int dir = DIR_LEFT;
	
	/**
	 * Constructor.
	 * @param x		Position x
	 * @param y		Position y
	 * @param name	Sprite type
	 */
	public Rogue(int x, int y, String name) {
		super("res/rogue.png", x, y, name);
	}
	
	/**
	 * Override makeMove method from Unit class.
	 * Rogue moves left initially, turn right if blocked.
	 * Rogue can push block if attaches.
	 * @param playerDir	Direction of Player object
	 * @param world		Object of World class
	 */
	@Override
	public void makeMove(int playerDir, World world) {
		int posX = this.getX();
		int posY = this.getY();
		int dir = this.dir;
		
		switch (dir) {
			case DIR_LEFT:
				posX--;
				break;
			case DIR_RIGHT:
				posX++;
				break;
		}
		// rogue push if Block object exists
		world.pushBlock(dir, posX, posY);
		// rogue moves if position is not blocked
		if (!world.isBlocked(posX, posY)) {
			setXY(posX, posY);
		} else {
			// turn left to right, right to left
			dir = (dir == DIR_LEFT) ? DIR_RIGHT : DIR_LEFT;
		}
		// set the current direciton of Rogue object
		this.dir = dir;
	}
	
	/**
	 * Override update method from Sprite class.
	 * Rogue makes move each time when player moves.
	 * @param playerDir	Direction of Player object
	 * @param delta		Delta
	 * @param world		Object of World class
	 */
	@Override
	public void update(int playerDir, int delta, World world) {
		// rogue makes move when player moves
		if (playerDir != DIR_NONE) {
			makeMove(playerDir, world);
		}
	}
	
}