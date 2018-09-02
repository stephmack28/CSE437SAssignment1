package ScoopsProject;

import java.awt.*;
import java.lang.Math;

public class Scoop {

	private int scoopRadius = 50;
	private int x = 0;
	private int y = 0;
	private Color color;
	private boolean scored;

	/**
	 * Represents a scoop object
	 * @param screenHeight An int representing the height of the game window
	 * @param screenWidth An int representing the width of the game window
	 * @param c A Color representing the color of the scoop
	 */
	public Scoop(int screenHeight, int screenWidth, Color c) {
		double rand = Math.random();
		x = (int) ((screenWidth - (scoopRadius * 2)) * rand);
		y = screenHeight + scoopRadius;
		color = c;
	}

	// Method to move scoops
	//		Takes in a number of pixels to jump (so scoop falling speed can be increased/decreased)
	// 		Upon a successful jump, return true
	// 		If the scoop touches the bottom of the screen, return false

	/**
	 * Called each time a scoop needs to be moved down the screen
	 * @param pixelsToJump
	 */
	public void moveScoop(int pixelsToJump) {
		y = y - pixelsToJump;
	}

	// Method to check if scoop has touched the ground
	public boolean isScoopOnGround() {
		if (y < (scoopRadius - 35)) {
			return true;
		} else {
			return false;
		}
	}

	// Method to return radius of scoop
	public int getRadius() {
		return scoopRadius;
	}

	// Method to return x coordinate of scoop
	public int getX() {
		return x;
	}

	// Method to return y coordinate of scoop
	public int getY() {
		return y;
	}

	//Method to set color
	public void setColor(Color c) {
		color = c;
	}

	//Method to get color
	public Color getColor() {
		return color;
	}

	public void setScored(boolean b) {
		scored = b;
	}

	public boolean getScored() {
		return scored;
	}
}