/**
 * Dolev Levi
 * 315150110
 */

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * the CountdownAnimation responsible for displaying a count down on the screen
 * before each level.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean isTimeInitialized;
    private double startingTimeMilliSec;
    private boolean isFinished;
    private static final int FONTSIZE = 30;
    private static final double MILISECINSEC = 1000.0;
    private static final double HEIGHTADAPTOR = 0.75;
    private static final double WIDTHADAPTOR = 0.5;

    /**
     * constructor.
     * @param numOfSeconds
     * @param countFrom
     * @param gameScreen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.isFinished = false;
        this.isTimeInitialized = false;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //making sure time is initialized once.
        if (!this.isTimeInitialized) {
            startingTimeMilliSec = System.currentTimeMillis();
            this.isTimeInitialized = true;
        }
        double currentTimeMiliSec = System.currentTimeMillis();
        this.gameScreen.drawAllOn(d);
        double oneNumberTime = numOfSeconds / (double) countFrom;
        double timePassedMiliSec =
                currentTimeMiliSec - this.startingTimeMilliSec;
        int numToDisplay = (int) ((double) (this.countFrom + 1)
                - timePassedMiliSec / oneNumberTime / MILISECINSEC);
        d.setColor(Color.red);
        d.drawText((int) (d.getWidth() * WIDTHADAPTOR), (int)
                (d.getHeight() * HEIGHTADAPTOR), "" + numToDisplay, FONTSIZE);
        //checking whether the countdown is done.
        if (timePassedMiliSec / MILISECINSEC > this.numOfSeconds) {
            this.isFinished = true;
        }

    }
    @Override
    public boolean shouldStop() {
       return this.isFinished;
    }
}