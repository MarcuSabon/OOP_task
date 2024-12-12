package streams.task4;

import oop.streams.OutputStream;

public class ByteArrayOutputStream implements OutputStream {

	protected byte[] buffer;
	protected int position;
	protected int size;

	public ByteArrayOutputStream(byte buffer[], int offset, int length) {
		this.buffer = buffer;
		this.position = offset;
		this.size = length;
	}

	public ByteArrayOutputStream(byte buffer[]) {
		this(buffer, 0, buffer.length);
	}

	@Override
	public int available() {
		if (size - position +1 == 0) {
			return 0;
		} else if (position < size) {
			return size - position;
		}
		return -1;
	}

	@Override
	public void write(byte value) {
		if ((available() == -1) || (available() == 0)) {
			throw new IllegalStateException("Fin du flux");
		}

		if (position >= buffer.length) {

			byte[] newTab = new byte[buffer.length + position];
			System.arraycopy(buffer, 0, newTab, 0, buffer.length);
			buffer = newTab;
		}
		buffer[position] = value;
		position++;
	}

	/**
	 * Returns an array containing the bytes written to this output stream.
	 */
	public byte[] getBytes() {
		return buffer;
	}

	/**
	 * Fills up the given array, starting at the given offset, with the bytes
	 * contained in this stream.
	 */
	public void getBytes(byte bytes[], int offset) {
		for (int i = offset; i < bytes.length; i++) {
			buffer[i] = bytes[i];
		}
	}

	/**
	 * @return the current size of this output stream, that is, the number of bytes
	 *         that have been written.
	 */
	public int getSize() {
		return position;
	}

}
