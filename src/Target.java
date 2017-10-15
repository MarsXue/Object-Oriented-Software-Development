/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Target class for "Target", inherited from Sprite class.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class Target extends Sprite {
	
	/**
	 * Constructor.
	 * @param x		Position x
	 * @param y		Position y
	 * @param name	Sprite type
	 */
	public Target(int x, int y, String name) {
		super("res/target.png", x, y, name);
	}
	
}