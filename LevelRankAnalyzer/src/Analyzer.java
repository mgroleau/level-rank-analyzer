
import java.util.*;
import javax.swing.*;

public class Analyzer {

    private Song[] noAAAsubset = new Song[Input.NUM_SONGS];
    private Song[] AAAsubset = new Song[Input.NUM_SONGS];
    private Song[] legacySubset = new Song[Input.NUM_SONGS];
    private Song[] blackflagSubset = new Song[Input.NUM_SONGS];
    private Song[] avflagSubset = new Song[Input.NUM_SONGS];
    private Song[] missflagSubset = new Song[Input.NUM_SONGS];
    private Song[] booflagSubset = new Song[Input.NUM_SONGS];
    private Song[] wtfSubset = new Song[Input.NUM_SONGS];
    private Song[] cleanSDGsubset = new Song[Input.NUM_SONGS];
    private Song[] dirtySDGsubset = new Song[Input.NUM_SONGS];
    private Song[] cleanFCsubset = new Song[Input.NUM_SONGS];
    private Song[] dirtyFCsubset = new Song[Input.NUM_SONGS];
    private Song[] unplayedSubset = new Song[Input.NUM_SONGS];
    private Song[] all;
    private Song[] subset;
    private Song[] newSubset;

    public Analyzer(Song[] songs) {
        all = songs;
        Collections.sort(Arrays.asList(all),
                new Comparators.SongNameAscComparator());
        subset = songs;
        subset("Initialize", null);
    }

    public Song[] getAll() {
        return all;
    }

