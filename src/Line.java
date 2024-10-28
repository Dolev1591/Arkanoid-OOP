import java.util.List;

/**
 * this class represents a line.
 * @author Dolev Levy
 * 315150110
 */
public class Line {
    private Point start;
    private Point end;
    private Double slope;
    private Double yIntersection;
    private Point origStart;
    private Point origEnd;
    private boolean isPoint;

    /**
     * getter for slope value.
     * @return Double  value
     */
    public Double getSlope() {
        return this.slope;
    }
    /**
     * getter for the yIntersection.
     * @return Double value.
     */
    public Double getYIntersection() {
        return this.yIntersection;
    }

    /**
     * constructor for the line class.
     * @param start first point
     * @param end second point
     */
    public Line(Point start, Point end) {
        this.origStart = start;
        this.origEnd = end;
        /* sorting the start and end point for convenience,start is the
        one with smaller x value,if equal then the one with smaller y values.
         */
        Point startPoint = startEndSorting(start, end);
        Point endPoint = startPoint == start ? end : start;
        this.start = startPoint;
        this.end = endPoint;
        this.slope = slopeCalc();
        this.yIntersection = intersectionWithYAxis();
        this.isPoint = isPoint();
    }
    /**
     * second type of constructor-accepts x,y values of the points.
     * @param x1 first point x value
     * @param y1 first point y value
     * @param x2 second point x value
     * @param y2 second point y value
     */
    public Line(double x1, double y1, double x2, double y2) {
        //calling the first constructor with the new points.
        this(new Point(x1, y1), new Point(x2, y2));
    }
    /**
     * the function accepts two points and returns
     * the start point-meaning the one with lower x value,
     * if the x values are same-returns the one with smaller y values.
     * @param pointOne first point
     * @param pointTwo second point
     * @return "start" point
     */
    public Point startEndSorting(Point pointOne, Point pointTwo) {
        //if they have same x values,start is the one with lower y value.
        if (pointOne.getX() == pointTwo.getX()) {
            //if they are the same,it does not matter -return point one.
            if (pointOne.getY() == pointTwo.getY()) {
                return pointOne;
            }
            //parallel to y axis
            if (pointOne.getY() < pointTwo.getY()) {
                return pointOne;
            } else {
                return pointTwo;
            }
        } else if (pointOne.getX() < pointTwo.getX()) {
            return pointOne;
        } else {
            return pointTwo;
        }



    }
    /**
     *returns the length of the line.
     * @return double
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * returns the middle of the line.
     * @return point object.
     */
    public Point middle() {
        double middleXCoordinate = (this.start.getX() + this.end.getX()) / 2.0;
        double middleYCoordinate = (this.start.getY() + this.end.getY()) / 2.0;
        return new Point(middleXCoordinate, middleYCoordinate);
    }

