public class GameEngine{
    public void enemyHandler(){
        Enemy[] all = Enemy.getAllEnemies(); 
        int enCount = Enemy.getEnemyCount(); 
        for (int i = 0; i < enCount; i++) {
            if(all[i].getHp()>0){
                all[i].getIcon().setLocation(all[i].getPosX(), all[i].getPosY());
            } else {
                gw.getGameBoard().remove(all[i].getIcon());
            }
        }
    }

    
}