package oop.streams.wjs;

import java.io.IOException;

import oop.streams.InputStream;

public class WrappedJavaInputStream implements InputStream {

  java.io.InputStream m_is;

  public WrappedJavaInputStream(java.io.InputStream is) {
    m_is = is;
  }

  @Override
  public int available() {
	  try {
		return m_is.available();
	} catch (IOException e) {
		return 0;
	}
  }

  @Override
  public byte read() {
	try {
		return (byte) m_is.read();
	} catch (IOException e) {
		return 0;
	}
  }

}
