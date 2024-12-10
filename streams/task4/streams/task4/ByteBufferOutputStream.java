package streams.task4;

public class ByteBufferOutputStream extends ByteArrayOutputStream {

	private final int chunkSize; 
	private Chunk head; 
	private Chunk tail; 

	public ByteBufferOutputStream(int chunkSize) {
		super(new byte[chunkSize]); 
		this.chunkSize = chunkSize;
		this.head = new Chunk(buffer); 
		this.tail = head;

	}
	@Override
	public void write(byte value) {
		if (available()!=0) {
            agrandir();
        }
        buffer[position++] = value;
	}
	public void agrandir() {
		Chunk newChunk = new Chunk(new byte[chunkSize]);
		tail.next = newChunk;
		tail = newChunk;
		buffer = newChunk.bytes;
		position = 0;
		size += chunkSize;
	}
	
	
	
	public Chunk header(){
		return head;
	}
}

