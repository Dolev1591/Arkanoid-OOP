/**
 * Dolev Levy
 * 315150110
 */

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * this class is responsible for a parallel to the axis rectangle.
 */
public class Rectangle {
    private Point topLeft;
    private double width;
    private double height;
    private Color color;
    public static final Color DEFAULTCOLOR = Color.gray;
    public static final Color EDGESCOLOR = Color.BLACK;
    /**
     * constructor-only rectangles that are parallel to the axis.
     * @param upperLeft
     * @param width
     * @param height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.topLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = DEFAULTCOLOR;
    }
    /**
     * constructor-only rectangles that are parallel to the axis.
     * @param upperLeft
     * @param width
     * @param height
     * @param color
     */
    public Rectangle(Point upperLeft, int width, int height, Color color) {
        this.topLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    /**
     * constructor-only rectangles that are parallel to the axis.
     * @param upperLeft
     * @param width
     * @param height
     * @param color
     */
    public Rectangle(Point upperLeft, double width,
                     double height, Color color) {
        this.topLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    /**
     * constructor accpeting x,y of upper left and width,height.
     * @param upperLeftX
     * @param upperLeftY
     * @param width
     * @param height
     */
    public Rectangle(double upperLeftX, double upperLeftY,
                     double width, double height) {
      this(new Point(upperLeftX, upperLeftY), width, height);
    }

    /**
     * getter for the color.
     * @return color object.
     */
    public Color getColor() {
        return color;
    }
    /**
     * setter for the top left .
     * @param topLeft
     */
    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    /**
     * returns the width of the rectangle.
     * @return double.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * returns the height of the rectangle.
     * @return double.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * @return Point obj.
     */
    public Point getUpperLeft() {
        return this.topLeft;
    }

    /**
     * color setter.
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line
     * @return array list with point obj.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Line> linesArr = rectangleLines();
        ArrayList<Point> interPoints = new ArrayList<>();
        for (int i = 0; i < linesArr.size(); i++) {
            Point possibInter = linesArr.get(i).intersectionWith(line);
            //if the point is not already in the array or null.
            if (isWorthAdding(interPoints, possibInter)) {
                interPoints.add(possibInter);
            }
        }
        return interPoints;
    }

    /**
     * returns a list with 4 lines constructing the rectangle.
     * 0-top,1-left,2-bottom,3-right.
     * @return Array list.
     */
    public ArrayList<Line> rectangleLines() {
        ArrayList<Line> lines = new ArrayList<>();
        double y = topLeft.getY();
        double x = topLeft.getX();
        //top border
        lines.add(new Line(topLeft, new Point(x + width, y)));
        //left border
        lines.add(new Line(topLeft, new Point(x, y + height)));
        //bottom border
        lines.add(new Line(new Point(x, y + height),
                new Point(x + width, y + height)));
        //right border
        lines.add(new Line(new Point(x + width, y + height),
                new Point(x + width, y)));
        return lines;

    }
    /**
     * this function checks whether the point is worth adding,
     * meaning-not null or already exists inside the array.
     * @param arr
     * @param p
     * @return boolean.
     */
    public boolean isWorthAdding(ArrayList<Point> arr, Point p) {
        //checks whether null
        if (p == null) {
            return false;
        //checks for if there is a point equal to the given point.
        } else {
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).equals(p)) {
                    return false;
                }

            }
        //if non of the above conditions were met,the point is worth adding.
        }
        return true;
    }
    /**
     * this function accepts a draw surface object and draws the rectangle
     * on him.
     * @param d
     */
    public void drawRectangle(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.topLeft.getX(),
                (int) this.topLeft.getY(), (int) width, (int) height);
        d.setColor(EDGESCOLOR);
        d.drawRectangle((int) this.topLeft.getX(),
                (int) this.topLeft.getY(), (int) width, (int) height);

    }
}
