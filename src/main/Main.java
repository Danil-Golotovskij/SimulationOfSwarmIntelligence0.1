package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame(); //Создаем главное окно приложения
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Необходимо для закрытия окна
        window.setResizable(false); //Зафиксировать размер окна
        window.setTitle("Роевой интеллект"); //Название окна
        ImageIcon icon = new ImageIcon("res/i.jpg"); //Получить иконку
        window.setIconImage(icon.getImage()); //Установить иконку

        GamePanel panel = new GamePanel(); //Создать игровую панель
        window.add(panel); //Добавить панель

        window.pack(); //Чтобы видеть JPanel

        window.setLocationRelativeTo(null); //Окно будет распологаться в центре экрана
        window.setVisible(true); //Окно будет видимым

        panel.StartThread(); //Старт симуляции
    }
}
