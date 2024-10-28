/**
 * Dolev levi
 * 315150110
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * the game class,responsible for managing the actual game.
 */
public class GameLevel implements Animation {
    private Paddle paddle;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter numOfBlocks;
    private BlockRemover blockRemover;
    private Counter numOfBalls;
    private BallRemover ballRemover;
    private Counter score;
    private ScoreTrackingListener scoreTracker;
    private ScoreIndicator scoreIndicator;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboardSensor;
    private LevelInformation levelInfo;
    private LevelNameIndicator levelname;
    private int guiWidth;
    private int guiHeight;
    private static final int PADDLEHEIGHT = 20;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDING = 25;
    private static final int COLORCONST = 256;
    private static final int BALLSIZE = 6;
    private static final int CLEARLEVELSCORE = 100;
    /**
     * adding a collidable to the game environment field.
     * @param c
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /**
     * adding a sprite object to the sprites field.
     * @param s
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }


    /**
     * constructor.
     * @param levelInfo
     * @param keyboardSensor
     * @param runner
     * @param scoreCounter
     * @param gameWidth
     * @param gameHeight
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor,
                     AnimationRunner runner, Counter scoreCounter,
                     int gameWidth, int gameHeight) {
        this.keyboardSensor = keyboardSensor;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.levelInfo = levelInfo;
        this.runner = runner;
        this.guiHeight = gameHeight;
        this.guiWidth = gameWidth;
        this.levelname = new LevelNameIndicator(levelInfo.levelName());
        this.numOfBalls = new Counter();
        this.ballRemover = new BallRemover(this, this.numOfBalls);
        this.numOfBlocks = new Counter();
        this.blockRemover = new BlockRemover(this, numOfBlocks);
        this.score = scoreCounter;
        this.scoreIndicator = new ScoreIndicator(this.score);
        this.scoreTracker = new ScoreTrackingListener(this.score);

    }

    /**
     * initializes the padding for the screen-up,left and right
     * blocks that make sure the ball does not go out of bounds.
     */
    public void paddingInitializer() {
        ArrayList<Block> blocks = new ArrayList<>();
        //top block
        blocks.add(new Block(new Rectangle(0, 0, WIDTH, PADDING)));
        //left block
        blocks.add(new Block(new Rectangle(0, PADDING, PADDING,
                HEIGHT - PADDING)));
        //right block
        blocks.add(new Block(new Rectangle(WIDTH - PADDING, PADDING,
                PADDING, HEIGHT - PADDING)));
        for (Block b:blocks) {
            b.addToGame(this);
        }
    }

    /**
     * this function generates a new random color.
     * @return color object.
     */
    public Color randomColor() {
        Random random = new Random();
        //color const accepts numbers from 0-255
        int r = random.nextInt(COLORCONST);
        int g = random.nextInt(COLORCONST);
        int b = random.nextInt(COLORCONST);
        return  new Color(r, g, b);
    }
    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     * basiclly responsible for creating everything in order
     * to call the run method.
     */
    public void initialize() {
        this.setBackground(levelInfo);
        this.createPaddle(levelInfo);
        this.createBallsOnPaddle(levelInfo.initialBallVelocities());
        this.createDeathBlock();
        this.paddingInitializer();
        this.createBlocks(levelInfo);
        this.initializeScoreIndicator();
        this.initializeLevelName();
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboardSensor.isPressed("p")) {
            PauseScreen p = new PauseScreen();
            KeyPressStoppableAnimation k = new KeyPressStoppableAnimation(
                    this.keyboardSensor, KeyboardSensor.SPACE_KEY, p);
            this.runner.run(k);
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.numOfBlocks.getValue() == 0) {
            score.increase(CLEARLEVELSCORE);
            this.running = false;
        }
        if (this.numOfBalls.getValue() == 0) {
            EndScreen e = new EndScreen(false, score);
            KeyPressStoppableAnimation k = new KeyPressStoppableAnimation(
                    this.keyboardSensor, KeyboardSensor.SPACE_KEY, e);
            this.runner.run(k);
            System.exit(1);
        }

    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
    /**
     * this method responsible for actually running the game-
     * starting the animation loop.
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.run(this);
    }
    /**
     * removes a given collidable.
     * @param c
     */
    void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removes a given sprite.
     * @param s
     */
    void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);

    }

    /**
     * creating the paddle in accord to level info.
     * @param levelInfo
     */
    public void createPaddle(LevelInformation levelInfo) {
        int topLeftX = this.guiWidth / 2 - levelInfo.paddleWidth() / 2;
        int topLeftY = this.guiHeight - PADDING;
        Rectangle paddleRec = new Rectangle(new Point(topLeftX, topLeftY),
                levelInfo.paddleWidth(), PADDLEHEIGHT, Color.YELLOW);
        Paddle paddle = new Paddle(this.keyboardSensor, paddleRec,
                PADDING, this.guiWidth - PADDING,
                Color.yellow, levelInfo.paddleSpeed());
        this.paddle = paddle;
        this.paddle.addToGame(this);
    }

    /**
     * creating the balls in accord to the level info accepted
     * (generating the balls slightly above the paddle).
     * @param velocityList
     */
    public void createBallsOnPaddle(List<Velocity> velocityList) {
        this.numOfBalls.increase(velocityList.size());
        Point startingPoint = this.paddle.middlePoint();
        startingPoint.setY(startingPoint.getY() - 20);
        for (Velocity v: velocityList) {
            Ball b = new Ball(startingPoint, BALLSIZE,
                    Color.white, v, this.environment);
            b.addToGame(this);
        }
    }

    /**
     * creating the death region.
     */
    public void createDeathBlock() {
        Block deathBlock =  new Block(new Rectangle(
                0, guiHeight, guiWidth, PADDING));
        deathBlock.addHitListener(this.ballRemover);
        deathBlock.addToGame(this);
    }

    /**
     * creating the blocks.
     * @param levelInfo
     */
    public void createBlocks(LevelInformation levelInfo) {
        List<Block> blocksList = levelInfo.blocks();
        for (Block b: blocksList) {
            b.addToGame(this);
            b.addHitListener(this.blockRemover);
            b.addHitListener(scoreTracker);
        }
        numOfBlocks.increase(blocksList.size());
    }

    /**
     * sets the background based on the levelinfo accepted.
     * @param levelInfo
     */
    public void setBackground(LevelInformation levelInfo) {
        Sprite background = levelInfo.getBackground();
        background.addToGame(this);
    }
    /**
     * initializing the level name sprite.
     */
    public void initializeLevelName() {
        this.levelname.addToGame(this);
    }

    /**
     * initalizing the score indicator.
     */
    public void initializeScoreIndicator() {
        this.scoreIndicator.addToGame(this);
    }
}