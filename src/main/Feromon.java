package main;

import javax.swing.*;

public class Feromon extends SuperObject{

    public Feromon(){
        collision = false;
        name = "feromon";
        image = new ImageIcon("res/feromon.png").getImage();
    }
}
