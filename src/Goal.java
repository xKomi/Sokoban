import java.awt.*;
import java.awt.Graphics2D;

/**
 * Klasa reprezentujaca pole, na ktorym nalezy umiescic paczke. Dziedziczy po
 * klasie Element.
 */
public class Goal extends Element {
    public Goal(int row, int collumn) {
        super(row, collumn);
    }

    /**
     * Metoda odpowiedzialna za rysowanie obiektu Goal.
     * 
     * @param g kontekst graficzny klasy Graphics.
     */
    protected void drawGoal(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int xpoints[] = { this.getX() + this.getWidthOfElement() / 2, this.getX(),
                this.getX() + this.getWidthOfElement() / 2, this.getX() + this.getWidthOfElement() };
        int ypoints[] = { this.getY(), this.getY() + this.getHeightOfElement() / 2,
                this.getY() + this.getHeightOfElement(), this.getY() + this.getHeightOfElement() / 2 };
        Shape diamond = new Polygon(xpoints, ypoints, 4);
        g2d.setColor(Color.GRAY);
        g2d.fill(diamond);
        g2d.setColor(Color.BLACK);
        g2d.draw(diamond);
    }

}
