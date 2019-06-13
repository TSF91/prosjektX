
import java.awt.Component;
import java.util.Scanner;
import javax.swing.*;


public class Test{
    public static final int enemyW = 50;
    public static final int enemyH = enemyW; 
    public static void main(String[] args){
        GameWindow gw = new GameWindow(808, 1292, "Test");
        JFrame frame = gw.getGameWindow();
        
        Enemy[] enemys = new Enemy[10];  
        int pos = 8; 
        for (int i = 0; i < enemys.length; i++) {
                enemys[i] = new Enemy(i*pos, 20,  0, enemyW, enemyH, 100, gw);  
                gw.getGameBoard().add(enemys[i].getIcon());  
        }
        while(true){
            for (int i = 0; i < enemys.length; i++) {
                if(enemys[i].getMoveDone()){
                 
                    enemys[i].setSpeed(MoveObject.randomPos(1, 30));
                    enemys[i].move(MoveObject.randomPos(0,gw.getGameBoard().getWidth()-enemyW), MoveObject.randomPos(0, gw.getGameBoard().getHeight()-enemyH));

                        CombatText.dmg(enemys[4], gw);
                }
                try {
                    Thread.sleep(34);
                } catch (Exception e) {
                    System.out.println("a");
                }
               
            }
           
        }
    
       
    
    }      
}