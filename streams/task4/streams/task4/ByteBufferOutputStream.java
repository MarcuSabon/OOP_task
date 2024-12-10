package streams.task4;

public class ByteBufferOutputStream extends ByteArrayOutputStream {

	private Chunk head; 
	private Chunk tail; 
	private int compteur;

	public ByteBufferOutputStream(int chunkSize) {
		super(new byte[chunkSize]); 
		this.head = null; 
		this.tail = head;
		this.compteur = 0;

	}
	@Override
	public void write(byte value) {
		buffer[position] = value;
		position++;
		compteur++;
		if (available()==0) {
            agrandir();
        }
	}
	
	@Override
	public byte[] getBytes() {
		while(head!=null) {
			buffer = head.get_bytes(); // changer ecrase le buffer
			head = head.get_next();
		}
		return buffer;
	}
	
	@Override
	public int getSize() {
		return compteur;
	}
	
	public void agrandir() {
		Chunk newChunk = new Chunk(buffer);
		if(head == null) {
			head = newChunk;
	        tail= head;
	        head.set_bytes(buffer);
	        position = 0;
		}
		else {
			
			tail.set_next(newChunk);
	        tail.get_next().set_next(null);
	        tail = tail.get_next();
	        newChunk.set_bytes(buffer);
	        position = 0;
		}
		
        
    }
}

