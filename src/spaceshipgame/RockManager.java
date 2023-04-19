package spaceshipgame;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for the collection of rocks
 * @authors Jonas C. Costa and Harry Werrell
 */
public class RockManager extends GraphicsGroup {

    public int listSize = 20;

    private List<Rock> rockList = new ArrayList<>();
    private int score = 0;
    private boolean gameover = false;

    public RockManager(CanvasWindow canvas) {
        for (int i = 0; i < listSize; i++) {
            Rock rock = new Rock(canvas.getWidth() - 10, 60 + Math.random() * (canvas.getHeight() - 70));
            rock.setMaximum(canvas.getWidth(), canvas.getHeight());

            canvas.add(rock.getShape());

            rockList.add(rock);
        }
    }

    /**
     * @return number of rocks currently in the screen
     */
    public int currentRockNumber() {
        return rockList.size();
    }

    public void move(CanvasWindow canvas) {
        List<Rock> removeList = new ArrayList<>();
        for (Rock rock : rockList) {
            rock.updatePosition(0.1);
            rock.bounce();
            removeList.add(rock);
            if(rock.reachedRightEdge()){
                rock.setdX(-1*rock.getdX());
            }
        }
        for (Rock rock : removeList) {
            removeRock(rock, canvas);
        }
    }

    /**
     * Removes a rock from the screen if it reached the left side of the canvas
     */
    public void removeRock(Rock rock, CanvasWindow canvas) {
        if (rock.reachedLeftEdge() && !gameover) {
            canvas.remove(rock.getShape());
            if (rockList.contains(rock)) {
                rockList.remove(rock);
            }
        }
    }

    /**
     * Continuously add more rocks to the canvas if there are still lives left
     */
    public void addMoreRocks(CanvasWindow canvas) {
        if (currentRockNumber() < listSize && !gameover) {
            for (int i = 0; i < listSize - currentRockNumber(); i++) {
                Rock rock = new Rock(canvas.getWidth() - 10, 60 + Math.random() * (canvas.getHeight() - 70));
                rock.setMaximum(canvas.getWidth(), canvas.getHeight());
                canvas.add(rock.getShape());
                rockList.add(rock);
                score++;
            }
        }
    }

    public List<Rock> getRockList() {
        return rockList;
    }

    public void setGameOver(boolean gameover) {
        this.gameover = gameover;
    }

    public String getScore() {
        return "" + score;
    }
}
