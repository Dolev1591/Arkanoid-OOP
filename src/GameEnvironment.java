/**
 * Dolev Levi
 * 315150110
 */

import java.util.ArrayList;

/**
 * the game enviroment class.
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidables;

    /**
     * constructor.
     *
     * @param collidables
     */
    public GameEnvironment(ArrayList<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * second constructor given that there is not a
     * collidables list.
     */
    public GameEnvironment() {
        this(new ArrayList<Collidable>());
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * given a collidable,if he exists in the collidables
     * field, it will remove him.
     * @param b
     */
    public void removeCollidable(Collidable b) {
        this.collidables.remove(b);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory
     * @return returns an collisionInfo object.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable closetCollidable = null;
        Point closetCollosion = null;
        Point currentCollosion = null;
        Comparisons comp = new Comparisons();
        for (Collidable current : this.collidables) {
            // skipping irrelevant ones.
            if (current == null) {
                continue;
            }
            currentCollosion = trajectory.closestIntersectionToStartOfLine(
                    current.getCollisionRectangle());
            //skipping cases where the line has no intersections with current.
            if (currentCollosion == null) {
                continue;
            } else {
                closetCollosion = comp.closerPoint(closetCollosion,
                        currentCollosion, trajectory.getOrigStart());
                //meaning a swap has occurred
                if (closetCollosion == currentCollosion) {
                    closetCollidable = current;
                }
            }
        }
        CollisionInfo info = new CollisionInfo(closetCollosion, closetCollidable);
        return info;
    }
}