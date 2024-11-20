import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
import java.util.ArrayList;

/**
 * The world class allows to create and display robot on the map.
 * The user can only add new robots and evolve the world, e g all robots move. 
 *
 * @author Biget Marie, Hecini Abdeldjalil, Lotte Lilian, Coulon Rosalie
 * @version 16/10/2024
 */
public class World
{
    static private ArrayList<Robot> RobotList; 
    public World()
    {
        RobotList = new ArrayList<Robot>();           
    }
    
    static public ArrayList getRobotList(){
        return RobotList;
    }
    
    public void addCrazyRobot()
    {
        if(!isSpawnPointRobot()){
            RobotList.add(new CrazyRobot());
        }
    }
    
    public void addArtistRobot()
    {
        if(!isSpawnPointRobot()){
            RobotList.add(new ArtistRobot());
        }
    }
    
    public void addDiscoRobot()
    {
        if(!isSpawnPointRobot()){
            RobotList.add(new DiscoRobot());
        }
    }

    public void addFearfulRobot()
    {
        if(!isSpawnPointRobot()){
            RobotList.add(new FearfulRobot());
        }
    }
    
    public void addKillerRobot()
    {
        if(!isSpawnPointRobot()){
            RobotList.add(new KillerRobot());
        }
    }
    
    private boolean isSpawnPointRobot(){
        for(Robot r:RobotList){ 
            if(r.getX() == 0 && r.getY() == 0){
                return true;
            }
        }
        return false;
    }
    
    private boolean isCollision(Robot r, Robot r2){
        if(r instanceof KillerRobot){ 
        //System.out.println("cherche collision : "+ r +" sur "+ r2);
        //System.out.println("cherche collision : "+ r.futurX() +" sur "+ r2.getX());
        //System.out.println("cherche collision : "+ r.futurY() +" sur "+ r2.getY());
    }
        if (r2.getX() == r.futurX() && r2.getY() == r.futurY()){
            //System.out.println("Attention collision : "+ r +" sur "+ r2);
            return true;
        }else{
            //System.out.println(r2.futurX());
            //System.out.println(r.futurX());
            //System.out.println(r2.futurY());
            //System.out.println(r.futurY());
            return false;
        }
    }
    
    public void evolve()
    {
        for(Robot r:RobotList){ 
            if(r.isAlive()){
                
                if(r instanceof KillerRobot){
                    ((KillerRobot)r).preMove();
                }
                
                for (Robot r2:RobotList){
                    if(!(r.equals(r2))){

                        if (r instanceof FearfulRobot) {
                            int distanceX = Math.abs(r.getX() - r2.getX());
                            int distanceY = Math.abs(r.getY() - r2.getY());
                            
                            if (distanceX <= 2 && distanceY <= 2) {
                                ((FearfulRobot)r).moveAwayFrom(r2);
                                break;
                            }
                        }
                        
                        if (r instanceof KillerRobot) {
                            if(r2.isAlive()){
                                ((KillerRobot)r).killVictim(r2);
                            }

                        }
                        
                        
                        
                        int tour = 0;
                        while(isCollision(r,r2)){
                            r.turn();
                            System.out.println("tour : "+tour);
                            if (tour == 5){
                                break;
                            }
                            tour++;
                        }
                    }
                }
                r.stayIn();
                r.move();
                
            }
            //Canvas.getCanvas().wait(300);
        }
    }
    
    public void test(){
        addArtistRobot();
        evolve();
        addDiscoRobot();
        evolve();
        addCrazyRobot();
        evolve();
        addKillerRobot();
        evolve();
        //addFearfulRobot();
        evolve();
        addDiscoRobot();
        evolve();
        addKillerRobot();
        evolve();
        evolve();
        addCrazyRobot();
        evolve();
        addArtistRobot();
        evolve();
        addKillerRobot();
        
        for(int i=0; i<50; i++){
            evolve();
            
            //Canvas.getCanvas().wait(100);
        }
    }
}
