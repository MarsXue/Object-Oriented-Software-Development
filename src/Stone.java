/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Stone class for "Stone", inherited from Block class.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class Stone extends Block {
	
	/**
	 * Constructor.
	 * @param x		Position x
	 * @param y		Position y
	 * @param name	Sprite type
	 */
	public Stone(int x, int y, String name) {
		super("res/stone.png", x, y, name);
	}

}