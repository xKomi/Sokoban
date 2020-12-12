package sokoban;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Klasa odpowiedzialna za glowne okno programu. Dziedziczy po JFrame.
 */
public class MainFrame extends JFrame {
	/**
	 * Pole przechowujace nick gracza.
	 */
	private String name;

	/**
	 * Pole przechowujące ilość żyć gracza. Na początko wczytywane z pliku
	 * konfiguracyjnego.
	 */
	private int numberOfLives;

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
					loadLevel();
					name = nameField.getText();
				}
			}
		});
		constraints.gridx = 0;
		constraints.gridy = 1;
		this.add(playButton, constraints);

		JButton rankingButton = new JButton("Najlepsze wyniki");
		// rankingButton.addActionListener(event ->this.metodaNaListeWynikow);
		constraints.gridx = 1;
		constraints.gridy = 1;
		this.add(rankingButton, constraints);

		JButton endButton = new JButton("Koniec");
		endButton.addActionListener(event -> this.dispose());
		constraints.gridx = 2;
		constraints.gridy = 1;
		this.add(endButton, constraints);

		revalidate();
		repaint();
	}

	/**
	 * Menu rozwiaje z paska, dostępne w każdym widoku aplikacji. Pozwala na powrotd
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

	void settingMenu() {
		this.getContentPane().removeAll();
		this.setLayout(new GridBagLayout());

		JButton returnButton = new JButton("Powrot");
		returnButton.addActionListener(event -> this.mainMenu());
		this.add(returnButton);

		revalidate();
		repaint();
	}

	/**
	 * Metoda dodajaca poziom gry do okna glownego aplikacji.
	 */

	private void loadLevel() {
		this.getContentPane().removeAll();
		this.setLayout(new BorderLayout());

		Level level = new Level();
		this.add(level, BorderLayout.CENTER);

		revalidate();
		repaint();
	}

	/***
	 * Funkcja main.
	 */
	public static void main(String[] args) {
		Config.loadConfig();
		new MainFrame();
	}
}