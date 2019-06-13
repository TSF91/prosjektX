/**
 * class for generating paths for the objects to move in.
 * @author Thomas S. Friestad
 */
import java.lang.*;
import java.io.*;
import javax.swing.*;

public class MoveObject {
    /**
     * @param movePatteren - move patteren (array generated with all points that the object will move in)
     * @param gw - gameBoard to mark path in
     */
   public void markPath(GameObject go){
       
        int selectColor = MoveObject.randomPos(1, 6);
        JLabel mrk = new JLabel("");
        //picks color based on selectColor above
        switch(selectColor){
            case 1: mrk = new JLabel(new ImageIcon("C:\\DEV\\Gui\\RainbowRain\\Graphics\\red.png"));
                    break; 
            case 2: mrk = new JLabel(new ImageIcon("C:\\DEV\\Gui\\RainbowRain\\Graphics\\orange.png"));
                    break;
            case 3: mrk = new JLabel(new ImageIcon("C:\\DEV\\Gui\\RainbowRain\\Graphics\\yellow.png"));
                    break;
            case 4: mrk = new JLabel(new ImageIcon("C:\\DEV\\Gui\\RainbowRain\\Graphics\\green.png"));
                    break;
            case 5: mrk = new JLabel(new ImageIcon("C:\\DEV\\Gui\\RainbowRain\\Graphics\\blue.png"));
                break;
            case 6: mrk = new JLabel(new ImageIcon("C:\\DEV\\Gui\\RainbowRain\\Graphics\\violet.png"));
                    break;
        }
/*
        Runnable  markMyPath = new Runnable(){
            JLabel finalMrk; 
            @Override
            public void run() {
                int[][] movePatteren = go.getMyPath(); 
                //marks path
                for (int i = 1; i < movePatteren.length; i++) {
                    finalMrk.setBounds(movePatteren[i][0],movePatteren[0][i], 1, 1);
                    gameBoard.add(finalMrk);
                } 
            }
            public void setMark(JLabel m) {
                finalMrk = m;
            }
        }; 
        markMyPath.setMark(mrk);
        */
        RunPath runPath = new RunPath(go);
        runPath.setMark(mrk);
        new Thread(runPath).start(); 
    } 

    class RunPath implements Runnable {
        JLabel finalMrk; 
        GameObject go;

        public RunPath(GameObject go) {
            this.go = go;
        }

        @Override
        public void run() {
            int[][] movePatteren = go.getMyPath(); 
            //marks path
            for (int i = 1; i < movePatteren.length; i++) {
                finalMrk.setBounds(movePatteren[i][0],movePatteren[0][i], 1, 1);
                go.getGameBoard().add(finalMrk);
            } 
        }
        public void setMark(JLabel m) {
            finalMrk = m;
        }
    }

   /**
    * gnerates random int between min and maxBounds
    * @param minBounds
    * @param maxBounds
    * @return
    */
   public static int randomPos(int minBounds, int maxBounds){
    int randomPos = minBounds  + (int)(Math.random() * ((maxBounds - minBounds) + 1));
    return randomPos;
   } 
   /**
    * sets path for bullet
    * @param x  x pos for bullet when fired
    * @param y  y pos for bullet when fired
    * @return
    */
   public static int[][] bulletPath(int x, int y){
       int moveDist = y;
       int[][]  myPath = new int[moveDist+1][moveDist+1];
       myPath[0][0] = 0;
       for (int i = 0; i < moveDist; i++) {
           if(i != 0){
                myPath[i][0] = x;
                myPath[0][i] = y;
                y--; 
           }
        }
        return myPath; 
   }
   /**
    * Sets path for enemys. moves x or y direction untill x = y then moves the remaining distance 
    * diagonally.
    * @param oldX initial x pos
    * @param oldY initial y pos
    * @param newX end x pos
    * @param newY end y pos
    * @return
    */
   public static int[][] pathFinder(int oldX, int oldY,int newX, int newY){ //For enemy
        int currentX = oldX; 
        int currentY = oldY; 
        int xDir;
        int yDir; 
        //find longest movement direction x or y
        int moveDistX = (newX - currentX);
        int moveDistY = (newY - currentY);
        xDir = (moveDistX >= 0) ? 1:-1; 
        yDir = (moveDistY >=0) ? 1:-1; 
        int xAbs = Math.abs(moveDistX);
        int yAbs = Math.abs(moveDistY);
        int moveDistXY; // Diagonal 
        int steps; // steps to move in x or y before diagonal movement
        if((xAbs) >= (yAbs)){
            moveDistXY = yAbs;
            steps = xAbs - yAbs;
        } else {
            moveDistXY = xAbs;
            steps = yAbs - xAbs;
        }
        int totalMoveLength = steps + moveDistXY;  
        // path array[x][y]
        int[][] myPath = new int[totalMoveLength+2][totalMoveLength+2]; 
        char firstMove = (xAbs >= yAbs)? 'x':'y';
        int iCount = 0;
        //selects algorithem based on longest movement direction (x or y)
        switch(firstMove){
            // CASE X
            case 'x':
            //move  untill x = y  
            for (int i = 1; i < steps + 1; i++) {
                myPath[i][0]=currentX;
                myPath[0][i]=currentY;
                currentX = currentX + xDir;
                iCount++;
                if(i == steps + 1){
                    currentY = currentY + yDir;
                }  
            }
            //move diagonal             
            for(int i = 0; i < moveDistXY+1; i++){
                iCount++;
                myPath[iCount][0]=currentX;
                myPath[0][iCount]=currentY;  
                currentX = currentX + xDir;
                currentY = currentY + yDir;
            }
            break;

            // CASE Y
            case 'y':
            //move untill y = x            
            for (int i = 1; i < steps + 1; i++) {
                myPath[i][0]=currentX;
                myPath[0][i]=currentY;
                currentY = currentY + yDir;
                iCount++;
                if(i == steps + 1){
                    currentX = currentX + xDir;
                }  
            }
            //move diagonal
            for(int i = 0; i < moveDistXY+1; i++){
                iCount++;
                myPath[iCount][0]=currentX;
                myPath[0][iCount]=currentY;  
                currentX = currentX + xDir;
                currentY = currentY + yDir;
            }
            break;
            //DEFAULT
            default: 
            break; 
        }
        return myPath;
   }
}
   
   
