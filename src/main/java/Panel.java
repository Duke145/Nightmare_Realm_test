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

    public static int getHeader_height() {
        return header_height;
    }

    public static int cell_size = 5;


    public static HashMap<Integer, Integer> getHeaderField() {
        return headerField;
    }

    //public static Boolean await_cond = false;

    public static boolean isFirstInit() {
        return firstInit;
    }



    private static int header_height = 100; //размер блока сверху
    public static int[][] field = new int[cell_size][cell_size]; //массив поля
    //public static ArrayList<Integer> headerField = new ArrayList<>();
    public static HashMap<Integer,Integer> headerField = new HashMap<>();
    private static boolean firstInit = true;

    public Timer mainTimer = new Timer(17,this); //главный таймер, вызывает метод actionPerformed каждые 30 миллисекунд

    public enum STATES{MENU,PLAY,MOVE} //объявляем 2 состояния игры
    public static STATES state = STATES.PLAY; //задает изначальное состояние - меню

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    //размер панели
    private static int WIDTH;
    private static int HEIGHT;

    //координаты мышки
    public static int mouseX;
    public static int mouseY;

    //координаты ходящей фишки
    public static int actionX;
    public static int actionY;

    private BufferedImage image;
    private Graphics2D g;

    Back back = new Back();

    Player player = Player.getInstance();



    @Override
    public void actionPerformed(ActionEvent e) {
        if (state.equals(STATES.MENU)) {

        }

        if (state.equals(STATES.PLAY)) {
            gameUpdate();
            //gameRender(); //добавление фона в буфер картинок
            back.draw(g);
            drawGrid(g); //отрисовка основного поля в буфер картинок
            initHeader(g); // отрисовка заголовка с квадратами в буфер картинок
            initMainBlock(g); // отрисовка фишек в буфер картинок
            player.draw(g);
            gameDraw(); // отрисовка всех элементов из буфера


        }

        if (state.equals(STATES.MOVE)) {
            gameUpdate();
            //gameRender(); //добавление фона в буфер картинок
            back.draw(g);
            drawGridMove(g,actionX,actionY);
            initMoveBlock(g,actionX,actionY);
            player.draw(g);
            gameDraw(); // отрисовка всех элементов из буфера
        }

    }


    public Panel(int WIDTH, int HEIGHT) {
        super();

        Panel.WIDTH = WIDTH;
        Panel.HEIGHT = HEIGHT;


        image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB); //создаем объект буфера для хранения картинок
        g = (Graphics2D) image.getGraphics(); // графическому объекту присвоим элемент из буфера

        enableEvents(AWTEvent.MOUSE_EVENT_MASK);//включает получение событий от мыши
        addMouseListener(new Listeners());
        addKeyListener(new Listeners());
        addMouseMotionListener(new Listeners());
        setFocusable(true); //передает фокус
        //requestFocus(); //активирует фокус
    }



    public void gameRender() {
        back.draw(g);
        player.draw(g);
    }

    public void gameUpdate() {
        player.update();
    }

    public void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image,0,0,null); // отрисовка картинки
        g2.dispose(); // команда для сборщика мусора
    }

    public void initHeader(Graphics g) {
        int width = HEIGHT - header_height;
        int dl = 10; // расстояние от границ ячеек
        int dw = width/cell_size;
        Color tempColor;
        g.setColor(Color.WHITE);

        for (int i = 0; i <= cell_size; i++) {
            g.drawLine((WIDTH - (HEIGHT - header_height))/2 + dw * i, 0, (WIDTH - (HEIGHT - header_height))/2 + dw * i, header_height);
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
            g.fillRect((WIDTH - (HEIGHT - header_height))/2 + dw*elem.getKey()+dl,dl,dw-2*dl,header_height-2*dl);
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

    public void initMoveBlock(Graphics g, int i, int j) {
        Color tempColor;
        int startXPos=0,startYPos=0,endXPos=0,endYPos=0;
        if (i>0) startXPos = i-1;
        else startXPos = i;
        if (j>0) startYPos = j-1;
        else startYPos = j;
        if (i<cell_size-1) endXPos = i+1;
        else endXPos = i;
        if (j<cell_size-1) endYPos = j+1;
        else endYPos = j;

        for (int t=startXPos;t<=endXPos;t++) {
            for (int k=startYPos;k<=endYPos;k++) {

                switch (field[t][k]) {
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
                drawBlock(t,k,g,tempColor);
            }
        }

    }

    public void drawGrid(Graphics g) {
        int width = HEIGHT-header_height;
        int height = HEIGHT-header_height;
        int dw = width/cell_size;
        int dh = height/cell_size;
        g.setColor(Color.WHITE);
        for (int i=0;i<=cell_size;i++) {
            g.drawLine((WIDTH - width)/2,dh*i + header_height,WIDTH - (WIDTH - width)/2,dh*i + header_height);
            g.drawLine((WIDTH - width)/2 + dw*i,header_height,(WIDTH - width)/2 + dw*i,height + header_height);
        }
    }

    public void drawGridMove(Graphics g, int i, int j) {
        int width = HEIGHT-header_height;
        int height = HEIGHT-header_height;
        int dw = width/cell_size;
        int dh = height/cell_size;
        int dt=1;
        g.setColor(Color.WHITE);

        int startXPos=0,startYPos=0,endXPos=0,endYPos=0;
        if (i>0) startXPos = i-1;
        else startXPos = i;
        if (j>0) startYPos = j-1;
        else startYPos = j;
        if (i<cell_size-1) endXPos = i+2;
        else endXPos = i+1;
        if (j<cell_size-1) endYPos = j+2;
        else endYPos = j+1;

        //if ((endXPos - startXPos)!=(endYPos - startYPos)) dt=0;

        for (int t=startXPos;t<=endXPos;t++) {
            //g.drawLine((WIDTH - width)/2 + dw*startXPos,dh*t + header_height,(WIDTH - width)/2 + dw*endXPos,dh*t + header_height);//горизонтальные линии
            g.drawLine((WIDTH - width)/2 + dw*t,header_height + dh*startYPos,(WIDTH - width)/2 + dw*t,header_height + dh*endYPos);//вертикальные линии
        }

        for (int t=startYPos;t<=endYPos;t++) {
            g.drawLine((WIDTH - width)/2 + dw*startXPos,dh*t + header_height,(WIDTH - width)/2 + dw*endXPos,dh*t + header_height);//горизонтальные линии
            //g.drawLine((WIDTH - width)/2 + dw*t,header_height + dh*startYPos,(WIDTH - width)/2 + dw*t,header_height + dh*endYPos);//вертикальные линии
        }
    }

    public void drawBlock(int i, int j,Graphics g, Color color) {
        g.setColor(color);
        int dl = 10; // расстояние от границ ячеек
        int dw = (HEIGHT - header_height) / cell_size;
        int dh = (HEIGHT - header_height) / cell_size;
        int x = i*dw;
        int y = j*dh;
        g.fillOval((WIDTH - (HEIGHT - header_height))/2 + x+dl,y+dl+header_height ,dw-2*dl,dh-2*dl);
    }
}
