import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
import java.util.ArrayList;

/**
 * Draws robots on a canvas.
 * 
 * @author Patrick Girard 
 * @version 2021.08
 */
public class CanvasRobot
{
    static private Canvas canvas = Canvas.getCanvas(); 
    static private Random randomGenerator; 
    
    static private ArrayList<CanvasRobot> canvasRobotList = new ArrayList<CanvasRobot>();
    static private boolean gameOver = false;
    
    // Coordinates for redraw
    private boolean redrawable;
    private int x;
    private int y;
    
    private int n = 0;
    private int lo = 40;
    private int la = 30;
    private int plo = 10;
    private int pla = 18;
    private int tlo = 16;
    private int tla = 9;
    private int qla = 3;
    private int milieu = 230;
    static private Colour colourHead = Colour.RED;
    private Colour colourHand = Colour.GRAY;
    static private Colour colourLeg = Colour.BLACK;
    static private Colour colourOeil = Colour.BLACK;
    static private Colour colourBody = Colour.ORANGE;
    static private Colour blue = Colour.BLUE;
    static private Colour green = Colour.GREEN;
    private Integer corps;
    private Integer brasG;
    private Integer brasD;
    private Integer roueG;
    private Integer roueD;
    private Integer tete;
    private Integer oeilD;
    private Integer oeilG;
    private Integer neck;
    
    private Integer attribut;
    private Integer brush;

    /**
     * CanvasRobot Constructor - Creates a new graphical robot, which can be drawn. 
     * if the colour is not correct, the colour body is set to BLUE
     * The robot is not drawn at this time (no coordinates)
     *
     * @param colour body colourBody
     */
    public CanvasRobot ( String colourBody )
    {
        // The random generator is instanciated if necessary
        if (randomGenerator == null) randomGenerator = new Random();
        // The different objects of the robot are instanciated, to allow the process of identification 
        // of graphical objects in the canvas. 
        corps = new Integer(randomGenerator.nextInt());
        brasG = new Integer(randomGenerator.nextInt());
        brasD = new Integer(randomGenerator.nextInt());
        roueG = new Integer(randomGenerator.nextInt());
        roueD = new Integer(randomGenerator.nextInt());
        tete = new Integer(randomGenerator.nextInt());
        oeilD = new Integer(randomGenerator.nextInt());
        oeilG = new Integer(randomGenerator.nextInt());
        neck = new Integer(randomGenerator.nextInt());
        
        attribut = new Integer(randomGenerator.nextInt());
        brush = new Integer(randomGenerator.nextInt());

        this.colourBody = valueOf(colourBody);
        redrawable = false;
        
        canvasRobotList.add(this);
    }
    /**
     * Draws a robot onto the canvas, on ly if not game over
     * If try do draw out of canvas or on an existing CanvasRobot => Game Over !
     * If the robot was already drawn, it is erased (thanks to Canvas)
     * stores the coordinates to allow redraw if the color changes
     *
     * @param  x,y  robot position
     */
    public void drawRobot(int x, int y)
    {
        if (!gameOver) {
            int xp = x*50;
            int yp = y*50;
        
            canvas.draw(neck, colourBody, new Rectangle(xp+23, yp+13, 
                                         5, 10));
            canvas.draw(corps, colourBody, new Rectangle(xp+13, yp+19, 
                                         25, 25));
            canvas.draw(brasG, colourHand, new Rectangle(xp+7, yp+21, 
                                         6, 6));
            canvas.draw(brasD, colourHand, new Rectangle(xp+38, yp+21, 
                                         6, 6));
            canvas.draw(roueG, colourLeg, new Rectangle(xp+6, yp+33, 
                                         8, 15));
            canvas.draw(roueD, colourLeg, new Rectangle(xp+38, yp+33, 
                                         8, 15));
            canvas.draw(tete, colourHand, new Rectangle(xp+15, yp+4, 
                                         21, 9));
            canvas.draw(oeilD, colourOeil, new Rectangle(xp+28, yp+7, 
                                         4, 4));
            canvas.draw(oeilG, colourOeil, new Rectangle(xp+19, yp+7, 
                                         4, 4));
            
            this.x = x;
            this.y = y;
            redrawable = true;
            
            if (outOfCanvas()) gameOver();                
            if (collision()) gameOver();
            
        }
    }
    
    public void drawCircle(int x, int y){
        if (!gameOver) {
            int xp = x*50;
            int yp = y*50;
            
            canvas.draw(attribut, colourOeil, new Ellipse2D.Double(xp+10, yp-4, 
                                         30, 20));
        }
    }
    
