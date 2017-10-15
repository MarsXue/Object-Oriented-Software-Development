/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Undoable interface for Player, Block class only.
 * Record and undo if called in World class.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public interface Undoable {
	
	/** Constant size for record (integer array) */
	public static final int SIZE = 2;
	/** Constant x position for record (integer array) */
	public static final int POS_X = 0;
	/** Constant y position for record (integer array) */
	public static final int POS_Y = 1;
	
	/**
	 * Abstract method, implemented in class.
	 * Record the history position.
	 */
	public void record();
	
	/**
	 * Abstract method, implemented in class.
	 * Undo the move, set back to last position.
	 */
	public void undo();
	
}
