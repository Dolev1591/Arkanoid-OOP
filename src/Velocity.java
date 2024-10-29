/**
 * Dolev Levy
 * 315150110
 */

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * this class represents velocity.
 * @author Dolev Levy
 * 315150110
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     * @param dx double-change in x
     * @param dy double change in y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * this function convertes speed and angle of movement to change
     * in x and y values.
     * @param angle  double angle accepted.
     * @param speed double speed of movement.
     * @return velocity object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //first converting to radians
        double radians = Math.toRadians(angle);
        //calculating the change in x,y values.
        double dx = speed * Math.sin(radians);
        double dy = speed * (-Math.cos(radians));
        return new Velocity(dx, dy);
    }

    /**
     * converting this velocity to speed,
     * based on dx^2+dy^2=speed^2.
     * @return double .
     */
    public double velocityToSpeed() {
        return sqrt(pow(abs(this.dx), 2) + pow(abs(this.dy), 2));
    }

    /**
     * getter for dx.
     * @return double dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * setter for dx.
     * @param dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     * getter for dy.
     * @return double value.
     */
    public double getDy() {
        return dy;
    }

    /**
     * setter for dy.
     * @param dy double value
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point\
     * with position (x+dx, y+dy).
     * @param p point accepted
     * @return new point
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + dx;
        double newY = p.getY() + dy;
        return new Point(newX, newY);
    }
}