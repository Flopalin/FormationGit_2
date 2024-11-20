/**
 * The robot class represents a robot defined by its position and its dierction which can move step by step.
 *
 * @author Biget Marie, Hecini Abdeldjalil, Lotte Lilian, Coulon Rosalie
 * @version 16/10/2024
 */
public class Robot
{
    private int x;
    private int y;
    private int direction;
    private CanvasRobot canvas;
    private boolean alive;

    /**
     * Robot class constructor
     */
    public Robot()
    {
        x = 0;
        y = 0;
        direction = 0;
        canvas = new CanvasRobot("ORANGE");
        drawRobot();
        alive = true;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getDirection(){
        return direction;
    }

    public CanvasRobot getCanvasRobot() {
        return canvas;
    }
    
    public void stayIn(){
        int compteur = 0;
        while(isOut()){
            turn();
            compteur++;
            if (compteur == 6){
                break;            
            }
        }
    }
    
    public boolean isOut(){
        switch(direction){
            case 0 : if(x+1 >= 12){
                return true;
            }break;
            case 1 : if(y+1 >= 12){
                return true;
            }break;
            case 2 : if(x-1 < 0){
                return true;
            }break;
            case 3 : if(y-1 < 0){
                return true;
            }break;
        }
        return false;
    }

    public void move(){
        if(alive){
        switch(direction){
            case 0 : x++; break;
            case 1 : y++; break;
            case 2 : x--; break;
            case 3 : y--; break;
        }
        drawRobot();
        }
    }
    
    public void turn(){
        direction = direction + 1;
        if (direction > 3){
            direction = 0;
        }
    }
    
    public int futurX(){
        int newX = x;
        switch(direction){
            case 0 : 
                if(!(x+1 >= 12)){
                    newX++;
                }
                break;
            case 1 : 
                if(y+1 >= 12){
                    newX--;
                } 
                break;
            case 2 : 
                if(!(x-1 < 0)){
                    newX--;
                }
                break;
            case 3 : 
                if(y-1 < 0){
                    newX++;
                } 
                break;
        }
        return newX;
    }
    
    public int futurY(){
        int newY = y;
        switch(direction){
            case 0 : 
                if(x+1 >= 12){
                   newY++; 
                } 
                break;
            case 1 : 
                if(!(y+1 >= 12)){
                   newY++; 
                } 
                break;
            case 2 : 
                if(x-1 < 0){
                   newY--; 
                } 
                break;
            case 3 : 
                if(!(y-1 < 0)){
                    newY--;
                } 
                break;
        }
        return newY;
    }

    public void drawRobot()
    {
        canvas.drawRobot(x,y);
    }
    
    public void kill(){
        alive=false;
        canvas.drawDeadEyes(x,y);
    }
    
    public boolean isAlive(){
        return alive;
    }
}
