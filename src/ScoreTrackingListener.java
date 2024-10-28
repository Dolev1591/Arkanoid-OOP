/**
 * Dolev Levi
 * 315150110
 */

/**
 * this class responsible for managing the score of the game.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private static final int HITSCORE = 5;
    /**
     * constructor.
     * @param scoreCounter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * whenever the ball has hit a block(which is not the death
     * block),and addition of 5 point should be added to the score.
     * @param beingHit
     * @param hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(HITSCORE);
    }
}