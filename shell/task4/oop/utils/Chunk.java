package oop.utils;

import java.util.Arrays;

class Chunk {
    private final byte[] bytes;
    private Chunk next;
    private int size;

    public Chunk(int capacity) {
        this.bytes = new byte[capacity];
        this.next = null;
        this.size = 0;
    }

    public void addByte(byte b) {
        if (size < bytes.length) {
            bytes[size] = b;
            size++;
        } else {
            throw new IllegalStateException("Chunk is full");
        }
    }

    public byte[] getBytes() {
        return Arrays.copyOf(bytes, size);
    }

    public Chunk getNext() {
        return next;
    }

    public void setNext(Chunk next) {
        this.next = next;
    }

    public boolean isFull() {
        return size >= bytes.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size >= 0 && size <= bytes.length) {
            this.size = size;
        } else {
            throw new IllegalArgumentException("Size out of bounds");
        }
    }
}
