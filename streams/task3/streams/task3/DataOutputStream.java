package streams.task3;

import java.io.UnsupportedEncodingException;

import streams.task2.step2.OutputStream;

/**
 * This is an data output stream that wraps an output stream of bytes, allowing
 * to write different Java types such as integers, floats, and strings. The
 * companion class is the class DataInputStream.
 * 
 * @author Pr. Olivier Gruber.
 */

public class DataOutputStream {
	OutputStream os;

	public DataOutputStream(OutputStream os) {
		this.os = os;
	}

	/**
	 * Writes the given double value. Encoded over 8-bytes, with big-endian
	 * encoding.
	 */
	public void writeDouble(double value) {
		writeLong(Double.doubleToRawLongBits(value));
	}

	/**
	 * Writes the given float value. Encoded over 4-bytes, with big-endian encoding.
	 */
	public void writeFloat(float value) {
		writeInt(Float.floatToRawIntBits(value));
	}

	/**
	 * Writes the given long value. Encoded over 8-bytes, with big-endian encoding.
	 */
	public void writeLong(long value) {
		for (int i = 7; i >= 0; i--) {
			writeByte((byte) (value >> (i * 8)));
		}
	}

	/**
	 * Writes the given integer value Encoded over 4-bytes, with big-endian
	 * encoding.
	 */
	public void writeInt(int value) {
		for (int i = 3; i >= 0; i--) {
			writeByte((byte) (value >> (i * 8)));
		}
	}

	/**
	 * Writes the given short value Encoded over 2-bytes, with big-endian encoding.
	 */
	public void writeShort(short value) {
		writeByte((byte) ((value >> 8) & 0xFF));
		writeByte((byte) (value & 0xFF));
	}

	/**
	 * Writes the given byte value
	 */
	public void writeByte(byte value) {
		os.write(value);
	}

	/**
	 * Writes the given boolean value Encoded over 1-bytes.
	 */
	public void writeBoolean(boolean value) {
		if (value) {
			writeByte((byte) 1);
		} else {
			writeByte((byte) 0);
		}
	}

	/**
	 * Writes the given character as UTF-8 encoded character.
	 */
	public void writeChar(char c) {
		writeByte((byte)c);
	}

	/**
   * Writes the given string as a sequence of 
   * UTF-8 encoded characters. 
 * @throws UnsupportedEncodingException 
   */
  public void writeUTF(String s) {
	  writeShort((short) s.length());
	  for (int i = 0;i<s.length();i++) {
	       writeChar(s.charAt(i));
	  }

	}

}
