

public class Test3 {
    public static void main(String[] args){
       GameWindow gw = new GameWindow(800, 1000, "Test3");
       Player pl = new Player(200, 200, 0, 10, 10, 10, gw);
       gw.getGameBoard().add(pl.getIcon());
       Enemy[] enemys = new Enemy[10];
       for (int i = 0; i < enemys.length; i++) {
           enemys[i] = new Enemy(i*50, 0, MoveObject.randomPos(20,50), 50, 50, 1000, gw);
           gw.getGameBoard().add(enemys[i].getIcon());
           enemys[i].move((MoveObject.randomPos(0, 750)),(MoveObject.randomPos(0, 800)));
           
       }
       gw.getGameBoard().revalidate();
       gw.getGameBoard().repaint();
       while(true){
           for (int i = 0; i < enemys.length; i++) {
                if(enemys[i].getMoveDone()){
                enemys[i].move((MoveObject.randomPos(0, 750)),(MoveObject.randomPos(0, 800)));
                }
            }
            GameWindow.refresh(20,gw);               
        }  
    }
}