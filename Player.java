/**
 * Enemy extends GameObject
 * @author Thomas S. Friestad
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
public class Player extends GameObject {
    /**
     * Constructor
     * @param posX
     * @param posY
     * @param speed
     * @param objWidth
     * @param objHeight
     * @param hp
     * @param gw
     */
    private Point  myLocation = new Point(); 
    private  JLabel icon = new JLabel(new ImageIcon("C:\\DEV\\Gui\\prosjektX\\Graphics\\player.png"));   
    public Player(int posX,int posY,int speed,int objWidth,int objHeight,int hp,GameWindow gw){
        super(posX, posY, speed, objWidth, objHeight,hp,gw);
        this.icon.setBounds(posX,posY, objWidth, objHeight);
        this.myLocation = this.getIcon().getLocation();
        addMouseMotionListenerToPlayer(gw);
    }
   
    public Point getMyLocation(){
        return this.myLocation;
    }
    public void setMyLocation(Point p){
        this.myLocation=p; 
        this.getIcon().setLocation(p);
    }
    public JLabel getIcon(){
        return this.icon;
    }
    public int[][] getMyPath(){
        return null;
    }
    public Rectangle getMyHitBox(){
        return null;
    }
    public Boolean getMoveDone(){
        return false;
    }
    public void move(int x,int y){

    }
    public void setHp(int hp){

    }

    

    public  void addMouseMotionListenerToPlayer(GameWindow gw) {
        MouseListener ml = new MouseListener(){
        
            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
        
            @Override
            public void mousePressed(MouseEvent e) {
                int x = (int)myLocation.getX();
                int y = (int)myLocation.getY();
                Bullet bu = new Bullet(x, y,10, 2, 2, 0, gw, 20);
                gw.getGameBoard().add(bu.getIcon());
                bu.move(0, 0);
                System.out.println("Components on GameBoard = " + gw.getGameBoard().getComponentCount());
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        
            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
        
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        };
        MouseMotionListener mml = new MouseMotionListener(){
        
            @Override
            public void mouseMoved(MouseEvent e) {
                
                Point  p = e.getPoint();
               if(p != myLocation){
                   setMyLocation(p);
               } 
               
            }

            @Override
            public void mouseDragged(MouseEvent e) {
              
            } 


        
        };
        gw.getGameBoard().addMouseMotionListener(mml);
        gw.getGameBoard().addMouseListener(ml);
    }
   
}

