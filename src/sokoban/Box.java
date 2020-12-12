package sokoban;

import java.awt.*;
import java.awt.Graphics2D;

/**
 * Klasa przedstawiajaca paczke przesuwana przez gracza.
 */
public class Box extends Element {
    public Box(int row, int collumn) {
        super(row, collumn);
    }

    /**
     * Metoda odpowiedzialna za rysowanie paczki.
     * 
     * @param g - kontekst graficzny klasy Graphics.
     */
    protected void drawBox(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.fillOval(this.getX(), this.getY(), this.getWidthOfElement(), this.getHeightOfElement());
        g2d.setColor(Color.BLACK);
        g2d.drawOval(this.getX(), this.getY(), this.getWidthOfElement(), this.getHeightOfElement());
    }

}
