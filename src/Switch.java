import java.util.ArrayList;

/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Switch class inherited from Sprite class, implements Actionable interface.
 * Switch object determines the condition of Door object.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class Switch extends Sprite implements Actionable {
	
	/**
	 * Constructor.
	 * @param x		Position x
	 * @param y		Position y
	 * @param name	Sprite type
	 */
	public Switch(int x, int y, String name) {
		super("res/switch.png", x, y, name);
	}
	
	/**
	 * Override makeAction method from Actionable interface.
	 * If Switch object covered by Block object,
	 * Set Door object's active to true (open).
	 * @param world Object of World class
	 */
	@Override
	public void makeAction(World world) {
		Sprite door = world.getSpriteOfType(Sprite.DOOR);
		
		int x = this.getX();
		int y = this.getY();
		// no Door object in loaded sprites
		if (door == null) {
			return;
		}
		// Check if covered by Block object
		ArrayList<Sprite> list = world.findSprite(x, y);
		// boolean attribute to check covered by Block object
		boolean cover = false;
		for (Sprite item : list) {
			if (item instanceof Block) {
				cover = true;
			}
		}
//		System.out.println("Door: " + cover);
//		System.out.println("Before:\t" + ((Door)door).getActive());
		((Door)door).setActive(cover);
//		System.out.println("Switch: " + ((Door)door).getActive());
	}

}