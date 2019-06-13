/**
 * Super class gameobjects, subclasses: player, enemy, bullet . 
 * @author Thomas S. Friestad
 */
import java.awt.Rectangle;
import javax.swing.*; 

public abstract class GameObject{
    private static int objectCount = 0; 
    private int id; 
    private int posX;
    private int posY; 
    private int speed; 
    private int objWidth; 
    private int objHeight;
    public int hp; 
    private JLabel icon; 
    private GameWindow gw; 
    
    
    /**
     * Constructor
     * @param posX
     * @param posY
     * @param speed
     * @param objWidth
     * @param objHeight
     * @param hp
     * @param gw - GameWindow
     */
    public GameObject(int posX,int posY,int speed,int objWidth,int objHeight,int hp,GameWindow gw){
        this.id = ++objectCount; 
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.objWidth = objWidth;
        this.objHeight = objHeight;  
        this.hp = hp;
        this.gw = gw; 
       
    }

    

   
    /**
     * removes grapichs of object from GameBoard.
     */
    public void removeFromGameBoard(){
        this.getGameBoard().remove(this.getIcon()); 
    }

    
    
    //Getters
    public JFrame getGameWindow(){
        return this.gw.getGameWindow(); 
    }
    public JPanel getGameBoard(){
        return this.gw.getGameBoard();
    }
    public int getId(){
        return this.id; 
    }

    public static int getObjectCount(){
        return objectCount; 
    }

    public int getPosX(){
        return this.posX; 
    }

    public int getPosY(){
        return this.posY; 
    }

    public int getSpeed(){
        return this.speed; 
    }

    public int getObjWidth(){
        return this.objWidth; 
    }
    
    public int getObjHeight(){
        return this.objHeight; 
    }

    public int getHp(){
        return this.hp;
    }

    //Setters
    public void setPosX(int posX){
        this.posX = posX;
    }

    public void setPosY(int posY){
        this.posY = posY; 
    }
    
    public void setSpeed(int speed){
        this.speed = speed; 
    }

    public void setObjectWidth(int objWidth){
        this.objWidth = objWidth; 
    }
    
    public void setObjectHeight(int objHeight){
        this.objHeight = objHeight; 
    }

  

    public GameWindow getMyGameWindow(){
        return this.gw;
    }
    public abstract void setHp(int hp);
    public abstract void move(int x, int y);

    public abstract JLabel getIcon();

    public abstract Boolean getMoveDone();

    public abstract Rectangle getMyHitBox();

    public abstract int[][] getMyPath(); 

    
    //not in use
    public static int setDirection(String dir){
        dir.toLowerCase();
        int dirInt = 0; 
        if(dir == "down" || dir == "right"){
            dirInt = 1; 
        } else if (dir == "up" || dir == "left"){
            dirInt = -1; 
        } else {
            dirInt = 0;
        }
        return dirInt; 
    }

}

enum ObjectType {
    ENEMY, 
    PLAYER, 
    BULLET
}