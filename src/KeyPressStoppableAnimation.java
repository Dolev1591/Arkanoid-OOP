/**
 * 315150110
 * Dolev Levi
 */

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * this class responsible for decorating an animation object,
 * and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation a;
    private boolean isAlreadyPressed;
    /**
     * Constructor.
     *
     * @param animation is the animation.
     * @param key       is the key that stops the animation.
     * @param keyboardSensor    is the KeyBoardSensor.
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor,
                                      String key, Animation animation) {
        this.keyboard = keyboardSensor;
        this.key = key;
        this.a = animation;
        this.isAlreadyPressed = true;
    }

    @Override
    public boolean shouldStop() {
        return this.keyboard.isPressed(key) && !this.isAlreadyPressed;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.isAlreadyPressed) {
            this.a.doOneFrame(d);
        }
        if (!this.keyboard.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }
}