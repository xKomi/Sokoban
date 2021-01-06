import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.image.BufferedImage;

/**
 * Klasa odpowiedzialna za glowne okno programu. Dziedziczy po JFrame.
 */
public class MainFrame extends JFrame implements Runnable {
	/**
	 * Pole przechowujace nick gracza.
	 */
	private String name;

	/**
	 * Obiekt klasy Level wczytywany do okna glownego.
	 */
	private Level level;

	/**
	 * Zmienna liczbowa oznaczajaca numer aktualnego levelu.
	 */
	private int currentLevel = 1;

	/**
	 * Zmienna liczbowa przechowujaca wynik punktowy gracza.
	 */
	private int score = 0;

	/**
	 * Konstruktor klasy MainFrame wczytujacy rozmiar okna z pliku konfiguracyjnego.
	 */
	public MainFrame() {
		super("SOKOBAN");
		this.setSize(Config.getWidth(), Config.getHeight());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		mainMenu();
		setVisible(true);

	}

	/**
	 * Menu glowne, wyswietlane po uruchomieniu aplikacji, zakonczeniu gry lub po
	 * prostu powrotu do menu; domyslny widok programu.
	 */
	private void mainMenu() {
		this.getContentPane().removeAll();
		this.getContentPane().setBackground(Color.WHITE);
		this.setLayout(new FlowLayout());
		JPanel panelHelper = new JPanel();
		panelHelper.setLayout(new BoxLayout(panelHelper, BoxLayout.PAGE_AXIS));
		panelHelper.setBackground(Color.WHITE);

		menuBar();

		ImageIcon imageIcon = new ImageIcon("Title.jpg");
		imageIcon.setImage(getScaledImage(imageIcon.getImage(), this.getWidth() / 3, this.getHeight() / 3));
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setAlignmentX(CENTER_ALIGNMENT);
		panelHelper.add(imageLabel);

		JLabel nameLabel = new JLabel("Enter name:");
		nameLabel.setAlignmentX(CENTER_ALIGNMENT);
		panelHelper.add(nameLabel);

		JTextField nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(75, 24));
		nameField.setAlignmentX(CENTER_ALIGNMENT);
		panelHelper.add(nameField);

