import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainPanel extends JPanel {

    private static MainPanel instance;

    private MainPanel() {

    }

    public static MainPanel getInstance() {
        if (instance == null) {
            instance = new MainPanel();
            instance.setLayout(new FlowLayout());
            instance.setPreferredSize(new Dimension(Main.getScreen_width(),Main.getScreen_height()));
            JPanel upPanel = new JPanel(); // верхняя панель состояния
            upPanel.setPreferredSize(new Dimension(Main.getScreen_width(),50));
            upPanel.setBackground(Color.lightGray); // фон светло-серый
            upPanel.setLayout(new FlowLayout());
            instance.add(upPanel); // распологается вверху
            Component statusLabel = new JLabel("TEST"); // Элемент, который будет показывать текст состояния программы
            statusLabel.setName("Test");
            upPanel.add(statusLabel,BorderLayout.CENTER);    // добавляем его в верхнюю панель


            JButton b1 = new JButton(new ButtonAction_1());
            b1.setText("INIT mode");
            b1.setVisible(true);
            JButton b2 = new JButton(new ButtonAction_2());
            b2.setText("PLAY mode");
            b2.setVisible(true);
            upPanel.add(b1);
            upPanel.add(b2);




            Panel bottomPanel = Panel.getInstance();
            bottomPanel.setPreferredSize(new Dimension(Panel.getInstance().getWIDTH(), Panel.getInstance().getHEIGHT()));


            instance.add(bottomPanel);
            bottomPanel.getMainTimer().start();

        }

        return instance;
    }
}
