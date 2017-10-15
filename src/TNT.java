import java.util.ArrayList;

/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * TNT class inherited from Block class, implements Actionable interface.
 * TNT object makes action when it attaches Cracked object,
 * Explosion effect displays for 0.4 second on same position.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class TNT extends Block implements Actionable {
	
	/** Boolean type of active attribute */
	private boolean active = false;
	
	/**
	 * Constructor.
	 * @param x		Position x
	 * @param y		Position y
	 * @param name	Sprite type
	 */
	public TNT(int x, int y, String name) {
		super("res/tnt.png", x, y, name);
	}
	
	/**
	 * Override method from Block class.
	 * TNT will be active when it attaches Cracked object.
	 * @param world	Object of World class
	 * @param dir	Direction of player made
	 * @param posX	Position x
	 * @param posY	Position y
	 */
	@Override
	public void makePush(World world, int dir, int posX, int posY) {
		switch (dir) {
			case Sprite.DIR_LEFT:
				posX--;
				break;
			case Sprite.DIR_RIGHT:
				posX++;
				break;
			case Sprite.DIR_UP:
				posY--;
				break;
			case Sprite.DIR_DOWN:
				posY++;
				break;
		}
		checkActive(world, posX, posY);
		
		if ((!world.isBlocked(posX, posY)) || active) {
			setXY(posX, posY);
		}
	}
	
	/**
	 * Override makeAction method from Actionable Interface.
	 * If TNT is active, TNT object makes action,
	 * Destroy all sprites on position except Floor object.
	 * Display the Explosion effect at the same time.
	 * @param world Object of World class
	 */
	@Override
	public void makeAction(World world) {
		if (this != null && this.getActive()) {
			int x = this.getX();
			int y = this.getY();
			ArrayList<Sprite> list = world.findSprite(x, y);
			for (Sprite sprite : list) {
				if (!(sprite instanceof Floor)) {
					world.destroySprite(sprite);
				}
			}
			world.addSprite(new Explosion(x, y, Sprite.EXPLOSION));
		}
	}
	
	/**
	 * Find whether TNT object satisfies active condition.
	 * Return true if object attaches Cracked object.
	 * @param world	Object of World class
	 * @param posX	Position x
	 * @param posY	Position y
	 * @return 	  	Boolean type, true if condition satisfies
	 */
	public boolean isActive(World world, int posX, int posY) {
		ArrayList<Sprite> list = world.findSprite(posX, posY);
		for (Sprite item : list) {
			// attaches to Cracked wall
			if (item instanceof Cracked) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check TNT object satisfies active condition and non-active,
	 * Inverse the boolean active attribute value.
	 * @param world Object of World class
	 * @param posX  Position x
	 * @param posY  Position y
	 */
	public void checkActive(World world, int posX, int posY) {
		if (isActive(world, posX, posY) && !active) {
			active = !active;
		}
	}
	
	/**
	 * Getter function.
	 * @return Boolean type of active attribtue
	 */
	public boolean getActive() {
		return active;
	}
	
}