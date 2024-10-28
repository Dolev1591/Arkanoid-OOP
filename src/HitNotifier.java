/**
 * Dolev Levy.
 * 315150110
 */

/**
 * this interface is used by objects that need the ability to
 * notify Listener's objects.
 */
public interface HitNotifier {
    /**
     * Adds a given HitListener as a listener to hit events.
     * @param hl
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl
     */
    void removeHitListener(HitListener hl);
}