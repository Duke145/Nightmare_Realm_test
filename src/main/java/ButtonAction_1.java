import javax.swing.*;
import java.awt.event.ActionEvent;

public class ButtonAction_1 extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        Panel.getInstance().setState(Panel.STATES.INIT);
    }
}
