/**
 * Dolev Levi
 * 315150110
 */

/**
 * this class accept a string and validates him in accord to given values.
 */
public class DigitValidator {
    private int notValidFlag;
    private int validUpperBoundery;
    private int validLowerBoundery;

    /**
     * constructor.
     * @param notValidFlag
     * @param validUpperBoundery
     * @param validLowerBoundery
     */
    public DigitValidator(int notValidFlag,
                          int validUpperBoundery, int validLowerBoundery) {
        this.notValidFlag = notValidFlag;
        this.validUpperBoundery = validUpperBoundery;
        this.validLowerBoundery = validLowerBoundery;
    }
    /**
     * this function accepts a string and "validate" it -meaning
     * if it is 1,2 or 3-it will return the number,else
     * it will return 0.
     * @param str
     * @return int
     */
    public int isValidDigit(String str) {
        if (str == null) {
            return notValidFlag;
        }
        try {
            int num = Integer.parseInt(str);
            if (num >= validLowerBoundery && num <= validUpperBoundery) {
                return num;
            } else {
                return notValidFlag;
            }
        } catch (NumberFormatException e) {
            return notValidFlag;
        }
    }
}