		JButton playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (nameField.getText().length() == 0) {
					showMessageDialog(null, "Please enter your name!");
				} else {
					reset();
					loadLevel(currentLevel);
					name = nameField.getText();
				}
			}
		});
		playButton.setAlignmentX(CENTER_ALIGNMENT);
		panelHelper.add(playButton);

		JButton scoreListButton = new JButton("Best scores");
		scoreListButton.addActionListener(event -> this.scoreListMenu());
		scoreListButton.setAlignmentX(CENTER_ALIGNMENT);
		panelHelper.add(scoreListButton);

		JButton endButton = new JButton("Exit");
		endButton.addActionListener(event -> this.dispose());
		endButton.setAlignmentX(CENTER_ALIGNMENT);
		panelHelper.add(endButton);

		this.add(panelHelper);
		revalidate();
		repaint();
	}

	/**
	 * Metoda przeskalowywujaca ikone w grze.
	 * @param srcImg obiekt klasy Image - obraz do przeskalowania.
	 * @param w szerokosc obrazka.
	 * @param h wysokosc obrazka.
	 * @return przeskalowany obraz.
	 */

	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

	/**
	 * Menu rozwijane z paska, dostępne w każdym widoku aplikacji. Pozwala na powrot
	 * do menu glownego, wejscie w ustawienia gry lub sprawdzenie instrukcji.
	 */
	private void menuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem settings, instruction, mainMenu;

		settings = new JMenuItem("Settings");
		settings.addActionListener(Event -> this.settingMenu());
		menu.add(settings);

		instruction = new JMenuItem("Instruction");
		instruction.addActionListener(Event -> this.instructionMenu());
		menu.add(instruction);

		mainMenu = new JMenuItem("Main Menu");
		mainMenu.addActionListener(Event -> this.mainMenu());
		menu.add(mainMenu);

		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}

	/**
	 * Metoda odpowiedzialna za wyswietlanie instrukcji gry.
	 */
	private void instructionMenu() {
		this.getContentPane().removeAll();
		this.setLayout(new GridBagLayout());

		JButton returnButton = new JButton("Back");
		returnButton.addActionListener(event -> this.mainMenu());
		this.add(returnButton);

		revalidate();
		repaint();
	}

	/**
	 * Menu ustawien gry.
	 */

	private void settingMenu() {
		this.getContentPane().removeAll();
		this.setLayout(new GridBagLayout());

		JButton returnButton = new JButton("Back");
		returnButton.addActionListener(event -> this.mainMenu());
		this.add(returnButton);

		revalidate();
		repaint();
	}

	/**
	 * Menu najlepszych wynikow.
	 */
	private void scoreListMenu() {
		this.getContentPane().removeAll();
		this.setLayout(new FlowLayout());
		JPanel helperPanel = new JPanel();
		helperPanel.setLayout(new BoxLayout(helperPanel, BoxLayout.PAGE_AXIS));
		helperPanel.setBackground(Color.WHITE);
		JLabel scoreLabel;
		int i = 1;
		for (String score : Config.getScoreList()) {
			scoreLabel = new JLabel(Integer.toString(i) + ": " + score);
			scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
			helperPanel.add(scoreLabel);
			i++;
		}

		JButton returnButton = new JButton("Back");
		returnButton.addActionListener(event -> this.mainMenu());
		returnButton.setAlignmentX(CENTER_ALIGNMENT);
		helperPanel.add(returnButton);

		this.add(helperPanel);
		revalidate();
		repaint();
	}

	/**
	 * Metoda odpowiedzialna za wyskakujace okno po przejsciu poziomu.
	 */

	private void endLevelPopUp() {
		JFrame frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		JButton okButton = new JButton("Next level");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadLevel(currentLevel);
				frame.dispose();
			}
		});
		if (frame.isDisplayable()) {
			System.out.println("null");
		}
		constraints.gridx = 1;
		constraints.gridy = 2;
		frame.add(okButton, constraints);

		JLabel praiseLabel = new JLabel("Congratulation! You completed level " + Integer.toString(currentLevel - 1));
		constraints.gridx = 1;
		constraints.gridy = 0;
		frame.add(praiseLabel, constraints);

		JLabel pointsLabel = new JLabel("Your score: " + score);
		constraints.gridx = 0;
		constraints.gridy = 1;
		frame.add(pointsLabel, constraints);

		JLabel livesLabel = new JLabel("Lives: " + level.getNumberOfLives());
		constraints.gridx = 1;
		constraints.gridy = 1;
		frame.add(livesLabel, constraints);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		level = null;
	}

	/**
	 * Menu wyswietlane po zakonczeniu gry.
	 */
	private void endGameMenu() {
		this.getContentPane().removeAll();
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JButton returnButton = new JButton("Main menu");
		returnButton.addActionListener(event -> this.mainMenu());
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(returnButton, c);

		JButton scoresButton = new JButton("Best scores");
		scoresButton.addActionListener(event -> this.scoreListMenu());
		c.gridx = 1;
		c.gridy = 1;
		this.add(scoresButton, c);

		JLabel praiseLabel = new JLabel("You finished the game! Your score: " + score);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		this.add(praiseLabel, c);

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

		Config.addScore(name + " " + score + " pkt " + formatter.format(date));
		Config.saveScoreList();

		revalidate();
		repaint();
	}

	/**
	 * Metoda dodajaca poziom gry do okna glownego aplikacji.
	 */
	private void loadLevel(int levelID) {
		this.getContentPane().removeAll();
		this.setLayout(new BorderLayout());

		level = new Level(levelID);
		this.add(level, BorderLayout.CENTER);

		InfoPanel infoPanel = new InfoPanel(currentLevel);
		infoPanel.setLevel(level);
		this.add(infoPanel, BorderLayout.SOUTH);

		revalidate();
		repaint();
		new Thread(level).start();
		new Thread(infoPanel).start();
	}

	/**
	 * Metoda wczytujaca kolejny poziom.
	 */
	private void loadNextLevel() {
		if (currentLevel < Config.getNumberOfLevels()) {
			countScore();
			currentLevel++;
			endLevelPopUp();
		} else {
			countScore();
			endGameMenu();
			reset();
		}

	}

	/**
	 * Metoda zliczajaca punkty gracza.
	 * 
	 * @return liczba punktow zdobytych przez gracza.
	 */
	private int countScore() {
		score = score + 100 - level.getMovesCounter();
		if (currentLevel == Config.getNumberOfLevels()) {
			for (int i = 0; i < level.getNumberOfLives(); i++) {
				score += 10;
			}
		}
		if (score < 0) {
			score = 0;
		}
		return score;
	}

	/**
	 * Metoda resetujaca gre.
	 */
	private void reset() {
		currentLevel = 1;
		level = null;
		score = 0;
	}

	/**
	 * Metoda run klasy MainFrame.
	 */
	@Override
	public void run() {
		while (true) {
			if (level != null) {
				if (level.getNumberOfLives() == 0) {
					endGameMenu();
					reset();
				} else if (level.isCompleted) {
					loadNextLevel();
				}

			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	/***
	 * Funkcja main.
	 */
	public static void main(String[] args) throws Exception {
		Config.loadConfig();
		Config.loadScoreList();
		new Thread(new MainFrame()).start();
	}
}