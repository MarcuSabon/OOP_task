package streams.task4;

import oop.streams.InputStream;

public class ByteArrayInputStream implements InputStream {

	protected byte[] buffer;
	protected int position;
	protected int size;

	public ByteArrayInputStream(byte buffer[], int offset, int length) {
		this.buffer = buffer;
		this.position = offset;
		this.size = length;
	}

	public ByteArrayInputStream(byte buffer[]) {
		this(buffer, 0, buffer.length);
	}

	public ByteArrayInputStream(ByteArrayOutputStream s) {
		this.size = s.getSize();
		this.buffer = s.getBytes();
	}

	@Override
	public int available() {
		if (size - position + 1 == 0) {
			return 0;
		} else if (position < size) {
			return size - position;
		}
		return -1;
	}

	@Override
	public byte read() {
		if (position >= size) {
			throw new IllegalStateException("no read more");
		}
		byte value = buffer[position];
		position++;
		return value;
	}

}
