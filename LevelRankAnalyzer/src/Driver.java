import java.io.*;
import java.net.*;
import java.util.regex.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.jsoup.nodes.Document;

public class Driver {
	
	public static void main(String[] args) throws IOException{
		WebScrape test = new WebScrape();
		Document[] scores = null;
		String[] form = new LoginDialog(false).showDialog();
		
		try {
			test.setUp();
			scores = test.test(form[0], form[1], form[2]=="true", form[3]);
			test.tearDown();
		} catch (Exception e1) {
		}
		
		if(scores.equals("")){
			System.exit(0);
		}
		
		Song[] songs = Input.readInput(scores);

        Analyzer analyze = new Analyzer(songs);
		Gui gui = new Gui(analyze);
	}
}
