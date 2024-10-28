/**
 * Dolev levi
 * 315150110
 */

/**
 * this class responsible for removing balls from the game
 * after a ball has hit the death block.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter numOfBalls;

    /**
     * constructor.
     * @param gameLevel
     * @param numOfBalls
     */
    public BallRemover(GameLevel gameLevel, Counter numOfBalls) {
        this.gameLevel = gameLevel;
        this.numOfBalls = numOfBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        numOfBalls.decrease(1);
    }
}
