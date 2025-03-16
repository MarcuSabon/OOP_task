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
    private final byte[] buffer;
    private int bufferIndex = 0;
    private boolean isClosed = false;

    public BufferedByteOutputStream(int capacity, OutputStream os) {
        this.capacity = capacity;
        this.os = os;
        this.buffer = new byte[capacity];
    }

    @Override
    public void set(Listener l) {
        os.set(l);
    }

    @Override
    public void close() {
        flush();
        isClosed = true;
        os.close();
    }

    @Override
    public boolean closed() {
        return isClosed;
    }

    @Override
    public boolean available() {
        return bufferIndex > 0;
    }

    @Override
    public void write(byte bits) {
        if (bufferIndex >= capacity) {
            flush();
        }
        buffer[bufferIndex++] = bits;
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

    private void flush() {
        if (bufferIndex > 0) {
            os.write(buffer, 0, bufferIndex);
            bufferIndex = 0;
        }
    }
}