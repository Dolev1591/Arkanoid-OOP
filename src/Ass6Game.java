/**
 * Dolev Levy.
 * 315150110
 */

import biuoop.GUI;
import java.util.ArrayList;
import java.util.List;

/**
 * the main class -starts the game.
 */
public class Ass6Game {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int FPS = 60;
    private static final int LVLNUMBER1 = 1;
    private static final int LVLNUMBER3 = 3;
    private static final int NOTVALIDFLAG = 60;
    /**
     * main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        GUI gui = new GUI("ARKANOID", WIDTH, HEIGHT);
        AnimationRunner runner = new AnimationRunner(gui, FPS);
        //creating the list of lvl to add from and for back up.
        FirstLevel lvl1 = new FirstLevel();
        SecondLevel lvl2 = new SecondLevel();
        ThirdLevel lvl3 = new ThirdLevel();
        List<LevelInformation> lvlInfos = new ArrayList<>();
        lvlInfos.add(lvl1);
        lvlInfos.add(lvl2);
        lvlInfos.add(lvl3);
        GameFlow gameFlow = new GameFlow(gui.getKeyboardSensor(),
                runner, new Counter(), WIDTH, HEIGHT);
        //creating the list accepted from the user.
        List<LevelInformation> lvlsAccepted = new ArrayList<>();
        DigitValidator digit =
                new DigitValidator(NOTVALIDFLAG, LVLNUMBER3, LVLNUMBER1);
        for (String s : args) {
            int currentNum = digit.isValidDigit(s);
            if (currentNum == NOTVALIDFLAG) {
                continue;
            } else {
                lvlsAccepted.add(lvlInfos.get(currentNum - 1));
            }
        }
        //if the arguments from the user are not valid,run default.
            if (lvlsAccepted.isEmpty()) {
                gameFlow.runLevels(lvlInfos);
            } else {
                gameFlow.runLevels(lvlsAccepted);
            }
        }
    }
