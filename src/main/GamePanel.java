package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements Runnable{
    //48x48, размер препятсвий
    public final int gp_size = 48;

    //Столбцы
    public final int gp_col = 28;

    //Строки
    public final int gp_row = 16;

    //Кол-во кадров в секунду
    final int fps = 60;

    //Музыка в симуляции
    Sound sound = new Sound();

    //Объект класса проверяет коллизию
    public CollisionChecker checker = new CollisionChecker(this);

    //Работа с препятсвиями
    TileManager tileManager = new TileManager(this);

    //Получить фон
    Image background_picture = new ImageIcon("res/grass.jpg").getImage();

    //Подключить многопоточность
    Thread thread;

    //Массив объектов(феромоны и еда)
    public SuperObject[] object = new SuperObject[300];

    //Массив муравьев
    public Ant[] ants = new Ant[12];

    //Класс для размещения объектов на поле
    public AssetSetter asset_setter = new AssetSetter(this);

    //Индекс для текущего размещаемого феромона
    public int index_feromon = 100, mouse_x,mouse_y;

    //Главное меню
    public boolean start_game = false;

    JButton startButton = new JButton("Подтвердить");
    JLabel label = new JLabel("Количество муравьев", SwingConstants.CENTER);
    JSlider slider = new JSlider(2, 12, 7);
    MouseEvent mouseEvent = new MouseEvent(this);
    public boolean click = false;

    public GamePanel(){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(gp_size * gp_col, gp_size * gp_row));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addMouseListener(mouseEvent);

        slider.setBounds(530,300,250,55);
        slider.setBackground(Color.white);
        slider.setPaintLabels(true);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        this.add(slider);

        startButton.setBounds(530, 350, 250, 50);
        startButton.setBackground(Color.white);
        startButton.setFont(new Font("Calibri", Font.BOLD, 25));
        this.add(startButton);

        label.setBounds(530,250,250,55);
        label.setFont(new Font("Calibri", Font.BOLD, 25));
        label.setBackground(Color.white);
        label.setOpaque(true);
        this.add(label);
    }

    public void setupGame(int n){
        asset_setter.setObject(); //Установить объекты на поле
        asset_setter.setAnts(n); //Установить муравьев на поле
        PlayMusic(); //Установить музыку
    }

    //Подключить многопоточность
    public void StartThread(){
        while(!start_game) {
            startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    start_game = true;
                }
            });
            if (start_game) {
                label.setVisible(false);
                startButton.setVisible(false);
                slider.setVisible(false);
                setupGame(slider.getValue()); //Установка начальных параметров
                thread = new Thread(this);
                thread.start();
                break;
            }
        }
    }

    //Цикл симуляции, устанавливаем fps = 60
    public void run(){
        double draw_interval = (double) 1000000000/fps;
        double delta = 0;
        long last_time = System.nanoTime();
        long curret_time;

        while (thread != null){
            curret_time = System.nanoTime();
            delta += (curret_time - last_time) / draw_interval;
            last_time = curret_time;
            if(delta >= 1){
                update();  //Обновить информацию о поле
                repaint(); //Вывести изменения на экран
                delta --;
            }
        }
    }

    //Обновить информацию о муравьях
    public void update(){
        if(click){
            for (int i = 0; i <= 99; i++){
                if(object[i] == null && ((mouse_x <576 || mouse_x > 720) || (mouse_y<284 || mouse_y>428))){
                    object[i] = new Food();
                    object[i].obj_x = mouse_x;
                    object[i].obj_y = mouse_y;
                    break;
                }
            }
            click = false;
        }
        for (Ant ant : ants) {
            if (ant != null) {
                ant.update();
            }
        }
    }

    //Вывести изменения на экран
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background_picture, 0, 0, null);
        if(start_game) {
            tileManager.draw(g2);
            for (SuperObject superObject : object) {
                if (superObject != null) {
                    superObject.draw(g2, this);
                }
            }
            for (Ant ant : ants) {
                if (ant != null) {
                    ant.draw(g2);
                }
            }
            g2.dispose();
        }
    }

    public void PlayMusic(){
        sound.setFile();
        sound.play();
        sound.loop();
    }

    public void StopMusic(){
        sound.stop();
    }
}
















