package streams.task3;

import java.io.IOException;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import streams.task2.step2.InputStream;

/**
 * This is an data input stream that wraps an input stream of bytes, allowing to
 * read different Java types such as integers, floats, and strings. The
 * companion class is the class DataOutputStream.
 * 
 * @author Pr. Olivier Gruber.
 */

public class DataInputStream {
	InputStream is;

	DataInputStream(InputStream is) {
		this.is = is;
	}

	/**
	 * @return true if the end of the stream has been reached, false otherwise.
	 */
	public boolean endOfStream() {
		return is.available() <= 0;
	}

	/**
	 * @return a double value that was encoded over 8-bytes, with big-endian
	 *         encoding.
	 */
	public double readDouble() {
		int valU1 = readByte();// & 0xFF;
		int valU2 = readByte();// & 0xFF;
		int valU3 = readByte();// & 0xFF;
		int valU4 = readByte();// & 0xFF;
		int valU5 = readByte();// & 0xFF;
		int valU6 = readByte();// & 0xFF;
		int valU7 = readByte();// & 0xFF;
		int valU8 = readByte();// & 0xFF;
		return ((valU1 << 56) | (valU2 << 48) | (valU3 << 40) | (valU4 << 32) | (valU5 << 24) | (valU6 << 16)
				| (valU7 << 8) | valU8);
	}

	/**
	 * @return a float value that was encoded over 4-bytes, with big-endian
	 *         encoding.
	 */
	public float readFloat() {
		int valU1 = readByte() & 0xFF;
		int valU2 = readByte() & 0xFF;
		int valU3 = readByte() & 0xFF;
		int valU4 = readByte() & 0xFF;
		int valeur = ((valU1 << 24) | (valU2 << 16) | (valU3 << 8) | valU4);
		return Float.intBitsToFloat(valeur);
	}

	/**
	 * @return a long value that was encoded over 8-bytes, with big-endian encoding.
	 */
	public long readLong() {
		int valU1 = readByte();// & 0xFF;
		int valU2 = readByte();// & 0xFF;
		int valU3 = readByte();// & 0xFF;
		int valU4 = readByte();// & 0xFF;
		int valU5 = readByte();// & 0xFF;
		int valU6 = readByte();// & 0xFF;
		int valU7 = readByte();// & 0xFF;
		int valU8 = readByte();// & 0xFF;
		int valeur = ((valU1 << 56) | (valU2 << 48) | (valU3 << 40) | (valU4 << 32) | (valU5 << 24) | (valU6 << 16)
				| (valU7 << 8) | valU8);
		return Double.doubleToLongBits(valeur);
	}

	/**
	 * @returns a signed integer value that was encoded over 4-bytes, with
	 *          big-endian encoding.
	 */
	public int readInt() {
		int valU1 = readByte() & 0xFF;
		int valU2 = readByte() & 0xFF;
		int valU3 = readByte() & 0xFF;
		int valU4 = readByte() & 0xFF;
		return ((valU1 << 24) | (valU2 << 16) | (valU3 << 8) | valU4);
	}

	/**
	 * @returns a signed short value that was encoded over 2-bytes, with big-endian
	 *          encoding.
	 */
	public short readShort() {
		int valU1 = readByte() & 0xFF;
		int valU2 = readByte() & 0xFF;
		return (short) ((valU1 << 8) | valU2);
	}

	/**
	 * @returns a signed byte value
	 */
	public byte readByte() {
		return is.read();
	}

	/**
	 * @returns a boolean value. Encoded over 1-bytes.
	 */
	public boolean readBoolean() {
		return readByte() != 0;
	}

	/**
	 * Reads a UTF-8 encoded character
	 * 
	 * @return the read character
	 * @throws UTFDataFormatException
	 * @throws IllegalStateException  if the next bytes cannot be decoded as a
	 *                                utf8-encoded character.
	 */
	public char readChar() {
		int c1 = readByte() & 0xFF;

	    if (c1 <= 0x7F) {
	        // 1-byte character: 0xxxxxxx
	        return (char) c1;
	    } else if (c1 >= 0xC0 && c1 <= 0xDF) {
	        // 2-byte character: 110xxxxx 10xxxxxx
	        int c2 = readByte() & 0xFF;
	        if ((c2 & 0xC0) != 0x80) {
	            throw new IllegalStateException("Malformed input: invalid UTF-8 sequence");
	        }
	        return (char) (((c1 & 0x1F) << 6) | (c2 & 0x3F));
	    } else if (c1 >= 0xE0 && c1 <= 0xEF) {
	        // 3-byte character: 1110xxxx 10xxxxxx 10xxxxxx
	        int c2 = readByte() & 0xFF;
	        int c3 = readByte() & 0xFF;
	        if ((c2 & 0xC0) != 0x80 || (c3 & 0xC0) != 0x80) {
	            throw new IllegalStateException("Malformed input: invalid UTF-8 sequence");
	        }
	        return (char) (((c1 & 0x0F) << 12) | ((c2 & 0x3F) << 6) | (c3 & 0x3F));
	    } else {
	        throw new IllegalStateException("Malformed input: invalid UTF-8 sequence");
	    }
	    }

	/**
	 * Reads a string of UTF-8 encoded characters.
	 * 
	 * @return the read string.
	 * @throws UTFDataFormatException
	 * @throws IllegalStateException        if the next bytes cannot be decoded as a
	 *                                      utf8-encoded character.
	 * @throws UnsupportedEncodingException
	 */
	public String readUTF() {
		StringBuilder result = new StringBuilder();
	    char c = readChar();
	    while (c != '\n') {
	        result.append(c);
	        c = readChar();
	    }
	    return result.toString();

}}
