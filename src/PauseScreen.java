/**
 * Dolev Levi
 * 315150110
 */

import biuoop.DrawSurface;

/**
 * the PauseScreen class responsible for pausing the screen when asked.
 */
public class PauseScreen implements Animation {
    private static final int FONTSIZE = 32;
    private static final int LEFTCORNERXTEXT = 300;
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(LEFTCORNERXTEXT, d.getHeight() / 2,
                "paused -- press space to continue", FONTSIZE);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
