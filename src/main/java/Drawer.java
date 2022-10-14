import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class Drawer extends JComponent {
    public static final int cell_size = 5;
    public static final int block_color_1 = 1;
    public static final int block_color_2 = 2;
    public static final int block_color_3 = 3;
    public static int[][] field = new int[cell_size][cell_size]; //массив поля
    //public static HashMap<int[][],Integer> field = new HashMap<>();
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        initGame(g);

    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);
        if (e.getButton() == MouseEvent.BUTTON1 && e.getID()==MouseEvent.MOUSE_PRESSED) { //проверка на нажатие левой кнопки мыши
            int x = e.getX(); // координаты левого клика мыши
            int y = e.getY();

            int i = (int) ((float) x / getWidth() * cell_size);
            int j = (int) ((float) y / getHeight() * cell_size);
            if (field[i][j]==3) field[i][j]=1;
                else field[i][j]++;
            repaint();
        }
    }

    public Drawer() {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);//включает получение событий от мыши
    }

    public void initGame(Graphics g) {
        Color tempColor;
        for (int i=0;i<cell_size;i++) {
            for (int j=0;j<cell_size;j++) {
                if (field[i][j]==0) field[i][j] = (int)(1 + (Math.random() * 3));

                switch (field[i][j]) {
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
                drawBlock(i,j,g,tempColor);
            }
        }
        //repaint();
    }

    public void drawGrid(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int dw = width/cell_size;
        int dh = height/cell_size;
        g.setColor(Color.BLACK);
        for (int i=0;i<cell_size;i++) {
            g.drawLine(0,dh*i,width,dh*i);
            g.drawLine(dw*i,0,dw*i,height);
        }
        //repaint();
    }

    public void drawBlock(int i, int j,Graphics g, Color color) {
        g.setColor(color);
        int dl = 20; // расстояние от границ ячеек
        int dw = getWidth() / cell_size;
        int dh = getHeight() / cell_size;
        int x = i*dw;
        int y = j*dh;
        //g.drawOval(x + dw/2,y + dh/2,dw,dh);
        //g.drawOval(x+dl,y+dl ,dw-2*dl,dh-2*dl);
        g.fillOval(x+dl,y+dl ,dw-2*dl,dh-2*dl);
    }
}
