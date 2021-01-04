import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Klasa odpowiedzialna za glowne okno programu. Dziedziczy po JFrame.
 */
public class MainFrame extends JFrame implements Runnable {
	/**
	 * Pole przechowujace nick gracza.
	 */
	private String name;

	private Level level;

	private int currentLevel = 1;

	private int score = 0;

	/**
	 * Konstruktor wczytujacy rozmiar okna z pliku konfiguracyjnego.
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
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		menuBar();

		JLabel nameLabel = new JLabel("Wprowadz imie:");
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add(nameLabel, constraints);

		JTextField nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(75, 24));
		constraints.gridx = 1;
		constraints.gridy = 0;
		this.add(nameField, constraints);

		JButton playButton = new JButton("Graj");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (nameField.getText().length() == 0) {
					showMessageDialog(null, "Prosze wprowadzic nick!");
				} else {
					loadLevel(currentLevel);
					name = nameField.getText();
				}
			}
		});
		constraints.gridx = 0;
		constraints.gridy = 1;
		this.add(playButton, constraints);

		JButton scoreListButton = new JButton("Najlepsze wyniki");
		scoreListButton.addActionListener(event ->this.scoreListMenu());
		constraints.gridx = 1;
		constraints.gridy = 1;
		this.add(scoreListButton, constraints);

		JButton endButton = new JButton("Koniec");
		endButton.addActionListener(event -> this.dispose());
		constraints.gridx = 2;
		constraints.gridy = 1;
		this.add(endButton, constraints);

		revalidate();
		repaint();
	}

	/**
	 * Menu rozwijane z paska, dostępne w każdym widoku aplikacji. Pozwala na powrot do
	 * menu glownego, wejscie w ustawienia gry lub sprawdzenie instrukcji.
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

		JButton returnButton = new JButton("Powrot");
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

		JButton returnButton = new JButton("Powrot");
		returnButton.addActionListener(event -> this.mainMenu());
		this.add(returnButton);

		revalidate();
		repaint();
	}

	private void scoreListMenu(){
		this.getContentPane().removeAll();
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		for(String score: Config.getScoreList()){
			this.add(new JLabel(score, SwingConstants.CENTER));
		}
		JButton returnButton = new JButton("Powrot");
		returnButton.addActionListener(event -> this.mainMenu());
		this.add(returnButton);

		revalidate();
		repaint();
	}

	private void endLevelPopUp() {
		JFrame frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

		JButton okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadLevel(currentLevel);
				frame.dispose();
			}
		});
		constraints.gridx = 1;
		constraints.gridy = 20;
		frame.add(okButton, constraints);

		JLabel praiseLabel = new JLabel("Brawo! Ukonczyles poziom " + Integer.toString(currentLevel - 1));
		constraints.gridx = 10;
		constraints.gridy = 0;
		frame.add(praiseLabel, constraints);

		JLabel pointsLabel = new JLabel("Ilosc punktow: " + score);
		constraints.gridx = 0;
		constraints.gridy = 10;
		frame.add(pointsLabel, constraints);

		JLabel livesLabel = new JLabel("Ilosc zyc: " + level.getNumberOfLives());
		constraints.gridx = 10;
		constraints.gridy = 10;
		frame.add(livesLabel, constraints);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		level = null;
	}

	private void endGameMenu() {
		this.getContentPane().removeAll();
		this.setLayout(new GridBagLayout());

		JButton returnButton = new JButton("Menu glowne");
		returnButton.addActionListener(event -> this.mainMenu());
		this.add(returnButton);

		JButton scoresButton = new JButton("Najlepsze wyniki");
		returnButton.addActionListener(event -> this.mainMenu());
		this.add(scoresButton);

		JLabel praiseLabel = new JLabel("Ukonczyles gre! Twoj wynik to: " + score);
		this.add(praiseLabel);

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

		Config.addScore(name + " " + score + " pkt "  + formatter.format(date));
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

		JLabel jLabel = new JLabel("Tekst");
		this.add(jLabel);

		revalidate();
		repaint();
		new Thread(level).start();
	}

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

	private int countScore() {
		score = score + 100 - level.getMovesCounter();
		if (currentLevel == Config.getNumberOfLevels()) {
			for (int i = 0; i < level.getNumberOfLives(); i++) {
				score += 10;
			}
		}
		if (score < 0){
			score = 0;
		}
		return score;
	}

	private void reset() {
		currentLevel = 1;
		level = null;
		score = 0;
	}

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

	public Level getLevel() {
		return this.level;
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