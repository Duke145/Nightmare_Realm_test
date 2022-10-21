import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    private static final int screen_width = 1200;
    private static final int screen_height = 775;
    public static int getScreen_width() {
        return screen_width;
    }

    public static int getScreen_height() {
        return screen_height;
    }


    public static void main(String[] args) {



        System.out.println("запускаем игру");
        JFrame window = new JFrame("Nightmare_realm");//создаем главное окно
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//задаем выход из приложения на крестик
        window.setSize(screen_width,screen_height);//задаем размер окна
        window.setLayout(new FlowLayout());//менеджер компоновки объектов внутри окна
        window.setLocationRelativeTo(null);//задает расположение окна по центру экрана



        MainPanel mainPanel = MainPanel.getInstance();
        mainPanel.getComponent(1).setCursor( window.getToolkit().createCustomCursor(
                new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
                new Point(),
                null ) );
        window.add(mainPanel);

        window.setVisible(true);//делает окно видимым
        mainPanel.getComponent(1).requestFocus();//задает фокус на игровую панель

        System.out.println("закрываем игру");
    }
}
