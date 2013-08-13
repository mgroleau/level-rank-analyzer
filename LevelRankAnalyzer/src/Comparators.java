import java.util.Comparator;

public class Comparators {
	static class PerfectAscComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			int p1 = s1 == null ? 99999 : s1.getPerfects();
			int p2 = s2 == null ? 99999 : s2.getPerfects();
			return p1 - p2;
		}
	}

	static class PerfectDescComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			int p1 = s1 == null ? 0 : s1.getPerfects();
			int p2 = s2 == null ? 0 : s2.getPerfects();
			return p2 - p1;
		}
	}

	static class GoodAscComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			int g1 = s1 == null ? 99999 : s1.getGoods();
			int g2 = s2 == null ? 99999 : s2.getGoods();
			return g1 - g2;
		}
	}

	static class GoodDescComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			int g1 = s1 == null ? 0 : s1.getGoods();
			int g2 = s2 == null ? 0 : s2.getGoods();
			return g2 - g1;
		}
	}

	static class AverageAscComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			int a1 = s1 == null ? 99999 : s1.getAverages();
			int a2 = s2 == null ? 99999 : s2.getAverages();
			return a1 - a2;
		}
	}

	static class AverageDescComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			int a1 = s1 == null ? 0 : s1.getAverages();
			int a2 = s2 == null ? 0 : s2.getAverages();
			return a2 - a1;
		}
	}

	static class MissAscComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			int m1 = s1 == null ? 9999 : s1.getMisses();
			int m2 = s2 == null ? 9999 : s2.getMisses();
			return m1 - m2;
		}
	}

	static class MissDescComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			int m1 = s1 == null ? 0 : s1.getMisses();
			int m2 = s2 == null ? 0 : s2.getMisses();
			return m2 - m1;
		}
	}

	static class BooAscComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			int b1 = s1 == null ? 99999 : s1.getBoos();
			int b2 = s2 == null ? 99999 : s2.getBoos();
			return b1 - b2;
		}
	}

	static class BooDescComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			int b1 = s1 == null ? 0 : s1.getBoos();
			int b2 = s2 == null ? 0 : s2.getBoos();
			return b2 - b1;
		}
	}

	static class RankAscComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			int r1 = s1 == null ? 9999999 : s1.getRank();
			int r2 = s2 == null ? 9999999 : s2.getRank();
			return r1 - r2;
		}
	}

	static class RankDescComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			int r1 = s1 == null ? 0 : s1.getRank();
			int r2 = s2 == null ? 0 : s2.getRank();
			return r2 - r1;
		}
	}

	static class DifficultyAscComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			int d1 = s1 == null ? 999 : s1.getDifficulty();
			int d2 = s2 == null ? 999 : s2.getDifficulty();
			return d1 - d2;
		}
	}

	static class DifficultyDescComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			int d1 = s1 == null ? 0 : s1.getDifficulty();
			int d2 = s2 == null ? 0 : s2.getDifficulty();
			return d2 - d1;
		}
	}

	static class SongNameAscComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			String sn1 = s1 == null ? "~~" : s1.getSongName();
			String sn2 = s2 == null ? "~~" : s2.getSongName();
			return sn1.compareToIgnoreCase(sn2);
		}
	}

	static class SongNameDescComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			String sn1 = s1 == null ? "" : s1.getSongName();
			String sn2 = s2 == null ? "" : s2.getSongName();
			return sn2.compareToIgnoreCase(sn1);
		}
	}
	
	static class RankDifAscComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			int r1 = s1 == null ? 99999999 : s1.getRankDif();
			int r2 = s2 == null ? 99999999 : s2.getRankDif();
			return r1 - r2;
		}
	}
	
	static class RankDifDescComparator implements Comparator<Song> {
		public int compare(Song s1, Song s2) {
			int r1 = s1 == null ? -99999999 : s1.getRankDif();
			int r2 = s2 == null ? -99999999 : s2.getRankDif();
			return r2 - r1;
		}
	}
}
