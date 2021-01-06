import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Klasa opdowiedzialna za wyswietlanie instrukcji. Dopiero w trakcie tworzenia.
 */
import java.awt.Graphics;

public class InstructionMenu extends JPanel implements Runnable {
    private int page;

    public InstructionMenu() {
        initInstructionMenu();
        this.setVisible(true);
        this.setFocusable(true);
        page = 0;
    }

    public void initInstructionMenu() {
        JButton nextButton = new JButton("Next");
        this.add(nextButton);
        JButton previousButton = new JButton("Previous");
        this.add(previousButton);
    }

    @Override
    public void paintComponent(Graphics g) {

    }

    @Override
    public void run() {
        repaint();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
