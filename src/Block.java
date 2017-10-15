import java.util.Stack;

/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Block class inherited from Sprite class, implements Undoable interface.
 * Block class for pushable blockes, including Ice, Stone and TNT class.
 * Stack of integer array for recording the history position.
 * Methods of makePush, record, undo are implemented.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class Block extends Sprite implements Undoable {
	
	/** Record stack of history position */
	private Stack<int[]> record;
	
	/**
	 * Constructor.
	 * @param image_src	Image source
	 * @param x			Position x
	 * @param y			Position y
	 * @param name		Sprite type
	 */
	public Block(String image_src, int x, int y, String name) {
		super(image_src, x, y, name);
		record = new Stack<>();
	}
	
	/**
	 * Make push if towards position is not blocked.
	 * If blocked, object is Ice class and valid input,
	 * Then set the original position into last position,
	 * And record in its record stack, set the direction
	 * Back to none.
	 * @param world Object of World class
	 * @param dir   Direction of Player made
	 * @param posX  Position X
	 * @param posY  Position Y
	 */
	public void makePush(World world, int dir, int posX, int posY) {
		int deltaX = 0, deltaY = 0;
		switch (dir) {
			case DIR_LEFT:
				deltaX--;
				break;
			case DIR_RIGHT:
				deltaX++;
				break;
			case DIR_UP:
				deltaY--;
				break;
			case DIR_DOWN:
				deltaY++;
				break;
		}
		// check whether next move is blocked
		if (!world.isBlocked(posX+deltaX, posY+deltaY)) {
			setXY(posX+deltaX, posY+deltaY);
		} else {
			// if Ice object is blocked, set last position and direction
			if (this instanceof Ice && dir != DIR_NONE) {
				((Ice)this).setLastXY(posX, posY);
				// set direction to none
				((Ice)this).setDir();
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
		// push the current position into record stack.
		record.push(position);
	}
	
	/**
	 * Undo the moves, set the position to last record in stack.
	 */
	public void undo() {
		// make undo if history position existss
		if (!record.empty()) {
			// pop the last history position in record stack.
			int[] lastRecord = record.pop();
			this.setXY(lastRecord[POS_X], lastRecord[POS_Y]);
		}
	}
	
	/**
	 * Getter function.
	 * @return Stack of integer array
	 */
	public Stack<int[]> getRecord() {
		return record;
	}
	
}