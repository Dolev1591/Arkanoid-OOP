/**
 * the point class.
 * @author  Dolev Levi
 * 315150110
 */
public class Point {
    static final double THRESHOLD = 0.00001;
    private double x;
    private double y;
    /**
     * constructor for the point class given all fields.
     * @param x double x coordinate
     * @param y double y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * constructor for cases that only one argument
     * was given,and if so-the point gets the default values.
     * @param eitherOne
     */
    public Point(double eitherOne) {
        this.x = 0;
        this.y = 0;
    }
    /**
     * constructor used in case arguments weren't given
     * so the fields are initialized with the default values.
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }
    /**
     * calculates the distance between current point
     * and other point given.
     * @param other the other point.
     * @return double-the distance.
     */
    public double distance(Point other) {
        double otherX = other.getX();
        double otherY = other.getY();
        /*calculating based on the following formula:
         d=√((x2 – x1)² + (y2 – y1)²).
         */
        double xPartResult = Math.pow(otherX - this.x, 2);
        double yPartResult = Math.pow(otherY - this.y, 2);
        return Math.sqrt(xPartResult + yPartResult);
    }
/**
     * the following method checks whether  h point
     * is equal to this point(meaning the x,y coordinate
     * are the same.
     * @param other - the point checked.
     * @return true if equal and false otherwise.
     */
    public boolean equals(Point other) {
        //dealing with comparing null points.
        if (this == null && other == null) {
            return true;
        } else if (this == null || other == null) {
            return false;
        } else {
        return (thresholdCompare(this.x, other.getX())
                && thresholdCompare(this.y, other.getY()));
        }
    }
    /**
     * simple getter for the x coordinate.
     * @return int x- the x coordinate
     */
    public double getX() {
        return this.x;
    }
    /**
     * simple getter for the y coordinate.
     * @return int y- the x coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * checks whether two doubles are equal using threshold value.
     * @param numOne first double
     * @param numTwo second double
     * @return true if equal,otherwise false
     */
    public boolean thresholdCompare(double numOne, double numTwo) {
        return Math.abs(numOne - numTwo) < THRESHOLD;

    }

    /**
     * setter for x.
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * setter for y.
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }
}