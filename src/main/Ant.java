package main;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Ant {
    //Координаты x,y и скорость муравья
    public int ant_x, ant_y, ant_speed;

    //Панель к которой привязвн муравей
    GamePanel gp;

    //направление движения муравья
    String direction;

    //Переменные для вывода спрайтов
    int sprite_counter = 0, sprite_num = 1;

    //Коллизия муравья
    public Rectangle ant_solid_area;

    //Поле зрения муравья
    public Rectangle ant_visible_solid_area;

    //Коллизия с препятствиями
    public boolean collision = false;

    //Начальные координаты прямоугольника коллизии
    int solidAreaDefaultX,solidAreaDefaultY;

    //Муравей с едой или нет
    boolean hasFood = false;

    //Счетчики для смены направления движения муравья через определенное время
    public int actionLock = 0,actionLockMax;

    //Счетчики для установки феромон
    public int feromonLock = 0,feromonLocMax = 10;

    //Увидел ли муравей объект(феромон или еду)
    public boolean visible = false;

    //Индекс увиденнного объекта
    public int visible_tek;

    //Координаты увиденного объекта
    public int visialX,visialY;

    //Массив проверяет какие феромоны муравей уже прошел
    public boolean[] is_check = new boolean[300];

    //Коллизия с феромонами
    public boolean feromonCollision = false;


    //Подгружаем картинки для анимации муравья
    Image w1 = new ImageIcon("res/w-1.png").getImage();
    Image w2 = new ImageIcon("res/w-2.png").getImage();
    Image a1 = new ImageIcon("res/a-1.png").getImage();
    Image a2 = new ImageIcon("res/a-2.png").getImage();
    Image d1 = new ImageIcon("res/d-1.png").getImage();
    Image d2 = new ImageIcon("res/d-2.png").getImage();
    Image s1 = new ImageIcon("res/s-1.png").getImage();
    Image s2 = new ImageIcon("res/s-2.png").getImage();
    Image aw1 = new ImageIcon("res/aw-1.png").getImage();
    Image aw2 = new ImageIcon("res/aw-2.png").getImage();
    Image wd1 = new ImageIcon("res/wd-1.png").getImage();
    Image wd2 = new ImageIcon("res/wd-2.png").getImage();
    Image sd1 = new ImageIcon("res/sd-1.png").getImage();
    Image sd2 = new ImageIcon("res/sd-2.png").getImage();
    Image sa1 = new ImageIcon("res/sa-1.png").getImage();
    Image sa2 = new ImageIcon("res/sa-2.png").getImage();

    public Ant(GamePanel gp){
        this.gp = gp;
        setValues();
        ant_solid_area = new Rectangle(0,0,32,32);
        ant_visible_solid_area = new Rectangle(0,0,96,96);
        solidAreaDefaultX = ant_solid_area.x;
        solidAreaDefaultY = ant_solid_area.y;
    }

    public void setValues(){
        ant_x = gp.gp_size*10;
        ant_y = gp.gp_size*8;
        ant_speed = 2;
        String[] s = new String[8];
        s = new String[]{"w", "d", "a", "s", "wd", "aw", "sa", "sd"};
        Random random1 = new Random();
        actionLockMax = random1.nextInt(80)+40;
        direction = s[random1.nextInt(s.length)];
        Arrays.fill(is_check, false);
        //for (int i = 0; i < gp.object.length; i++){
        //    if(gp.object[i] == null){
        //        gp.index_feromon = i;
        //        break;
        //    }
        //}
    }

    //Метод устанавливающий модель поведения муравья
    public void setAction(){

        //Обзор муравья
        if(!hasFood && !visible && !collision){
            gp.checker.CheckObject(this,ant_visible_solid_area, false);
        }

        //Если муравей увидел объект(феромон или еду)
        if(!hasFood && visible && !collision){
            if(gp.object[visible_tek] == null){
                visible = false;
            }
            if(ant_x >= visialX+12 && ant_y >= visialY && ant_y <= visialY+4){
                direction = "a";
            }
            else if(ant_x < visialX+4 && ant_y >= visialY && ant_y <= visialY+4) {
                direction = "d";
            }
            else if(ant_x >= visialX && ant_x <= visialX+4 && ant_y <visialY+4){
                direction = "s";
            }
            else if(ant_x >= visialX && ant_x <= visialX+4 && ant_y >visialY+12){
                direction = "w";
            }
            else if(ant_x < visialX && ant_y < visialY){
                direction = "sd";
            }
            else if(ant_x > visialX+12 && ant_y < visialY){
                direction = "sa";
            }
            else if(ant_x > visialX+12){
                direction = "aw";
            }
            else if(ant_x < visialX){
                direction = "wd";
            }
        }

        //Если муравей ничего не увидел
        else if(!hasFood && !collision){
            actionLock++;
            if (actionLock == actionLockMax) {
                Random random = new Random();
                int i = random.nextInt(60) + 1;

                switch (direction) {
                    case "w" -> {
                        if (i <= 20) direction = "w";
                        else if (i <= 40) direction = "aw";
                        else direction = "wd";
                    }
                    case "s" -> {
                        if (i <= 20) direction = "s";
                        else if (i <= 40) direction = "sa";
                        else direction = "sd";
                    }
                    case "d" -> {
                        if (i <= 20) direction = "d";
                        else if (i <= 40) direction = "aw";
                        else direction = "sd";
                    }
                    case "a" -> {
                        if (i <= 20) direction = "a";
                        else if (i <= 40) direction = "aw";
                        else direction = "sa";
                    }
                    case "wd" -> {
                        if (i <= 20) direction = "wd";
                        else if (i <= 40) direction = "w";
                        else direction = "d";
                    }
                    case "aw" -> {
                        if (i <= 20) direction = "aw";
                        else if (i <= 40) direction = "w";
                        else direction = "a";
                    }
                    case "sa" -> {
                        if (i <= 20) direction = "sa";
                        else if (i <= 40) direction = "s";
                        else direction = "a";
                    }
                    case "sd" -> {
                        if (i <= 20) direction = "sd";
                        else if (i <= 40) direction = "s";
                        else direction = "d";
                    }
                }
                actionLock = 0;
            }
        }

        //Муравей с едой
        else if (hasFood){
            feromonLock++;

            if (ant_x > 668 && ant_y >= 336 && ant_y <= 340) {
                direction = "a";
            } else if (ant_x < 628 && ant_y >= 336 && ant_y <= 340) {
                direction = "d";
            } else if (ant_x >= 624 && ant_x <= 628 && ant_y < 340) {
                direction = "s";
            } else if (ant_x >= 624 && ant_x < 628 && ant_y > 380) {
                direction = "w";
            } else if (ant_x < 628 && ant_y < 340) {
                direction = "sd";
            } else if (ant_x > 668 && ant_y < 340) {
                direction = "sa";
            } else if (ant_x > 668) {
                direction = "aw";
            } else if (ant_x < 628) {
                direction = "wd";
            }

            //if(feromonCollision && !collision){
            //    feromonLock = 0;
            //    if(index1 != 999) {
            //if(index1 >= 100) {
            //            gp.object[index1].counter = 7000;
            //            for (int i = 0; i < gp.ants.length; i++) {
                            //if (((gp.ants[i].ant_x < 576) || (gp.ants[i].ant_x > 672)) && ((gp.ants[i].ant_y < 288) || (gp.ants[i].ant_y > 384))) {
            //                    gp.ants[i].is_check[index1] = false;
                            //}
            //            }
            //        }
            //    }
            //}

            int objIndex = gp.checker.CheckObject(this,ant_solid_area, true);
            //if(feromonLock == feromonLocMax){//if (!feromonCollision) {
                if(gp.index_feromon == 299){
                    gp.index_feromon = 100;
                }
                if(feromonCollision && objIndex!=999 && objIndex>=100){
                    gp.object[objIndex].counter = 7000;
                    for (int i = 0; i < gp.slider.getValue(); i++){
                        if(gp.ants[i].ant_x < ant_x-64 || gp.ants[i].ant_x>ant_x+96 || gp.ants[i].ant_y < ant_y-64 || gp.ants[i].ant_y>ant_y+96)
                            gp.ants[i].is_check[objIndex] = false;
                    }
                }
                else {
                    gp.object[gp.index_feromon] = new Feromon();
                    gp.object[gp.index_feromon].obj_x = ant_x + 8;
                    gp.object[gp.index_feromon].obj_y = ant_y + 8;
                    gp.index_feromon++;
                }
                    //for (int i = 0; i < gp.slider.getValue(); i++){
                    //    gp.ants[i].is_check[gp.index_feromon] = false;
                    //}

                feromonLock = 0;
                System.out.println(gp.index_feromon);
            //}
        }
    }

    public void update(){
        setAction();

        collision = false;
        //feromonCollision = false;
        gp.checker.CheckTile(this);

        //Проверка коллизии
        if(!hasFood){
            int objIndex = gp.checker.CheckObject(this,ant_solid_area, true);
            PickUpObject(objIndex);
        }

        //Если нет коллизии
        if(!collision){
            switch (direction) {
                case "wd" -> {
                    ant_y -= ant_speed - 1;
                    ant_x += ant_speed - 1;
                }
                case "aw" -> {
                    ant_y -= ant_speed - 1;
                    ant_x -= ant_speed - 1;
                }
                case "sa" -> {
                    ant_y += ant_speed - 1;
                    ant_x -= ant_speed - 1;
                }
                case "sd" -> {
                    ant_y += ant_speed - 1;
                    ant_x += ant_speed - 1;
                }
                case "w" -> {
                    ant_y -= ant_speed;
                }
                case "d" -> {
                    ant_x += ant_speed;
                }
                case "s" -> {
                    ant_y += ant_speed;
                }
                case "a" -> {
                    ant_x -= ant_speed;
                }
            }
        }

        //Если произошло столкновение
        if(collision){
            switch (direction) {
                case "w" -> {
                    direction = "s";
                }
                case "s" -> {
                    direction = "w";
                }
                case "d" -> {
                    direction = "a";
                }
                case "a" -> {
                    direction = "d";
                }
                case "wd" -> {
                    direction = "sa";
                }
                case "aw" -> {
                    direction = "sd";
                }
                case "sa" -> {
                    direction = "wd";
                }
                case "sd" -> {
                    direction = "aw";
                }
            }
        }

        //Счетчик вывода спрайтов
        sprite_counter++;
        if(sprite_counter > 6){
            if(sprite_num == 1)
                sprite_num =2;
            else if(sprite_num ==2)
                sprite_num = 1;
                sprite_counter = 0;
            }

        //исчезновение феромонов
        for (int i = 0; i < gp.object.length; i++){
            if(gp.object[i] != null) {
                if (Objects.equals(gp.object[i].name, "feromon")) {
                    gp.object[i].counter--;
                    if (gp.object[i].counter == 0) {
                        gp.object[i] = null;
                    }
                }
            }
        }
    }

    public void PickUpObject(int i){
        if(i!=999) {
            if (gp.object[i] != null) {
                String objectName = gp.object[i].name;

                switch (objectName) {
                    case "food" -> {
                        if (!hasFood)
                            gp.object[i] = null;
                        w1 = new ImageIcon("res/w-1F.png").getImage();
                        w2 = new ImageIcon("res/w-2F.png").getImage();
                        a1 = new ImageIcon("res/a-1F.png").getImage();
                        a2 = new ImageIcon("res/a-2F.png").getImage();
                        d1 = new ImageIcon("res/d-1F.png").getImage();
                        d2 = new ImageIcon("res/d-2F.png").getImage();
                        s1 = new ImageIcon("res/s-1F.png").getImage();
                        s2 = new ImageIcon("res/s-2F.png").getImage();
                        aw1 = new ImageIcon("res/aw-1F.png").getImage();
                        aw2 = new ImageIcon("res/aw-2F.png").getImage();
                        wd1 = new ImageIcon("res/wd-1F.png").getImage();
                        wd2 = new ImageIcon("res/wd-2F.png").getImage();
                        sd1 = new ImageIcon("res/sd-1F.png").getImage();
                        sd2 = new ImageIcon("res/sd-2F.png").getImage();
                        sa1 = new ImageIcon("res/sa-1F.png").getImage();
                        sa2 = new ImageIcon("res/sa-2F.png").getImage();
                        hasFood = true;
                    }
                    case "feromon" -> {
                        is_check[i] = true;
                        visible = false;
                    }
                }
            }
        }
    }

    public void draw(Graphics2D g2){
        Image image = null;

        switch (direction) {
            case "wd" -> {
                if (sprite_num == 1)
                    image = wd1;
                if (sprite_num == 2)
                    image = wd2;}
            case "aw" -> {
                if (sprite_num == 1)
                    image = aw1;
                if (sprite_num == 2)
                    image = aw2;}
            case "sa" -> {
                if (sprite_num == 1)
                    image = sa1;
                if (sprite_num == 2)
                    image = sa2;}
            case "sd" -> {
                if (sprite_num == 1)
                    image = sd1;
                if (sprite_num == 2)
                    image = sd2;}
            case "w" -> {
                if (sprite_num == 1)
                    image = w1;
                if (sprite_num == 2)
                    image = w2;}
            case "d" -> {
                if (sprite_num == 1)
                    image = d1;
                if (sprite_num == 2)
                    image = d2;}
            case "s" -> {
                if (sprite_num == 1)
                    image = s1;
                if (sprite_num == 2)
                    image = s2;}
            case "a" -> {
                if (sprite_num == 1)
                    image = a1;
                if (sprite_num == 2)
                    image = a2;}
        }
        g2.drawImage(image, ant_x, ant_y,32,32,null);
    }
}
