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
        g2d.setColor(Color.BLACK);
        g2d.fillOval(this.getX(), this.getY(), this.getWidthOfElement(), this.getHeightOfElement());
        g2d.setColor(Color.BLACK);
        g2d.drawOval(this.getX(), this.getY(), this.getWidthOfElement(), this.getHeightOfElement());
    }

    /**
     * Metoda przesuwajaca pudelko we wskazanym kierunku.
     * @param direction obiekt klasy String mowiacy, w ktorym kierunku pudelko sie przesuwa (lewo, prawo, gora, dol).
     */
    void moveBox(String direction){
        if(direction == "LEFT"){
            setCollumn(getCollumn()-1);
        }
        if(direction == "RIGHT"){
            setCollumn(getCollumn()+1);
        }
        if(direction == "UP"){
            setRow(getRow()-1);
        }
        if(direction == "DOWN"){
            setRow(getRow()+1);
        }
    }
}
