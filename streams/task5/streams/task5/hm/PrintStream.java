package streams.task5.hm;

import oop.streams.OutputStream;

class PrintStream {

	private OutputStream my_os;

	PrintStream(oop.streams.OutputStream os) {
		this.my_os = os;
	}

	void print(String s) {
		for (int i = 0; i < s.length(); i++) {
			my_os.write((byte) s.charAt(i));
		}

	}

	void println(String s) {
		print(s + System.lineSeparator());
	}
}