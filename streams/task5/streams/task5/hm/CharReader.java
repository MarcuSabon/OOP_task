package streams.task5.hm;

import oop.streams.InputStream;

public class CharReader {

	private InputStream is;

	public CharReader(InputStream is) {
		this.is = is;
	}

	/*
	 * Reads a line, that is, a sequence of characters terminated with a '\n', but
	 * the '\n' is not included in the returned string.
	 */
	public String readLine() {
		StringBuilder mot = new StringBuilder();
		char c;

		while (true) {
			c = readChar();
			if (c == '\n' || c == '\0') {
				break;
			}
			mot.append(c);
		}
		return mot.toString();
	}

	/*
	 * Read a char that is not '\n'.
	 */
	public char readChar() {
		int data = is.read();
		if (data == -1) {
			return '\0';
		}
		char c = (char) data;
		return c;
	}
}