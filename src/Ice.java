import java.util.Stack;

/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Ice class for "Ice", inherited from Block class.
 * Ice moves in speed 0.25 second per tile as required.
 * When undo, roll back to position that before moving.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class Ice extends Block {
	
	/** Ice speed 0.25s per tile, 0.25s = 250ms */
	private static final int ICE_TIME = 250;
	/** Direction of Ice be pushed */
	private int dir;
	/** Last position of X */
	private int lastX;
	/** Last position of Y */
	private int lastY;
	/** Time left to determine the moving speed */
	private int timeLeft;
	
	/**
	 * Constructor.
	 * @param x		Position x
	 * @param y		Position y
	 * @param name	Sprite type
	 */
	public Ice(int x, int y, String name) {
		super("res/ice.png", x, y, name);
		dir = DIR_NONE;
		lastX = x;
		lastY = y;
	}
	
	/**
	 * Override makePush method from Block class.
	 * Store the direction and required move time.
	 * @param world	Obejct of World class
	 * @param dir	Direction of Player made
	 * @param posX	Position x
	 * @param posY	Position y
	 */
	@Override
	public void makePush(World world, int dir, int posX, int posY) {
		this.dir = dir;
		this.timeLeft = ICE_TIME;
		super.makePush(world, dir, posX, posY);
	}
	
	/**
	 * Override record method from Block class.
	 * Record the position (x, y), and push into record stack.
	 */
	@Override
	public void record() {
		Stack<int[]> record = getRecord();
		int[] position = new int[SIZE];
		position[POS_X] = lastX;
		position[POS_Y] = lastY;
		// push the position into record stack.
		record.push(position);
	}
	
	/**
	 * Override undo method from Block class.
	 * Undo the moves, set the position to last record in stack.
	 */
	@Override
	public void undo() {
		Stack<int[]> record = getRecord();
		// make undo if history position exists
		if (!record.empty()) {
			// pop the last history position in record stack.
			int[] lastRecord = record.pop();
			// set the direction to none, and set current and last position.
			this.setDir();
			this.setXY(lastRecord[POS_X], lastRecord[POS_Y]);
			this.setLastXY(lastRecord[POS_X], lastRecord[POS_Y]);
		}
	}
	
	/**
	 * Override update method from Sprite class.
	 * @param playerDir	Direction of Player object
	 * @param delta		Delta
	 * @param world		Object of World class
	 */
	@Override
	public void update(int playerDir, int delta, World world) {
		timeLeft -= delta;
		// determine the time left for ice speed for each tile
		if (timeLeft <= 0) {
			makePush(world, this.dir, this.getX(), this.getY());
		}
	}
	
	/**
	 * Getter function.
	 * @return Last position x
	 */
	public int getLastX() {
		return lastX;
	}
	
	/**
	 * Getter function.
	 * @return Last position y
	 */
	public int getLastY() {
		return lastY;
	}
 	
	/**
	 * Set the position (x, y).
	 * @param posX Position x
	 * @param posY Position y
	 */
	public void setLastXY(int posX, int posY) {
		this.lastX = posX;
		this.lastY = posY;
	}
	
	/**
	 * Set the direction to none.
	 */
	public void setDir() {
		dir = Sprite.DIR_NONE;
	}
}