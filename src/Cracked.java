/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Cracked class for "Cracked Wall", inherited from Sprite class.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class Cracked extends Sprite {
	
	/**
	 * Constructor.
	 * @param x		Position x
	 * @param y		Position y
	 * @param name	Sprite type
	 */
	public Cracked(int x, int y, String name) {
		super("res/cracked_wall.png", x, y, name);
	}
	
}