    public Song[] subset(String command1, String command2) {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int f = 0;
        int g = 0;
        int h = 0;
        int l = 0;
        int j = 0;
        int k = 0;
        int m = 0;
        int n = 0;

        newSubset = new Song[Input.NUM_SONGS];

        if (command1.equals("Initialize")) {
            for (int i = 0; i < subset.length && subset[i] != null; i++) {
                if (subset[i].getAAAd() == false
                        && subset[i].getIsLegacy() == false
                        && subset[i].getUnplayed() == false) {
                    noAAAsubset[a] = subset[i];
                    a++;
                }
                if (subset[i].getIsLegacy() == true) {
                    legacySubset[b] = subset[i];
                    b++;
                }
                if (subset[i].getAAAd() == true) {
                    AAAsubset[c] = subset[i];
                    c++;
                }
                if (subset[i].getBlackflag()) {
                    blackflagSubset[d] = subset[i];
                    d++;
                }
                if (subset[i].getAvflag()) {
                    avflagSubset[e] = subset[i];
                    e++;
                }
                if (subset[i].getMissflag()) {
                    missflagSubset[f] = subset[i];
                    f++;
                }
                if (subset[i].getBooflag()) {
                    booflagSubset[g] = subset[i];
                    g++;
                }
                if (subset[i].getGoods() <= subset[i].getAverages()
                        + subset[i].getMisses() + subset[i].getBoos()
                        && subset[i].getGoods() > 1
                        && subset[i].getAAAd() == false) {
                    wtfSubset[h] = subset[i];
                    h++;
                }
                if (subset[i].getFCd() == true && subset[i].getGoods() <= 9
                        && subset[i].getGoods() > 1
                        && subset[i].getAverages() + subset[i].getBoos() == 0) {
                    cleanSDGsubset[l] = subset[i];
                    l++;
                }
                if (subset[i].getFCd() == true && subset[i].getGoods() <= 9
                        && subset[i].getGoods() > 1
                        && subset[i].getAverages() + subset[i].getBoos() >= 1) {
                    dirtySDGsubset[n] = subset[i];
                    n++;
                }
                if (subset[i].getFCd() == true
                        && subset[i].getAverages() + subset[i].getBoos() == 0
                        && subset[i].getAAAd() == false) {
                    cleanFCsubset[j] = subset[i];
                    j++;
                }
                if (subset[i].getFCd() == true
                        && subset[i].getAverages() + subset[i].getBoos() >= 1) {
                    dirtyFCsubset[k] = subset[i];
                    k++;
                }
                if (subset[i].getUnplayed() == true) {
                    unplayedSubset[m] = subset[i];
                    m++;
                }
            }
            newSubset = noAAAsubset;
        }

        if (command1.equals("Show AAAs")) {
            newSubset = AAAsubset;
        }

        if (command1.equals("Show All")) {
            newSubset = noAAAsubset;
        }

        if (command1.equals("Show Legacy")) {
            newSubset = legacySubset;
        }

        if (command1.equals("Show Blackflags")) {
            newSubset = blackflagSubset;
        }

        if (command1.equals("Show Avflags")) {
            newSubset = avflagSubset;
        }

        if (command1.equals("Show Missflags")) {
            newSubset = missflagSubset;
        }

        if (command1.equals("Show Booflags")) {
            newSubset = booflagSubset;
        }

        if (command1.equals("Show wtf")) {
            newSubset = wtfSubset;
        }

        if (command1.equals("Show clean SDGs")) {
            newSubset = cleanSDGsubset;
        }

        if (command1.equals("Show dirty SDGs")) {
            newSubset = dirtySDGsubset;
        }

        if (command1.equals("Show clean FCs")) {
            newSubset = cleanFCsubset;
        }

        if (command1.equals("Show dirty FCs")) {
            newSubset = dirtyFCsubset;
        }

        if (command1.equals("Show Unplayed")) {
            newSubset = unplayedSubset;
        }

        if (command1.equals("Perfect")) {
            if (command2.equals("yes")) {
                Collections.sort(Arrays.asList(subset),
                        new Comparators.PerfectAscComparator());
                newSubset = subset;
            } else {
                Collections.sort(Arrays.asList(subset),
                        new Comparators.PerfectDescComparator());
                newSubset = subset;
            }
        }

        if (command1.equals("Good")) {
            if (command2.equals("yes")) {
                Collections.sort(Arrays.asList(subset),
                        new Comparators.GoodAscComparator());
                newSubset = subset;
            } else {
                Collections.sort(Arrays.asList(subset),
                        new Comparators.GoodDescComparator());
                newSubset = subset;
            }
        }
        if (command1.equals("Average")) {
            if (command2.equals("yes")) {
                Collections.sort(Arrays.asList(subset),
                        new Comparators.AverageAscComparator());
                newSubset = subset;
            } else {
                Collections.sort(Arrays.asList(subset),
                        new Comparators.AverageDescComparator());
                newSubset = subset;
            }
        }
        if (command1.equals("Miss")) {
            if (command2.equals("yes")) {
                Collections.sort(Arrays.asList(subset),
                        new Comparators.MissAscComparator());
                newSubset = subset;
            } else {
                Collections.sort(Arrays.asList(subset),
                        new Comparators.MissDescComparator());
                newSubset = subset;
            }
        }
        if (command1.equals("Boo")) {
            if (command2.equals("yes")) {
                Collections.sort(Arrays.asList(subset),
                        new Comparators.BooAscComparator());
                newSubset = subset;
            } else {
                Collections.sort(Arrays.asList(subset),
                        new Comparators.BooDescComparator());
                newSubset = subset;
            }
        }
        if (command1.equals("Rank")) {
            if (command2.equals("yes")) {
                Collections.sort(Arrays.asList(subset),
                        new Comparators.RankAscComparator());
                newSubset = subset;
            } else {
                Collections.sort(Arrays.asList(subset),
                        new Comparators.RankDescComparator());
                newSubset = subset;
            }
        }
        if (command1.equals("SortDifficulty")) {
            if (command2.equals("yes")) {
                Collections.sort(Arrays.asList(subset),
                        new Comparators.DifficultyAscComparator());
                newSubset = subset;
            } else {
                Collections.sort(Arrays.asList(subset),
                        new Comparators.DifficultyDescComparator());
                newSubset = subset;
            }
        }
        if (command1.equals("SongName")) {
            if (command2.equals("yes")) {
                Collections.sort(Arrays.asList(subset),
                        new Comparators.SongNameAscComparator());
                newSubset = subset;
            } else {
                Collections.sort(Arrays.asList(subset),
                        new Comparators.SongNameDescComparator());
                newSubset = subset;
            }
        }

        if (command1.equals("Difficulty")) {
            j = 0;
            StringTokenizer tokens = new StringTokenizer(command2, "-");
            int low = Integer.parseInt(tokens.nextToken());
            int high = Integer.parseInt(tokens.nextToken());

            for (int i = 0; i < subset.length && subset[i] != null; i++) {
                if (subset[i].getDifficulty() >= low
                        && subset[i].getDifficulty() <= high) {
                    newSubset[j] = subset[i];
                    j++;
                }
            }
        }
        subset = newSubset;
        return subset;
    }

    public static void setRankDif(int rank, String songName) {
    }

    public int getLength() {
        int i = 0;
        while (subset[i] != null) {
            i++;
        }
        return i;
    }

    public String toString() {
        String ranks = "";
        for (int i = 0; i < subset.length; i++) {
            if (subset[i] != null) {
                ranks += (subset[i].toString("null"));
            }
        }

        return ranks;
    }

    public String info(String command) {
        String message = "";
        if (command.equals("All")) {
            Song[] temp = subset;
            subset = AAAsubset;
            message += getLength() + " AAAs removed\n";
            subset = unplayedSubset;
            message += getLength() + " Unplayed songs removed\n";
            subset = legacySubset;
            message += getLength() + " Legacys songs removed\n"
                    + "To see these ranks click the corresponding\nbuttons.";
            subset = temp;
        }
        return message;

    }

    public void newScore(Song song) {
        int index = Collections.binarySearch(Arrays.asList(all), song,
                new Comparators.SongNameAscComparator());
        if (index < 0) {
            JOptionPane.showMessageDialog(null,
                    "Song Not Found.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            song.setDifficulty(all[index].getDifficulty());
            all[index] = song;
        }
    }
}
