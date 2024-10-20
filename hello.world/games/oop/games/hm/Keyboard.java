package oop.games.hm;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.Reader;

public class Keyboard {

	PrintStream out;
	Reader reader;
	BufferedReader br;

	public Keyboard(InputStream in, PrintStream out) {
		this.out = out;
		this.reader = new InputStreamReader(in);
		this.br = new BufferedReader(reader);
	};

	/*
	 * If given a message, this method prints the message and reads one character,
	 * the first of the line entered by the player. It will be used to read the
	 * letter proposals and the character to know if the player want to play again
	 * or not, that is, the character (y/n).
	 */
	public char read(String msg) throws IOException {
		if (msg != null) {
			out.print(msg);
			out.flush();
		}
		String line = br.readLine();
		char c = line.charAt(0);
		return c;
	};
}