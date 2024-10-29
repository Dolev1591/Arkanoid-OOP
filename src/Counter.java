/**
 * Dolev Levi
 * 315150110
 */

/**
 * is a simple class that is used for counting.
 */
public class Counter {
    private int counter;

    /**
     * basic constructor.
     * @param counter
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * counstructor with no arguments.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * add number to current count.
     * @param number
     */

    void increase(int number) {
        this.counter = counter + number;
    }
    /**
     * subtract number from current count.
     * @param number
     */
    void decrease(int number) {
        this.counter = counter - number;
    }
    /**
     * getter for the current value.
     * @return int
     */
    int getValue() {
        return this.counter;
    }
}