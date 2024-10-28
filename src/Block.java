/**
 * Dolev Levi
 * 315150110
 */

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * the Block class-responsible for  creating a block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private List<HitListener> hitListeners;

    /**
     * constructor.
     * @param rectangle
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.hitListeners = new ArrayList<HitListener>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        List<Line> rectangleLines = rectangle.rectangleLines();
        //the rectangleLines function return list is sorted this way.
        Line top = rectangleLines.get(0);
        Line left = rectangleLines.get(1);
        Line bottom = rectangleLines.get(2);
        Line right = rectangleLines.get(3);
        this.notifyHit(hitter);
        //checking if the collosion occur on the bottom or top.
        if (top.isPointOnLine(collisionPoint)
                || bottom.isPointOnLine(collisionPoint)) {
            double newDx = currentVelocity.getDx();
            double newDy = -currentVelocity.getDy();
            return new Velocity(newDx, newDy);
        //checking if the collosion occur on the left or right.
        } else if (left.isPointOnLine(collisionPoint)
                || right.isPointOnLine(collisionPoint)) {
            double newDx = -currentVelocity.getDx();
            double newDy = currentVelocity.getDy();
            return new Velocity(newDx, newDy);
        //otherwise,the collosion is not on this block,velocity remains same.
        } else {
            return currentVelocity;
        }

    }

    /**
     * given a drawSurface object, the block is being
     * drawn on the surface.
     * @param d
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.getCollisionRectangle().drawRectangle(d);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * this function removes this block from a given game.
     * @param gameLevel
     */
    void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * notifies all listeners object that a hit has
     * occurred .
     * @param hitter
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);

    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
