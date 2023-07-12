package main;

import java.awt.event.MouseListener;

public class MouseEvent implements MouseListener {
    GamePanel gp;
    public MouseEvent(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        gp.click = true;
        gp.mouse_x = e.getX();
        gp.mouse_y = e.getY();
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }
}
