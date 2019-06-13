/**
 * Enemy extends GameObject
 * @author Thomas S. Friestad
 */
import javax.swing.*;
import java.awt.*;


public class Enemy extends GameObject{
    private  JLabel icon = new JLabel(new ImageIcon("C:\\DEV\\Gui\\prosjektX\\Graphics\\Enemy.png"));
    private int[][] myPath;
    private Runnable startMove;  
    private boolean hit = false; 
    private Boolean moveDone;
    private GameWindow gw; 
    private Rectangle myHitBox;
    private Color red = new Color(255,0,0);
    private static int enemyCount = 1; 
    
    private static Enemy[] allEnemies = new Enemy[100];
    
    /**
     * Constructor
     * @param posX 
     * @param posY
     * @param speed
     * @param objWidth
     * @param objHeight
     * @param hp
     * @param gw -GameWindow
     */ 
    public Enemy(int posX,int posY,int speed,int objWidth,int objHeight,int hp,GameWindow gw){
        super(posX, posY, speed, objWidth, objHeight,hp,gw);
        icon.setBounds(posX,posY,objWidth,objHeight); 
        this.moveDone = true;
        this.hit = false;
        this.myHitBox = this.getIcon().getBounds(); 
        allEnemies[enemyCount -1] = this; 
        enemyCount++;
    }

    
    public static Enemy[] getAllEnemies(){
        return allEnemies;
    }
    public void setHp(int hp){
        this.hp = hp;
        CombatText.flashHit(this);
        if(hp <= 0){
            this.remove();
        }
    }
   
    //Hitbox that bullet can intersect with 
    public void setMyHitBox(){
        this.myHitBox = this.getIcon().getBounds();
    }
    public Rectangle getMyHitBox(){
        return this.myHitBox;
    }
    //removes graphic from GameBoard
    public void remove(){
        this.gw.getGameBoard().remove(this.icon);
    }

    //check if enemy is hit
    public Boolean getHit(){
        return this.hit;
    }
    // Check if enemy is moveing 
    public Boolean getMoveDone(){
        return this.moveDone;
    }
    //Graphics 
    public JLabel getIcon(){
        return this.icon; 
    }
    
    
    //movement path
    public int[][] getMyPath(){
        return this.myPath;
    }

    
    public void setHit(Boolean hit){
        this.hit = hit;
    }
    public void setMoveDone(Boolean moveDone){
        this.moveDone = moveDone; 
        
    }
    /**
     * Generates movement path 
     * @param newX end pos x
     * @param newY end pos y
     */
    public void setMyPath(int newX, int newY){
        this.myPath = MoveObject.pathFinder(this.getPosX(), this.getPosY(), newX, newY);
    }

    /**
     * starts new thread that moves the object on the gameboard on given path. 
     */
    public void move(int newX, int newY){
        setMoveDone(false);
        setMyPath(newX, newY);
        this.startMove = new Runnable(){
            @Override
            public void run() {
                int[][] movePatteren = getMyPath();
                for (int i = 1; i < movePatteren.length; i++) {
                    setPosX(movePatteren[i][0]);
                    setPosY(movePatteren[0][i]); 
                    icon.setLocation(movePatteren[i][0], movePatteren[0][i]);
                    setMyHitBox();
                    try {
                        Thread.sleep(getSpeed());
                    } catch (Exception e) {
                        System.out.println("BUGGA");
                    }
                }
                movePatteren = new int[0][0]; 
                setMoveDone(true);  
            }
        };
        new Thread(this.startMove).start();      
    }   
}