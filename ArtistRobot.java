import java.util.Random;

/**
 * Subclass ArtistRobot which inherits from Robot. 
 * A robot which draws a trace whose color changes at each move.
 * 
 * @author Coulon Rosalie
 * @version 16/10/2024
 */
public class ArtistRobot extends Robot {
    private Colour color; 
    private Colour colourBody;
    private static Random randomGenerator = new Random(); 

    /**
     * Constructor
     */
    public ArtistRobot() {
        super();
        this.color = generateRandomColor(); 
        colourBody = Colour.ORANGE;
        getCanvasRobot().drawBrush(getX(), getY(), colourBody);
    }

    /**
     * Method move makes the robot advance while drawing a random-colored line.
     */
    public void move() {
        getCanvasRobot().setColourBody(colourBody.toString());
        this.color = generateRandomColor(); 
        
        int startX = getX() * 50 + 25;  
        int startY = getY() * 50 + 25; 
        
        super.stayIn();
        super.move();
        
        switch (getDirection()) {
            case 0:  // East
                getCanvasRobot().ecrisHorizontalEst(startX, startY, color);  
                break;
            case 1:  // South
                getCanvasRobot().ecrisVerticalSud(startX, startY, color);                 
                break;
            case 2:  // West
                getCanvasRobot().ecrisHorizontalOuest(startX, startY, color);  
                break;
            case 3:  // North
                getCanvasRobot().ecrisVerticalNord(startX, startY, color);  
                break;
        }
        super.drawRobot();
        getCanvasRobot().drawBrush(getX(), getY(), color);
    }

    /**
     * Generate a random color for the trace
     */
    private Colour generateRandomColor() {
        Colour[] colors = Colour.values(); 
        return colors[randomGenerator.nextInt(colors.length)]; 
    }
}
