package main;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    int[][] mapsTileNum;
    Image hill = new ImageIcon("res/hill.png").getImage();

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapsTileNum = new int[gp.gp_col+2][gp.gp_row+2];
        GetTileImage();
        LoadMap();
    }

    public void GetTileImage(){
        tile[0] = new Tile();

        tile[1] = new Tile();
        tile[1].image = new ImageIcon("res/water.png").getImage();
        tile[1].collision = true;

        tile[2] = new Tile();
        tile[2].image = new ImageIcon("res/food.png").getImage();
        tile[2].collision = true;

        tile[3] = new Tile();
        tile[3].collision = true;
    }

    public void LoadMap(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("res/map.txt"));

            int col = 0;
            int row = 0;
            while (col < gp.gp_col+2 && row < gp.gp_row+2){
                String line = br.readLine();

                while (col < gp.gp_col+2){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapsTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.gp_col+2){
                    col = 0;
                    row ++;
                }
            }
            br.close();
        }catch (Exception e){
        }
    }

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = -48;
        int y = -48;

        while (col < gp.gp_col+2 && row < gp.gp_row+2) {
            int tileNum = mapsTileNum[col][row];

            g2.drawImage(tile[tileNum].image,x,y,gp.gp_size,gp.gp_size,null);
            col ++;
            x += gp.gp_size;

            if(col == gp.gp_col+2){
                col = 0;
                x = -48;
                row ++;
                y += gp.gp_size;
            }
        }
        g2.drawImage(hill,gp.gp_size*12,gp.gp_size*6,gp.gp_size*3,gp.gp_size*3,null);
    }
}














