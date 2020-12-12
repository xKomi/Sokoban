package sokoban;

/**
 * Klasa bedaca klasa bazowa dla wszystkich elementow znajdujacych sie na
 * planszy.
 */
public class Element {
    /**
     * Zmienna liczbowa informujaca, w ktorym rzedzie znajduje sie dany element.
     */
    private int width = 0;

    /**
     * Zmienna liczbowa informujaca, w ktorej kolumnie znajduje sie dany element.
     */
    private int height = 0;

    /**
     * Zmienna liczbowa reprezentujaca polozenie elementu w ukladzie kartezjanskim w
     * osi odcietych.
     */
    private int x;

    /**
     * Zmienna liczbowa reprezentujaca polozenie elementu w ukladzie kartezjankim w
     * osi rzednych.
     */
    private int y;

    /**
     * Zmienna liczbowa reprezentujaca indeks rzedu planszy, w ktorym znajduje sie
     * dany element.
     */
    private int row;

    /**
     * Zmienna liczbowa reprezentujaca indeks kolumny, w ktorej znajduje sie dany
     * element.
     */
    private int collumn;

    /**
     * Konstruktor klasy Element.
     * 
     * @param row     - zmienna liczbowa informujaca, w ktorym rzedzie znajduje sie
     *                dany element.
     * @param collumn - zmienna liczbowa informujaca, w ktorej kolumnie znajduje sie
     *                dany element.
     */
    public Element(int row, int collumn) {
        this.row = row;
        this.collumn = collumn;
    }

    /**
     * Getter dla zmiennej liczbowej row.
     * 
     * @return indeks rzedu, w ktorym znajduje sie element.
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter dla zmiennej liczbowej collumn.
     * 
     * @return indeks kolumny, w ktorej znajduje sie dany element.
     */
    public int getCollumn() {
        return collumn;
    }

    /**
     * Getter dla zmiennej x.
     * 
     * @return aktualne polozenie elementu w osi OX.
     */
    public int getX() {
        return x;
    }

    /**
     * Getter dla zmiennej y.
     * 
     * @return aktualne polozenie elementu w osi OY.
     */
    public int getY() {
        return y;
    }

    /**
     * Setter dla zmiennej x.
     * 
     * @param x - ustawione polozenie elementu w osi OX.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter dla zmiennej y.
     * 
     * @param y - ustawione polozenie elementu w osi OY.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Metoda zwracajaca szerokosc elementu.
     * 
     * @return zmienna liczbowa reprezentujaca szerokosc elementu (dlugosc boku).
     */
    public int getWidthOfElement() {
        return width;
    }

    /**
     * Metoda zwracajaca wysokosc elementu.
     * 
     * @return zmienna liczbowa reprezentujaca wysokosc elementu (dlugosc boku).
     */
    public int getHeightOfElement() {
        return height;
    }

    /**
     * Setter ustawiajacy wymiary elementu.
     * 
     * @param width  - zmienna liczbowa reprezentujaca szerokosc elementu.
     * @param height - zmienna liczbowa reprezentujaca wysokosc elementu.
     */
    public void setDimension(int width, int height) {
        this.width = width;
        this.height = height;
    }
}