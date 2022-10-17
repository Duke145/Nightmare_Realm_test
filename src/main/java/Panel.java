import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Panel extends JPanel implements ActionListener{
    public static final int cell_size = 5;
    public static final int header_height = 100; //размер блока сверху
    public static int[][] field = new int[cell_size][cell_size]; //массив поля
    //public static ArrayList<Integer> headerField = new ArrayList<>();
    public static HashMap<Integer,Integer> headerField = new HashMap<>();
    private static boolean firstInit = true;

    Timer mainTimer = new Timer(17,this); //главный таймер, вызывает метод actionPerformed каждые 30 миллисекунд

    @Override
    public void actionPerformed(ActionEvent e) {
        if (state.equals(STATES.MENU)) {

        }

        if (state.equals(STATES.PLAY)) {
            gameRender();
            drawGrid(g);
            initHeader(g);
            initMainBlock(g);
            gameDraw();


        }
    }

    public static enum STATES{MENU,PLAY} //объявляем 2 состояния игры
    public static STATES state = STATES.PLAY; //задает изначальное состояние - меню

    //размер панели
    public int WIDTH;
    public int HEIGHT;

    //координаты мышки
    public static int mouseX;
    public static int mouseY;

    private BufferedImage image;
    private Graphics2D g;

    Back back = new Back();

    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);
        if (e.getButton() == MouseEvent.BUTTON1 && e.getID()==MouseEvent.MOUSE_PRESSED) { //проверка на нажатие левой кнопки мыши
            int x = e.getX(); // координаты левого клика мыши
            int y = e.getY();
            if (y>header_height) {
                int i = (int) ((float) x / WIDTH * cell_size);
                int j = (int) ((float) (y-header_height) / (getHeight()-header_height) * cell_size);
                if (field[i][j] == 4) field[i][j] = 0;
                else field[i][j]++;
                //repaint();
            } else {
                int i = (int) ((float) x / WIDTH * cell_size);


                if (headerField.containsKey(i)){
                    if (headerField.get(i) == 3) headerField.put(i,1);
                    else headerField.put(i,headerField.get(i)+1);
                }

                //repaint();

            }
        }
    }


    public Panel(int WIDTH, int HEIGHT) {
        super();
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        setFocusable(true); //передает фокус
        requestFocus(); //активирует фокус

        image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB); //создаем объект буфера для хранения картинок

        g = (Graphics2D) image.getGraphics(); // графическому объекту присвоим элемент из буфера

        enableEvents(AWTEvent.MOUSE_EVENT_MASK);//включает получение событий от мыши
    }



    public void gameRender() {
        back.draw(g);
    }

    public void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image,0,0,null); // отрисовка картинки
        g2.dispose(); // команда для сборщика мусора
    }

    public void initHeader(Graphics g) {
        int width = WIDTH;
        int dl = 20; // расстояние от границ ячеек
        int dw = width/cell_size;
        Color tempColor;
        g.setColor(Color.BLACK);

        for (int i = 0; i < cell_size; i++) {
            g.drawLine(dw * i, 0, dw * i, header_height);
            if (i % 2 == 0 && firstInit) {
                headerField.put(i,1);
            }
        }


        for(Map.Entry<Integer,Integer> elem : headerField.entrySet()) {
            switch (elem.getValue()) {
                case 1:
                    tempColor = Color.RED;
                    break;
                case 2:
                    tempColor = Color.GREEN;
                    break;
                case 3:
                    tempColor = Color.BLUE;
                    break;
                default:
                    tempColor = Color.WHITE;
            }
            g.setColor(tempColor);
            g.fillRect(dw*elem.getKey()+dl,dl,dw-2*dl,header_height-2*dl);
        }

    }

    public void initMainBlock(Graphics g) {
        Color tempColor;
        for (int i=0;i<cell_size;i++) {
            for (int j=0;j<cell_size;j++) {
                if (firstInit) field[i][j] = (int)((Math.random() * 5)); //Min + (int)(Math.random() * ((Max - Min) + 1)) - общая формула для рандома с включенными границами от min до max

                switch (field[i][j]) {
                    case 1:
                        tempColor = Color.BLACK;
                        break;
                    case 2:
                        tempColor = Color.RED;
                        break;
                    case 3:
                        tempColor = Color.GREEN;
                        break;
                    case 4:
                        tempColor = Color.BLUE;
                        break;
                    default:
                        tempColor = Color.WHITE;
                }
                drawBlock(i,j,g,tempColor);
            }
        }

        firstInit = false;
    }

    public void drawGrid(Graphics g) {
        int width = WIDTH;
        int height = HEIGHT-header_height;
        int dw = width/cell_size;
        int dh = height/cell_size;
        g.setColor(Color.BLACK);
        for (int i=0;i<cell_size;i++) {
            g.drawLine(0,dh*i + header_height,width,dh*i + header_height);
            g.drawLine(dw*i,header_height,dw*i,height + header_height);
        }
    }

    public void drawBlock(int i, int j,Graphics g, Color color) {
        g.setColor(color);
        int dl = 20; // расстояние от границ ячеек
        int dw = WIDTH / cell_size;
        int dh = (HEIGHT - header_height) / cell_size;
        int x = i*dw;
        int y = j*dh;
        g.fillOval(x+dl,y+dl+header_height ,dw-2*dl,dh-2*dl);
    }
}
