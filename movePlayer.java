import java.awt.*;
import java.awt.event.*;

import javax.swing.*;



public class movePlayer  {
    public static Boolean moveing = true; 

    public static void move(Player player){
        MouseMotionListener lm = new MouseMotionListener(){
        
            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println("mMoved");
                Point p = e.getPoint();
                if(p != player.getMyLocation()){
                    player.setMyLocation(p);
                }
            }
        
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println("mDragged");

            }
        };
        MouseListener l = new MouseListener(){
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("mouseReleased");
            }
        
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("mousePressed");
                Point p = e.getPoint();
                if(p != player.getMyLocation()){
                    player.setMyLocation(p);
                }
            }
        
            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("mouseExit");
                moveing = false; 
            }
        
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println(" playerIsMoveing(player,e);"); 
              
            }

           
            
        
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("mouseClicked");
            }
        };
        JPanel panelToMoveIn = player.getGameBoard(); 
        panelToMoveIn.addMouseListener(l);
        panelToMoveIn.addMouseMotionListener(lm);
        panelToMoveIn.setCursor(null);




    }

    public static void cursorActive(MouseEvent e,Player player){
        while(true){
            Point p = e.getPoint();
        if(p != player.getMyLocation()){
            player.setMyLocation(p);
        }
        try {
            Thread.sleep(20);
        } catch (Exception ea) {
            System.out.println("err");
        }

        }
        
    }

   
}