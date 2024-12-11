package oop.streams;

import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * This is an data input stream that wraps an input stream of bytes, allowing to
 * read different Java types such as integers, floats, and strings. The
 * companion class is the class DataOutputStream.
 * 
 * @author Pr. Olivier Gruber.
 */

public class DataInputStream {
	InputStream is;

	public DataInputStream(InputStream is) {
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
		return (char) readByte();
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
	    short length = readShort();
	    byte[] tab= new byte[length];
	    for (int i=0;i<length;i++) {
	    	tab[i]=readByte();
	    }
	    return new String(tab,StandardCharsets.ISO_8859_1);


}}
