package entity;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import java.awt.Color;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH; 

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try { 
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/ja.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/ja.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/ja.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/ja.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/ja.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/ja.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/ja.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/ja.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed == true){
            direction = "up";
            worldY -= speed;
        }
        else if(keyH.downPressed == true){
            direction = "down";
            worldY += speed;
        }
        else if(keyH.leftPressed ==true ){
            direction = "left";
            worldX -= speed;
        }
        else if(keyH.rightPressed == true){
            direction = "right";
            worldX += speed;
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch(direction){
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize,null);

    }
}
