import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Back {
    Image image = new ImageIcon("image/fon1.jpg").getImage();//загрузка картинки


    public void draw(Graphics2D g) {

        g.drawImage(image,0,0,null);
    }
}
