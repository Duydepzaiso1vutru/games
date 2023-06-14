
package entity;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import my2Dgame.GamePanel;
import my2Dgame.KeyHandler;


public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public Player(GamePanel gp , KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDeafaultValues();
        getPlayerImage();
    }
    
   
    public void setDeafaultValues(){
        int x = 100;
        int y = 100;
        speed = 4;
        direction = "down";
    }
    
    public void getPlayerImage(){
        try{
          up = ImageIO.read(getClass().getResourceAsStream("/player/up.png"));
          down = ImageIO.read(getClass().getResourceAsStream("/player/down.png"));  
          left = ImageIO.read(getClass().getResourceAsStream("/player/left.png"));  
          right = ImageIO.read(getClass().getResourceAsStream("/player/right.png"));  
             
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update() {
            if(keyH.upPressed == true){
                direction = "up";
                y -= speed;
            }
            else if(keyH.downPressed == true){
                direction = "down";
                y += speed;
            }
            else if(keyH.leftPressed == true){
                direction = "left";
                x -= speed;
            }
            else if(keyH.rightPressed == true){
                direction = "right";
                x += speed;
            }
            
        }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x,y,gp.tileSize,gp.tileSize);
          BufferedImage image = null;
          switch(direction){
              case "up": 
                  image = up;
                  break;
              case "down": 
                  image = down;
                  break;
              case "left": 
                  image = left;
                  break;
              case "right": 
                  image = right;
                  break;
          }
          g2.drawImage(image, x, y, gp.tileSize, gp.tileSize,null );
    }
}
