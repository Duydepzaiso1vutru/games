package my2Dgame;


import entity.Player;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    //Screen setting
    final int originalTileSize  = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 46px
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768px
    final int screenHeight = tileSize * maxScreenRow; // 576px
    public TileManager tileM = new TileManager(this);
    
    //FPS
     int FPS = 60;
    
//    tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();// dieu khien-> xong add vao game panel
    Thread gameThread;//khi thread chay se gi cho chuong trinh chay -> xong add vao game panel
    Player player = new Player(this,keyH);
   
    
  
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        
        //add keyH (nut dieu khien)
        this.addKeyListener(keyH);
        this.setFocusable(true);// de nhan gia tri nhap vao(key input)
        
        
    }
    
    public void startgameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long timer = 0;
        long drawCount = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/ drawInterval;
            timer = currentTime - lastTime;
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            
            if(timer >= 1000000000){
                System.out.println("FPS:"+ drawCount);
                drawCount = 0;
                timer = 0;
            }
           
            
        }
    } 
        //khi ma thay doi vi tri thi phai update lai
        public void update() {
        player.update();
            
        }
        
    @Override
        public void paintComponent(Graphics g){ // graphics g de ve ra man hinh
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;// thay doi graphic g thanh graphics2D
            tileM.draw(g2);
            player.draw(g2);
            g2.dispose();
            
        }
        
        
}