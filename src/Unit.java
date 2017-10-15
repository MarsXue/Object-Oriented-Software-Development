/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Unit class for "Unit", inherited from Sprite class.
 * All Unit objects can make move with makeMove method.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class Unit extends Sprite {
	
	/**
	 * Constructor.
	 * @param image_src	Image source
	 * @param x			Position x
	 * @param y			Position y
	 * @param name		Sprite type
	 */
	public Unit(String image_src, int x, int y, String name) {
		super(image_src, x, y, name);
	}
	
	/**
	 * Abstract makeMove method, implements in subclass.
	 * @param playerDir	Direction of player object
	 * @param world		Object of World class
	 */
	public void makeMove(int playerDir, World world) {
	}

}
