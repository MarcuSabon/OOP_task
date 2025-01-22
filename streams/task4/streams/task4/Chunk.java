package streams.task4;

public class Chunk {
	private byte[] bytes;
    private Chunk next;
    
    public Chunk(byte[] bytes) {
        this.bytes = bytes;
        this.next = null;
    }
    
    public Chunk get_next() {
    	return next;
    }
    public byte[] get_bytes() {
    	return bytes;
    }
    public void set_next(Chunk next) {
    	this.next = next;
    }
    public void set_bytes(byte[] bytes) {
    	this.bytes = bytes;
    }
}
