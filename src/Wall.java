import java.awt.*;
import java.awt.Graphics2D;

/**
 * Klasa przedstawiajaca sciane poziomu.
 */
public class Wall extends Element {
    /**
     * Obiekt klasy Color - kolor sciany.
     */
    Color color = Color.RED;

    public Wall(int row, int collumn) {
        super(row, collumn);
    }

    /**
     * Metoda odpowiedzialna za rysowanie sciany.
     * 
     * @param g - kontekst graficzny klasy Graphics.
     */
    protected void drawWall(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.fillRect(this.getX(), this.getY(), this.getWidthOfElement(), this.getHeightOfElement());
        g2d.setColor(Color.BLACK);
        g2d.drawRect(this.getX(), this.getY(), this.getWidthOfElement(), this.getHeightOfElement());
    }

    /**
     * Metoda zmieniajaca kolor sciany. W przyszlosci mozliwe ze zniknie.
     * 
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

}
