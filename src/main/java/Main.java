import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    private static int screen_width = 1200;
    private static int screen_height = 775;
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
        window.setLayout(new FlowLayout());//менеджер компоновки(по умолчанию компоненты окна будут располагаться по центру)
        window.setLocationRelativeTo(null);//задает расположение окна по центру экрана
        window.setCursor( window.getToolkit().createCustomCursor(
                new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
                new Point(),
                null ) );


        Drawer_2 game  = new Drawer_2();
        game.setPreferredSize(new Dimension(screen_width,screen_height-100));


        JPanel upPanel = new JPanel(); // верхняя панель состояния
        upPanel.setPreferredSize(new Dimension(screen_width,50));
        upPanel.setBackground(Color.lightGray); // фон светло-серый
        upPanel.setLayout(new FlowLayout());
        window.add(upPanel); // распологается вверху
        JLabel statusLabel = new JLabel("TEST"); // Элемент, который будет показывать текст состояния программы
        upPanel.add(statusLabel,BorderLayout.CENTER);    // добавляем его в верхнюю панель


        JButton b1 = new JButton();
        b1.setText("button1");
        b1.setVisible(true);
        JButton b2 = new JButton();
        b2.setText("button2");
        b2.setVisible(true);
        upPanel.add(b1);
        upPanel.add(b2);


        Panel panel = Panel.getInstance();
        panel.setPreferredSize(new Dimension(Panel.getInstance().getWIDTH(), Panel.getInstance().getHEIGHT()));

        window.add(panel);
        panel.getMainTimer().start();
        window.setVisible(true);//делает окно видимым
        panel.requestFocus();//задает фокус на игровую панель
        System.out.println("закрываем игру");
    }
}
