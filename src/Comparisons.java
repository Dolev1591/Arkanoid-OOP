/**
 * Dolev Levi.
 * 315150110
 */

/**
 * This class holds multiple functions that compares between objects
 * or primitive types.
 */
public class Comparisons {
    static final double THRESHOLD = 0.00001;
    /**
     * this function returns the max between the two
     * given numbers,if equal,still returns the equal
     * object.
     *
     * @param num1
     * @param num2
     * @return Double obj
     */
    public Double max(double num1, double num2) {
        if (num1 >= num2) {
            return num1;
        } else {
            return num2;
        }

    }

    /**
     * this function returns the min between the two
     * given numbers,if equal,still returns the equal
     * object.
     *
     * @param num1
     * @param num2
     * @return Double obj
     */
    public Double min(double num1, double num2) {
        if (num1 >= num2) {
            return num2;
        } else {
            return num1;
        }

    }

    /**
     * this function compares between the distance of 2 point from
     * one point,and returns the closer one.
     *
     * @param p1
     * @param p2
     * @param baseP
     * @return Point obj.
     */
    public Point closerPoint(Point p1, Point p2, Point baseP) {
        //checking the distance from a null object
        if (baseP == null) {
            return null;
            //dealing with cases that one of them is null or both.
        } else if (p1 == null || p2 == null) {
            if (p1 == null && p2 == null) {
                return null;
            } else if (p2 == null) {
                return p1;
            } else {
                return p2;
            }
        } else {
            //checking which distance is smaller and returning the closer one.
            double distance1 = p1.distance(baseP);
            double distance2 = p2.distance(baseP);
            if (distance1 >= distance2) {
                return p2;
            } else {
                return p1;
            }


        }
    }

    /**
     * checking wether the distance between base point to check point,
     * if yes-returns null,otherwise returns the cehck point.
     * @return Point
     * @param base
     * @param check
     */
    public Point isNeglegibleDis(Point base, Point check) {
        if (base == null || check == null) {
            return null;
        }
        double distance = base.distance(check);
        boolean isNegligible = distance <= THRESHOLD;
        if (isNegligible) {
            return base;
        } else {
            return check;
        }
    }
}
