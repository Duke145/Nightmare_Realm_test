import javax.swing.*;
import java.awt.*;

public class Player {
    //нач координаты и размер объекта
    private static Player instance;
    private double x;
    private double y;
    private double w;
    private double h;

    private int speed;

    //клавиши перемещения
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    private boolean mouse_moved = false;

    private Image img;

    private Player() {
        x = Panel.mouseX;
        y = Panel.mouseY;
        w = 82;
        h = 100;
        Image image = new ImageIcon("image/cursor_2.png").getImage();
        img = image.getScaledInstance((int)w,(int)h,Image.SCALE_SMOOTH);
        speed = 7;
        up = false;
        down = false;
        left = false;
        right = false;
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }

        return instance;
    }

    public boolean isMouse_moved() {
        return mouse_moved;
    }

    public void setMouse_moved(boolean mouse_moved) {
        this.mouse_moved = mouse_moved;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void update() {

        if (mouse_moved) {
            x = Panel.mouseX;
            y = Panel.mouseY;
        }

        if (up && y>0) {
            y-=speed;
            mouse_moved = false;
        }

        if (down && y<Panel.getHEIGHT()-h) {
            y+=speed;
            mouse_moved = false;
        }

        if (left && x>0) {
            x-=speed;
            mouse_moved = false;
        }

        if (right && x<Panel.getWIDTH()-w) {
            x+=speed;
            mouse_moved = false;
        }
    }

    public void draw(Graphics2D g) {
        g.drawImage(img,(int)x,(int)y,null);
    }
}
