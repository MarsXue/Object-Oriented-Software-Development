/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Explosion class for "Explosion", inherited from Sprite class.
 * Object will be created only when TNT attaches Cracked Wall.
 * It displays only 0.4 second as required in specification.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class Explosion extends Sprite {
	
	/** Required display time 400ms = 0.4s */
	private static final int EXPLOSION_TIME = 400;
	/** Time left to determine the effect */
	private int timeLeft;
	/** Boolean type of active attribute */
	private boolean active = false;
	
	/**
	 * Constructor.
	 * @param x		Position x
	 * @param y		Position y
	 * @param name	Sprite type
	 */
	public Explosion(int x, int y, String name) {
		super("res/explosion.png", x, y, name);
		timeLeft = EXPLOSION_TIME;
	}
	
	/**
	 * Override update function from Sprite class.
	 * After display time, boolean type set to true.
	 * After update loop method in World,
	 * Explosion object will be destroyed immediately.
	 * @param playerDir	Direction of Player obejct
	 * @param delta	    Delta
	 * @param world		Object of World class
	 */
	@Override
	public void update(int playerDir, int delta, World world) {
		timeLeft -= delta;
		// run out required display time
		if (timeLeft <= 0) {
			active = true;
		}	
	}
	
	/**
	 * Getter function.
	 * @return Boolean type of active attribute.
	 */
	public boolean getActive() {
		return active;
	}
	
}