import java.util.Random;

/**
 * Subclass FearfulRobot which inherite from Robot. 
 * A robot which move in the opposite direction if a robot is close to him (2 or closer).
 * 
 * @author Lotte Lilian
 * @version 16/10/2024
 */ 

public class FearfulRobot extends Robot {
    private Colour color; 
    private int speed;
    private static Random randomGenerator = new Random(); 
    private Colour colourBody;

    /**
     * Constructor 
     */
    public FearfulRobot() {
        super(); 
        this.color = generateRandomColor(); 
        this.speed = randomGenerator.nextInt(3) + 1; //Random speed between 1 and 3
        colourBody = Colour.ORANGE;
    }
    
    private Colour generateRandomColor() {
        Colour[] colors = Colour.values(); 
        return colors[randomGenerator.nextInt(colors.length)]; 
    }
    
    /**
     * Move the robot in the opposite direction of the detected robot. 
     */
    public void moveAwayFrom(Robot otherRobot) {
        int deltaX = this.getX() - otherRobot.getX();
        int deltaY = this.getY() - otherRobot.getY();

        // Déplacer dans la direction opposée en fonction de la distance
        if (Math.abs(deltaX) >= Math.abs(deltaY)) {
            // Mouvement horizontal dans la direction opposée
            if (deltaX > 0 && this.getX() < 11) {
                super.move(); // Déplacement vers la droite
            } else if (deltaX < 0 && this.getX() > 0) {
             // Déplacement vers la gauche
                super.turn();
                super.move();
            }
        } else {
            // Mouvement vertical dans la direction opposée
            if (deltaY > 0 && this.getY() < 11) {
                super.turn(); // Déplacement vers le bas
                super.move();
            } else if (deltaY < 0 && this.getY() > 0) {
             // Déplacement vers le haut
                super.turn();
                super.move();
            }
        }

        // Redessine le robot à la nouvelle position
        getCanvasRobot().setColourBody(colourBody.toString());
        getCanvasRobot().drawRobot(getX(), getY());
    }
    
    public void move(){
        getCanvasRobot().setColourBody(colourBody.toString());
        super.stayIn();
        super.move();
    }
    
}