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
    private static int enemyCount = 0; 
    private Color red = new Color(255,0,0);
    private Color orange = new Color(255,200,0);
    private Color green = new Color(0,255,0); 
    private static Enemy[] allEnemies = new Enemy[100];
    private JPanel hBar = new JPanel();
    private double startHp;
    private double startWidth; 
    
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
        this.icon.setBounds(posX,posY,objWidth,objHeight);
        this.hBar.setBounds(0, 0, objWidth, 5);
        this.hBar.setBackground(green);
        icon.add(this.hBar);
        this.moveDone = true;
        this.hit = false;
        this.myHitBox = this.getIcon().getBounds(); 
        allEnemies[enemyCount] = this; 
        enemyCount++;
        this.startHp =(double)hp;
        this.startWidth = (double)objWidth; 
    }

    public  void setHealthBar(){
        double currHp = (double)getHp(); 
        if(currHp/startHp < 0.55 && currHp/startHp > 0.25 ){
            this.hBar.setBackground(orange);
        } else if(currHp/startHp < 0.25 ){
            this.hBar.setBackground(red);
        }
        double hBarWidht = (currHp/startHp)*startWidth;
        this.hBar.setSize((int)hBarWidht, hBar.getHeight());
    }

    
    
    public static Enemy[] getAllEnemies(){
        return allEnemies;
    }
    public  void setHp(int hp){
        this.hp = hp;
        CombatText.flashHit(this);
        setHealthBar();
        System.out.println("id: " + getId() + " my hp: " + getHp());
    }
    public static int getEnemyCount(){
        return enemyCount; 
    }
   
    //Hitbox that bullet can intersect with 
    public void setMyHitBox(){
        this.myHitBox = this.getIcon().getBounds();
    }
    public synchronized Rectangle getMyHitBox(){
        return this.myHitBox;
    }
    //removes graphic from GameBoard
    public void remove(){
        this.gw.getGameBoard().remove(getIcon());
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
                    if(getHp() <= 0 ){
                        
                        break; 
                    }
                    setPosX(movePatteren[i][0]);
                    setPosY(movePatteren[0][i]); 
                    
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