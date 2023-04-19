package spaceshipgame;

import edu.macalester.graphics.Path;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.*;

import java.awt.*;

/**
 * Class for a spaceship object
 * @authors Jonas C. Costa and Harry Werrell
 */

public class Spaceship extends Path {
    private CanvasWindow canvas;

    public Spaceship(CanvasWindow canvas) {
        super(new Point(0, 380), new Point(70, 400), new Point(0, 420), new Point(15,400));
        this.canvas = canvas;
        this.setFillColor(new Color(192, 192, 192));
    }

    /**
     * Move the spaceship baseďon the position of the mouse
     */
    public void move(MouseMotionEvent e){
        if(this.mouseIsInBounds(e))
            this.setPosition(e.getPosition());
    }

    /**
     * Check if the mouse position is within the canvas
     */
    public boolean mouseIsInBounds(MouseMotionEvent e){
        return (e.getPosition().getX() > 0 && e.getPosition().getX() + this.getWidth() < canvas.getWidth()
                && e.getPosition().getY() > 51 && e.getPosition().getY() + this.getHeight() < canvas.getHeight()) ;
    }

}
