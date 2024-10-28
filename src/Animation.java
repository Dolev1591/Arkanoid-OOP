/**
 * Dolev Levi
 * 315150110
 */

import biuoop.DrawSurface;

/**
 * the animation interface.
 */
public interface Animation {
    /**
     * this method preforms whats needed to be done in
     * one frame over the draw surface object.
     * @param d
     */
    void doOneFrame(DrawSurface d);

    /**
     * this method checks when the animation should stop.
     * @return boolean.
     */
    boolean shouldStop();
}