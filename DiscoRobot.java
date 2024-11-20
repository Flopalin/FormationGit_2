import java.util.Random;
/**
 * Subclass DiscoRobot which inherite from Robot. 
 * A robot which change its color at each step.
 * 
 * @author Biget Marie,Coulon Rosalie
 * @version 16/10/2024
 */
public class DiscoRobot extends Robot {
    private Colour color; 
    private static Random randomGenerator = new Random(); 

    /**
     * Constructor
     */
    public DiscoRobot() {
        super();
        this.color = generateRandomColor(); 
        getCanvasRobot().drawCircle(super.getX(),super.getY());
        getCanvasRobot().drawRobot(super.getX(),super.getY());
    }
    
    public void move() {
        Colour newColor = generateRandomColor(); 
        getCanvasRobot().setColourBody(newColor.toString());
        super.stayIn();
        getCanvasRobot().drawCircle(super.futurX(),super.futurY());
        super.move(); 
    }

    private Colour generateRandomColor() {
        Colour[] colors = Colour.values(); 
        return colors[randomGenerator.nextInt(colors.length)]; 
    }
}
