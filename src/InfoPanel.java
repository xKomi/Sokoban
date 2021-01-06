import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Klasa odpowiedzialna za wyswietlanie informacji w trakcie rozgrywki.
 * Dziedziczy po JPanel, implementuje Runnable.
 */
public class InfoPanel extends JPanel implements Runnable {
    JLabel movesLabel;
    JLabel livesLabel;
    JLabel levelLabel;
    Level level;

    /**
     * Konstruktor klasy InfoPanel. Tworzy nowe JLabel i dodaje do panelu.
     * 
     * @param currentLevel zmienna liczbowa przedstawiajaca numer obecnego poziomu.
     */
    public InfoPanel(int currentLevel) {
        movesLabel = new JLabel();
        livesLabel = new JLabel();
        levelLabel = new JLabel("Current level: " + Integer.toString(currentLevel));
        this.add(movesLabel);
        this.add(livesLabel);
        this.add(levelLabel);
        this.setBackground(Color.CYAN);
    }

    /**
     * Metpda odpowiedzialna za uaktualnianie labeli.
     * 
     * @param level obiekt klasy Level.
     */
    public void updateInfoPanel(Level level) {
        movesLabel.setText("Moves: " + Integer.toString(level.getMovesCounter()));
        livesLabel.setText("Lives: " + Integer.toString(level.getNumberOfLives()));
    }

    /**
     * Setter dla levelu.
     * 
     * @param level obiekt klasy Level.
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Metoda run klasy InfoPanel.
     */
    @Override
    public void run() {
        while (true) {
            updateInfoPanel(level);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}