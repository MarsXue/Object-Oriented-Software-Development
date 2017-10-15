/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Mage class for "Mage", inherited from Unit class.
 * Mage object moves each time when Player object moves.
 * Movement is based on its own algorithm.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class Mage extends Unit {
	
	/** Constant positive 1 for Mage algorithm */
	public static final int POSITIVE = 1;
	/** Constant negative 1 for Mage algorithm */
	public static final int NEGATIVE = -1;
	
	/**
	 * Constructor.
	 * @param x		Position x
	 * @param y		Position y
	 * @param name	Sprite type
	 */
	public Mage(int x, int y, String name) {	
		super("res/mage.png", x, y, name);
	}
	
	/**
	 * Override makeMove method from Unit class.
	 * Mage's movement is calculated by algorithm,
	 * Which is implemented in specification.
	 * @param playerDir	Direction of Player object
	 * @param world		Object of World class
	 */
	@Override
	public void makeMove(int playerDir, World world) {
		Sprite player = world.getSpriteOfType(Sprite.PLAYER);
		
		int posX = this.getX();
		int posY = this.getY();
		int distX = player.getX() - posX;
		int distY = player.getY() - posY;
		int dir;
		
		if (Math.abs(distX) > Math.abs(distY)) {
			dir = sgn(distX);
			switch (dir) {
				case NEGATIVE:
					posX--;
					break;
				case POSITIVE:
					posX++;
					break;
			}
		} else {
			dir = sgn(distY);
			switch (dir) {
				case NEGATIVE:
					posY--;
					break;
				case POSITIVE:
					posY++;
					break;
			}
		}
		// If movement is possible, set the position
		if (!world.isBlocked(posX, posY)) {
			setXY(posX, posY);
		}
	}
	
	/**
	 * Sgn function as mentioned in specification.
	 * @param value
	 * @return Integer value of output
	 */
	public int sgn(int value) {
		int output = (value < 0) ? NEGATIVE : POSITIVE;
		return output;
	}
	
	/**
	 * Override update method from Sprite class.
	 * Mage makes move each time when player moves.
	 * @param playerDir	Direction of Player object
	 * @param delta		Delta
	 * @param world		Object of World class
	 */
	@Override
	public void update(int playerDir, int delta, World world) {
		// mage makes move when player moves
		if (playerDir != DIR_NONE) {
			makeMove(playerDir, world);
		}
	}
	
}