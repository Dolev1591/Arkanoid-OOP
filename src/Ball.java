/**
 * Dolev levy
 * 315150110
 */
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * this is the ball class.
 * @author Dolev Levy
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment environment;
    public static final Color DEFAULTCOLOR = Color.black;


    /**
     * constructor for ball.
     * @param center
     * @param radius
     * @param color
     * @param velocity
     * @param environment
     */
    public Ball(Point center, int radius, Color color,
                Velocity velocity, GameEnvironment environment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = velocity;
        this.environment = environment;
    }

    /**
     * second constructor- no need for color.
     * @param center
     * @param radius
     * @param velocity
     * @param environment
     */
    public Ball(Point center, int radius,
                Velocity velocity, GameEnvironment environment) {
        this(center, radius, velocity, environment, DEFAULTCOLOR);
    }
    /**
     * constructor.
     * @param center
     * @param radius
     * @param velocity
     * @param environment
     * @param color
     */
    public Ball(Point center, int radius,
                Velocity velocity, GameEnvironment environment, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = velocity;
        this.environment = environment;
        this.color = DEFAULTCOLOR;
    }

    /**
     * getter for the x value of the center rounded down.
     *
     * @return int value
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * getter for the y value of the center rounded down.
     *
     * @return int value
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * getter for the radius size of the ball.
     *
     * @return int value
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * getter for the color of the ball.
     *
     * @return an java.awt.Color object.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * given an drawSurface object,this function draws this ball
     * on him.
     * @param surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        int centerX = this.getX();
        int centerY = this.getY();
        int radius = this.getSize();
        surface.setColor(this.getColor());
        surface.fillCircle(centerX, centerY, radius);
        surface.setColor(DEFAULTCOLOR);
        surface.drawCircle(centerX, centerY, radius);
    }
    /**
     * velocity setter accepting velocity object.
     *
     * @param v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * velocity setter accepting dx and dy.
     *
     * @param dx double-change in x.
     * @param dy double- change in y.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /**
     * getter for the velocity of the ball.
     *
     * @return velocity object
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * moveontstep function responsible for moving the ball,
     * making sure to change direction if a hit has occurred.
     */
    public void moveOneStep() {
        //first creating a the trajectory
        Point end = this.velocity.applyToPoint(this.center);
        Line trajectory = new Line(this.center, end);
        CollisionInfo info = environment.getClosestCollision(trajectory);
        //if null,meaning there is not collision, move the ball
        if (info.isNull()) {
            this.center = velocity.applyToPoint(this.center);
            //if a collision is about to occur, change the velocity.
        } else {
            this.velocity = info.collisionObject().
                    hit(this, info.collisionPoint(), this.velocity);
        }

    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
    /**
     * removes the ball from a given game.
     * @param g
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}