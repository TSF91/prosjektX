/**
 * Bullet class extends GameObject
 * 
 */

import javax.swing.*;
import java.awt.Rectangle;
import java.awt.Image;
public class Bullet extends GameObject{
    private  JLabel icon = new JLabel(new ImageIcon("C:\\DEV\\Gui\\prosjektX\\Graphics\\Bullet.png"));
    private int[][] myPath;
    private Runnable startMove;  
    private Boolean moveDone;
    private GameWindow gw; 
    private int dmg; 
    private Rectangle myHitBox;
    private boolean bulletHit = false; 
    private int critChance = 38; 
    private boolean crit = false;  
    public static Bullet[] bullets = new Bullet[100];
    public static int bulletCount = 0; 
    
    public Bullet(int posX,int posY,int speed,int objWidth,int objHeight,int hp,GameWindow gw, int dmg){
        super(posX, posY, speed, objWidth, objHeight,hp,gw);
        icon.setBounds(posX,posY,objWidth,objHeight); 
        this.moveDone = true;
        int criticalHit = MoveObject.randomPos(0, 100);
        if(criticalHit < critChance){ 
            this.crit = true; 
        }
        this.dmg = crit? dmg+(dmg*3)/4:dmg; // gives approx 75% more damage if crit
        this.myHitBox = this.getIcon().getBounds();
    }
    public boolean getCrit(){
        return this.crit;
    }
    /**
     * if the bullet hits an enemy bullethit = true -> bullet done, clear hitbox to 0,0 
     * remove graphics from GameBoard
     * @param bulletHit
     */
    public void setBulletHit(boolean bulletHit){
        this.bulletHit = bulletHit;
        if(bulletHit){
            //this.myHitBox = new Rectangle(); 
            this.removeFromGameBoard();
        }
    } 
    public boolean getBulletHit(){
        return this.bulletHit; 
    }
    public void setMyHitBox(){
        this.myHitBox = this.getIcon().getBounds();
    }
    public void deleteMyHitBox(){
        this.myHitBox = new Rectangle();   
    }
    public synchronized Rectangle getMyHitBox(){
        return this.myHitBox;
    }
    public JLabel getIcon(){
        return this.icon; 
    }
    
    // Remove graphics from gameBoard
    public void remove(){
       gw.getGameBoard().remove(getIcon());
    }
    public void setMoveDone(Boolean moveDone){
        this.moveDone = moveDone;
        if(moveDone){
            remove();
        } 
    }
    public Boolean getMoveDone(){
        return this.moveDone; 
    }

    /** Generate bulletpath based on fire position/spawnpoint for bullet
     * @param myPath path for bullet to move in
     */
    public void setMyPath() {
        this.myPath = MoveObject.bulletPath(this.getPosX(), this.getPosY());
    }
    public int[][] getMyPath(){
        return this.myPath; 
    }
    public void setDmg(int dmg){
        this.dmg = dmg; 
    }
    public int getDmg(){
        return this.dmg; 
    }
    /**Starts new thread that moves the bullet in given path at given speed 
     * @param x - not in use, from abstarct method in GameObjects
     * @param y - not in use, from abstract method in GameObjects
     */
    public void move(int x, int y){
        Enemy[] allEnemies = Enemy.getAllEnemies();
        Bullet bu = this; 
        setMoveDone(false);
        setMyPath();
        System.out.println("Bulletpath created");
        this.startMove = new Runnable(){
            @Override
            public void run() {
                Thread.currentThread().setName("Bullet-Thread");
                int[][] movePatteren = getMyPath();
                for (int i = 1; i < movePatteren.length; i++) {
                    if(getBulletHit()){
                        break;
                    }
                    setPosX(movePatteren[i][0]);
                    setPosY(movePatteren[0][i]);
                    icon.setLocation(movePatteren[i][0], movePatteren[0][i]);
                    setMyHitBox();
                    for (int j = 0; j < Enemy.getEnemyCount(); j++) {
                       
                        if(allEnemies[j].getHp() > 0 && getMyHitBox().intersects(allEnemies[j].getMyHitBox())){
                            allEnemies[j].setHp(allEnemies[j].getHp()-getDmg());
                            if(allEnemies[j].getHp() < 1){
                                break;
                            }
                            CombatText.dmg(allEnemies[j], bu);
                            System.out.println("my hp is: " + allEnemies[j].getHp());
                            bu.setBulletHit(true);
                        } 
                       
                    }
                    try {
                        Thread.sleep(getSpeed());
                    } catch (Exception e) {
                        System.out.println("BUGGA");
                    }
                }
                if(!getBulletHit()){
                    setBulletHit(true); 
                }
            }
        };
        new Thread(this.startMove).start();      
    }

    public void setHp(int hp){
        
    }
    
}