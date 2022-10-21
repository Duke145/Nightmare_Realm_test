import javax.swing.*;
import java.awt.*;
public class Back {
    Image image = new ImageIcon("image/fon1.jpg").getImage();//загрузка картинки


    public void draw(Graphics2D g) {

        g.drawImage(image,0,0,null);
    }
}
