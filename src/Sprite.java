/**
 * Dolev Levy
 * 315150110
 */

import biuoop.DrawSurface;

/**
 * the sprite interface-specified for every object
 * that is printed on the screen.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d
     */
    void drawOn(DrawSurface d);
    /**
     *  notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * adding the sprite to a given Game object.
     * @param gameLevel
     */
    void addToGame(GameLevel gameLevel);
}