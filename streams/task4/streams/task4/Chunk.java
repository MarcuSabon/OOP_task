package streams.task4;

public class Chunk {
	byte[] bytes;
    Chunk next;

    Chunk(byte[] bytes) {
        this.bytes = bytes;
        this.next = null;
    }
}
