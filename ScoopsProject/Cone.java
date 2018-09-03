package ScoopsProject;

import java.util.*;

public class Cone {

    private double baseHeight;
    private double baseWidth;
    private double speed;
    private double xPosition;
    private int widthLevel = 0;

    /**
     * Represents the cone object (to catch falling scoops with)
     */
    public Cone() {
        this.baseHeight = 10;
        this.baseWidth = 25;
        this.speed = 5;
        this.xPosition = 300;
    }

    /**
     * Returns the base height of the cone
     * @return A double representing the base height of the cone
     */
    public double getBaseHeight() {
        return this.baseHeight;
    }

    /**
     * Returns the base width of the cone
     * @return A double representing the base width of the cone
     */
    public double getBaseWidth() {
        return this.baseWidth + (20 * widthLevel);
    }

    /**
     * Returns the speed of the cone
     * The speed represents how quickly the user can move the cone from side to side.
     * @return A double representing the speed of the cone
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * A method to set a new height of the cone
     * @param newSize A double representing the new height of the cone
     */
    public void setBaseHeight(double newSize) {
        this.baseHeight = newSize;
    }

    /**
     * A method to set a new width of the cone
     * @param newSize A double representing the new width of the cone
     */
    public void setBaseWidth(double newSize) {
        this.baseWidth = newSize;
    }

    /**
     * A method to upgrade the cone width
     */
    public void widthLevelUp() {
        this.widthLevel++;
    }

    /**
     * A method to downgrade the cone width
     */
    public void widthLevelDown() {
        if (this.widthLevel > 0) {
            this.widthLevel--;
        }
    }

    /**
     * A method to return the cone width level
     * @return An int representing the cone width level
     */
    public int getWidthLevel() {
        return this.widthLevel;
    }

    /**
     * A method to set a new speed for the cone
     * @param newSpeed A double representing the new speed for the cone
     */
    public void setSpeed(double newSpeed) {
        this.speed = newSpeed;
    }

    /**
     * A method to get the x coordinate of the cone
     * @return A double representing the x coordinate of the cone
     */
    public double getXPosition() {
        return xPosition;
    }

    /**
     * A method to move the cone to the left
     */
    public void moveLeft() {
        this.xPosition = this.xPosition - this.speed;
    }

    /**
     * A method to move the cone to the right
     */
    public void moveRight() {
        this.xPosition = this.xPosition + this.speed;
    }

}

