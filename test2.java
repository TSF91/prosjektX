import java.awt.event.MouseMotionListener;

public class test2  {
    public  static int c = 0; 
    public static int bulletCheck = 0;
    public static int nextPos =0; 
    public static Bullet[] bu = new Bullet[1];
    public static void main(String[] args){
        GameWindow gw = new GameWindow(1000, 1000, "test2");
        Enemy[] en = new Enemy[1];
        
        
        for (int i = 0; i < en.length; i++) {
            en[i]= new Enemy(225, 0, 40, 50, 50, 300, gw);
            gw.getGameBoard().add(en[i].getIcon());
            
        }
        GameObject[] go = new GameObject[gw.getGameBoard().getComponentCount()];
        
      
        
     
        gw.getGameBoard().revalidate();
        gw.getGameBoard().repaint();
        
      
        System.err.println("GameObject array length = " + go.length);
        
            System.out.println("test start");

        for (int i = 0; i < go.length; i++) {
            go[i] = en[i];
            go[i].getIcon().setIgnoreRepaint(true);
            en[i].move(225, 500);
            
            
        }  


        while(true){
            refresh(gw, go); 
        }     
    }
   

    public static void refresh(GameWindow gw,GameObject[] go){
        //en.getIcon().setBounds(en.getPosX(), en.getPosY(), en.getObjWidth(), en.getObjHeight());
        long ms = System.currentTimeMillis();
        
        if (bulletCheck == 0){
            for (int i = 0; i < bu.length; i++) { 
            bu[i] = new Bullet(250,500, 50, 2, 2, 10, gw, 10);
            gw.getGameBoard().add(bu[i].getIcon());
            bu[i].getIcon().setIgnoreRepaint(true);
            bulletCheck = 1; 
            bu[i].move(0, 0); 
            }
            

        }
       
        for (int i = 0; i < go.length; i++) {
                
                if(!go[i].getMoveDone()){
                    
                    go[i].getIcon().setLocation(go[i].getPosX(), go[i].getPosY());
                    
                    for(int j = 0; j<bu.length;j++){
                        bu[j].getIcon().setLocation(bu[j].getPosX(), bu[j].getPosY());
                        if(go[i].getMyHitBox().intersects(bu[j].getMyHitBox())){
                            go[i].setHp(go[i].getHp()-bu[j].getDmg());
                            CombatText.dmg((Enemy)go[i], bu[j]);
                            bu[j].setBulletHit(true);
                            System.out.println("my hp is: " + go[i].getHp());                          
                        }
                    }
                    
                    
                } else {
                    if(c == 0){
                         nextPos = MoveObject.randomPos(1,4);
                         c++;
                    }
                    
                    switch(nextPos){
                        default: break; 
                        case 1: go[i].move(0,0);
                                c++;
                                if(c == go.length+1){
                                    c=0;
                                }
                                break; 
                        case 2: go[i].move(350,0);
                                c++;
                                if(c == go.length+1){
                                    c=0;
                                }
                                break; 
                        case 3: go[i].move(0,550);
                                c++;
                                if(c == go.length+1){
                                    c=0;
                                }
                                break; 
                        case 4: go[i].move(350,550);
                                c++;
                                if(c == go.length+1){
                                c=0;
                                }
                                break; 

                    }
                    
                }
            }
    
        long after = System.currentTimeMillis() - ms;
        
        
        gw.refresh(20); 
        
            
        
       
      
        
    }
}