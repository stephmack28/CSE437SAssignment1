package ScoopsProject;

import java.lang.Math;

public class Scoop {

	private int scoopRadius = 50;
	private int x = 0;
	private int y = 0;

	// Object constructor
	//		Takes in the height and width of the screen
	// 		Randomly determines a horizontal coordinate for the scoop
	// 		Scoops spawn just above the top of the screen
	public Scoop(int screenHeight, int screenWidth) {
		double rand = Math.random();
		x = (int) ((screenWidth - (scoopRadius * 2)) * rand);
		y = screenHeight + scoopRadius;
	}

	// Method to move scoops
	//		Takes in a number of pixels to jump (so scoop falling speed can be increased/decreased)
	// 		Upon a successful jump, return true
	// 		If the scoop touches the bottom of the screen, return false
	public boolean moveScoop(int pixelsToJump) {
		y = y - pixelsToJump;
		if (y < scoopRadius) {
			return false;
		} else {
			return true;
		}
	}

	// Method to return x coordinate of scoop
	public int getX() {
		return x;
	}

	// Method to return y coordinate of scoop
	public int getY() {
		return y;
	}
}