package ScoopsProject;

public class Cone {

    private double baseHeight;
    private double baseWidth;
    private double speed;
    private double xPosition;

    /*
        Creates a cone object with default width and speed.
     */
    public Cone() {
        this.baseHeight = 10;
        this.baseWidth = 50;
        this.speed = 5;
        this.xPosition = 300;
    }

    /*
        Returns the base height of the Cone object. Base size affects amount of scoops that the cone can catch.
     */
    public double getBaseHeight() {
        return this.baseHeight;
    }

    /*
        Returns the width of the Cone object.
     */
    public double getBaseWidth() {
        return this.baseWidth;
    }

    /*
        Returns the speed of the Cone object. The speed represents how quickly the user can move the cone from side to side.
     */
    public double getSpeed() {
        return this.speed;
    }

    /*
        Sets the base height of the Cone object. Base size affects amount of scoops that the cone can catch.
     */
    public void setBaseHeight(double newSize) {
        this.baseHeight = newSize;
    }

    /*
    Sets the base width of the Cone object.
 */
    public void setBaseWidth(double newSize) {
        this.baseWidth = newSize;
    }

    /*
        Sets the speed of the Cone object. The speed represents how quickly the user can move the cone from side to side.
     */
    public void setSpeed(double newSpeed) {
        this.speed = newSpeed;
    }

    /*
        Returns the current X position of the Cone object.
     */
    public double getXPosition() {
        return xPosition;
    }

    /*
        Moves the cone left one position (speed interval).
     */
    public void moveLeft() {
        this.xPosition = this.xPosition - this.speed;
    }

    /*
        Moves the cone right one position (speed interval).
     */
    public void moveRight() {
        this.xPosition = this.xPosition + this.speed;
    }

}

