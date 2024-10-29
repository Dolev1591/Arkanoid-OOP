import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Dolev Levy.
 * 315150110
 */

public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    private static final int MILISECINSEC = 1000;

    /**
     * constructor.
     * @param gui
     * @param framesPerSecond
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
    }

    /**
     * getter for the GUI.
     * @return GUI
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * the run method responsible for running the actual animation.
     * @param animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = MILISECINSEC / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}