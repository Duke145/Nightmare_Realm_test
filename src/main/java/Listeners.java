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

        if (key == KeyEvent.VK_ENTER) { //проверка на нажатие левой кнопки мыши  && e.getID()==MouseEvent.MOUSE_PRESSED
            //int x = e.getX(); // координаты левого клика мыши
            //int y = e.getY();

            Panel.getInstance().setMouseX((int)Player.getInstance().getX());
            Panel.getInstance().setMouseY((int)Player.getInstance().getY());

            if (Panel.getInstance().getMouseX()>(Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()))/2 && Panel.getInstance().getMouseX()<(Panel.getInstance().getWIDTH() - (Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()))/2) && Panel.getInstance().getState().equals(Panel.STATES.INIT)) {

                int i = (int) ((float) (Panel.getInstance().getMouseX() - (Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height())) / 2) / (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()) * Panel.getInstance().getCell_size());
                if (Panel.getInstance().getMouseY() > Panel.getInstance().getHeader_height()) {
                    int j = (int) ((float) (Panel.getInstance().getMouseY() - Panel.getInstance().getHeader_height()) / (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()) * Panel.getInstance().getCell_size());
                    if (Panel.getInstance().getField()[i][j] == 4) Panel.getInstance().setField(i,j,0);
                    else Panel.getInstance().setField(i,j,Panel.getInstance().getField()[i][j]+1);
                    //repaint();
                } else {
                    if (Panel.getInstance().getHeaderField().containsKey(i)) {
                        if (Panel.getInstance().getHeaderField().get(i) == 3) Panel.getInstance().getHeaderField().put(i, 1);
                        else Panel.getInstance().getHeaderField().put(i, Panel.getInstance().getHeaderField().get(i) + 1);
                    }

                    //repaint();

                }

            } else if (Panel.getInstance().getMouseX()>(Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()))/2 && Panel.getInstance().getMouseX()<(Panel.getInstance().getWIDTH() - (Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()))/2) && Panel.getInstance().getMouseY() > Panel.getInstance().getHeader_height() && Panel.getInstance().getState().equals(Panel.STATES.MOVE)) {
                int i = (int) ((float) (Panel.getInstance().getMouseX() - (Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height())) / 2) / (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()) * Panel.getInstance().getCell_size());
                int j = (int) ((float) (Panel.getInstance().getMouseY() - Panel.getInstance().getHeader_height()) / (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()) * Panel.getInstance().getCell_size());
                System.out.println("Координаты фишки " + Panel.getInstance().getActionX() + " " + Panel.getInstance().getActionY());
                System.out.println("Координаты клика " + i + " " + j);
                System.out.println("Цвет фишки" + Panel.getInstance().getField()[i][j]);
                if (!(Math.abs(i - Panel.getInstance().getActionX())==1 && Math.abs(j - Panel.getInstance().getActionY())==1) && Math.abs(i - Panel.getInstance().getActionX())<2 && Math.abs(j - Panel.getInstance().getActionY())<2 && Panel.getInstance().getField()[i][j]==0) {
//                    //Panel.await_cond = false;
                    Panel.getInstance().setField(i,j,Panel.getInstance().getField()[Panel.getInstance().getActionX()][Panel.getInstance().getActionY()]);
                    Panel.getInstance().setField(Panel.getInstance().getActionX(),Panel.getInstance().getActionY(),0);
                    Panel.getInstance().setState(Panel.STATES.PLAY);
                    System.out.println("Выход из состояния движения");
                }
            }
        }

        if (key == KeyEvent.VK_F && (Panel.getInstance().getState().equals(Panel.STATES.PLAY) || Panel.getInstance().getState().equals(Panel.STATES.MOVE))) { //проверка на нажатие правой кнопки мыши  && e.getID()==MouseEvent.MOUSE_PRESSED
            Panel.getInstance().setMouseX((int)Player.getInstance().getX());
            Panel.getInstance().setMouseY((int)Player.getInstance().getY());

            System.out.println("Правый клик мыши!");

            int i = (int) ((float) (Panel.getInstance().getMouseX() - (Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height())) / 2) / (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()) * Panel.getInstance().getCell_size());
            int j = (int) ((float) (Panel.getInstance().getMouseY() - Panel.getInstance().getHeader_height()) / (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()) * Panel.getInstance().getCell_size());

            if (Panel.getInstance().getMouseX()>(Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()))/2 && Panel.getInstance().getMouseX()<(Panel.getInstance().getWIDTH() - (Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()))/2) && Panel.getInstance().getMouseY() > Panel.getInstance().getHeader_height() && Panel.getInstance().getState().equals(Panel.STATES.PLAY) && Panel.getInstance().getField()[i][j]>1) {

                //Panel.await_cond = true;
                Panel.getInstance().setState(Panel.STATES.MOVE);
                System.out.println("Состояние движения");


                Panel.getInstance().setActionX(i);
                Panel.getInstance().setActionY(j);

            } else if (Panel.getInstance().getState().equals(Panel.STATES.MOVE)) {
                Panel.getInstance().setState(Panel.STATES.PLAY);
                System.out.println("Выход из состояния движения");
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

            Panel.getInstance().setMouseX(e.getX());
            Panel.getInstance().setMouseY(e.getY());

            if (Panel.getInstance().getMouseX()>(Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()))/2 && Panel.getInstance().getMouseX()<(Panel.getInstance().getWIDTH() - (Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()))/2) && Panel.getInstance().getState().equals(Panel.STATES.INIT)) {

                int i = (int) ((float) (Panel.getInstance().getMouseX() - (Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height())) / 2) / (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()) * Panel.getInstance().getCell_size());
                if (Panel.getInstance().getMouseY() > Panel.getInstance().getHeader_height()) {
                    int j = (int) ((float) (Panel.getInstance().getMouseY() - Panel.getInstance().getHeader_height()) / (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()) * Panel.getInstance().getCell_size());
                    if (Panel.getInstance().getField()[i][j] == 4) Panel.getInstance().setField(i,j,0);
                    else Panel.getInstance().setField(i,j,Panel.getInstance().getField()[i][j]+1);
                    //repaint();
                } else {
                    if (Panel.getInstance().getHeaderField().containsKey(i)) {
                        if (Panel.getInstance().getHeaderField().get(i) == 3) Panel.getInstance().getHeaderField().put(i, 1);
                        else Panel.getInstance().getHeaderField().put(i, Panel.getInstance().getHeaderField().get(i) + 1);
                    }

                    //repaint();

                }

            } else if (Panel.getInstance().getMouseX()>(Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()))/2 && Panel.getInstance().getMouseX()<(Panel.getInstance().getWIDTH() - (Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()))/2) && Panel.getInstance().getMouseY() > Panel.getInstance().getHeader_height() && Panel.getInstance().getState().equals(Panel.STATES.MOVE)) {
                int i = (int) ((float) (Panel.getInstance().getMouseX() - (Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height())) / 2) / (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()) * Panel.getInstance().getCell_size());
                int j = (int) ((float) (Panel.getInstance().getMouseY() - Panel.getInstance().getHeader_height()) / (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()) * Panel.getInstance().getCell_size());
                System.out.println("Координаты фишки " + Panel.getInstance().getActionX() + " " + Panel.getInstance().getActionY());
                System.out.println("Координаты клика " + i + " " + j);
                System.out.println("Цвет фишки" + Panel.getInstance().getField()[i][j]);
                if (!(Math.abs(i - Panel.getInstance().getActionX())==1 && Math.abs(j - Panel.getInstance().getActionY())==1) && Math.abs(i - Panel.getInstance().getActionX())<2 && Math.abs(j - Panel.getInstance().getActionY())<2 && Panel.getInstance().getField()[i][j]==0) {
//                    //Panel.await_cond = false;
                    Panel.getInstance().setField(i,j,Panel.getInstance().getField()[Panel.getInstance().getActionX()][Panel.getInstance().getActionY()]);
                    Panel.getInstance().setField(Panel.getInstance().getActionX(),Panel.getInstance().getActionY(),0);
                    Panel.getInstance().setState(Panel.STATES.PLAY);
                    System.out.println("Выход из состояния движения");
                }
            }
        }

        if (e.getButton() == MouseEvent.BUTTON3 && (Panel.getInstance().getState().equals(Panel.STATES.PLAY) || Panel.getInstance().getState().equals(Panel.STATES.MOVE))) { //проверка на нажатие правой кнопки мыши  && e.getID()==MouseEvent.MOUSE_PRESSED
            Panel.getInstance().setMouseX(e.getX());
            Panel.getInstance().setMouseY(e.getY());

            System.out.println("Правый клик мыши!");

            int i = (int) ((float) (Panel.getInstance().getMouseX() - (Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height())) / 2) / (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()) * Panel.getInstance().getCell_size());
            int j = (int) ((float) (Panel.getInstance().getMouseY() - Panel.getInstance().getHeader_height()) / (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()) * Panel.getInstance().getCell_size());

            if (Panel.getInstance().getMouseX()>(Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()))/2 && Panel.getInstance().getMouseX()<(Panel.getInstance().getWIDTH() - (Panel.getInstance().getWIDTH() - (Panel.getInstance().getHEIGHT() - Panel.getInstance().getHeader_height()))/2) && Panel.getInstance().getMouseY() > Panel.getInstance().getHeader_height() && Panel.getInstance().getState().equals(Panel.STATES.PLAY) && Panel.getInstance().getField()[i][j]>1) {

                //Panel.await_cond = true;
                Panel.getInstance().setState(Panel.STATES.MOVE);
                System.out.println("Состояние движения");


                Panel.getInstance().setActionX(i);
                Panel.getInstance().setActionY(j);

            } else if (Panel.getInstance().getState().equals(Panel.STATES.MOVE)) {
                Panel.getInstance().setState(Panel.STATES.PLAY);
                System.out.println("Выход из состояния движения");
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
        Panel.getInstance().setMouseX(e.getX());
        Panel.getInstance().setMouseY(e.getY());
    }
}
