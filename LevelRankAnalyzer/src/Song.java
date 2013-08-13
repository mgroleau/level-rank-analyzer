public class Song {
	private int perfects;
	private int goods;
	private int averages;
	private int misses;
	private int boos;
	private int score;
	private String scoreString;
	private int rank;
	private int difficulty;
	private int maxCombo;
	private int rankDif;
	private String songName;
	private Boolean AAAd = false;
	private Boolean FCd = false;
	private Boolean blackflag = false;
	private Boolean avflag = false;
	private Boolean booflag = false;
	private Boolean missflag = false;
	private Boolean isLegacy = false;
	private Boolean unplayed = false;

	public Song(int perfects, int goods, int averages, int misses, int boos,
			String score, String rank, String difficulty, String songName,
			String maxCombo) {
		this.perfects = perfects;
		this.goods = goods;
		this.averages = averages;
		this.misses = misses;
		this.boos = boos;
		this.scoreString = score;
		this.score = Integer.parseInt(removeCommaSpaces(score));
		this.rank = Integer.parseInt(removeCommaSpaces(rank));
		this.difficulty = Integer.parseInt(removeCommaSpaces(difficulty));
		this.songName = removeCommaSpaces(songName);
		this.maxCombo = Integer.parseInt(removeCommaSpaces(maxCombo));
		check();

	}

	public Song(int rank, int perfects, int goods, int averages, int misses, int boos,
			String songName, int maxCombo, boolean FCd) {
		this.perfects = perfects;
		this.goods = goods;
		this.averages = averages;
		this.misses = misses;
		this.boos = boos;
		this.songName = songName;
		this.rank = rank;
		this.maxCombo = maxCombo;
		scoreString = maxCombo * 1000 + perfects * 500 + 250 * goods + 50
				* averages - misses * 300 - boos * 15 + (perfects * 50)
				+ (goods * 25) + (averages * 5) - (misses * 10) - (boos * 5)
				+ "";
		if (FCd == true)
			scoreString += "*";
		this.score = Integer.parseInt(removeCommaSpaces(scoreString));
		check();
	}

	public int getRankDif() {
		return rankDif;
	}

	public void setRankDif(int rankDif) {
		this.rankDif = rankDif;
	}

	public Boolean getUnplayed() {
		return unplayed;
	}

	public String removeCommaSpaces(String string) {
		String newString = "";
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) != ',')
				newString += string.charAt(i);
		}

		if (newString.charAt(newString.length() - 1) == ' ') 
			newString = newString.substring(0, newString.length() - 1);
		
		if (newString.charAt(newString.length() - 1) == '*') {
			FCd = true;
			if (goods + averages + boos == 0)
				AAAd = true;
			newString = newString.substring(0, newString.length() - 1);
		}

		return newString;
	}

	public String toString(String command) {
		String result = "";
		if (command.equals("null"))
			result = String.format("%-10d%-12d%-53s%-9d%-6d%-5d%-7d%-6d%-7d\n",
					rank, difficulty, songName, perfects, goods, averages,
					misses, boos, maxCombo);
		if (command.equals("Compare"))
			result = String.format("%-10d%-12d%-53s%-9d%-6d%-5d%-7d%-6d%-11s",
					rank, difficulty, songName, perfects, goods, averages,
					misses, boos, difFormat());
		if (command.equals("Compare2"))
			result = String.format(" %-10d%-9d%-6d%-5d%-7d%-6d", rank,
					perfects, goods, averages, misses, boos);
		return result;
	}

	public String difFormat() {
		String rankString = "| " + rankDif;
		for (int i = rankString.length(); i < 9; i++)
			rankString += " ";
		rankString += "|";
		return rankString;
	}

	public int getPerfects() {
		return perfects;
	}

	public int getGoods() {
		return goods;
	}

	public int getAverages() {
		return averages;
	}

	public int getMisses() {
		return misses;
	}

	public int getBoos() {
		return boos;
	}

	public int getScore() {
		return score;
	}

	public int getRank() {
		return rank;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public int getMaxCombo() {
		return maxCombo;
	}

	public String getSongName() {
		return songName;
	}

	public Boolean getAAAd() {
		return AAAd;
	}

	public Boolean getFCd() {
		return FCd;
	}

	public Boolean getBlackflag() {
		return blackflag;
	}

	public Boolean getAvflag() {
		return avflag;
	}

	public Boolean getBooflag() {
		return booflag;
	}

	public Boolean getMissflag() {
		return missflag;
	}

	public Boolean getIsLegacy() {
		return isLegacy;
	}

	public String getScoreString() {
		return scoreString;
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public void check() {
		if ((misses + averages + goods + boos) == 1) {
			if (misses == 1)
				missflag = true;
			if (averages == 1)
				avflag = true;
			if (boos == 1)
				booflag = true;
			if (goods == 1)
				blackflag = true;
		}
		if (this.score == 0)
			unplayed = true;

		if (songName.equals("Trip to the Moon (Club Remix)")
				|| songName.equals("SuperMario 8bit Eighties Remix")
				|| songName.equals("Seattle's Finest")
				|| songName.equals("Dance 2 This")
				|| songName.equals("Halcyon [SGX]")
				|| songName.equals("Love You Anymore")
				|| songName.equals("Activity Unbroken")
				|| songName.equals("Falling Into You")
				|| songName.equals("Freak Out")
				|| songName.equals("Computer Dreams")
				|| songName.equals("For Who I Am")
				|| songName.equals("This Beat is For Tha Streets!")
				|| songName.equals("Soul Shift (FFR Edit)")
				|| songName.equals("Stay With Me (Unlikely)")
				|| songName.equals("Too Tight")
				|| songName.equals("Skellybones")
				|| songName.equals("Moonlight Sonata")
				|| songName.equals("Raindrops")
				|| songName.equals("Spontaneous Hydroxide")
				|| songName.equals("Euphoria [Antinomie]")
				|| songName.equals("The Color of Battle")
				|| songName.equals("Get on the Move")
				|| songName.equals("100 Bar Blackout")
				|| songName.equals("Salad for Your Tuesday")
				|| songName.equals("Resistance")
				|| songName.equals("Cat Walk II")
				|| songName.equals("Bubbleman Stage Theme")
				|| songName.equals("The Return F SuperDouble")
				|| songName.equals("FUNK IN G")
				|| songName.equals("Terror From Beyond")
				|| songName.equals("Mega Man 2 - Dr. Wily Theme #1")
				|| songName.equals("Shag") || songName.equals("136.6")
				|| songName.equals("Secret Zombie Room")
				|| songName.equals("Alpha Helix")
				|| songName.equals("Dendrite")
				|| songName.equals("She Bangs (William Hung Version)")
				|| songName.equals("Massive (FFR Edit)")
				|| songName.equals("Slap Guitar 2")
				|| songName.equals("Life Goes On")
				|| songName.equals("Crystalis - House Leaves (Remix)")
				|| songName.equals("Burnout")
				|| songName.equals("Breaking the Common")
				|| songName.equals("Story of Snowman and Sunshine Girl")
				|| songName.equals("Contradistinction")
				|| songName.equals("Ball of Malt")
				|| songName.equals("Until The End")
				|| songName.equals("Metalman Goes Clubbing")
				|| songName.equals("Emotions")
				|| songName.equals("Blank Knowing")
				|| songName.equals("Legend of Zelda Remix")
				|| songName.equals("4F73R M3 (FFR Edit)")
				|| songName.equals("The Last Firstborn")
				|| songName.equals("Stalled Out and Sushi Bars")
				|| songName.equals("Beethoven Virus")
				|| songName.equals("Lethal Injection")
				|| songName.equals("CIA Rave")
				|| songName.equals("Rondo Alla Turca")
				|| songName.equals("Uber Rave") || songName.equals("HELLBEAT")
				|| songName.equals("Caprice")
				|| songName.equals("Runny Mornings (SGX Mix)")
				|| songName.equals("Flight of the Bumblebee")
				|| songName.equals("Molto Vivace")
				|| songName.equals("One Minute Waltz")
				|| songName.equals("Revolutionary Etude"))
			isLegacy = true;
	}
}
