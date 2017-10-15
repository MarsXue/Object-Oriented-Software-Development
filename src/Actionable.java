/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Actionable interface for Switch, TNT class only.
 * Make action if the current condition satisfies.
 * 
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public interface Actionable {
	
	/**
	 * Abstract method, implemented in class.
	 * Make action if the condition satisfies.
	 * @param world Object of World class
	 */
	public void makeAction(World world);
	
}
