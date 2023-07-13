package main;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void CheckTile(Ant ant){
        int ant_left_world_x = ant.ant_x+48 + ant.ant_solid_area.x;
        int ant_right_world_x = ant.ant_x+48 + ant.ant_solid_area.x + ant.ant_solid_area.width;
        int ant_top_world_y = ant.ant_y+48 + ant.ant_solid_area.y;
        int ant_bottom_world_y = ant.ant_y+48 + ant.ant_solid_area.y + ant.ant_solid_area.height;

        int ant_left_col = ant_left_world_x / gp.gp_size;
        int ant_right_col = ant_right_world_x / gp.gp_size;
        int ant_top_row = ant_top_world_y / gp.gp_size;
        int ant_bottom_row = ant_bottom_world_y / gp.gp_size;

        int tilenum1=0, tilenum2=0;

        switch (ant.direction) {
            case "aw" -> {
                ant_top_row = (ant_top_world_y - ant.ant_speed) / gp.gp_size;
                ant_left_col = (ant_left_world_x - ant.ant_speed) / gp.gp_size;
                tilenum1 = gp.tileManager.mapsTileNum[ant_left_col][ant_top_row];
                tilenum2 = gp.tileManager.mapsTileNum[ant_left_col][ant_bottom_row];
                if(gp.tileManager.tile[tilenum1].collision || gp.tileManager.tile[tilenum2].collision){
                    ant.collision = true;
                }
            }
            case "sa" -> {
                ant_bottom_row = (ant_bottom_world_y + ant.ant_speed) / gp.gp_size;
                ant_left_col = (ant_left_world_x - ant.ant_speed) / gp.gp_size;
                tilenum1 = gp.tileManager.mapsTileNum[ant_left_col][ant_top_row];
                tilenum2 = gp.tileManager.mapsTileNum[ant_left_col][ant_bottom_row];
                if(gp.tileManager.tile[tilenum1].collision || gp.tileManager.tile[tilenum2].collision){
                    ant.collision = true;
                }
            }
            case "sd" -> {
                ant_bottom_row = (ant_bottom_world_y + ant.ant_speed) / gp.gp_size;
                ant_right_col = (ant_right_world_x + ant.ant_speed) / gp.gp_size;
                tilenum1 = gp.tileManager.mapsTileNum[ant_right_col][ant_bottom_row];
                tilenum2 = gp.tileManager.mapsTileNum[ant_right_col][ant_top_row];
                if(gp.tileManager.tile[tilenum1].collision || gp.tileManager.tile[tilenum2].collision){
                    ant.collision = true;
                }
            }
            case "wd" -> {
                ant_top_row = (ant_top_world_y - ant.ant_speed) / gp.gp_size;
                ant_right_col = (ant_right_world_x + ant.ant_speed) / gp.gp_size;
                tilenum1 = gp.tileManager.mapsTileNum[ant_right_col][ant_top_row];
                tilenum2 = gp.tileManager.mapsTileNum[ant_right_col][ant_bottom_row];
                if(gp.tileManager.tile[tilenum1].collision || gp.tileManager.tile[tilenum2].collision){
                    ant.collision = true;
                }
            }
            case "w" -> {
                ant_top_row = (ant_top_world_y - ant.ant_speed) / gp.gp_size;
                tilenum1 = gp.tileManager.mapsTileNum[ant_left_col][ant_top_row];
                tilenum2 = gp.tileManager.mapsTileNum[ant_right_col][ant_top_row];
                if(gp.tileManager.tile[tilenum1].collision || gp.tileManager.tile[tilenum2].collision){
                    ant.collision = true;
                }
            }
            case "d" -> {
                ant_right_col = (ant_right_world_x + ant.ant_speed) / gp.gp_size;
                tilenum1 = gp.tileManager.mapsTileNum[ant_right_col][ant_top_row];
                tilenum2 = gp.tileManager.mapsTileNum[ant_right_col][ant_bottom_row];
                if(gp.tileManager.tile[tilenum1].collision || gp.tileManager.tile[tilenum2].collision){
                    ant.collision = true;
                }
            }
            case "s" -> {
                ant_bottom_row = (ant_bottom_world_y + ant.ant_speed) / gp.gp_size;
                tilenum1 = gp.tileManager.mapsTileNum[ant_left_col][ant_bottom_row];
                tilenum2 = gp.tileManager.mapsTileNum[ant_right_col][ant_bottom_row];
                if(gp.tileManager.tile[tilenum1].collision || gp.tileManager.tile[tilenum2].collision){
                    ant.collision = true;
                }
            }
            case "a" -> {
                ant_left_col = (ant_left_world_x - ant.ant_speed) / gp.gp_size;
                tilenum1 = gp.tileManager.mapsTileNum[ant_left_col][ant_top_row];
                tilenum2 = gp.tileManager.mapsTileNum[ant_left_col][ant_bottom_row];
                if(gp.tileManager.tile[tilenum1].collision || gp.tileManager.tile[tilenum2].collision){
                    ant.collision = true;
                }
            }
        }

        if((tilenum1 == 3 || tilenum2 == 3) && ant.visible && !ant.hasFood  && ant.collision){
            ant.collision = false;
        }
        if((tilenum1 == 3 || tilenum2 == 3) && ant.collision){
            ant.w1 = new ImageIcon("res/w-1.png").getImage();
            ant.w2 = new ImageIcon("res/w-2.png").getImage();
            ant.a1 = new ImageIcon("res/a-1.png").getImage();
            ant.a2 = new ImageIcon("res/a-2.png").getImage();
            ant.d1 = new ImageIcon("res/d-1.png").getImage();
            ant.d2 = new ImageIcon("res/d-2.png").getImage();
            ant.s1 = new ImageIcon("res/s-1.png").getImage();
            ant.s2 = new ImageIcon("res/s-2.png").getImage();
            ant.aw1 = new ImageIcon("res/aw-1.png").getImage();
            ant.aw2 = new ImageIcon("res/aw-2.png").getImage();
            ant.wd1 = new ImageIcon("res/wd-1.png").getImage();
            ant.wd2 = new ImageIcon("res/wd-2.png").getImage();
            ant.sd1 = new ImageIcon("res/sd-1.png").getImage();
            ant.sd2 = new ImageIcon("res/sd-2.png").getImage();
            ant.sa1 = new ImageIcon("res/sa-1.png").getImage();
            ant.sa2 = new ImageIcon("res/sa-2.png").getImage();
            ant.hasFood = false;
            Arrays.fill(ant.is_check, false);
            ant.visible = false;
        }
    }

    public int CheckObject(Ant ant, Rectangle solidArea, boolean is_ant){
        int counter = 0;
        int index = 999;
        for(int i =0;i<gp.object.length;i++){
            if(gp.object[i] != null){
                if(is_ant) {
                    solidArea.x = ant.ant_x + solidArea.x;
                    solidArea.y = ant.ant_y + solidArea.y;
                }
                if(!is_ant){
                    switch (ant.direction){
                        case "wd" -> {
                            solidArea.x = ant.ant_x + solidArea.x;
                            solidArea.y = ant.ant_y -64 + solidArea.y;
                        }
                        case "aw" -> {
                            solidArea.x = ant.ant_x -64 + solidArea.x;
                            solidArea.y = ant.ant_y -64 + solidArea.y;
                        }
                        case "sa" -> {
                            solidArea.x = ant.ant_x -64 + solidArea.x;
                            solidArea.y = ant.ant_y + solidArea.y;
                        }
                        case "sd" -> {
                            solidArea.x = ant.ant_x + solidArea.x;
                            solidArea.y = ant.ant_y + solidArea.y;
                        }
                        case "w" -> {
                            solidArea.x = ant.ant_x -32 + solidArea.x;
                            solidArea.y = ant.ant_y -64 + solidArea.y;
                        }
                        case "d" -> {
                            solidArea.x = ant.ant_x + solidArea.x;
                            solidArea.y = ant.ant_y -32 + solidArea.y;
                        }
                        case "s" -> {
                            solidArea.x = ant.ant_x -32 + solidArea.x;
                            solidArea.y = ant.ant_y + solidArea.y;
                        }
                        case "a" -> {
                            solidArea.x = ant.ant_x -64 + solidArea.x;
                            solidArea.y = ant.ant_y -32 + solidArea.y;
                        }
                    }
                }
                gp.object[i].solidArea.x = gp.object[i].obj_x + gp.object[i].solidArea.x;
                gp.object[i].solidArea.y = gp.object[i].obj_y + gp.object[i].solidArea.y;

                switch (ant.direction){
                    case "wd" -> {
                        solidArea.y-= ant.ant_speed - 1;
                        solidArea.x += ant.ant_speed - 1;
                        if(solidArea.intersects(gp.object[i].solidArea)){
                            if(gp.object[i].collision && is_ant){
                                ant.collision = true;
                                if(gp.object[i] != null)
                                    ant.PickUpObject(i);
                                index = i;
                            }
                            else if(is_ant && Objects.equals(gp.object[i].name, "feromon")){
                                ant.feromonCollision = true;
                                ant.is_check[i] = true;
                                ant.visible = false;
                                index = i;
                            }
                            else if(!is_ant && !ant.is_check[i]){
                                if(gp.object[i].counter > counter) {
                                    counter = gp.object[i].counter;
                                    ant.visible = true;
                                    ant.visialX = gp.object[i].solidArea.x;
                                    ant.visialY = gp.object[i].solidArea.y;
                                    ant.visible_tek = i;
                                    index = i;
                                }
                            }
                            //index = i;
                        }
                    }
                    case "aw" -> {
                        solidArea.y -= ant.ant_speed - 1;
                        solidArea.x -= ant.ant_speed - 1;
                        if(solidArea.intersects(gp.object[i].solidArea)){
                            if(gp.object[i].collision && is_ant){
                                ant.collision = true;
                                if(gp.object[i] != null)
                                    ant.PickUpObject(i);
                                index = i;
                            }
                            else if(is_ant && Objects.equals(gp.object[i].name, "feromon")){
                                ant.feromonCollision = true;
                                ant.is_check[i] = true;
                                ant.visible = false;
                                index = i;
                            }
                            else if(!is_ant && !ant.is_check[i]){
                                if(gp.object[i].counter > counter) {
                                    counter = gp.object[i].counter;
                                    ant.visible = true;
                                    ant.visialX = gp.object[i].solidArea.x;
                                    ant.visialY = gp.object[i].solidArea.y;
                                    ant.visible_tek = i;
                                    index = i;
                                }
                            }
                            //index = i;
                        }
                    }
                    case "sa" -> {
                        solidArea.y += ant.ant_speed - 1;
                        solidArea.x -= ant.ant_speed - 1;
                        if(solidArea.intersects(gp.object[i].solidArea)){
                            if(gp.object[i].collision && is_ant){
                                ant.collision = true;
                                if(gp.object[i] != null)
                                    ant.PickUpObject(i);
                                index = i;
                            }
                            else if(is_ant && Objects.equals(gp.object[i].name, "feromon")){
                                ant.feromonCollision = true;
                                ant.is_check[i] = true;
                                ant.visible = false;
                                index = i;
                            }
                            else if(!is_ant && !ant.is_check[i]){
                                if(gp.object[i].counter > counter) {
                                    counter = gp.object[i].counter;
                                    ant.visible = true;
                                    ant.visialX = gp.object[i].solidArea.x;
                                    ant.visialY = gp.object[i].solidArea.y;
                                    ant.visible_tek = i;
                                    index = i;
                                }
                            }
                            //index = i;
                        }
                    }
                    case "sd" -> {
                        solidArea.y += ant.ant_speed - 1;
                        solidArea.x += ant.ant_speed - 1;
                        if(solidArea.intersects(gp.object[i].solidArea)){
                            if(gp.object[i].collision && is_ant){
                                ant.collision = true;
                                if(gp.object[i] != null)
                                    ant.PickUpObject(i);
                                index = i;
                            }
                            else if (is_ant && Objects.equals(gp.object[i].name, "feromon")){
                                ant.feromonCollision = true;
                                ant.is_check[i] = true;
                                ant.visible = false;
                                index = i;
                            }
                            else if(!is_ant && !ant.is_check[i]){
                                if(gp.object[i].counter > counter) {
                                    counter = gp.object[i].counter;
                                    ant.visible = true;
                                    ant.visialX = gp.object[i].solidArea.x;
                                    ant.visialY = gp.object[i].solidArea.y;
                                    ant.visible_tek = i;
                                    index = i;
                                }
                            }
                        }
                    }
                    case "w" -> {
                        solidArea.y -= ant.ant_speed;
                        if(solidArea.intersects(gp.object[i].solidArea)){
                            if(gp.object[i].collision && is_ant){
                                ant.collision = true;
                                if(gp.object[i] != null)
                                    ant.PickUpObject(i);
                                index = i;
                            }
                            else if(is_ant && Objects.equals(gp.object[i].name, "feromon")){
                                ant.feromonCollision = true;
                                ant.is_check[i] = true;
                                ant.visible = false;
                                index = i;
                            }
                            else if(!is_ant && !ant.is_check[i]){
                                if(gp.object[i].counter > counter) {
                                    counter = gp.object[i].counter;
                                    ant.visible = true;
                                    ant.visialX = gp.object[i].solidArea.x;
                                    ant.visialY = gp.object[i].solidArea.y;
                                    ant.visible_tek = i;
                                    index = i;
                                }
                            }
                        }
                    }
                    case "d" -> {
                        solidArea.x += ant.ant_speed;
                        if(solidArea.intersects(gp.object[i].solidArea)){
                            if(gp.object[i].collision && is_ant){
                                ant.collision = true;
                                if(gp.object[i] != null)
                                    ant.PickUpObject(i);
                                index = i;
                            }
                            else if(is_ant && Objects.equals(gp.object[i].name, "feromon")){
                                ant.feromonCollision = true;
                                ant.is_check[i] = true;
                                ant.visible = false;
                                index = i;
                            }
                            else if(!is_ant && !ant.is_check[i]){
                                if(gp.object[i].counter > counter) {
                                    counter = gp.object[i].counter;
                                    ant.visible = true;
                                    ant.visialX = gp.object[i].solidArea.x;
                                    ant.visialY = gp.object[i].solidArea.y;
                                    ant.visible_tek = i;
                                    index = i;
                                }
                            }
                        }
                    }
                    case "s" -> {
                        solidArea.y += ant.ant_speed;
                        if(solidArea.intersects(gp.object[i].solidArea)){
                            if(gp.object[i].collision && is_ant){
                                ant.collision = true;
                                if(gp.object[i] != null)
                                    ant.PickUpObject(i);
                                index = i;
                            }
                            else if(is_ant && Objects.equals(gp.object[i].name, "feromon")){
                                ant.feromonCollision = true;
                                ant.is_check[i] = true;
                                ant.visible = false;
                                index = i;
                            }
                            else if(!is_ant && !ant.is_check[i]){
                                if(gp.object[i].counter > counter) {
                                    counter = gp.object[i].counter;
                                    ant.visible = true;
                                    ant.visialX = gp.object[i].solidArea.x;
                                    ant.visialY = gp.object[i].solidArea.y;
                                    ant.visible_tek = i;
                                    index = i;
                                }
                            }
                        }
                    }
                    case "a" -> {
                        solidArea.x -= ant.ant_speed;
                        if(solidArea.intersects(gp.object[i].solidArea)){
                            if(gp.object[i].collision && is_ant){
                                ant.collision = true;
                                if(gp.object[i] != null)
                                    ant.PickUpObject(i);
                                index = i;
                            }
                            else if(is_ant && Objects.equals(gp.object[i].name, "feromon")){
                                ant.feromonCollision = true;
                                ant.is_check[i] = true;
                                ant.visible = false;
                                index = i;
                            }
                            else if(!is_ant && !ant.is_check[i]){
                                if(gp.object[i].counter > counter) {
                                    counter = gp.object[i].counter;
                                    ant.visible = true;
                                    ant.visialX = gp.object[i].solidArea.x;
                                    ant.visialY = gp.object[i].solidArea.y;
                                    ant.visible_tek = i;
                                    index = i;
                                }
                            }
                        }
                    }
                }
                solidArea.x = -64;
                solidArea.y = -64;
                ant.ant_solid_area.x = ant.solidAreaDefaultX;
                ant.ant_solid_area.y = ant.solidAreaDefaultY;
                if(gp.object[i]!=null) {
                    gp.object[i].solidArea.x = gp.object[i].solidAreaDefaultX;
                    gp.object[i].solidArea.y = gp.object[i].solidAreaDefaultY;
                }
            }
        }
        return index;
    }
}












