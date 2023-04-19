package spaceshipgame;

import edu.macalester.graphics.Point;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;

import java.awt.*;

/**
 * Main Spaceship Game Class
 * @authors Jonas C. Costa and Harry Werrell
 */
public class Game {
    private static final int CANVAS_WIDTH = 1500;
    private static final int CANVAS_HEIGHT = 800;
    private Spaceship spaceship;
    private CanvasWindow canvas;
    private RockManager rockManager;
    private int lifeCounter = 3;
    private GraphicsText lives;
    private GraphicsText score;
    private Line dividingLine;

    
    public Game(){
        canvas = new CanvasWindow("Game", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(new Color(0,0,0));

        lives = new GraphicsText("Lives: " + lifeCounter);
        lives.setFillColor(new Color(213, 213, 213, 243));
        lives.setCenter(30,37);
        lives.setFont(FontStyle.BOLD, 50);
        canvas.add(lives);

        score = new GraphicsText("Score: 0");
        score.setFillColor(new Color(213, 213, 213, 243));
        score.setCenter(1100,37);
        score.setFont(FontStyle.BOLD, 50);
        canvas.add(score);

        dividingLine = new Line(0, 50, CANVAS_WIDTH, 50);
        dividingLine.setStrokeColor(new Color(213, 213, 213, 243));
        canvas.add(dividingLine);

        spaceship = new Spaceship(canvas);
        canvas.add(spaceship);

        rockManager = new RockManager(canvas);
        canvas.add(rockManager);


        canvas.draw();
        canvas.pause(3000);
        canvas.onMouseMove(e -> spaceship.move(e));
        canvas.animate(() -> {
            rockManager.move(canvas);
            rockManager.addMoreRocks(canvas);
            remove(spaceship, rockManager);
            increaseScore(rockManager);
        });

    }


    public static void main(String[] args) {
        new Game();
    }

    /**
     * Handles collisions between rock and spaceship, losing a life and reflecting the 
     * direction of the rock if there is a collision between a rock and the spaceship 
     */
    public void remove(Spaceship spaceship, RockManager rockManager){
        for(Rock rock: rockManager.getRockList()){
            for(Point point: rock.getContactPoints()){
                if(spaceship.getElementAt(point.getX(), point.getY()) != null) {
                    loseLife(canvas);
                    rock.setdY(-1*rock.getdY());
                    rock.setdX(-1*rock.getdX());
                 }
            }
        }
    }

    /**
     * Updates the lives text if the the number of lives is greater than 0
     * Otherwise, game over
     */
    public void loseLife(CanvasWindow canvas) {
        lifeCounter --;

        if (lifeCounter == 0) {
            canvas.pause(100);
            this.gameOver(canvas);
        }

        else{
            lives.setText("Lives: " + lifeCounter);
        }
    }

    /**
     * Updates the score text
     */
    public void increaseScore(RockManager rockManager){
        score.setText("Score: " + rockManager.getScore());
    }

    /**
     * Creates a game over screen, showing the user's score
     */
    public void gameOver(CanvasWindow canvas) {
        GraphicsText gameOver = new GraphicsText("Game Over!");
        gameOver.setFont(FontStyle.BOLD, 150);
        gameOver.setFillColor(new Color(200, 0, 0));
        gameOver.setCenter(CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
        rockManager.setGameOver(true);

        score.setCenter(CANVAS_WIDTH/2, CANVAS_HEIGHT/2 + 100);
        score.setFillColor(new Color(0, 200, 0));
        canvas.removeAll();
        canvas.add(gameOver);
        canvas.add(score);
        canvas.draw();

    }

}
