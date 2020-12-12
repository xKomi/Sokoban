package sokoban;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Klasa odpowiedzialna za wczytywanie ustawien i poziomow z plikow
 * konfiguracyjnych.
 */
public class Config {
    /**
     * Domyslna szerokosc okna glownego wczytana z pliku konfiguracyjnego.
     */
    static private int width;

    /**
     * Domyslna wysokosc okna glownego wczytana z pliku konfiguracyjnego.
     */
    static private int height;

    /**
     * Pole reprezentujace ilosc zyc wczytana z pliku konfiguracyjnego.
     */
    static private int numberOfLives;

    /**
     * Definicja poziomu przedstawiona za pomoca sekwencji znakow, wczytana z pliku
     * konfiguracyjnego.
     */
    static private String Level;

    /**
     * Metoda wczytujaca ustawiena z pliku konfiguracyjnego. Do ustawien naleza:
     * rozmiar okna, ilosc zyc itp.
     */
    static void loadConfig() {
        try (InputStream configFile = new FileInputStream("Config.txt");) {
            Properties properties = new Properties();
            properties.load(configFile);
            width = Integer.parseInt(properties.getProperty("Width"));
            height = Integer.parseInt(properties.getProperty("Height"));
            numberOfLives = Integer.parseInt(properties.getProperty("NumberOfLives"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda wczytujaca definicje poziomu z pliku konfiguracyjnego.
     */
    static void loadLevelConfig() {
        try (InputStream levelFile = new FileInputStream("Level.txt");) {
            Properties properties = new Properties();
            properties.load(levelFile);
            Level = properties.getProperty("LEVEL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter dla zmiennej width wczytanej z pliku konfiguracyjnego.
     * 
     * @return zmienna liczbowa definiujaca szerokosc okna glownego.
     */
    public static int getWidth() {
        return width;
    }

    /**
     * Getter dla zmiennej height wczytanej z pliku konfiguracyjnego.
     * 
     * @return zmienna liczbowa definiujaca wysokosc okna glownego.
     */
    public static int getHeight() {
        return height;
    }

    /**
     * Getter dla zmiennej Level wczytanej z pliku konfiguracyjnego.
     * @return reprezentacja poziomu w postaci ciagu znakow.
     */
    public static String getLevel() {
        return Level;
    }

}
