import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

/**
 * Klasa inicjujaca i rysujaca poziom gry. Rozszerza JPanel i implementuje
 * Runnable.
 */

public class Level extends JPanel implements Runnable {
	/**
	 * Obiekt klasy String przechowujacy sekwencje znakow definiujaca poziom,
	 * wczytana z pliku konfiguracyjnego.
	 */
	private String definitionOfLevel;

	/**
	 * Lista przechowujaca obiekty klasy Wall.
	 */
	public ArrayList<Wall> Walls;

	/**
	 * Lista przechowujaca obiekty klasy Box.
	 */
	public ArrayList<Box> Boxes;

	/**
	 * Lista przechowujaca obiekty klasy Goal.
	 */
	private ArrayList<Goal> Goals;

	/**
	 * Lista przechowujaca obiekty klasy Portal.
	 */
	public ArrayList<Portal> Portals;

	/**
	 * Pole przechowujace ilosc wykonanych ruchow.
	 */
	private int movesCounter = 0;

	/**
	 * Pole typu boolean informujaca czy poziom zostal ukonczony.
	 */
	boolean isCompleted = false;

	/**
	 * Pole przechowujace liczbe zyc gracza.
	 */
	private int numberOfLives;

	/**
	 * Obiekt klasy Player reprezentujacy gracza.
	 */
	Player player;

	/**
	 * Zmienna liczbowa pomocna w animacji miedzy levelami. W przyszlosci
	 * prawdopodobnie zniknie.
	 */
	private int i = 0;
	/**
	 * Zmienna typu boolean sprawdzajaca czy rozgrywka juz sie zaczela.
	 */
	private boolean hasStarted = false;

	/**
	 * Konstrukotr klasy Level; wczytuje definicje poziomu z plikow
	 * konfiguracyjnych.
	 *
	 */
	public Level(int levelID) {
		Config.loadLevelConfig(levelID);
		definitionOfLevel = Config.getLevel();
		numberOfLives = Config.getNumberOfLives();
		initLevel();
		this.setVisible(true);
		new Collision(this);
	}

	/**
	 * Metoda paintComoponent zaimplementowana przez nas. Wywoluje metode
	 * printLevel() rysujaca poziom.
	 * 
	 * @param g - kontekst graficzny typu Graphics.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!hasStarted) {
			betweenAnimation(g);
		} else {
			printLevel(g);
		}
	}

	/**
	 * Metoda inicjalizująca poziom gry. Parsuje definicje poziomu zawarta w pliku
	 * konfiguracyjnym i na jej podstawie tworzy odpowiednie obiekty oraz dodaje je
	 * do kolekcji.
	 */

	private void initLevel() {
		int row = 1;
		int collumn = 1;

		Goal goal;
		Box box;
		Wall wall;
		Portal portal;

		Walls = new ArrayList<>();
		Boxes = new ArrayList<>();
		Goals = new ArrayList<>();
		Portals = new ArrayList<>();

		for (int i = 0; i < definitionOfLevel.length(); i++) {

			char item = definitionOfLevel.charAt(i);

			switch (item) {
				case '\n':
					row++;
					collumn = 0;
					break;

				case '#':
					collumn++;
					wall = new Wall(row, collumn);
					Walls.add(wall);
					break;

				case '$':
					collumn++;
					box = new Box(row, collumn);
					Boxes.add(box);
					break;

				case '.':
					collumn++;
					goal = new Goal(row, collumn);
					Goals.add(goal);
					break;

				case '@':
					collumn++;
					player = new Player(row, collumn);
					break;

				case ' ':
					collumn++;
					break;

				case 'L':
					collumn++;
					portal = new Portal(row, collumn);
					portal.setDirection("LEFT");
					Portals.add(portal);
					break;

				case 'R':
					collumn++;
					portal = new Portal(row, collumn);
					portal.setDirection("RIGHT");
					Portals.add(portal);
					break;

				case 'U':
					collumn++;
					portal = new Portal(row, collumn);
					portal.setDirection("UP");
					Portals.add(portal);
					break;

				case 'D':
					collumn++;
					portal = new Portal(row, collumn);
					portal.setDirection("DOWN");
					Portals.add(portal);
					break;

				default:
					break;
			}

		}
	}

	/**
	 * Metoda zawierajaca instrukcje dotyczace rysowania obiektow znajdujacych sie w
	 * zainicjalizowanym poziomie.
	 * 
	 * @param g - kontekst graficzny typu Graphics.
	 */

