/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Floor class for "Floor", inherited from Sprite class.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class Floor extends Sprite {
	
	/**
	 * Constructor.
	 * @param x		Position x
	 * @param y		Position y
	 * @param name	Sprite type
	 */
	public Floor(int x, int y, String name) {
		super("res/floor.png", x, y, name);
	}
	
}