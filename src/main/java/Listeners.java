import java.awt.event.*;

public class Listeners implements MouseListener, KeyListener, MouseMotionListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            Player.getInstance().setUp(true);
        }

        if (key == KeyEvent.VK_S) {
            Player.getInstance().setDown(true);
        }

        if (key == KeyEvent.VK_A) {
            Player.getInstance().setLeft(true);
        }

        if (key == KeyEvent.VK_D) {
            Player.getInstance().setRight(true);
        }

        if (key == KeyEvent.VK_ENTER) {
            Panel.mouseX = (int)Player.getInstance().getX();
            Panel.mouseY = (int)Player.getInstance().getY();

            if (Panel.mouseX>(Panel.getWIDTH() - (Panel.getHEIGHT() - Panel.getHeader_height()))/2 && Panel.mouseX<(Panel.getWIDTH() - (Panel.getWIDTH() - (Panel.getHEIGHT() - Panel.getHeader_height()))/2)) {

                int i = (int) ((float) (Panel.mouseX - (Panel.getWIDTH() - (Panel.getHEIGHT() - Panel.getHeader_height())) / 2) / (Panel.getHEIGHT() - Panel.getHeader_height()) * Panel.cell_size);
                if (Panel.mouseY > Panel.getHeader_height()) {
                    int j = (int) ((float) (Panel.mouseY - Panel.getHeader_height()) / (Panel.getHEIGHT() - Panel.getHeader_height()) * Panel.cell_size);
                    if (Panel.field[i][j] == 4) Panel.field[i][j] = 0;
                    else Panel.field[i][j]++;
                    //repaint();
                } else {
                    if (Panel.headerField.containsKey(i)) {
                        if (Panel.headerField.get(i) == 3) Panel.headerField.put(i, 1);
                        else Panel.headerField.put(i, Panel.headerField.get(i) + 1);
                    }

                    //repaint();

                }

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            Player.getInstance().setUp(false);
        }
        if (key == KeyEvent.VK_S) {
            Player.getInstance().setDown(false);
        }

        if (key == KeyEvent.VK_A) {
            Player.getInstance().setLeft(false);
        }

        if (key == KeyEvent.VK_D) {
            Player.getInstance().setRight(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { //проверка на нажатие левой кнопки мыши  && e.getID()==MouseEvent.MOUSE_PRESSED
            //int x = e.getX(); // координаты левого клика мыши
            //int y = e.getY();

            Panel.mouseX = e.getX();
            Panel.mouseY = e.getY();

            if (Panel.mouseX>(Panel.getWIDTH() - (Panel.getHEIGHT() - Panel.getHeader_height()))/2 && Panel.mouseX<(Panel.getWIDTH() - (Panel.getWIDTH() - (Panel.getHEIGHT() - Panel.getHeader_height()))/2) && Panel.state.equals(Panel.STATES.PLAY)) {

                int i = (int) ((float) (Panel.mouseX - (Panel.getWIDTH() - (Panel.getHEIGHT() - Panel.getHeader_height())) / 2) / (Panel.getHEIGHT() - Panel.getHeader_height()) * Panel.cell_size);
                if (Panel.mouseY > Panel.getHeader_height()) {
                    int j = (int) ((float) (Panel.mouseY - Panel.getHeader_height()) / (Panel.getHEIGHT() - Panel.getHeader_height()) * Panel.cell_size);
                    if (Panel.field[i][j] == 4) Panel.field[i][j] = 0;
                    else Panel.field[i][j]++;
                    //repaint();
                } else {
                    if (Panel.headerField.containsKey(i)) {
                        if (Panel.headerField.get(i) == 3) Panel.headerField.put(i, 1);
                        else Panel.headerField.put(i, Panel.headerField.get(i) + 1);
                    }

                    //repaint();

                }

            } else if (Panel.mouseX>(Panel.getWIDTH() - (Panel.getHEIGHT() - Panel.getHeader_height()))/2 && Panel.mouseX<(Panel.getWIDTH() - (Panel.getWIDTH() - (Panel.getHEIGHT() - Panel.getHeader_height()))/2) && Panel.mouseY > Panel.getHeader_height()) {
//                int i = (int) ((float) (Panel.mouseX - (Panel.getWIDTH() - (Panel.getHEIGHT() - Panel.getHeader_height())) / 2) / (Panel.getHEIGHT() - Panel.getHeader_height()) * Panel.cell_size);
//                int j = (int) ((float) (Panel.mouseY - Panel.getHeader_height()) / (Panel.getHEIGHT() - Panel.getHeader_height()) * Panel.cell_size);
//                if (Panel.field[i][j]<2) {
//                    //Panel.await_cond = false;
                    Panel.state = Panel.STATES.PLAY;
                    System.out.println("Выход из состояния движения");
//                }
            }
        }

        if (e.getButton() == MouseEvent.BUTTON3) { //проверка на нажатие правой кнопки мыши  && e.getID()==MouseEvent.MOUSE_PRESSED
            Panel.mouseX = e.getX();
            Panel.mouseY = e.getY();

            System.out.println("Правый клик мыши!");

            if (Panel.mouseX>(Panel.getWIDTH() - (Panel.getHEIGHT() - Panel.getHeader_height()))/2 && Panel.mouseX<(Panel.getWIDTH() - (Panel.getWIDTH() - (Panel.getHEIGHT() - Panel.getHeader_height()))/2) && Panel.mouseY > Panel.getHeader_height() && Panel.state.equals(Panel.STATES.PLAY)) {

                //Panel.await_cond = true;
                Panel.state = Panel.STATES.MOVE;
                System.out.println("Состояние движения");

                int i = (int) ((float) (Panel.mouseX - (Panel.getWIDTH() - (Panel.getHEIGHT() - Panel.getHeader_height())) / 2) / (Panel.getHEIGHT() - Panel.getHeader_height()) * Panel.cell_size);
                int j = (int) ((float) (Panel.mouseY - Panel.getHeader_height()) / (Panel.getHEIGHT() - Panel.getHeader_height()) * Panel.cell_size);

                Panel.actionX = i;
                Panel.actionY = j;

            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Player.getInstance().setMouse_moved(true);
        Panel.mouseX = e.getX();
        Panel.mouseY = e.getY();
    }
}
