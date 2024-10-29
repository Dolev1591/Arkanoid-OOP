/**
 * Dolev Levi
 * 315150110
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * the paddle class.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRec;
    private int speed;
    private double screenLeftBorder;
    private double screenRightBorder;
    private  static final Color DEFAULTCOLOR = Color.BLUE;
    private  static final double NUMBEROFREGIONS = 5.0;

    /**
     * constructor without color.
     * @param keyboard
     * @param paddleRec
     * @param screenLeftBorder
     * @param screenRightBorder
     * @param speed
     */
    public Paddle(KeyboardSensor keyboard, Rectangle paddleRec,
                  double screenLeftBorder,
                  double screenRightBorder, int speed) {
       this(keyboard, paddleRec, screenLeftBorder,
                screenRightBorder, DEFAULTCOLOR, speed);


    }

    /**
     * constructor with all parameters.
     * @param keyboard
     * @param paddleRec
     * @param screenLeftBorder
     * @param screenRightBorder
     * @param color
     * @param speed
     */
    public Paddle(KeyboardSensor keyboard, Rectangle paddleRec,
                  double screenLeftBorder, double screenRightBorder,
                  Color color, int speed) {
        this.keyboard = keyboard;
        this.paddleRec = paddleRec;
        this.screenLeftBorder = screenLeftBorder;
        this.screenRightBorder = screenRightBorder;
        this.paddleRec.setColor(color);
        this.speed = speed;
    }

    /**
     * this method moves the paddle left if possible.
     */
    public void moveLeft() {
        if (isValidMove(-speed)) {
            Point newTopLeft = this.paddleRec.getUpperLeft();
            newTopLeft.setX(newTopLeft.getX() - speed);
            this.paddleRec.setTopLeft(newTopLeft);
        }
    }
    /**
     * this method moves the paddle right if possible.
     */
    public void moveRight() {
        if (isValidMove(speed)) {
            Point newTopLeft = this.paddleRec.getUpperLeft();
            newTopLeft.setX(newTopLeft.getX() + speed);
            this.paddleRec.setTopLeft(newTopLeft);
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        paddleRec.drawRectangle(d);

    }

    /**
     * responsible for checking if a move action was asked,
     * and calls the function to preform if possible.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    };

    // Collidable

    /**
     * returns the rectangle consisting the paddle.
     * meaning the paddlerec field.
     * @return rectangle object.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleRec;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double speed = currentVelocity.velocityToSpeed();
        double paddleWidth = this.paddleRec.getWidth();
        double regionSize = paddleWidth / NUMBEROFREGIONS;
        double collisionPointX = collisionPoint.getX();
        double regionStartingX = this.paddleRec.getUpperLeft().getX();
        for (int i = 0; i < NUMBEROFREGIONS; i++) {
            if (regionStartingX <= collisionPointX
                    && regionStartingX + regionSize >= collisionPointX) {
                double angle = 300 + i * 30;
                return Velocity.fromAngleAndSpeed(angle, speed);
            } else {
                regionStartingX = regionStartingX + regionSize;
            }
        }
        return null;

    }
    /**
     * Add this paddle to the game.
     * @param g
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    };

    /**
     * returns the middle point of the upper boundery of the paddle.
     * @return point
     */
    public Point middlePoint() {
        double y = this.paddleRec.getUpperLeft().getY();
        //calculating the middle of the paddle.
        double x = this.paddleRec.getUpperLeft().getX()
                + (this.paddleRec.getWidth() / 2.0);
        return new Point(x, y);
    }

    /**
     * checking wether moving the paddle left or right a given size is ok,
     * meaning the paddle wont step out of the screen.
     * @param movement
     * @return true if valid, false if not.
     */
    public boolean isValidMove(double movement) {
        Point topLeft = this.paddleRec.getUpperLeft();
        double topLeftX = topLeft.getX();
        double paddleWidth = paddleRec.getWidth();
        if (topLeftX + movement < this.screenLeftBorder || topLeftX
                + paddleWidth + movement > this.screenRightBorder) {
            return false;
        }
        return true;
    }
}