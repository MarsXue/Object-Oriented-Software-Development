/**
 * Project skeleton for SWEN20003: Object Oriented Software Development 2017
 * Implemented by Wenqing XUE
 * 
 * Sprite class for rending each sprite separately,
 * including calculating the offset in game window,
 * with the x and y coordinates and type.
 */

import org.newdawn.slick.*;

/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Sprite class is superclass for all sprite objects.
 * With all the common attributes constructed.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class Sprite {
	
	/** Constant integer to determine the direction */
	public static final int DIR_NONE = 0;
	public static final int DIR_LEFT = 1;
	public static final int DIR_RIGHT = 2;
	public static final int DIR_UP = 3;
	public static final int DIR_DOWN = 4;
	
	/** Constant string to determine the sprite type */
	public static final String TNT = "tnt";
	public static final String DOOR = "door";
	public static final String PLAYER = "player";
	public static final String SWITCH = "switch";
	public static final String EXPLOSION = "explosion";
	
	private Image img = null;
	private int x;
	private int y;
	private String type;
	
	/**
	 * Constructor.
	 * @param image_src	Image source provided
	 * @param x		    Position x
	 * @param y			Position y
	 * @param name		Sprite type
	 * @exception SlickException Game running error
	 */
	public Sprite(String image_src, int x, int y, String name) {
		try {
			img = new Image(image_src);
		} catch (SlickException e) {
			e.printStackTrace();
		}	
		this.x = x;
		this.y = y;
		this.type = name;
	}
	
	/**
	 * Abstract update method.
	 * Implementation depends on different classes.
	 * @param playerDir	Direction of player object
	 * @param delta		Delta
	 * @param world		Object of World class
	 */
	public void update(int playerDir, int delta, World world) {
	}
	
	/**
	 * Render function.
	 * @param g Graphics
	 */
	public void render(Graphics g) {
		int posX = Loader.getOffsetX() + x * App.TILE_SIZE;
		int posY = Loader.getOffsetY() + y * App.TILE_SIZE;
		img.drawCentered(posX, posY);
	}
	
	/**
	 * Getter function.
	 * @return Position x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Getter function.
	 * @return Position y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Getter function.
	 * @return Type of Sprite object
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Setter function.
	 * @param x Position x
	 * @param y Position y
	 */
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}