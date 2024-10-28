/**
 * Dolev Levi
 * 315150110
 */

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * this class responsible for presenting the name of the level.
 */
public class LevelNameIndicator implements Sprite {
    private String levelName;
    private static final int STARTINGX = 700;
    private static final int STARTINGY = 15;
    private static final int FONT = 12;

    /**
     * constructor.
     * @param levelName
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(STARTINGX, STARTINGY, levelName, FONT);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);

    }
}
