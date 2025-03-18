/*
 *  Copyright (C) Pr. Olivier Gruber <olivier dot gruber at acm dot org>
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package oop.utils;

import oop.streams.OutputStream;

/*
 * An instance of this class wraps an output stream
 * and offers a buffered output stream, that is, 
 * an output stream that always accepts to write all 
 * the given bytes, buffering them if necessary.
 * 
 * The buffering scheme is a list of chunks, each chunk
 * being of the capacity given to the constructor. The 
 * written bytes are buffered until they can be written
 * through the wrapped output stream.
 */

public class BufferedByteOutputStream implements OutputStream {
	private final int capacity;
	private final OutputStream os;
	private Chunk Head;
	private Chunk tail;
	private boolean fermé = false;

	public BufferedByteOutputStream(int capacity, OutputStream os) {
		this.capacity = capacity;
		this.os = os;
		this.Head = new Chunk(capacity);
		this.tail = Head;
	}

	@Override
	public void set(Listener l) {
		os.set(l);
	}

	@Override
	public void close() {
		flush();
		fermé = true;
		os.close();
	}

	@Override
	public boolean closed() {
		return fermé;
	}

	@Override
	public boolean available() {
		return !Head.isEmpty();
	}
	public boolean full() {
		return tail.isFull();
	}
	@Override
    public void write(byte bits) {
		//System.out.println("Current tail size: " + tail.getSize());

	    if (tail.isFull()) {
	        flush();
	        Chunk newChunk = new Chunk(capacity);
	        tail.setNext(newChunk);
	        tail = newChunk;
	        //System.out.println("New chunk created: " + (tail.getNext() != null));
	    }
	    tail.addByte(bits);
    }

	@Override
	public int write(byte[] bytes, int offset, int length) {
		int written = 0;
		for (int i = offset; i < offset + length; i++) {
			write(bytes[i]);
			written++;
		}
		return written;
	}

	public void flush() {
	    while (Head != null) {
	        os.write(Head.getBytes(), 0, Head.getSize());
	        Head = Head.getNext();
	    }
	    if (Head == null) { 
	        Head = new Chunk(capacity);
	    }
	    tail = Head;
	}


}
