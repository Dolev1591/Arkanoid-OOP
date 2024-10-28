import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * Dolev Levi
 * 315150110
 */
public class EndScreen implements Animation {
    private boolean isWin;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private static final int FONTSIZE = 32;
    private static final int LEFTCORNERXTEXT = 200;

    /**
     * constructor.
     * @param isWin
     * @param score
     */
    public EndScreen(boolean isWin, Counter score) {
        this.isWin = isWin;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!isWin) {
            d.drawText(LEFTCORNERXTEXT, d.getHeight() / 2,
                    "Game Over. Your score is " + score.getValue(), FONTSIZE);
        } else {
            d.drawText(LEFTCORNERXTEXT, d.getHeight() / 2,
                    "You Win! Your score is " + score.getValue(), FONTSIZE);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
