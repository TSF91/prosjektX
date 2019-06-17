import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

public class Test3 {
    public static void main(String[] args){
       GameWindow gw = new GameWindow(850, 567, "Test3");
       Player pl = new Player(200, 200, 0, 10, 10, 10, gw);
        gw.getGameBoard().add(pl.getIcon());
        Enemy[] enemys = new Enemy[5];
        for (int i = 0; i < enemys.length; i++) {
           enemys[i] = new Enemy(i*50, 0, MoveObject.randomPos(5,20), 50, 50, MoveObject.randomPos(500,1500), gw);
           gw.getGameBoard().add(enemys[i].getIcon());
           
       }
       
        

       gw.getGameBoard().revalidate();
       gw.getGameBoard().repaint();
       while(true){
           for (int i = 0; i < enemys.length; i++) {
                if(enemys[i].getMoveDone()){
                enemys[i].move((MoveObject.randomPos(0, 750)),(MoveObject.randomPos(0,500)));
                }
            }
            GameWindow.refresh(20,gw);               
        }  
    }
}