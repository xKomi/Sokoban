import java.awt.*;
import java.awt.Graphics2D;

/**
 * Klasa reprezentujaca portal teleportujacy gracza lub paczke. Dziedziczy po klasie Element.
 */
public class Portal extends Element {
    /**
     * Pole typu String informujace w ktora strone skierowany jest portal (lewo, prawo, gora, dol).
     */
    private String direction;

    public Portal(int row, int collumn) {
        super(row, collumn);
    }

    /**
     * Metoda odpowiedzialna za rysowanie obiektu Portal.
     * @param g kontekst graficzny klasy Graphics.
     */
    public void drawPortal(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.fillRect(this.getX(), this.getY(), this.getWidthOfElement(), this.getHeightOfElement());
        g2d.setColor(Color.BLACK);
        g2d.drawRect(this.getX(), this.getY(), this.getWidthOfElement(), this.getHeightOfElement());
        g2d.setColor(Color.ORANGE);
        if (direction == "LEFT") {
            for (int i = 0; i < this.getWidthOfElement() / 10; i++) {
                g2d.drawLine(this.getX() + this.getWidthOfElement() - i, this.getY(),
                        this.getX() + this.getWidthOfElement() - i, this.getY() + this.getHeightOfElement());
            }
        } else if (direction == "RIGHT") {
            for (int i = 0; i < this.getWidthOfElement() / 10; i++) {
                g2d.drawLine(this.getX() + i, this.getY(), this.getX() + i, this.getY() + this.getHeightOfElement());
            }
        } else if (direction == "UP") {
            for (int i = 0; i < this.getHeightOfElement() / 10; i++) {
                g2d.drawLine(this.getX(), this.getY() + this.getHeightOfElement() - i,
                        this.getX() + this.getWidthOfElement(), this.getY() + this.getHeightOfElement() - i);
            }
        } else if (direction == "DOWN") {
            for (int i = 0; i < this.getHeightOfElement() / 10; i++) {
                g2d.drawLine(this.getX(), this.getY() + i, this.getX() + this.getWidthOfElement(), this.getY() + i);
            }
        }
    }

    /**
     * Metoda ustawiajaca kierunek portalu.
     * @param direction - zmienna oznaczajaca w ktora strone skierowany jest portal (lewo, prawo, gora ,dol).
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Metoda zwracajaca pole direction.
     * @return Zwraca pole direction informujace w ktora strone skierowany jest portal (lewo, prawo, gora, dol).
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Metoda odpowiedzialna za przenoszenie elementu z jednego portalu do drugiego.
     * @param exit Obiekt klasy portal; docelowe miejsce teleportacji.
     * @param element Obiekt teleportowany do exit.
     */
    public void teleport(Portal exit, Element element){
        if (exit.getDirection() == "LEFT") {
            element.setCollumn(exit.getCollumn() + 1);
            element.setRow(exit.getRow());
        } else if (exit.getDirection() == "RIGHT") {
            element.setCollumn(exit.getCollumn() - 1);
            element.setRow(exit.getRow());
        } else if (exit.getDirection() == "UP") {
            element.setCollumn(exit.getCollumn());
            element.setRow(exit.getRow() + 1);
        } else if (exit.getDirection() == "DOWN") {
            element.setCollumn(exit.getCollumn());
            element.setRow(exit.getRow() - 1);
        }
    }

}
