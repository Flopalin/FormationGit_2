import java.util.Random;
import java.util.ArrayList;

/**
 * Décrivez votre classe KillerRobot ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class KillerRobot extends Robot
{
    private Random compteTurn;
    private Colour colourBody;
    
    public KillerRobot(){
        super();
        chooseDirection();
        colourBody = Colour.ORANGE;
        getCanvasRobot().drawKnife(super.getX(), super.getY());
    }
    
    public void chooseDirection(){
        compteTurn = new Random();
    }
    
    public void turnRobot(){
        for(int i=0; i < compteTurn.nextInt(4); i++){
            super.turn();
        }
    }
    
    public void preMove(){
        getCanvasRobot().setColourBody(colourBody.toString());
        chooseDirection();
        this.turnRobot();
        super.stayIn();
    }
    
    public void move(){
        super.move();
        getCanvasRobot().drawKnife(super.getX(), super.getY());
    }
    
    public void killVictim(Robot anotherRobot){
        if (anotherRobot.isAlive() && this.isAlive()){
            for(int i=-1;i<2;i++){
                for(int j=-1;j<2;j++){
                    if(this.getX()==(anotherRobot.getX()-i) && this.getY()==(anotherRobot.getY()-j) && !(i==0 & j==0)){
                        anotherRobot.kill();
                    }
                }
            }
        }
    }
    

//public void turnRobot(){
        //while(super.getDirection() != turn.nextInt(4)){
            //super.turn();
       // }
    //}
    
    
}
