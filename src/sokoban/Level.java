package sokoban;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * Klasa inicjujaca i rysujaca poziom gry.
 */

public class Level extends JPanel {
	/**
	 * Obiekt klasy String przechowujacy sekwencje znakow definiujaca poziom,
	 * wczytana z pliku konfiguracyjnego.
	 */
	private String definitionOfLevel;

	/**
	 * Lista przechowujaca obiekty klasy Wall.
	 */
	private ArrayList<Wall> Walls;

	/**
	 * Lista przechowujaca obiekty klasy Box.
	 */
	private ArrayList<Box> Boxes;

	/**
	 * Lista przechowujaca obiekty klasy Goal.
	 */
	private ArrayList<Element> Goals;


	/**
	 * Obiekt klasy Player reprezentujacy gracza.
	 */
	Player player;

	/**
	 * Konstrukotr klasy Level; wczytuje definicje poziomu z plikow
	 * konfiguracyjnych.
	 *
	 */
	public Level() {
		Config.loadLevelConfig();
		definitionOfLevel = Config.getLevel();
		initLevel();
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
		printLevel(g);
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

		Walls = new ArrayList<>();
		Boxes = new ArrayList<>();
		Goals = new ArrayList<>();

		for (int i = 0; i < definitionOfLevel.length(); i++) {

			char item = definitionOfLevel.charAt(i);

			switch (item) {
				case '\n':
					row++;
					collumn = 0;
					break;

				case '#':
					collumn++;
					wall = new Wall(collumn, row);
					Walls.add(wall);
					break;

				case '$':
					collumn++;
					box = new Box(collumn, row);
					Boxes.add(box);
					break;

				case '.':
					collumn++;
					goal = new Goal(collumn, row);
					Goals.add(goal);
					break;

				case '@':
					collumn++;
					player = new Player(collumn, row);
					break;

				case ' ':
					collumn++;
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

		ArrayList<Element> level = new ArrayList<>();
		int heightOfElement = this.getHeight() / (this.getHeightOfBoard() + this.getWidth() / 100);
		int widthOfElement = this.getWidth() / (this.getWidthOfBoard() + this.getHeight() / 100);
		level.addAll(Walls);
		level.addAll(Boxes);
		level.addAll(Goals);
		level.add(player);

		for (int i = 0; i < level.size(); i++) {

			Element item = level.get(i);

			if (item instanceof Wall) {
				Wall wall = (Wall) level.get(i);
				wall.setX(widthOfElement * wall.getRow());
				wall.setY(heightOfElement * wall.getCollumn());
				wall.setDimension(widthOfElement, heightOfElement);
				wall.drawWall(g);
			} else if (item instanceof Box) {
				Box box = (Box) level.get(i);
				box.setX(widthOfElement * box.getRow());
				box.setY(heightOfElement * box.getCollumn());
				box.setDimension(widthOfElement, heightOfElement);
				box.drawBox(g);
			} else if (item instanceof Player) {
				Player player = (Player) level.get(i);
				player.setX(widthOfElement * player.getRow());
				player.setY(heightOfElement * player.getCollumn());
				player.setDimension(widthOfElement, heightOfElement);
				player.drawPlayer(g);
			} else if (item instanceof Goal) {
				Goal goal = (Goal) level.get(i);
				goal.setX(widthOfElement * goal.getRow());
				goal.setY(heightOfElement * goal.getCollumn());
				goal.setDimension(widthOfElement, heightOfElement);
				goal.drawGoal(g);
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
}
