package entity;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import java.awt.Color;
import java.awt.Rectangle;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH; 

    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
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

    public void update() {
    collisionOn = false;
    gp.cChecker.checkTile(this);
    int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);


    if(keyH.upPressed) {
        direction = "up";
        if(!collisionOn) worldY -= speed;
    }
    if(keyH.downPressed) {
        direction = "down";
        if(!collisionOn) worldY += speed;
    }
    if(keyH.leftPressed) {
        direction = "left";
        if(!collisionOn) worldX -= speed;
    }
    if(keyH.rightPressed) {
        direction = "right";
        if(!collisionOn) worldX += speed;
    }
}
    public void pickUpObject(int i){

        if(i != 999){
            String objectName = gp.obj[i].name;

            switch(objectName){
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Key: "+hasKey);
                    break;
                case "Door":
                    if(hasKey>0){
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    System.out.println("Key: "+hasKey);
                    break;
            }
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
