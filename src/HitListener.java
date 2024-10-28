/**
 * Dolev Levy.
 * 315150110
 */

/**
 * this interface is used by objects that need the ability to
 * "listen" when a hit has occurred.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.

    /**
     * This method is called whenever the beingHit object is hit
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit
     * @param hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}