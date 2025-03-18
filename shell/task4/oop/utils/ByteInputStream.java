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

import oop.streams.InputStream;

public class ByteInputStream extends ByteStream implements InputStream {
	public ByteInputStream(ByteOutputStream os) {
		super(os.m_ring);
	}

	@Override
	public byte read() {
		if (m_ring.empty()) {
			throw new IllegalStateException("The stream is empty");
		} else {
			return m_ring.pull();
		}
	}

	@Override
	public int read(byte[] bytes, int offset, int length) {
		int counter = 0;
		for (int i = offset; i < offset + length; i++) {
			if (m_ring.empty()) {
				break;
			}
			bytes[i] = read();
			counter++;
		}
		return counter;
	}
	@Override
	public boolean available() {
		return !m_ring.empty();
	}
}