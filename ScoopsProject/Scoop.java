package ScoopsProject;

import java.lang.Math; // Random number generation

public class Scoop {

	public int scoopRadius = 50;
	public int x = 0;
	public int y = 0;

	// Object constructor
	public Scoop(int screenWidth, int screenHeight) {
		double rand = Math.random();
		x = (int) ((screenWidth - (scoopRadius * 2)) * rand);
		y = screenHeight + scoopRadius;
	}

	public int moveScoop(int pixelsToJump) {
		y = y - pixelsToJump;
		return 0;
	}
}