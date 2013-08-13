import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.*;

public class NewScore extends JFrame {
	private final int WINDOW_WIDTH = 300;
	private final int WINDOW_HEIGHT = 300;

	JLabel rankL, songNameL, perfectsL, goodsL, averagesL, missesL, boosL,
			maxComboL;
	JTextField rank, songName, perfects, goods, averages, misses, boos,
			maxCombo;
	JButton submit;
	JPanel spreadsheet, southPanel;
	JCheckBox FCd;
	ButtonHandler buttonHandler = new ButtonHandler();
	JFrame frame;
	Analyzer analyze;
	int score;

	public NewScore(Analyzer analyze) {
		frame = this;
		this.analyze = analyze;

		setTitle("Score Submit");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		rankL = new JLabel("Rank");
		songNameL = new JLabel("Song Name");
		perfectsL = new JLabel("Perfects");
		goodsL = new JLabel("Good");
		averagesL = new JLabel("Averages");
		missesL = new JLabel("Misses");
		boosL = new JLabel("Boos");
		maxComboL = new JLabel("Max Combo");

		rank = new JTextField();
		songName = new JTextField();
		perfects = new JTextField();
		goods = new JTextField();
		averages = new JTextField();
		misses = new JTextField();
		boos = new JTextField();
		maxCombo = new JTextField();
		submit = new JButton("Submit Score");
		FCd = new JCheckBox("FCd", false);
		spreadsheet = new JPanel();
		southPanel = new JPanel();
		submit.addActionListener(buttonHandler);

		setLayout(new BorderLayout());
		spreadsheet.setLayout(new GridLayout(8, 8));
		southPanel.setLayout(new GridLayout(1, 2));
		spreadsheet.add(rankL);
		spreadsheet.add(rank);
		spreadsheet.add(songNameL);
		spreadsheet.add(songName);
		spreadsheet.add(perfectsL);
		spreadsheet.add(perfects);
		spreadsheet.add(goodsL);
		spreadsheet.add(goods);
		spreadsheet.add(averagesL);
		spreadsheet.add(averages);
		spreadsheet.add(missesL);
		spreadsheet.add(misses);
		spreadsheet.add(boosL);
		spreadsheet.add(boos);
		spreadsheet.add(maxComboL);
		spreadsheet.add(maxCombo);
		southPanel.add(FCd);
		southPanel.add(submit);

		add(spreadsheet, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		setVisible(true);
	}

	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Song song = new Song(Integer.parseInt(rank.getText()), Integer.parseInt(perfects.getText()),
					Integer.parseInt(goods.getText()),
					Integer.parseInt(averages.getText()),
					Integer.parseInt(misses.getText()), Integer.parseInt(boos
							.getText()), songName.getText(),
					Integer.parseInt(maxCombo.getText()), FCd.isSelected());
			analyze.newScore(song);
			frame.dispose();
		}
	}

}
