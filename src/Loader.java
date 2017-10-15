import java.io.*;
import java.util.*;

/**
 * Project 2B for SWEN20003: Object Oriented Software Development 2017
 * 
 * Loader class for loading specific level file,
 * Store the sprite positions in the ArrayList.
 * Create corresponding sprite with position and type.
 * 
 * Loader skeleton credits to Eleanor McMurtry.
 * 
 * @author Eleanor McMurtry
 * @author Wenqing Xue
 * @version 1.0, October 2017
 */
public class Loader {
	/** source path for loading image */
	public static String PATH = "res/levels/";
	/** Width of world */
	private static int world_width;
	/** Height of world */
	private static int world_height;
	/** Offset of x direction */
	private static int offset_x;
	/** Offset of y direction */
	private static int offset_y;
	
	/**
	 * Create the appropriate sprite given a name and coordinate.
	 * @param name	the name of the sprite
	 * @param x		the x position
	 * @param y		the y position
	 * @return		the sprite object
	 */
	private static Sprite createSprite(String name, int x, int y) {
		switch (name) {
			case "cracked":
				return new Cracked(x, y, name);
			case "door":
				return new Door(x, y, name);
			case "floor":
				return new Floor(x, y, name);
			case "ice":
				return new Ice(x, y, name);
			case "mage":
				return new Mage(x, y, name);
			case "player":
				return new Player(x, y, name);
			case "rogue":
				return new Rogue(x, y, name);
			case "skeleton":
				return new Skeleton(x, y, name);
			case "stone":
				return new Stone(x, y, name);
			case "switch":
				return new Switch(x, y, name);
			case "target":
				return new Target(x, y, name);
			case "tnt": 
				return new TNT(x, y, name);
			case "wall":
				return new Wall(x, y, name);
		}
		return null;
	}
		
	/**
	 * Loads the sprites from a given file.
	 * @param filename					The name of file.
	 * @return							ArrayList of loaded sprites.
	 * @exception FileNotFoundException	If file does not exist.
	 * @exception IOException		    On input error.
	 */
	public static ArrayList<Sprite> loadSprites(String filename) {
		ArrayList<Sprite> list = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader
				(PATH+filename))) {
			
			String line = br.readLine();
			
			String[] parts = line.split(",");
			world_width = Integer.parseInt(parts[0]);
			world_height = Integer.parseInt(parts[1]);
			
			offset_x = (App.SCREEN_WIDTH - world_width * App.TILE_SIZE) / 2;
			offset_y = (App.SCREEN_HEIGHT - world_height * App.TILE_SIZE) / 2;
			
			while ((line = br.readLine()) != null) {
				String name;
				int x, y;
				
				parts = line.split(",");
				name = parts[0];
				x = Integer.parseInt(parts[1]);
				y = Integer.parseInt(parts[2]);
				
				list.add(createSprite(name, x, y));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Getter function.
	 * @return Offset value in x direction
	 */
	public static int getOffsetX() {
		return offset_x;
	}
	
	/**
	 * Getter function.
	 * @return Offset value in y direction
	 */
	public static int getOffsetY() {
		return offset_y;
	}
	
}