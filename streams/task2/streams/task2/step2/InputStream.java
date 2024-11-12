package streams.task2.step2;

import java.io.EOFException;

/*
 * This is an implementation of an input stream that
 * is based on the use of a byte array to hold the 
 * bytes that will be read via the method "read" on this stream. 
 * The close operation has not effect on this implementation,
 * it is a non-operation (no-op). 
 * 
 * @author Pr. Olivier Gruber.
 */

public class InputStream {
	
	private byte[] buffer;
    private int position;    
    private int size;
  /**
   * Constructs an input stream from the given output stream
   */
  public InputStream(OutputStream s) {
    this.buffer = s.getBytes();
    this.position = 0;
    this.size = s.getSize()
;  }

  /**
   * @return the number of available bytes in this input stream.
   *         Returning 0 means that are no available bytes but 
   *         some might become available later.
   *         Returning -1 indicates the end of the stream.
   */
  public int available() {
	 //return 0 quand ca peut revenir
	  // return -1 quand il y en aura plus
	  if (position < size) {
          return size - position; 
      }
      return -1;
  }
  
  /**
   * Reads the next byte from this input stream. <br>
   * 
   * @return the read byte
   * @throws IllegalStateException if there are no more byte to read
   */
  public byte read() {
	  if (position >= size) {
          throw new IllegalStateException("no read more");
      }
	  byte value = buffer[position]; 
      position++;                
      return value;
  }
  
}
