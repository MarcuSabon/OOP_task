package streams.task5.hm;

import java.io.IOException;

import oop.streams.InputStream;
import oop.streams.OutputStream;

public class Script {
	/*
	 * words: the array of secret words. is: the input stream that provides the
	 * player's moves. os: the output stream to print to.
	 */

	private String[] words;
	private String[] crypted_words;
	private CharReader is;
	private PrintStream ps;
	private HangedMan hangedMan;

	public Script(String words[], InputStream is, OutputStream os) throws IOException {
		this.ps = new PrintStream(os);
		this.words = words;
		this.is = new CharReader(is);
		this.hangedMan = new HangedMan(words);
		this.crypted_words = Scriptwords();
	}

	/*
	 * Plays the script.
	 */
	public String[] Scriptwords() throws IOException {
		int SIZE_MAX = 100;
		String[] lines = new String[SIZE_MAX];
		String line = null;
		int i = 0;
		while ((line = is.readLine()) != null) {
			if (line.equals("done")) {
				break;
			}
			if (i >= SIZE_MAX) {
				System.out.println("Erreur taille max");
				break;
			}
			lines[i] = line;
			i++;
		}
		String[] result = new String[i];
		System.arraycopy(lines, 0, result, 0, i);
		return result;
	}

	public void play() {
		int ntries = (int)crypted_words[0].toCharArray()[8]-48;
		int n = (int)crypted_words[0].toCharArray()[6]-48;
		hangedMan.newGame(n, ntries);
		for (int i = 0; i < crypted_words.length-1; i++) {
			ps.print(crypted_words[i] + "\n");
			System.out.print(crypted_words[i] + "\n");
			if(i!=0) {
			hangedMan.play(crypted_words[i].toCharArray()[7]);
			}
		}
		if (crypted_words[crypted_words.length - 1].equals("win")) {
			ps.print("OK: win\n");
			System.out.print("OK: win\n");
		}
		if (crypted_words[crypted_words.length - 1].equals("loss")) {
			ps.print("OK: loss\n");
			System.out.print("OK: loss\n");
		}
	}

	/*
	 * Give access to the instance of our game.
	 */
	public HangedMan game() {
		return hangedMan;
	}
}