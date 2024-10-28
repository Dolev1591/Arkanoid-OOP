/**
 * Dolev Levi
 * 315150110
 */

import biuoop.KeyboardSensor;

import java.util.List;

/**
 * the Gameflow class is responsible for managing the levels of the game.
 */
public class GameFlow {
    private LevelInformation levelinfo;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner runner;
    private  Counter scoreCounter;
    private int gameWidth;
    private int gameHeight;

    /**
     * constructor.
     * @param keyboardSensor
     * @param runner
     * @param scoreCounter
     * @param gameWidth
     * @param gameHeight
     */
    public GameFlow(KeyboardSensor keyboardSensor,
                    AnimationRunner runner, Counter scoreCounter,
                    int gameWidth, int gameHeight) {
        this.keyboardSensor = keyboardSensor;
        this.runner = runner;
        this.scoreCounter = scoreCounter;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
    }

    /**
     * the run levels function manages the different level and responsible
     * for stopping when needed.
     * @param levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor, this.runner, this.scoreCounter,
                    this.gameWidth, this.gameHeight);
            level.initialize();
            level.run();
        }
         EndScreen e = new EndScreen(true, scoreCounter);
        KeyPressStoppableAnimation k = new KeyPressStoppableAnimation(
                this.keyboardSensor, KeyboardSensor.SPACE_KEY, e);
        this.runner.run(k);
        System.exit(1);
    }
}