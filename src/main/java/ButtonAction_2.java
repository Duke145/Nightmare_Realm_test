import javax.swing.*;
import java.awt.event.ActionEvent;

public class ButtonAction_2 extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        Panel.getInstance().setState(Panel.STATES.PLAY);
    }
}
