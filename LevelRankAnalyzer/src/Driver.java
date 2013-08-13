import java.io.*;
import java.net.*;
import java.util.regex.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Driver {
	
	public static void main(String[] args) throws IOException{
		/*URL url = new URL("http://www.flashflashrevolution.com/levelrank.php?sub=ReikonKeiri");
		URLConnection con = url.openConnection();
		Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
		Matcher m = p.matcher(con.getContentType());
		String charset = m.matches() ? m.group(1) : "UTF-8";
		Reader r = new InputStreamReader(con.getInputStream(), charset);
		StringBuilder buf = new StringBuilder();
		while (true) {
		  int ch = r.read();
		  if (ch < 0)
		    break;
		  buf.append((char) ch);
		}
		String str = buf.toString();
		System.out.println(str);*/
		Song[] songs = null;
		JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        try {
			Input input = new Input(f);
        	songs = Input.readInput();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
				    "Invalid File.",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
        Analyzer analyze = new Analyzer(songs);
		Gui gui = new Gui(analyze);
	}
}
