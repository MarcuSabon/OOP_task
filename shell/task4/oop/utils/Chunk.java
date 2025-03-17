package oop.utils;

import java.util.Arrays;

class Chunk {
    private byte[] bytes;
    private Chunk next;
    private int size=0;

    public Chunk(int capacity) {
        this.bytes = new byte[capacity];
        this.next = null;
        
    }

    public Chunk getNext() {
        return next;
    }
    
    public byte[] getBytes() {
        return Arrays.copyOf(bytes, size);
    }
    
    public void setNext(Chunk next) {
        this.next = next;
    }
    
    public void addByte(byte b) {
        if (size < bytes.length) {
            bytes[size] = b;
        }
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
    public void setSize(int new_size) {
        size = new_size;
         
    }
}
