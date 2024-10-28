/**
 * Dolev levi
 * 315150110
 */

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * the sprite collection class responsible for storing
 * multiple sprite objects and calling methods upon them.
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites;

    /**
     * constructor given a sprites array list.
     * @param sprites
     */
    public SpriteCollection(ArrayList<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * empty constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * adding sprites to the sprites array.
     * @param s
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * removes a given sprite from the sprites field.
     * @param s
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }

    /**
     *  call drawOn(d) on all sprites.
     * @param d
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
}