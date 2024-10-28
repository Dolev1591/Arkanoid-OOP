/**
 * Dolev Levi
 * 315150110
 */

import java.util.List;

/**
 * the LevelInformation specifies the information required to describe
 * a level.
 */
public interface LevelInformation {
    /**
     * returns the number of balls the level starts with.
     * @return int
     */
    int numberOfBalls();

    /**
     * he initial velocity of each ball,
     * Note that initialBallVelocities().size() == numberOfBalls().
     * @return list of Velocity objects.
     */
    List<Velocity> initialBallVelocities();

    /**
     * returns the paddle speed.
     * @return int
     */
    int paddleSpeed();

    /**
     * returns the paddle width.
     * @return int
     */
    int paddleWidth();
    // the level name will be displayed at the top of the screen.

    /**
     * returns the level's name.
     * @return String
     */
    String levelName();
    /**
     * Returns a sprite with the background of the level.
     * @return Sprite
     */
    Sprite getBackground();
    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return List of Blocks objects.
     */
    List<Block> blocks();
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number is <= blocks.size();
     * @return int
     */
    int numberOfBlocksToRemove();
}