package streams.task4;

public class DynamicByteArrayOutputStream extends ByteArrayOutputStream {
	private int delta;

	public DynamicByteArrayOutputStream(int capacity, int delta) {
		super(new byte[capacity]);
		this.delta = delta;
	}

	@Override
	public void write(byte value) {
		if (available() !=0) {
			grow();
		}
		buffer[position++] = value;

	}

	private void grow() {
		byte[] newboeuf = new byte[buffer.length + delta];
		System.arraycopy(buffer, 0, newboeuf, 0, buffer.length);
		buffer = newboeuf;

	}
}
