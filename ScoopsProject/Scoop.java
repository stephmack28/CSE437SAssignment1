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

	/**
	 * Called each time a scoop needs to be moved down the screen
	 * @param pixelsToJump An int representing how many pixels a scoop should be moved
	 */
	public void moveScoop(int pixelsToJump) {
		y = y - pixelsToJump;
	}

	/**
	 * A method to check if a scoop has touched the ground
	 * @return A boolean for if the scoop has touched the ground
	 */
	public boolean isScoopOnGround() {
		if (scored == false) {
			if (y < (scoopRadius - 35)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * A method to return the radius of the scoop
	 * @return An int representing the radius of the scoop
	 */
	public int getRadius() {
		return scoopRadius;
	}

	/**
	 * A method to return the x coordinate of scoop
	 * @return An int representing the x coordinate of the scoop
	 */
	public int getX() {
		return x;
	}

	/**
	 * A method to return the y coordinate of the scoop
	 * @return An int representing the y coordinate of the scoop
	 */
	public int getY() {
		return y;
	}

	/**
	 * A method to set the color of the scoop
	 * @param c A Color to set the color of the scoop as
	 */
	public void setColor(Color c) {
		color = c;
	}

	/**
	 * A method to return the color of the scoop
	 * @return A Color representing the color of the scoop
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * A method to set the scored state of the scoop
	 * @param b A boolean representing if the scoop has been scored or not
	 */
	public void setScored(boolean b) {
		scored = b;
	}

	/**
	 * A method to return the scored state of the scoop
	 * @return A boolean representing the scored state of the scoop
	 */
	public boolean getScored() {
		return scored;
	}
}