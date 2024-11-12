package streams.task2.step2;

/*
 * This is an implementation of an output stream that
 * is based on the use of a byte array to hold the 
 * bytes that will be written via the method "write" on this stream. 
 * The method "write" must never fail, meaning the internal array
 * must be grown as necessary.
 * The close operation has not effect on this implementation,
 * it is a non-operation (no-op). 
 * 
 * @author Pr. Olivier Gruber.
 */

public class OutputStream {
  private byte[] tab;
  private int delta;
  private int position;
  /**
   * Constructs an output stream with an initial capacity
   * of the given number of bytes.
   * 
   * @param capacity is the initial length of the byte array
   * @param delta is the number of bytes to grow the array by.
   */
  public OutputStream(int capacity, int delta) {
    this.tab = new byte[capacity];
    this.delta = delta;
    this.position = 0;
  }
  
  /**
   * Returns an array containing the bytes
   * written to this output stream.
   */
  public byte[] getBytes() { 
	  byte[] result = new byte[position]; 
      System.arraycopy(tab, 0, result, 0, position); 
      return result;
  }

  /**
   * @return the number of bytes written
   * to this output stream,
   */
  public int getSize() {
    return position;
  }

  /**
   * Writes the given byte into this stream.
   * This method must never fail, the array
   * must be grown if full, by adding 64 bytes
   * each time the array is grown.
   */
  public void write(byte value) {
	  if (position >= tab.length) {
          
          byte[] newTab = new byte[tab.length + delta];
          System.arraycopy(tab, 0, newTab, 0, tab.length);
          tab = newTab; 
      }
      tab[position] = value;
      position++; 
  }
}
