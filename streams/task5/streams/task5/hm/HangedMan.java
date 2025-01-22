package streams.task5.hm;

public class HangedMan {
	/*
	 * Imposed field to hold the secret words.
	 */
	private char[] mot_a_deviner;
	private char[] lettres_devines;
	private int nbr_incorrecte;
	char[] words[];

	/*
	 * The two constructors.
	 */

	public HangedMan(char[] words[]) {
		this.words = new char[words.length][];
		for (int i = 0; i < words.length; i++) {
			this.words[i] = words[i];
		}

	};

	public HangedMan(String words[]) {
		this.words = new char[words.length][];
		for (int i = 0; i < words.length; i++) {
			this.words[i] = words[i].toCharArray();
		}
	};

	/*
	 * Initializes a new game with nth word.
	 */
	public void newGame(int n) {

		this.mot_a_deviner = words[n];
		this.lettres_devines = new char[mot_a_deviner.length];

		for (int i = 0; i < lettres_devines.length; i++) {
			lettres_devines[i] = '_';
		}

		this.nbr_incorrecte = 7;
	};

	public void newGame(int n, int ntries) {
		this.nbr_incorrecte = ntries;
		this.mot_a_deviner = words[n];
		this.lettres_devines = new char[mot_a_deviner.length];
		for (int i = 0; i < lettres_devines.length; i++) {
			lettres_devines[i] = '_';
		}
	}

	/*
	 * Once a game has been initialized, this method is used to propose a character
	 */
	public void play(char c) {
		boolean fini = false;
		for (int i = 0; i < mot_a_deviner.length; i++) {
			if (mot_a_deviner[i] == c) {
				if (lettres_devines[i] != c) {
					lettres_devines[i] = c;
					fini = true;
				}
			}
		}
		if (!fini) {
			nbr_incorrecte--;
		}
	};

	/*
	 * Returns a string that corresponds to the current guessed letters. For
	 * example: -a-a-a for the secret word "banana" and the letter 'a' that has been
	 * guessed correctly.
	 */
	public String guessed() {
		return new String(lettres_devines);
	};

	/*
	 * Returns true if the player has won. false otherwise.
	 */
	public boolean won() {
		for (char c : lettres_devines) {
			if (c == '_') {
				return false;
			}
		}
		return true;
	};

	/*
	 * Returns true if the player has lost. false otherwise.
	 */
	public boolean lost() {
		return (nbr_incorrecte <= 0);
	};
}