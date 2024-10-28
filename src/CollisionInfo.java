/**
 * Dolev Levi.
 * 315150110
 */

/**
 * The Collision info class-holds info regarding an collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor.
     * @param collisionPoint
     * @param collisionObject
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    // the point at which the collision occurs.

    /**
     * returns the point at which the collision occurs.
     * @return Point obj
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     *  returns the collidable object involved in the collision.
     * @return collidable obj
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }

    /**
     * returns true if the collosion object and point are
     * null.
     * @return boolean
     */
    public boolean isNull() {
        //both should be null or not null at same time.
        return this.collisionObject == null || this.collisionPoint == null;
    }
}