    public void drawBrush(int x, int y, Colour colorPaint){
        if (!gameOver) {
            int xp = x*50;
            int yp = y*50;
            
            canvas.draw(brush, colourOeil, new Rectangle(xp+8, yp+18, 
                                         3, 13));
            canvas.draw(attribut, colorPaint, new Rectangle(xp+7, yp+12, 
                                         5, 8));
        }
    }
    
    public void drawKnife(int x, int y){
        if (!gameOver) {
            int xp = x*50;
            int yp = y*50;
            
            canvas.draw(oeilD, Colour.RED, new Rectangle(xp+28, yp+7, 
                                         4, 4));
            canvas.draw(oeilG, Colour.RED, new Rectangle(xp+19, yp+7, 
                                         4, 4));
            canvas.draw(brush, Colour.BLACK, new Rectangle(xp+8, yp+18, 
                                         3, 13));
            canvas.draw(attribut, Colour.RED, new Rectangle(xp+8, yp+12, 
                                         3, 8));
        }
    }
    
    public void drawCrazyEyes(int x, int y){
        if (!gameOver) {
            int xp = x*50;
            int yp = y*50;
            
            canvas.draw(oeilD, blue, new Rectangle(xp+28, yp+7, 
                                         4, 4));
            canvas.draw(oeilG, green, new Rectangle(xp+19, yp+7, 
                                         4, 4));
        }
    }
    
    public void drawDeadEyes(int x, int y){
        if (!gameOver) {
            int xp = x*50;
            int yp = y*50;
            
            canvas.draw(oeilD, colourHand, new Rectangle(xp+28, yp+7, 
                                         4, 4));
            canvas.draw(oeilG, colourHand, new Rectangle(xp+19, yp+7, 
                                         4, 4));
        }
    }
    
    /**
     * Method setColourBody
     * Sets the colour of the body and draw it again
     * 
     * @param colourBody the new colour
     */
    public void setColourBody(String colourBody){
        this.colourBody = valueOf(colourBody);
        if (redrawable) drawRobot(this.x, this.y);
    }
    
    /**
     * Method getColourBody
     *
     * @return The actual color of the body
     */
    public String getColourBody(){
        return colourBody.toString();
    }
    
    
    /**
     * Utilitary method valueOf
     * returns the value of the String param il enum Colour, with no sensitive letters
     * if the String is not a colour, returns "BLUE"
     *
     * @param colour the String that represents the colour
     * @return value in Colour enum
     */
    private static Colour valueOf(String colour){
       try {
            return Colour.valueOf(colour.toUpperCase());
        } catch (Exception e) {
        }
        return Colour.BLUE;
    }
    
    private boolean collision(){
        for (CanvasRobot c:canvasRobotList) 
            if ((c!=this)&&(x==c.x)&&(y==c.y)&&(c.redrawable)) {
                //System.out.println("COLLISION");
                //System.out.println(c.x);
                //System.out.println(c.y);
                return true;
            }
        return false;
    }
    
    private boolean outOfCanvas() {
        if (x<0) return true;
        if (y<0) return true;
        if (x>11) return true;
        if (y>11) return true;
        return false;
    }
    
    private static void gameOver(){
        gameOver = true;
        canvas.gameOver();
    }
    
    
    public void ecrisVerticalSud(int startX, int startY, Colour color) {
        String trait = "Trait" + n++;
        Canvas canvas = Canvas.getCanvas();
        
        int la = 5;   
        int lo = 55;  

        canvas.draw(trait, color, new Rectangle(startX, startY, la, lo));
    } 
    
    public void ecrisVerticalNord(int startX, int startY, Colour color) {
        String trait = "Trait" + n++;
        Canvas canvas = Canvas.getCanvas();

        int la = 5;  
        int lo = 55;  

        int newStartY = startY - lo; 
        canvas.draw(trait, color, new Rectangle(startX, newStartY, la, lo));
    }

    public void ecrisHorizontalEst(int startX, int startY, Colour color) {
        String trait = "Trait" + n++;
        Canvas canvas = Canvas.getCanvas();

        int la = 5;   
        int lo = 55;  
        canvas.draw(trait, color, new Rectangle(startX, startY, lo, la));
    }
    
    public void ecrisHorizontalOuest(int startX, int startY, Colour color) {
        String trait = "Trait" + n++;
        Canvas canvas = Canvas.getCanvas();

        int la = 5;   
        int lo = 55;  

        int newStartX = startX - lo; 
        canvas.draw(trait, color, new Rectangle(newStartX, startY, lo, la));
    }
}