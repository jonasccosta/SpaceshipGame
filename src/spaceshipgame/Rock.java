package spaceshipgame;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for a single rock object
 * @authors Jonas C. Costa and Harry Werrell
 */
public class Rock {
    private Ellipse shape;

    public static final double BALL_RADIUS = 10.0;
    public static final double SPEED_RANGE = 50.0;

    private double speed;
    private double angle;
    private double centerX;
    private double centerY;
    private double dX;
    private double dY;

    public double maxX;
    public double maxY;

    public Rock(double centerX, double centerY){
        shape = new Ellipse(centerX - BALL_RADIUS, centerY-BALL_RADIUS, 2*BALL_RADIUS, 2*BALL_RADIUS);

        this.centerX = centerX;
        this.centerY = centerY;

        this.angle = 105 + 150 * Math.random();
        this.speed = Math.random()*SPEED_RANGE + 50;

        this.dX = speed * Math.cos(Math.toRadians(angle));
        this.dY = speed * -Math.sin(Math.toRadians(angle));

        this.shape.setFillColor(new Color(255, 90, 21));
    }

    /*
     * Updates the position of the rock
     */
    public void updatePosition(double dt) {
        double newX = centerX + dX*dt;
        double newY = centerY + dY*dt;
        if(newX<maxX && newY<maxY) {
            shape.setPosition(newX, newY);
            centerX = newX;
            centerY = newY;
        }
    }

    /**
     * @return true if the rock has reached the left side of the screen
     */
    public boolean reachedLeftEdge(){
        return (centerX - BALL_RADIUS <= 0);
    }

    /**
     * @return true if the rock has reached the right side of the screen
     */
    public boolean reachedRightEdge(){ 
        return (centerX + BALL_RADIUS+1 >= maxX);
    }

    /**
     * @return true if the rock has reached either the upper or lower edge of the screen
     */
    public boolean hitVerticalBoundary(){
        return (centerY - BALL_RADIUS <= 51 || centerY + BALL_RADIUS >= maxY);
    }

    /**
     * Has dY go in the opposite direction when the rock reaches a vertical boundary.
     */
    public void bounce(){
        if(hitVerticalBoundary()){
            this.dY = -dY;
        }
    }

    /**
     * Allows for any change made to the window dimensions to be localised to one place - preferably the main class.
     * With this method, it's not necessary to put the actual numbers for the window dimensions into this class.
     */
    public void setMaximum(double newMaxX, double newMaxY){
        this.maxX = newMaxX;
        this.maxY = newMaxY;
    }

    public Ellipse getShape() {
        return shape;
    }

    /**
     * Returns a list of the contact points of a rock (center top, center bottom, center left, and center right)
     */
    public java.util.List<Point> getContactPoints(){
        return  new ArrayList<>(List.of(
                new Point(this.getShape().getX() - 1, this.getShape().getY() + BALL_RADIUS),
                new Point(this.getShape().getX() + 2 * BALL_RADIUS + 1, this.getShape().getY() + BALL_RADIUS),
                new Point(this.getShape().getX() + BALL_RADIUS, this.getShape().getY() - 1),
                new Point(this.getShape().getX() + BALL_RADIUS, this.getShape().getY() + 2 * BALL_RADIUS + 1)));
    }

    public double getdY() {
        return dY;
    }

    public void setdY(double dY) {
        this.dY = dY;
    }

    public double getdX() {
        return dX;
    }

    public void setdX(double dX) {
        this.dX = dX;
    }

}