    /**
     * returns the start of the line.
     * @return start point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     * @return end point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * checks whether the lines intersect(even in more then one point),and
     * returns true if it is,false otherwise.
     * @param other other line
     * @return Returns true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        /*if the intersection function returns null,
        it means that the lines are not intersecting or have more then one.
         */
        if (this.intersectionWith(other) == null) {
            boolean isThisOnOther = this.isPointOnLine(other.start)
                    || this.isPointOnLine(other.end);
            boolean isOtherOnThis = other.isPointOnLine(this.start)
                    || other.isPointOnLine(this.end);
            return isThisOnOther || isOtherOnThis;
        } else {
            return true;
        }
    }

    /**
     * this function finds the intersection point between this line and
     * a given line-if there isnt or there is more then one,returns null.
     * @param other other line.
     * @return the intersection point ,null otherwise.
     */
    public Point intersectionWith(Line other) {
        //first checking if both are actually points.
        if (this.isPoint && other.isPoint) {
           if (this.equals(other)) {
               //all four point are equal,thus it doesn't matter who returns.
               return this.start;
           } else {
               return null;
           }
        } else if (this.isPoint || other.isPoint) {
            /*creating new variables to keep the on which is actually point
            and the one which is line */
            Line pointOne =  this.isPoint ? this : other;
            Line lineOne =  this.isPoint ? other : this;
            //start and end points are equal,thus using the start for convince.
            if (lineOne.isPointOnLine(pointOne.start)) {
                return pointOne.start;
            } else {
                return null;
            }
        } else {
            if (areParallelToEachOther(other)) {
                    /*only case which they have a single intersection point
                    is if other line ends where this line begins or if
                    it starts when this one ends.
                     */
                    return continuingParallelLines(other);

            } else {
                //dealing with special case: one of them parallel to y axis
                if (this.isParallelToYAxis() || other.isParallelToYAxis()) {
                    Line parallel =  this.isParallelToYAxis() ? this : other;
                    Line noParallel =  this.isParallelToYAxis() ? other : this;
                    /*parallel one has the same x values all over the line,
                    thus,if there is intersection, it has the x value of the
                     start point*/
                    Double possibleX = parallel.start().getX();
                    Double possibleY = noParallel.yValueGetter(possibleX);
                    Point possiblePoint = (new Point(possibleX, possibleY));
                    if (noParallel.isPointOnLine(possiblePoint)
                            && parallel.isPointOnLine(possiblePoint)) {
                        return possiblePoint;
                    }  else {
                        return null;

                    }
                } else {
                    //those are regular lines-all special cases been dealt with
                    Point possibleP = this.normalIntersection(other);
                    if (this.isPointOnLine(possibleP)
                            && other.isPointOnLine(possibleP)) {
                        return possibleP;
                    } else {
                        return null;
                    }


                }

            }
        }



    }


    /**
     * return true is the lines are equal, false otherwise.
     * @param other other Line object
     * @return boolean value.
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }

    /**
     * calculating the slope of the line according to the
     * following formula:y2-y1/x2-x1.
     * @return double-slope value
     */
    public Double slopeCalc() {
        //if they have same x values,the line has no slope.
        if (this.start.thresholdCompare(this.start.getX(), this.end.getX())) {
            return null;
        }
        double yPart = this.start.getY() - this.end.getY();
        double xPart = this.start.getX() - this.end.getX();
        return yPart / xPart;
    }

    /**
     * checks whether the line is parallel to the Y axis.
     * @return true if parallel and false if is not
     */
    public boolean isParallelToYAxis() {
        //if end and start has the x coordinate value-then parallel.
        return this.start.getX() == this.end.getX();
    }
    /**
     * returns the x value of the intersection with
     * the y axis.
     * @return double-the x value
     */
    public Double intersectionWithYAxis() {
        if (this.slope == null) {
            return null;
        } else {
            return this.start.getY() - (this.start.getX() * this.slope);
        }

    }

    /**
     * this function checks whether a point is on the line,given that
     * the line has a slope(meaning not parallel to y axis).
     * @param point given point
     * @return true if on line,false otherwise.
     */
    public boolean isPointOnLine(Point point) {
        if (this.isPoint) {
            //start and end are equal,so it doesn't start or end.
            return this.start.equals(point);
        } else if (isOnLinearEquation(point)) {
            // a check for the case the line is parallel to y axis.
            if (isParallelToYAxis()) {
                return this.start.getY() <= point.getY()
                        && this.end.getY() >= point.getY();
            } else {
                ///checks for regular cases.
                boolean isStartEqual = this.start.thresholdCompare(
                        this.start.getX(), point.getX());
                boolean isEndEqual = this.start.thresholdCompare(
                        this.start.getX(), point.getX());
                return (this.start.getX() <= point.getX() || isStartEqual)
                        && (this.end.getX() >= point.getX() || isEndEqual);

            }
        } else {
            return false;
        }
    }

    /**
     * checks whether this and the given other line are parallel to each other.
     * @param other other line object.
     * @return true if the are,false otherwise.
     */
    public boolean areParallelToEachOther(Line other) {
        //if both parallel to y,then of course they are parallel to each other.
        if (this.isParallelToYAxis() && other.isParallelToYAxis()) {
            return true;
        //after checked for both,now if only on of them.
        } else if (this.isParallelToYAxis() || other.isParallelToYAxis()) {
            return false;
        //now we can calc the slope value ,and return if equal.
        } else {
            return this.slopeCalc().equals(other.slopeCalc());
        }

    }
    /**
     * checks wether this line is actually a point,meaning:
     * the start and end point are the same.
     * @return true if a point and false otherwise
     */
    public boolean isPoint() {
        return this.start.equals(this.end);
    }

    /**
     * checks wehther a point is on the linear equation which
     *  this segment is part of.
     * @param point point object .
     * @return true if it is and false otherwise
     */
    public boolean isOnLinearEquation(Point point) {
        //points does not have specific linear equation.
        if (this.isPoint) {
            return false;
        } else if (isParallelToYAxis()) {
            //if parallel then,checking if the point has the same x value.
            return this.start.thresholdCompare(
                    this.start.getX(), point.getX());
        } else {
            /*placing the x,y values of the point in the linear equation of
        the line,if the equation is true-the point is on the line(not
        the segment).
        */
            double yValueOfEquation = slope * point.getX() + yIntersection;
            return point.thresholdCompare(point.getY(), yValueOfEquation);
        }
    }

    /**
     * given that the lines are parallel ,if the given line starts
     * where this line ends or ends where this line starts,
     * the function will return the intersection point
     * and null if this is not the case.
     * @param other line
     * @return point if the point exists,null otherwise
     */
    public Point continuingParallelLines(Line other) {
        boolean isStartsWhereThisEnds = this.end.equals(other.start);
        boolean isEndsWhereThisStarts = this.start.equals(other.end);
        if (isStartsWhereThisEnds) {
            return this.end;
        } else if (isEndsWhereThisStarts) {
            return this.start;
        } else {
            return null;
        }
    }

    /**
     * this function accepts an x value, and returns the y value of
     * the point with the x value one the line(not the segment).
     * @param x x value given
     * @return y value, null otherwise(if parallel to y axis).
     */
    public Double yValueGetter(Double x) {
        /*parallel to y axis meaning there is not a point with
        this x value/or there are infinity.
         */
        if (this.isParallelToYAxis()) {
            return null;
        } else {
            //acordding to y=ax+b linear equation.
            return this.slope * x + yIntersection;
        }
    }

    /**
     * given that this line and the other line given are
     * "noramal": meaning they are not points,parallel to
     * y axis or to each other,this calculates their intersection point.
     * @param other -other line object.
     * @return the intersection point
     */
    public Point normalIntersection(Line other) {
        //calculates the x value according to: a1x+b1=a2x+b2;
        Double xValue = (this.yIntersection - other.getYIntersection())
                / (other.getSlope() - this.getSlope());
        Double yValue = this.yValueGetter(xValue);
         //xValue = ((float)xValue);
         //yValue = float)yValue);
        return new Point(xValue, yValue);
    }
    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     * @param rect
     * @return Point obj
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> interArr = rect.intersectionPoints(this);
        if (interArr.isEmpty()) {
            return null;
        } else {
            return minDistance(this.origStart, interArr);

        }

    }

    /**
     * returns the point with the smallest distance from the
     * given p point.
     * @param p
     * @param arr
     * @return Point obj
     */
    public Point minDistance(Point p, List<Point> arr) {
        Point tempMin = null;
        for (Point currentPoint: arr) {
            //dealing with the first iteration-inserting the first point.
            if (tempMin == null) {
                tempMin = currentPoint;
            } else if (currentPoint == null) {
                //checking null is not relevant.
                continue;
                } else {
                //checking if indeed current point is the closet.
                if (p.distance(currentPoint) < p.distance(tempMin)) {
                    tempMin = currentPoint;
                }
            }

        }
        return tempMin;
    }

    /**
     * original start given getter.
     * @return point obj.
     */
    public Point getOrigStart() {
        return origStart;
    }

    /**
     * getter for the given end.
     * @return point
     */
    public Point getOrigEnd() {
        return origEnd;
    }
}