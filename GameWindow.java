/**
 * @author T.Friestad
 * Class to generate gamewindow. Frame with JPanel 
 */
import javax.swing.*;
import java.awt.*;
public class GameWindow{
    private  JLabel bg = new JLabel(new ImageIcon("C:\\DEV\\Gui\\prosjektX\\Graphics\\bg.jpg"));
    private JFrame frame;
    private JPanel gameBoard; 
    private int frameW; 
    private int frameH; 
    private String frameName;
    //BG Color for JPanel/GameBoard 
    private static Color color = new Color(200,200,200); 

    /**
     * 
     * @param frameW
     * @param frameH
     * @param frameName
     */
    public GameWindow(int frameW, int frameH,String frameName){
        this.frameW = frameW; 
        this.frameH = frameH;
        this.frameName = frameName; 
        this.frame = new JFrame(frameName);
        bg.setBounds(frame.getBounds());
        frame.add(bg);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(frameW,frameH);
        this.frame.add(this.bg);
        this.frame.setVisible(true);
        
    }
    /**
     * Refresh gameboard at given rate. 
     * @param rate - refresh rate for frame
     */
    public static void refresh(int rate,GameWindow gw){
        Enemy[] all = Enemy.getAllEnemies(); 
        int enCount = Enemy.getEnemyCount(); 
        for (int i = 0; i < enCount; i++) {
            if(all[i].getHp()>0){
                all[i].getIcon().setLocation(all[i].getPosX(), all[i].getPosY());
            } else {
                gw.getGameBoard().remove(all[i].getIcon());
            }
            

        }
        gw.getGameBoard().revalidate();
        gw.getGameBoard().repaint();
        try {
            Thread.sleep(rate);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("err");
        }
    }
   
    public int getFrameW(){
        return this.frameW;
    }
    public int getFrameH(){
        return this.frameH; 
    }

    public void setFrameW(int frameW){
        this.frameW = frameW;
    }

    public void setFrameH(int frameH){
        this.frameH = frameH;
    }

    public void setFrameName(String frameName){
        this.frameName = frameName;  
    }

    public JFrame getGameWindow(){
        return this.frame; 
    }
    public JLabel getGameBoard(){
        return this.bg;
    }
        
        
}