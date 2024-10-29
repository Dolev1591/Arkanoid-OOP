/**
 * Dolev Levi
 * 315150110
 */
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * this class responsible for displaying the updated score
 * of the game.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private static final int WIDTH = 800;
    private static final int TEXTBOXHEIGHT = 20;
    private static final int TEXTBOXSTARTX = 0;
    private static final int TEXTBOXSTARTY = 0;
    private static final int FONTSIZE = 12;
    private static final int TEXTSTARTX = 400;
    private static final int TEXTSTARTY = 15;
    /**
     * constructor.
     * @param score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(TEXTBOXSTARTX, TEXTBOXSTARTY, WIDTH, TEXTBOXHEIGHT);
        String scoreString = "Score:" + this.score.getValue();
        d.setColor(Color.black);
        d.drawText(TEXTSTARTX, TEXTSTARTY, scoreString, FONTSIZE);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);

    }
}
