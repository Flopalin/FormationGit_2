import java.awt.*;
import java.util.Random;

/**
 * Subclass CrazyRobot which inherits from Robot.
 * A robot that moves diagonally while staying within the map's limits.
 * 
 * @author Biget Marie
 * @version 16/10/2024
 */
public class CrazyRobot extends Robot {
    private Colour colourBody;
    
    /**
     * Constructor
     */
    public CrazyRobot() {
        super();
        colourBody = Colour.ORANGE;
        getCanvasRobot().drawCrazyEyes(super.getX(), super.getY());
    }

    /**
     * Method to calculate the future X position based on the diagonal movement.
     * This robot moves diagonally, so X changes in both East (0) and West (2) directions.
     */
    public int futurX() {
        int x = super.getX();
        switch (super.getDirection()) {
            case 0: 
            case 3: 
                return x + 1;
            case 1: 
            case 2: 
                return x - 1;
            default:
                return x;
        }
    }

    public int futurY() {
        int y = super.getY();
        switch (super.getDirection()) {
            case 0: 
            case 1: 
                return y + 1;
            case 2: 
            case 3: 
                return y - 1;
            default:
                return y;
        }
    }

    public void stayIn() {
        int compteur = 0;
        while(this.isOut()){
            super.turn();
            compteur++;
            if (compteur == 5){
                break;
            }
        }
    }
    
    public boolean isOut(){
        if (super.futurY() > 10 || futurY() < 0 || futurX() > 10 || futurX() < 0) {
            return true;
        }
        return false;
    }

    public void move() {
        if(super.isAlive()){
        getCanvasRobot().setColourBody(colourBody.toString());
        this.stayIn();
        super.move();  
        super.turn();  
        super.move();  
        super.turn();  
        super.turn();  
        super.turn();  
        getCanvasRobot().drawCrazyEyes(super.getX(), super.getY());
    }
    }
}
