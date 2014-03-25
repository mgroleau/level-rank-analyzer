import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;

import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class Input {
	static int NUM_SONGS;
	static ArrayList<Song> songs;

	public static Song[] readInput(Document[] source) {
		int i=0;
		songs = new ArrayList<Song>();
		
		i=parseDOM(source[0]);
		if(source[1]!=null)
			i+=parseDOM(source[1]);
		NUM_SONGS = i;
		Song[] newSongs = new Song[NUM_SONGS];
		newSongs = songs.toArray(newSongs);
		return newSongs;
	}
	
	public static int parseDOM(Document d){
		int i=0;
		
		Elements tables = d.getElementsByTag("table");
		Element table = tables.first();
		
		Elements rows = table.getElementsByTag("tr");
		rows.remove(0);
		for(Element row : rows){
			Elements cells = row.getElementsByTag("td");
			String rank = cells.get(0).html();
			String difficulty = cells.get(1).html();
			String songName = cells.get(2).getElementsByTag("a").get(0).html();
			String score = cells.get(3).html();
			int perfects = Integer.parseInt(cells.get(4).html());
			int goods = Integer.parseInt(cells.get(5).html());
			int averages = Integer.parseInt(cells.get(6).html());
			int misses = Integer.parseInt(cells.get(7).html());
			int boos = Integer.parseInt(cells.get(8).html());
			String maxCombo = cells.get(9).html();
			songs.add(new Song(perfects, goods, averages, misses, boos, score,
					rank, difficulty, songName, maxCombo));
			
			i++;
		}
		
		return i;
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
