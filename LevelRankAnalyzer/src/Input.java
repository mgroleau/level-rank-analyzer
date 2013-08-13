import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;

public class Input {
	static int NUM_SONGS;
	private static int i;
	private static Song[] songs;
	private static Scanner ranks;

	public Input(File f) throws IOException {
		songs = new Song[2000];
		ranks = new Scanner(f);
		i = 0;
	}

	public static Song[] readInput() {

		String text = "";
		while (ranks.hasNext())
			text += ranks.nextLine() + '\n';
		StringTokenizer tokens = new StringTokenizer(text, "\n\t");
		while (tokens.hasMoreTokens()) {
			String rank = tokens.nextToken();
			String difficulty = tokens.nextToken();
			String songName = tokens.nextToken();
			System.out.println(songName);
			String score = tokens.nextToken();
			int perfects = Integer.parseInt(tokens.nextToken());
			int goods = Integer.parseInt(tokens.nextToken());
			int averages = Integer.parseInt(tokens.nextToken());
			int misses = Integer.parseInt(tokens.nextToken());
			int boos = Integer.parseInt(tokens.nextToken());
			String maxCombo = tokens.nextToken();
			tokens.nextToken();
			songs[i] = new Song(perfects, goods, averages, misses, boos, score,
					rank, difficulty, songName, maxCombo);
			i++;
		}
		NUM_SONGS = i;
		Song[] temp = new Song[i];
		System.arraycopy(songs, 0, temp, 0, i);
		songs = temp;
		return songs;
	}

	public static void writeOutput(Song[] songs) throws IOException {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File f = chooser.getSelectedFile();

		f.setWritable(true);
		FileWriter writer = new FileWriter(f);
		writer.write("");
		for (int j = 0; j < Input.NUM_SONGS; j++) {
			writer.append(songs[j].getRank() + "\t" + songs[j].getDifficulty()
					+ "\t" + songs[j].getSongName() + "\t"
					+ songs[j].getScoreString() + "\t" + songs[j].getPerfects()
					+ "\t" + songs[j].getGoods() + "\t"
					+ songs[j].getAverages() + "\t" + songs[j].getMisses()
					+ "\t" + songs[j].getBoos() + "\t" + songs[j].getMaxCombo()
					+ "\t" + "0" + System.getProperty("line.separator"));
		}
		writer.close();
	}
}
