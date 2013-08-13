import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Gui extends JFrame {
	private final int WINDOW_WIDTH = 1024;
	private final int WINDOW_HEIGHT = 700;
	private final String HEADER = "%-10s%-12s%-53s%-9s%-6s%-5s%-7s%-6s%-7s";
	private Analyzer analyze;

	JLabel title, watermark, sort, levelRanks, filter;
	JRadioButton showBlackFlags, showAvFlags, showBooFlags, showMissFlags,
			cleanSDGs, notCleanSDGs, cleanFCs, notCleanFCs, AAAs, showLegacy,
			showAll, wtf, showUnplayed;
	JTextField difficultyRange;
	JScrollPane scrollpane;
	JTextArea ranks, descriptor;
	JButton submitDifficulty, newScore, compareUser;
	ButtonHandler buttonHandler = new ButtonHandler();
	RadioButtonHandler radioButtonHandler = new RadioButtonHandler();
	CheckBoxHandler checkBoxHandler = new CheckBoxHandler();
	ButtonGroup optionsGroup = new ButtonGroup();
	ButtonGroup sortGroup = new ButtonGroup();
	ButtonGroup ascdescGroup = new ButtonGroup();
	JRadioButton sortPerfect, sortGood, sortMiss, sortBoo, sortRank,
			sortDifficulty, ascending, descending, sortAv, sortSongName;
	JPanel optionsPanel, southPanel, titlePanel, sortPanel, sortPanelPanel,
			filterPanel, options, ascdesc, menuPanel;
	JFrame frame;
	JMenuBar menubar;
	JMenu file;
	JMenuItem saveexit;
	MenuHandler menuHandler = new MenuHandler();
	
	String selectedoption;
	NewScore newS;
	CompareGUI compare;
	
	private static int rankDifTotal = 0;

	public static int getRankDifTotal() {
		return rankDifTotal;
	}

	public static void setRankDifTotal(int total) {
		rankDifTotal = rankDifTotal;
	}

	public Gui(Analyzer analyze) {
		this.analyze = analyze;
		frame = this;
		
		setTitle("Level Rank Analyzer");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		menubar = new JMenuBar();
		file = new JMenu("File");
		menubar.add(file);
		saveexit = new JMenuItem("Save and Exit");
		file.add(saveexit);
		saveexit.addActionListener(menuHandler);
		
		
		title = new JLabel("FlashFlashRevolution Unofficial Level Rank App");
		watermark = new JLabel("made by ReikonKeiri v1.2");
		sort = new JLabel("Sort");
		levelRanks = new JLabel("Level Ranks");
		filter = new JLabel("Filter");
		watermark.setForeground(Color.red);

		difficultyRange = new JTextField(
				"Type '58-66' etc and hit submit.");
		ranks = new JTextArea();
		descriptor = new JTextArea();
		ranks.setFont(new Font("Monospaced", Font.PLAIN, 10));
		scrollpane = new JScrollPane(ranks);
		submitDifficulty = new JButton("Submit range.");
		newScore = new JButton("New Score");
		compareUser = new JButton("Compare!");
		AAAs = new JRadioButton("Show AAAs");
		showBlackFlags = new JRadioButton("Show Blackflags");
		showAvFlags = new JRadioButton("Show Av flags");
		showBooFlags = new JRadioButton("Show booflags");
		showMissFlags = new JRadioButton("Show miss flags");
		showAll = new JRadioButton("Show All");
		cleanSDGs = new JRadioButton("Show clean SDGs");
		notCleanSDGs = new JRadioButton("Show dirty SDGs");
		cleanFCs = new JRadioButton("Show clean FCs");
		notCleanFCs = new JRadioButton("Show dirty FCs");
		showLegacy = new JRadioButton("Show Legacy");
		wtf = new JRadioButton("Show WTF");
		sortPerfect = new JRadioButton("Perfect");
		sortGood = new JRadioButton("Good");
		sortBoo = new JRadioButton("Boo");
		sortMiss = new JRadioButton("Miss");
		sortRank = new JRadioButton("Rank");
		sortDifficulty = new JRadioButton("Difficulty");
		sortAv = new JRadioButton("Average");
		sortSongName = new JRadioButton("Song Name");
		ascending = new JRadioButton("Ascending");
		descending = new JRadioButton("Descending");
		showUnplayed = new JRadioButton("Show Unplayed");

		submitDifficulty.addActionListener(buttonHandler);
		newScore.addActionListener(buttonHandler);
		compareUser.addActionListener(buttonHandler);
		AAAs.addActionListener(radioButtonHandler);
		showBlackFlags.addActionListener(radioButtonHandler);
		showAvFlags.addActionListener(radioButtonHandler);
		showMissFlags.addActionListener(radioButtonHandler);
		showBooFlags.addActionListener(radioButtonHandler);
		showAll.addActionListener(radioButtonHandler);
		cleanSDGs.addActionListener(radioButtonHandler);
		notCleanSDGs.addActionListener(radioButtonHandler);
		cleanFCs.addActionListener(radioButtonHandler);
		notCleanFCs.addActionListener(radioButtonHandler);
		showLegacy.addActionListener(radioButtonHandler);
		sortPerfect.addActionListener(radioButtonHandler);
		sortGood.addActionListener(radioButtonHandler);
		sortBoo.addActionListener(radioButtonHandler);
		sortMiss.addActionListener(radioButtonHandler);
		sortRank.addActionListener(radioButtonHandler);
		sortDifficulty.addActionListener(radioButtonHandler);
		sortAv.addActionListener(radioButtonHandler);
		sortSongName.addActionListener(radioButtonHandler);
		ascending.addActionListener(radioButtonHandler);
		descending.addActionListener(radioButtonHandler);
		wtf.addActionListener(radioButtonHandler);
		showUnplayed.addActionListener(radioButtonHandler);

		optionsGroup.add(AAAs);
		optionsGroup.add(showBlackFlags);
		optionsGroup.add(showAvFlags);
		optionsGroup.add(showMissFlags);
		optionsGroup.add(showBooFlags);
		optionsGroup.add(wtf);
		optionsGroup.add(showLegacy);
		optionsGroup.add(cleanSDGs);
		optionsGroup.add(notCleanSDGs);
		optionsGroup.add(cleanFCs);
		optionsGroup.add(notCleanFCs);
		optionsGroup.add(showAll);
		optionsGroup.add(showUnplayed);

		sortGroup.add(sortPerfect);
		sortGroup.add(sortGood);
		;
		sortGroup.add(sortMiss);
		sortGroup.add(sortBoo);
		sortGroup.add(sortRank);
		sortGroup.add(sortDifficulty);
		sortGroup.add(sortAv);
		sortGroup.add(sortSongName);

		ascdescGroup.add(ascending);
		ascdescGroup.add(descending);

		optionsPanel = new JPanel();
		southPanel = new JPanel();
		titlePanel = new JPanel();
		sortPanel = new JPanel();
		sortPanelPanel = new JPanel();
		filterPanel = new JPanel();
		options = new JPanel();
		ascdesc = new JPanel();
		menuPanel = new JPanel();

		sortPanelPanel.setLayout(new BorderLayout());
		filterPanel.setLayout(new BorderLayout());
		sortPanelPanel.add(sort, BorderLayout.NORTH);
		sortPanelPanel.add(sortPanel, BorderLayout.CENTER);
		sortPanelPanel.add(ascdesc, BorderLayout.SOUTH);
		filterPanel.add(filter, BorderLayout.NORTH);
		filterPanel.add(options, BorderLayout.CENTER);
		sortPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		sortPanelPanel
				.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		options.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		filterPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		titlePanel.setLayout(new GridLayout(2, 2));
		southPanel.setLayout(new GridLayout(1, 3));
		sortPanel.setLayout(new GridLayout(4, 2));
		ascdesc.setLayout(new GridLayout(1, 2));
		options.setLayout(new GridLayout(6, 2));
		menuPanel.setLayout(new GridLayout(2,1));
		southPanel.add(difficultyRange);
		southPanel.add(submitDifficulty);
		southPanel.add(compareUser);
		optionsPanel.setLayout(new GridLayout(4, 1));
		optionsPanel.setPreferredSize(new Dimension(260, 650));
		optionsPanel.add(sortPanelPanel);
		optionsPanel.add(filterPanel);
		optionsPanel.add(newScore);
		optionsPanel.add(descriptor);

		sortPanel.add(sortPerfect);
		sortPanel.add(sortGood);
		sortPanel.add(sortMiss);
		sortPanel.add(sortBoo);
		sortPanel.add(sortRank);
		sortPanel.add(sortDifficulty);
		sortPanel.add(sortAv);
		sortPanel.add(sortSongName);

		ascdesc.add(ascending);
		ascdesc.add(descending);

		options.add(AAAs);
		options.add(showBlackFlags);
		options.add(showAvFlags);
		options.add(showBooFlags);
		options.add(cleanSDGs);
		options.add(notCleanSDGs);
		options.add(cleanFCs);
		options.add(notCleanFCs);
		options.add(wtf);
		options.add(showLegacy);
		options.add(showUnplayed);
		options.add(showAll);

		titlePanel.add(title);
		titlePanel.add(watermark);
		titlePanel.add(levelRanks);
		titlePanel.setBackground(Color.black);
		title.setForeground(Color.red);
		levelRanks.setForeground(Color.white);
		menuPanel.add(menubar);
		menuPanel.add(titlePanel);
		add(menuPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		add(scrollpane, BorderLayout.CENTER);
		add(optionsPanel, BorderLayout.EAST);
		
		showAll.setSelected(true);
		ascending.setSelected(true);
		sortRank.doClick();
		
		display();
		setVisible(true);
	}

	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();

			if (actionCommand.equals("Submit range.")) {
				analyze.subset("Difficulty", difficultyRange.getText());
				submitDifficulty.setEnabled(false);
				getSelection(sortGroup).doClick();
			}
			if(actionCommand.equals("New Score"))
				newS = new NewScore(analyze);
			if(actionCommand.equals("Compare!")){
				JFileChooser chooser = new JFileChooser();
		        chooser.showOpenDialog(null);
		        File f = chooser.getSelectedFile();
		        Song[] songs2 = null;
		        try {
					Input input = new Input(f);
					songs2 = Input.readInput();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,
						    "Invalid File.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
		        Song[] songs = analyze.getAll();
		        Collections.sort(Arrays.asList(songs2),
						new Comparators.SongNameAscComparator());
				int j=0;
		        for(int i=0;i<Input.NUM_SONGS;i++){
		        	j=songs[i].getRank()-songs2[i].getRank();
					songs[i].setRankDif(j);
					songs2[i].setRankDif(j);
					rankDifTotal += j;
				}
				Collections.sort(Arrays.asList(songs),
						new Comparators.RankDifAscComparator());
		        Collections.sort(Arrays.asList(songs2),
						new Comparators.RankDifAscComparator());
		        compare = new CompareGUI(songs, songs2);
			}	
			display();
		}
	}

	private class RadioButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();

			if (actionCommand.equals("Show AAAs"))
				analyze.subset("Show AAAs", null);
			if (actionCommand.equals("Show All"))
				analyze.subset("Show All", null);
			if (actionCommand.equals("Show Legacy"))
				analyze.subset("Show Legacy", null);
			if (actionCommand.equals("Show Blackflags"))
				analyze.subset("Show Blackflags", null);
			if (actionCommand.equals("Show Av flags"))
				analyze.subset("Show Avflags", null);
			if (actionCommand.equals("Show booflags"))
				analyze.subset("Show Booflags", null);
			if (actionCommand.equals("Show miss flags"))
				analyze.subset("Show Missflags", null);
			if (actionCommand.equals("Show WTF"))
				analyze.subset("Show wtf", null);
			if (actionCommand.equals("Show clean SDGs"))
				analyze.subset("Show clean SDGs", null);
			if (actionCommand.equals("Show dirty SDGs"))
				analyze.subset("Show dirty SDGs", null);
			if (actionCommand.equals("Show clean FCs"))
				analyze.subset("Show clean FCs", null);
			if (actionCommand.equals("Show dirty FCs"))
				analyze.subset("Show dirty FCs", null);
			if (actionCommand.equals("Show Unplayed"))
				analyze.subset("Show Unplayed", null);

			String selected = "no";
			if (ascending.isSelected())
				selected = "yes";
			if (actionCommand.equals("Perfect"))
				analyze.subset("Perfect", selected);
			if (actionCommand.equals("Good"))
				analyze.subset("Good", selected);
			if (actionCommand.equals("Average"))
				analyze.subset("Average", selected);
			if (actionCommand.equals("Miss"))
				analyze.subset("Miss", selected);
			if (actionCommand.equals("Boo"))
				analyze.subset("Boo", selected);
			if (actionCommand.equals("Rank"))
				analyze.subset("Rank", selected);
			if (actionCommand.equals("Difficulty"))
				analyze.subset("SortDifficulty", selected);
			if (actionCommand.equals("Song Name"))
				analyze.subset("SongName", selected);

			if (actionCommand.equals(getSelection(optionsGroup).getText())){
				submitDifficulty.setEnabled(true);
				difficultyRange.setText("Type '58-66' etc and hit submit.");
				getSelection(sortGroup).doClick();
			}
			if(actionCommand.equals(getSelection(ascdescGroup)
					.getText()))
				getSelection(sortGroup).doClick();

			display();
		}
	}

	private class MenuHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String actionCommand = e.getActionCommand();
			
			if(actionCommand.equals("Save and Exit")){
				try {
					Input.writeOutput(analyze.getAll());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,
						    "Invalid File.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
			frame.dispose();
		}
	}
	
	private class CheckBoxHandler implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			Object source = e.getItemSelectable();
		}
	}

	public void display() {
		String descriptorMessage = "Showing ";
		int z = analyze.getLength();
		String selected = getSelection(optionsGroup).getText().substring(4);
		descriptorMessage += z + selected + "\n";
		if (selected.equals(" All"))
			descriptorMessage += analyze.info("All");
		if (submitDifficulty.isEnabled() == false)
			descriptorMessage += "Ranks between difficulties:\n"
					+ difficultyRange.getText() + " shown.";
		descriptor.setText(descriptorMessage);
		ranks.setText("");
		ranks.append(String.format(HEADER + "\n", "Rank", "Difficulty",
				"Song Name", "Perfects", "Goods", "Avs", "Misses", "Boos",
				"MaxCombo"));
		ranks.append(analyze.toString());
		ranks.setCaretPosition(0);
	}

	public JRadioButton getSelection(ButtonGroup group) {
		for (Enumeration e = group.getElements(); e.hasMoreElements();) {
			JRadioButton b = (JRadioButton) e.nextElement();
			if (b.getModel() == group.getSelection()) {
				return b;
			}
		}
		return null;
	}

}
