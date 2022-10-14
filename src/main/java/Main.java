import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("запускаем игру");
        JFrame window = new JFrame("Nightmare_realm");//создаем главное окно
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//задаем выход из приложения на крестик
        window.setSize(800,800);//задаем размер окна
        window.setLayout(new BorderLayout());//менеджер компоновки(по умолчанию компоненты окна будут располагаться по центру)
        window.setLocationRelativeTo(null);//задает расположение окна по центру экрана
        Drawer game  = new Drawer();
        window.add(game);
        window.setVisible(true);//делает окно видимым
        System.out.println("закрываем игру");
    }
}
