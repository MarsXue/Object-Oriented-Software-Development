import org.newdawn.slick.Graphics;

/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Door class for "Door", inherited from Sprite class.
 * Boolean type of active attribute to determine if close.
 * If open, active = true, door is not blocked, and dont render.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class Door extends Sprite {
	
	/** Boolean type of active attribute */
	private boolean active;
	
	/**
	 * Constructor.
	 * @param x		Position x
	 * @param y		Position y
	 * @param name	Sprite type
	 */
	public Door(int x, int y, String name) {
		super("res/door.png", x, y, name);
		active = false;
	}
	
	/**
	 * Getter function.
	 * @return Boolean type of active attribute.
	 */
	public boolean getActive() {
		return active;
	}
	
	/**
	 * Setter function.
	 * @param type Required boolean type
	 */
	public void setActive(boolean type) {
		active = type;
	}
	
	/**
	 * Override render function from Sprite class.
	 * Render Door object if it is closed.
	 * @param g Graphics
	 */
	@Override
	public void render(Graphics g) {
		if (!active) {
			super.render(g);
		}
	}
	
}