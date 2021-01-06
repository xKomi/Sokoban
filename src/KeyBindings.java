import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

/**
 * Klasa odpowiedzialna za obsluge klawiatury.
 */
public class KeyBindings {
    private ActionMap actionMap;
    private InputMap inputMap;
    private int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;;
    String vkLeft = "VK_LEFT";
    String vkRight = "VK_RIGHT";
    String vkUp = "VK_UP";
    String vkDown = "VK_DOWN";
    String vkSpace = "VK_SPACE";
    String r = "VK_R";
    Level level;

    public KeyBindings(Level level) {
        this.level = level;
        setActionMap(level);
        setInputMap(level);
    }

    private void setActionMap(Level level) {
        actionMap = level.getActionMap();
        actionMap.put(vkLeft, new KeyAction(vkLeft));
        actionMap.put(vkRight, new KeyAction(vkRight));
        actionMap.put(vkUp, new KeyAction(vkUp));
        actionMap.put(vkDown, new KeyAction(vkDown));
        actionMap.put(vkSpace, new KeyAction(vkSpace));
        actionMap.put(r, new KeyAction(r));
    }

    private void setInputMap(Level level) {
        inputMap = level.getInputMap(condition);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), vkLeft);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), vkRight);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), vkUp);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), vkDown);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), vkSpace);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), r);
    }

    private class KeyAction extends AbstractAction {
        public KeyAction(String actionCommand) {
            putValue(ACTION_COMMAND_KEY, actionCommand);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println(actionEvent.getActionCommand() + " pressed");

            if (actionEvent.getActionCommand() == "VK_SPACE") {
                System.out.println("Ilosc ruchow: " + level.getMovesCounter());
            }
            if (actionEvent.getActionCommand() == "VK_LEFT") {
                if (Collision.checkWallCollision(level.player, "LEFT")) {
                    return;
                }
                if (Collision.checkBoxCollision("LEFT")) {
                    return;
                }
                if (Collision.checkPortalCollision(level.player, "LEFT")) {
                    return;
                }
                level.player.movePlayer(actionEvent.getActionCommand());
                level.countMove();
            }
            if (actionEvent.getActionCommand() == "VK_RIGHT") {
                if (Collision.checkWallCollision(level.player, "RIGHT")) {
                    return;
                }
                if (Collision.checkBoxCollision("RIGHT")) {
                    return;
                }
                if (Collision.checkPortalCollision(level.player, "RIGHT")) {
                    return;
                }
                level.player.movePlayer(actionEvent.getActionCommand());
                level.countMove();
            }
            if (actionEvent.getActionCommand() == "VK_UP") {
                if (Collision.checkWallCollision(level.player, "UP")) {
                    return;
                }
                if (Collision.checkBoxCollision("UP")) {
                    return;
                }
                if (Collision.checkPortalCollision(level.player, "UP")) {
                    return;
                }
                level.player.movePlayer(actionEvent.getActionCommand());
                level.countMove();
            }
            if (actionEvent.getActionCommand() == "VK_DOWN") {
                if (Collision.checkWallCollision(level.player, "DOWN")) {
                    return;
                }
                if (Collision.checkBoxCollision("DOWN")) {
                    return;
                }
                if (Collision.checkPortalCollision(level.player, "DOWN")) {
                    return;
                }
                level.player.movePlayer(actionEvent.getActionCommand());
                level.countMove();
            }
            if (actionEvent.getActionCommand() == "VK_R") {
                level.restart();
            }
        }
    }
}