import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;

import javax.swing.*;

public class CompareGUI extends JFrame {
	private final int WINDOW_WIDTH = 1024;
	private final int WINDOW_HEIGHT = 700;
	private final String HEADER = "%-10s%-12s%-53s%-9s%-6s%-5s%-7s%-6s%-12s%-10s%-9s%-6s%-5s%-7s%-6s";
	JRadioButton ascending, descending;
	JTextField difficultyRange;
	JButton submit;
	ButtonGroup group = new ButtonGroup();
	ButtonHandler buttonHandler = new ButtonHandler();
	RadioButtonHandler radioButtonHandler = new RadioButtonHandler();
	JLabel info, you, them;
	JTextPane ranks;
	JScrollPane scrollpane;
	Song[] songs, songs2, newSongs, newSongs2;

	public CompareGUI(Song[] songs, Song[] songs2) {
		this.songs = songs;
		this.songs2 = songs2;
		newSongs = songs;
		newSongs2 = songs2;
		setTitle("Compare Users");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		difficultyRange = new JTextField("Type '58-66' etc and hit submit.");
		submit = new JButton("Submit range.");
		submit.addActionListener(buttonHandler);
		ranks = new JTextPane();
		ascending = new JRadioButton("Ascending");
		descending = new JRadioButton("Descending");
		group.add(ascending);
		group.add(descending);
		ascending.addActionListener(radioButtonHandler);
		descending.addActionListener(radioButtonHandler);
		info = new JLabel();
		you=new JLabel("You");
		you.setForeground(Color.WHITE);
		them=new JLabel("Them", SwingConstants.CENTER);
		them.setForeground(Color.WHITE);
		JPanel panel = new JPanel();
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.BLACK);
		panel.setLayout(new GridLayout(1, 4));
		infoPanel.setLayout(new GridLayout(2,2));
		infoPanel.add(you);
		infoPanel.add(them);
		infoPanel.add(info);
		panel.add(ascending);
		panel.add(descending);
		panel.add(difficultyRange);
		panel.add(submit);
		ranks.setFont(new Font("Monospaced", Font.PLAIN, 10));
		scrollpane = new JScrollPane(ranks);
		add(scrollpane, BorderLayout.CENTER);
		add(panel, BorderLayout.NORTH);
		add(infoPanel, BorderLayout.SOUTH);
		ascending.doClick();
		setVisible(true);
		display();
	}

	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			songs = newSongs;
			songs2 = newSongs2;
			newSongs = songs;
			newSongs2 = songs2;

			if (actionCommand.equals("Submit range.")) {
				songs = new Song[Input.NUM_SONGS];
				songs2 = new Song[Input.NUM_SONGS];
				int j = 0;
				StringTokenizer tokens = new StringTokenizer(
						difficultyRange.getText(), "-");
				int low = Integer.parseInt(tokens.nextToken());
				int high = Integer.parseInt(tokens.nextToken());

				for (int i = 0; i < newSongs.length; i++) {
					if (newSongs[i].getDifficulty() >= low
							&& newSongs[i].getDifficulty() <= high) {
						songs[j] = newSongs[i];
						songs2[j] = newSongs2[i];
						j++;
					}
				}
			}
			ascending.doClick();
			display();
		}
	}

	private class RadioButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			if (actionCommand.equals("Ascending")) {
				Collections.sort(Arrays.asList(songs),
						new Comparators.RankDifAscComparator());
				Collections.sort(Arrays.asList(songs2),
						new Comparators.RankDifAscComparator());
			}
			if (actionCommand.equals("Descending")) {
				Collections.sort(Arrays.asList(songs),
						new Comparators.RankDifDescComparator());
				Collections.sort(Arrays.asList(songs2),
						new Comparators.RankDifDescComparator());
			}
			display();
		}
	}

	public void display() {
		String message = "";
		message += String.format(HEADER + "\n", "Rank", "Difficulty",
				"Song Name", "Perfects", "Goods", "Avs", "Misses", "Boos",
				"|Rank Dif|", "Rank", "Perfects", "Goods", "Avs", "Misses",
				"Boos");
		for (int i = 0; i < Input.NUM_SONGS && songs[i] != null; i++)
			message += songs[i].toString("Compare")
					+ songs2[i].toString("Compare2") + "\n";
		ranks.setText(message);
		int[] rankDif = rankDifTotal(); 
		if (rankDif[0] < 0){
			info.setForeground(Color.GREEN);
			info.setText("You are better by " + -(rankDif[0]) + "! "
					+ -(rankDif[0]) / rankDif[1] + " per song!");
		}
		else{
			info.setForeground(Color.RED);
			info.setText("You are worse by " + (rankDif[0]) + "! "
					+ (rankDif[0]) / rankDif[1] + " per song :(");
		}
		ranks.setCaretPosition(0);
	}
	
	public int[] rankDifTotal(){
		int[] rankDif = new int[2];
		rankDif[0] = 0;
		rankDif[1] = 0;
		int i=0;
		do{
			if(songs[i] != null){
			rankDif[0] += songs[i].getRankDif();
			rankDif[1]++;
			i++;
			}
			else
				break;
		}while (i<Input.NUM_SONGS);
			
		return rankDif;
	}
}
