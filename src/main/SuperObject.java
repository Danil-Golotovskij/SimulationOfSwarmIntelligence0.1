package main;

import java.awt.*;

public class SuperObject {
    public Image image;
    public String name;
    public boolean collision = false;
    public int obj_x, obj_y;
    public Rectangle solidArea = new Rectangle(0,0,16,16);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public int counter = 7000;

    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, obj_x, obj_y,16,16,null);
    }
}











