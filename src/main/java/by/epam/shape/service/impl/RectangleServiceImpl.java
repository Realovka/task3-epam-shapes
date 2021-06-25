package by.epam.shape.service.impl;

import by.epam.shape.entity.Point;
import by.epam.shape.entity.Rectangle;
import by.epam.shape.service.RectangleService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RectangleServiceImpl implements RectangleService {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean isRectangle(Rectangle rectangle) {
        if (!(rectangle.getLeftTopPoint().getX() - rectangle.getRightTopPoint().getX() /
                rectangle.getRightBottomPoint().getX() - rectangle.getRightTopPoint().getX() ==
                rectangle.getLeftTopPoint().getY() - rectangle.getRightTopPoint().getY() /
                        rectangle.getRightBottomPoint().getY() - rectangle.getRightTopPoint().getY())) {
            logger.log(Level.INFO, "It is rectangle " + rectangle);
            return true;
        } else {
            logger.log(Level.INFO, "It isn't rectangle " + rectangle);
            return false;
        }
    }

    @Override
    public double findPerimeter(Rectangle rectangle) {
        double height = findHeight(rectangle);
        double width = findWidth(rectangle);
        double perimeter = (height + width) * 2;
        logger.log(Level.INFO, "Perimeter of rectangle is " + perimeter);
        return perimeter;
    }

    @Override
    public double findArea(Rectangle rectangle) {
        double area = findHeight(rectangle) * findWidth(rectangle);
        logger.log(Level.INFO, "Area of rectangle is " + area);
        return area;
    }

    //if all angles are 90 degrees
    @Override
    public boolean isConvex(Rectangle rectangle) {
        if(areAllRightAngles(rectangle)) {
            logger.log(Level.INFO, "Rectangle is convex");
            return true;
        } else {
            logger.log(Level.INFO, "Rectangle isn't convex");
        }
        return false;
    }

    @Override
    public boolean isRhombus(Rectangle rectangle) {
        if(findHeight(rectangle )== findWidth(rectangle)) {
            logger.log(Level.INFO, "This shape is rhombus " + rectangle);
            return true;
        } else {
            logger.log(Level.INFO, "This shape isn't rhombus " + rectangle);
            return false;
        }
    }

    @Override
    public boolean isSquare(Rectangle rectangle) {
        if(findHeight(rectangle )== findWidth(rectangle) && areAllRightAngles(rectangle)) {
            logger.log(Level.INFO, "This shape is square " + rectangle);
            return true;
        } else {
            logger.log(Level.INFO, "This shape isn't square " + rectangle);
            return false;
        }
    }

    private double findHeight(Rectangle rectangle) {
        double height = rectangle.getLeftTopPoint().getY() - rectangle.getRightBottomPoint().getY();
        logger.log(Level.INFO, "Height of rectangle is " + height);
        return height;
    }

    private double findWidth(Rectangle rectangle) {
        double width = rectangle.getRightBottomPoint().getX() - rectangle.getLeftTopPoint().getX();
        logger.log(Level.INFO, "Width of rectangle is " + width);
        return width;
    }

    private double findTheFirstCoordinateVector(Point first, Point second) {
        return first.getX() + second.getX();
    }

    private double findTheSecondCoordinateVector(Point first, Point second) {
        return first.getY() + second.getY();
    }

    private boolean isRightAngleBetweenVectors(Point first, Point second, Point third) {
        double firstCoordinateFirstVector = findTheFirstCoordinateVector(first, second);
        double secondCoordinateFirstVector = findTheSecondCoordinateVector(first, second);
        double firstCoordinateSecondVector = findTheFirstCoordinateVector(first, third);
        double secondCoordinateSecondVector = findTheSecondCoordinateVector(first, third);
        if (firstCoordinateFirstVector * firstCoordinateSecondVector + secondCoordinateFirstVector * secondCoordinateSecondVector == 0) {
            logger.log(Level.INFO, "Angle between vector {}  is right", first, second, third);
            return true;
        } else {
            logger.log(Level.INFO, "Angle between vector {}  isn't right", first, second, third);
            return false;
        }
    }

    private boolean areAllRightAngles(Rectangle rectangle) {
        if(isRightAngleBetweenVectors(rectangle.getLeftTopPoint(), rectangle.getRightTopPoint(), rectangle.getLeftBottomPoint()) &&
                isRightAngleBetweenVectors(rectangle.getRightTopPoint(), rectangle.getRightBottomPoint(), rectangle.getLeftTopPoint()) &&
                isRightAngleBetweenVectors(rectangle.getLeftBottomPoint(), rectangle.getLeftTopPoint(), rectangle.getRightBottomPoint()) &&
                isRightAngleBetweenVectors(rectangle.getRightBottomPoint(), rectangle.getRightTopPoint(), rectangle.getLeftBottomPoint())) {
            logger.log(Level.INFO, "All angles of shape {} are right", rectangle);
            return true;
        } else {
            logger.log(Level.INFO, "Not all angles of shape {} are right", rectangle);
        }
        return false;
    }

}