	private void printLevel(Graphics g) {
		new KeyBindings(this);
		ArrayList<Element> level = new ArrayList<>();
		int heightOfElement = this.getHeight() / (this.getHeightOfBoard() + this.getWidth() / 100);
		int widthOfElement = this.getWidth() / (this.getWidthOfBoard() + this.getHeight() / 100);
		level.addAll(Walls);
		level.addAll(Boxes);
		level.addAll(Goals);
		level.addAll(Portals);
		level.add(player);

		for (int i = 0; i < level.size(); i++) {

			Element item = level.get(i);

			if (item instanceof Wall) {
				Wall wall = (Wall) level.get(i);
				wall.setX(widthOfElement * wall.getCollumn());
				wall.setY(heightOfElement * wall.getRow());
				wall.setDimension(widthOfElement, heightOfElement);
				wall.drawWall(g);
			} else if (item instanceof Box) {
				Box box = (Box) level.get(i);
				box.setX(widthOfElement * box.getCollumn());
				box.setY(heightOfElement * box.getRow());
				box.setDimension(widthOfElement, heightOfElement);
				box.drawBox(g);
			} else if (item instanceof Player) {
				Player player = (Player) level.get(i);
				player.setX(widthOfElement * player.getCollumn());
				player.setY(heightOfElement * player.getRow());
				player.setDimension(widthOfElement, heightOfElement);
				player.drawPlayer(g);
			} else if (item instanceof Goal) {
				Goal goal = (Goal) level.get(i);
				goal.setX(widthOfElement * goal.getCollumn());
				goal.setY(heightOfElement * goal.getRow());
				goal.setDimension(widthOfElement, heightOfElement);
				goal.drawGoal(g);
			} else if (item instanceof Portal) {
				Portal portal = (Portal) level.get(i);
				portal.setX(widthOfElement * portal.getCollumn());
				portal.setY(heightOfElement * portal.getRow());
				portal.setDimension(widthOfElement, heightOfElement);
				portal.drawPortal(g);
			}

		}
	}

	/**
	 * Metoda zwracajaca ilosc rzedow w danym poziomie.
	 * 
	 * @return ilosc rzedow planszy danego poziomu.
	 */
	private int getHeightOfBoard() {
		int height = 0;
		for (int i = 0; i < definitionOfLevel.length(); i++) {
			if (definitionOfLevel.charAt(i) == '\n')
				height++;
		}
		return height;
	}

	/**
	 * Metoda zwracajaca ilosc kolumn w danym poziomie.
	 * 
	 * @return ilosc kolumn planszy danego poziomu.
	 */
	private int getWidthOfBoard() {
		int width = 0;
		String[] rows = definitionOfLevel.split("\n");

		for (String row : rows) {
			if (row.length() > width) {
				width = row.length();
			}
		}
		return width;
	}

	/**
	 * Metoda restartujaca poziom.
	 */
	public void restart() {
		Walls.clear();
		Boxes.clear();
		Goals.clear();
		numberOfLives--;
		initLevel();
	}

	/**
	 * Metoda sprawdzajaca czy poziom zostal ukonczony.
	 */
	public void isCompleted() {
		int correctBoxes = 0;
		for (int i = 0; i < Boxes.size(); i++) {
			Box box = Boxes.get(i);
			for (int j = 0; j < Goals.size(); j++) {
				Goal goal = Goals.get(j);
				if (box.getRow() == goal.getRow() && box.getCollumn() == goal.getCollumn()) {
					correctBoxes++;
				}
			}
		}
		if (correctBoxes == Boxes.size()) {
			isCompleted = true;
			// repaint();
		}
	}

	/**
	 * Metoda zliczajaca liczbe ruchow.
	 */
	public void countMove() {
		movesCounter++;
	}

	/**
	 * Getter dla zmiennej movesCounter.
	 * 
	 * @return zwraca liczbe wykonanych ruchow.
	 */
	public int getMovesCounter() {
		return movesCounter;
	}

	/**
	 * Getter dla zmiennej numberOfLives.
	 * 
	 * @return zwraca liczbe zyc.
	 */
	public int getNumberOfLives() {
		return numberOfLives;
	}

	/**
	 * Metoda odpowiedzialna za animacje pomiedzy poziomami.
	 * 
	 * @param g
	 */
	private void betweenAnimation(Graphics g) {
		int widthOfElement = this.getWidth() / 20;
		int heightOfElement = this.getHeight() / 20;
		ArrayList<Wall> Animation = new ArrayList<>();

		for (int a = 0; a < i; a++) {
			for (int b = 0; b < i; b++) {
				Animation.add(new Wall(a, b));
			}
		}

		for (Wall wall : Animation) {
			Random rand = new Random();
			wall.setX(widthOfElement * wall.getCollumn());
			wall.setY(heightOfElement * wall.getRow());
			wall.setDimension(widthOfElement, heightOfElement);
			wall.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
			wall.drawWall(g);
		}
	}

	/**
	 * Metoda run dla klasy Level.
	 */
	@Override
	public void run() {
		while (true) {
			if (i < 20) {
				i++;
			} else {
				hasStarted = true;
			}
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}
