import java.awt.*;
import java.awt.Graphics2D;

/**
 * Klasa reprezentujaca gracza.
 */
public class Player extends Element {
    public Player(int row, int collumn) {
        super(row, collumn);
    }

    /**
     * Metoda odpowiedzialna za rysowanie gracza
     * 
     * @param g - kontekst graficzny klasy Graphics.
     */
    protected void drawPlayer(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        g2d.fillOval(this.getX(), this.getY(), this.getWidthOfElement(), this.getHeightOfElement());
        g2d.setColor(Color.BLACK);
        g2d.drawOval(this.getX(), this.getY(), this.getWidthOfElement(), this.getHeightOfElement());
    }

    /**
     * Metoda przesuwajaca gracza we wskazanym kierunku.
     * 
     * @param direction obiekt klasy String informujacy w ktorym kierunku gracz sie
     *                  porusza.
     */
    void movePlayer(String direction) {
        if (direction == "VK_LEFT") {
            setCollumn(getCollumn() - 1);
        }
        if (direction == "VK_RIGHT") {
            setCollumn(getCollumn() + 1);
        }
        if (direction == "VK_UP") {
            setRow(getRow() - 1);
        }
        if (direction == "VK_DOWN") {
            setRow(getRow() + 1);
        }
    }
}
