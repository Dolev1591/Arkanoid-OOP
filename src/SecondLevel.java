/**
 * Dolev Levi
 * 315150110
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * the second level of the arkanoid game called Wide Easy.
 */
public class SecondLevel implements LevelInformation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final double SPEED = 10.0;
    private static final double ANGLESTART = 50.0;
    private static final double ANGLECHANGE = 10.0;
    private static final int PADDLEWIDTH = 600;
    private static final int PADDLESPEED = 2;
    private static final int NUMOFBLOCKS = 15;
    private static final double BLOCKSSTARTINGHEIGHT = 250.0;
    private static final double BLOCKSHEIGHT = 30.0;
    private static final double BLOCKSWIDTH = 50.0;
    private static final double BLOCKSSTARTINGX = 25.0;
    private static final double BLOCKSXCHANGE = 50.0;

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        double speed = SPEED;
        for (double i = ANGLESTART; i >= -ANGLESTART; i = i - ANGLECHANGE) {
            velocities.add(Velocity.fromAngleAndSpeed(i, speed));
        }
        return velocities;
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
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0),
                WIDTH, HEIGHT, Color.white));
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        double height = BLOCKSSTARTINGHEIGHT;
        double blocksHeight = BLOCKSHEIGHT;
        double blocksWidth = BLOCKSWIDTH;
        List<Double> xValues = new ArrayList<>();
        for (double i = BLOCKSSTARTINGX; i <= 725.0; i = i + 50.0) {
            xValues.add(i);
        }
        Iterator<Double> it = xValues.iterator();
        Color[] colors = {Color.red, Color.red, Color.orange, Color.orange,
                Color.yellow, Color.yellow, Color.green, Color.green,
                Color.green, Color.blue, Color.blue,
                Color.pink, Color.pink, Color.cyan, Color.cyan};
        for (Color c :colors) {
            blocks.add(new Block(new Rectangle(new Point(it.next(),
                    height), blocksWidth, blocksHeight, c)));
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUMOFBLOCKS;
    }
}
