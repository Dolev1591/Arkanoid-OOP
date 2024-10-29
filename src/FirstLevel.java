/**
 * Dolev Levi
 * 315150110.
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * First level- the "direct hit" level.
 */
public class FirstLevel implements LevelInformation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BLOCKTOPLEFTX = 380;
    private static final int BLOCKTOPLEFTY = 200;
    private static final int BLOCKWIDTH = 40;
    private static final int BLOCKHEIGHT = 40;
    private static final int PADDLESPEED = 10;
    private static final int PADDLEHEIGHT = 100;

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v = Velocity.fromAngleAndSpeed(0.0, 10);
        List<Velocity> vList = new ArrayList<>();
        vList.add(v);
        return vList;
    }

    @Override
    public int paddleSpeed() {
        return PADDLESPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLEHEIGHT;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT, Color.black));
    }

    @Override
    public List<Block> blocks() {
        Point topLeft = new Point(BLOCKTOPLEFTX, BLOCKTOPLEFTY);
        Block block = new Block(new Rectangle(topLeft, BLOCKWIDTH, BLOCKHEIGHT, Color.red));
        List<Block> blockList = new ArrayList<>();
        blockList.add(block);
        return blockList;

    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
