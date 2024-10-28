/**
 * Dolev Levi
 * 315150110
 */

/**
 *  a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 *  of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param gameLevel
     * @param removedBlocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game
     * Remember to remove this listener from the block
     * that is being removed from the game.
     * @param beingHit
     * @param hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.gameLevel.removeCollidable(beingHit);
        this.gameLevel.removeSprite(beingHit);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
    }
}