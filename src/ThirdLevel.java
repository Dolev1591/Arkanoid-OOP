/**
 * Dolev Levi
 * 315150110
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * the third level.
 */
public class ThirdLevel implements LevelInformation {
    private static final int BLOCKSTARINGHEIGHT = 200;
    private static final int BLOCKSTARINGWIDTH = 175;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PADDING = 25;
    private static final int BRICKWIDTH = 60;
    private static final int BRICKHEIGHT = 30;
    private static final int PADDLEWIDTH = 100;
    private static final int PADDLESPEED = 7;
    private static final int BALLSPEED = 7;
    private static final double BALLANGLE = 50.0;
    private static final int NUMOFBLOCKS = 40;



    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int speed = BALLSPEED;
        Velocity v1 = Velocity.fromAngleAndSpeed(BALLANGLE, speed);
        Velocity v2 = Velocity.fromAngleAndSpeed(-BALLANGLE, speed);
        List<Velocity> lst = new ArrayList<>();
        lst.add(v1);
        lst.add(v2);
        return lst;
    }

    @Override
    public int paddleSpeed() {
        return PADDLESPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLEWIDTH;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0),
                WIDTH, HEIGHT, Color.decode("#2a8215")));
    }

    @Override
    public List<Block> blocks() {
        Color[] colors = {Color.gray, Color.red, Color.yellow,
                Color.blue, Color.WHITE};
        List<Block> blockList = new ArrayList<>();
        int currentBlockHeight;
        for (int i = 0; i < 5; i++) {
            currentBlockHeight = BLOCKSTARINGHEIGHT + i * BRICKHEIGHT;
            Color currentColor = colors[i];
            for (int j = BLOCKSTARINGWIDTH + i * BRICKWIDTH;
                 j <= WIDTH - PADDING - BRICKWIDTH; j = j + BRICKWIDTH) {
                //creating each block.
                Block b = new Block(new Rectangle(j, currentBlockHeight,
                        BRICKWIDTH, BRICKHEIGHT));
                b.getCollisionRectangle().setColor(currentColor);
                blockList.add(b);
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMOFBLOCKS;
    }
}
