/**
 * @author T.Friestad
 * Class to generate and show combat text in GameWindow
 */

import java.awt.*;
import javax.swing.*;
public class CombatText{
    /**
     * 
     * @param fi - enemy hit by bullet
     * @param bu - bullet that hit 
     */

    public static boolean dirRight = true; 

    public static void dmg(Enemy fi,Bullet bu){// add bullet?
        int dmgLabelW = 100; 
        int dmgLabelH = 50; 
        int xDir = 0; // Direction for combattext to float away.
        int fiX = fi.getPosX();
        int fiY = fi.getPosY();
        int offsetX = fi.getObjWidth()/2; // spwanpoint for combat text
        JLabel dmgLabel = new JLabel("");
        Font font = new Font("Comic Sans MS", Font.BOLD,20);
        Color white = new Color(255,255,255);
    
        if(bu.getCrit()){ //Larger + yellow text if critical hit   
            font = new Font("Comic Sans MS", Font.BOLD,30);
            Color yellow = new Color(255,255,0);
            dmgLabel.setForeground(yellow);
            dmgLabel.setFont(font);
        } else {
            dmgLabel.setForeground(white);
            dmgLabel.setFont(font);
        }
        //sets start pos for combat text
        dmgLabel.setBounds(fi.getPosX()+offsetX,fi.getPosY(),dmgLabelW,dmgLabelH);
        dmgLabel.setText(Integer.toString(bu.getDmg()));
        //New thread moves combat text and remove when movement is done
        Runnable showDmg = new Runnable(){
            @Override
            public void run() {
                JPanel gameBoard = fi.getGameBoard();
                int x = fi.getPosX();
                int y = dmgLabel.getY(); 
                int xDir = (dirRight)? 1:-1; //sets direction for CT to float away. 
              
                if(dirRight){
                    dirRight = false; 
                }else{
                    dirRight = true; 
                }
                gameBoard.add(dmgLabel);
                for(int i=0; i < 150; i++){
                    dmgLabel.setBounds(x,y,dmgLabelW,dmgLabelH);
                    y--;
                    x = x + xDir; 
                    try {
                        Thread.sleep(10); 
                    } catch (Exception e) {
                        System.out.println("err");
                    }
                }
                gameBoard.remove(dmgLabel);
                gameBoard.revalidate();
                gameBoard.repaint();
            }
        };
        new Thread(showDmg).start(); 
    }
    //makes JLabel blink red when hit
    public static void flashHit(Enemy fi){
        JPanel flash = new JPanel();
        Color red = new Color(255,0,0);
        Runnable blink = new Runnable(){
        
            @Override
            public void run() {
                flash.setBounds(0,0,fi.getObjHeight(),fi.getObjHeight());
                flash.setBackground(red);
                fi.getIcon().add(flash);
                try {
                    Thread.sleep(25);
                } catch (Exception e) {
                    System.out.println("err");
                }  
                fi.getIcon().remove(flash);
            }
        }; 
       new Thread(blink).start();
    }
}