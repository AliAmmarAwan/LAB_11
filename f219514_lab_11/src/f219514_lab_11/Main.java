package f219514_lab_11;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;

import java.util.ArrayList;



import javax.swing.JButton;

import javax.swing.JComboBox;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTabbedPane;

import javax.swing.JTextArea;

import javax.swing.JTextField;

import javax.swing.SwingUtilities;



public class Main {



	private JFrame frame;

	private JTextArea textArea;

	private JComboBox<String> verseDropdown;

	private JTextField rootField;

	private ArrayList<String> poems = new ArrayList<>();

	private final String POEM_FILE = "Poem.txt";

	private final String ROOT_FILE = "Roots.txt";



	public Main() {

		loadPoemsFromFile();



		frame = new JFrame("Poem App");

		frame.setSize(600, 400);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		JTabbedPane tabbedPane = new JTabbedPane();



		JPanel addPoemPanel = new JPanel(); 

		textArea = new JTextArea(10, 40);

		JButton addButton = new JButton("Add Poem");

		addButton.addActionListener(e -> addPoem());

		addPoemPanel.add(new JScrollPane(textArea));

		addPoemPanel.add(addButton);

		tabbedPane.addTab("Add Poem", addPoemPanel);



		JPanel assignRootsPanel = new JPanel();

		verseDropdown = new JComboBox<>();

		rootField = new JTextField(20);

		JButton assignButton = new JButton("Assign Root");

		assignButton.addActionListener(e -> assignRoot());

		assignRootsPanel.add(verseDropdown);

		assignRootsPanel.add(rootField);

		assignRootsPanel.add(assignButton);

		tabbedPane.addTab("Assign Roots", assignRootsPanel);



		JPanel tokenizePanel = new JPanel();

		JButton tokenizeButton = new JButton("Tokenize");

		tokenizeButton.addActionListener(e -> tokenizeVerse());

		tokenizePanel.add(tokenizeButton);

		tabbedPane.addTab("Tokenize Verse", tokenizePanel);



		frame.add(tabbedPane);

		frame.setVisible(true);

	}



	private void loadPoemsFromFile() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(POEM_FILE));

			String line;

			while ((line = reader.readLine()) != null) {

				poems.add(line);

			}

			reader.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}



	private void addPoem() {

		String newPoem = textArea.getText();

		poems.add(newPoem);

		writePoemToFile(newPoem);

		textArea.setText("");

		updateVerseDropdown();

	}



	private void writePoemToFile(String poem) {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(POEM_FILE, true)); // true for append mode

			writer.write(poem);

			writer.newLine();

			writer.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}



	private void assignRoot() {

		String verse = (String) verseDropdown.getSelectedItem();

		String root = rootField.getText();

		writeRootToFile(verse, root);

		System.out.println("Assigned root " + root + " to verse: " + verse);

	}



	private void writeRootToFile(String verse, String root) {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(ROOT_FILE, true)); 

			writer.write(verse + " -> " + root);

			writer.newLine();

			writer.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}



	private void tokenizeVerse() {

		String verse = (String) verseDropdown.getSelectedItem();

		String[] tokens = verse.split(" ");

		JOptionPane.showMessageDialog(frame, "Tokens: " + String.join(", ", tokens));

	}



	private void updateVerseDropdown() {

		verseDropdown.removeAllItems();

		for (String poem : poems) {

			verseDropdown.addItem(poem);

		}

	}



	public static void main(String[] args) {

		SwingUtilities.invokeLater(Main::new);

	}

}