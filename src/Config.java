import java.util.ArrayList;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
     * Pole reprezentujace liczbe poziomow dostepnych w grze.
     */
    static private int numberOfLevels;

    /**
     * Definicja poziomu przedstawiona za pomoca sekwencji znakow, wczytana z pliku
     * konfiguracyjnego.
     */
    static private String Level;

    /**
     * Lista przechowujaca najlepsze wyniki.
     */
    static private ArrayList<String> scoreList = new ArrayList<>();

    /**
     * Liczba wczytywanych i zapisywanych najlepszych wynikow.
     */
    static private int numberOfScores = 10;

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
            numberOfLevels = Integer.parseInt(properties.getProperty("NumberOfLevels"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda wczytujaca definicje poziomu z pliku konfiguracyjnego.
     */
    static void loadLevelConfig(int levelID) {
        try (InputStream levelFile = new FileInputStream("Level.txt");) {
            Properties properties = new Properties();
            properties.load(levelFile);
            Level = properties.getProperty("LEVEL" + levelID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda wczytujaca z pliku liste najlepszych wynikow.
     */
    static void loadScoreList() {
        try (InputStream scoreFile = new FileInputStream("ScoreList.txt");) {
            Properties properties = new Properties();
            properties.load(scoreFile);
            for (int i = 0; i < numberOfScores; i++) {
                if(properties.getProperty("SCORE" + Integer.toString(i + 1)).length() >0);{
                scoreList.add(properties.getProperty("SCORE" + Integer.toString(i + 1)));
                //System.out.println("laodscorelist if " + i + properties.getProperty("SCORE" + Integer.toString(i + 1)));
                //TODO: DO POPRAWY
                }
            }
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
     * 
     * @return reprezentacja poziomu w postaci ciagu znakow.
     */
    public static String getLevel() {
        return Level;
    }

    /**
     * Getter dla zmiennej numberOfLevels.
     * @return liczba dostepnych poziomow.
     */
    public static int getNumberOfLevels() {
        return numberOfLevels;
    }

    /**
     * Getter dla zmiennej numberOfLives.
     * @return domyslna liczba zyc.
     */
    public static int getNumberOfLives() {
        return numberOfLives;
    }

    /**
     * Metoda zapisujaca liste najlepszych wynikow do pliku.
     */
    static void saveScoreList() {
        try (OutputStream scoreFile = new FileOutputStream("ScoreList.txt");) {
            Properties properties = new Properties();
            for (int i = 0; i < numberOfScores; i++) {
                properties.setProperty("SCORE" + Integer.toString(i+1), scoreList.get(i));
            }
            properties.store(scoreFile, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda sprawdzajaca czy wynik gracza powinien znalezc sie na liscie najlepszych wynikow i ewentualnie dodajaca ten wynik.
     * @param newScore wynik osiagniety w ostatniej grze.
     */
    static void addScore(String newScore) {
        String[] newScoreSplitted = newScore.split(" ");
        int newScoreValue = Integer.parseInt(newScoreSplitted[1]);
        int i = 0;
        for (String score : scoreList) {
            String[] temp = score.split(" ");
            System.out.println("addscore " + i);
            if (newScoreValue > Integer.parseInt(temp[1])) {
                scoreList.add(i, newScore);
                break;
            }
            i++;
        }
    }
    
    /**
     * Getter dla listy wynikow.
     * @return lista wynikow.
     */
    static ArrayList<String> getScoreList() {
        return scoreList;
    }

}


