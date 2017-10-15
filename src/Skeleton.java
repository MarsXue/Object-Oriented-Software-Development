/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Skeleton class for "Skeleton", inherited from Unit class.
 * Skeleton object moves one tile per second as constant speed.
 * Direction starts from up, turn down if blocked.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class Skeleton extends Unit {
	
	/** Skeleton speed 1s per tile, 1s = 1000ms */
	private int MOVE_TIME = 1000;
	/** Skeleton's intial direction starts from up */
	private int dir = DIR_UP;
	/** Time left to determine the moving speed */
	private int timeLeft;
	
	/**
	 * Constructor.
	 * @param x		Position x
	 * @param y		Position y
	 * @param name	Sprite type
	 */
	public Skeleton(int x, int y, String name) {
		super("res/skull.png", x, y, name);
		timeLeft = MOVE_TIME;
	}
	
	/**
	 * Override makeMove method from Unit class.
	 * @param dir   Direction of self
	 * @param world Object of World class
	 */
	@Override
	public void makeMove(int dir, World world) {
		int posX = this.getX();
		int posY = this.getY();
		
		switch (dir) {
			case DIR_UP:
				posY--;
				break;
			case DIR_DOWN:
				posY++;
				break;
		}
		// skeleton moves if position is not blocked
		if (!world.isBlocked(posX, posY)) {
			this.setXY(posX, posY);
			// turn up to down, down to up
		} else {
			dir = (dir == DIR_UP) ? DIR_DOWN : DIR_UP;
		}
		// set the current direciton of Skeleton object
		this.dir = dir;
		// set the timeLeft back to 1 second
		this.timeLeft = MOVE_TIME;
	}
	
	/**
	 * Override update method from Sprite class.
	 * Skeleton makes constant move, one tile per second.
	 * @param playerDir	Direction of Player
	 * @param delta		Delta
	 * @param world		Object of World class
	 */
	@Override
	public void update(int playerDir, int delta, World world) {
		timeLeft -= delta;
		// skeleton moves independently, one tile per second
		if (timeLeft <= 0) {
			makeMove(this.dir, world);
		}
	}
	
}