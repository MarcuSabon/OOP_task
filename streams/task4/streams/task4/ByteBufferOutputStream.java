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
		if (size - position == 0) {
			agrandir();	
		}
		else if(available() < 0) {
			throw new IllegalStateException("End of Stream");
		}

		
	}

	@Override
	public byte[] getBytes() {
		if (position > 0) {
			byte[] last_bytes = new byte[position];
		    for (int i = 0; i < position; i++) {
		        last_bytes[i] = buffer[i];
		    } 
	        Chunk newChunk = new Chunk(last_bytes);

	        if (head == null) {
	            head = newChunk;
	            tail = head;
	        } else {
	            tail.set_next(newChunk);
	            tail = newChunk;
	        }
	    }
		
		
		byte[] liste_fin = new byte[compteur];
		Chunk current = head;
		int index = 0;
		while (current != null) {
			byte[] currentBytes = current.get_bytes();
			for (int i = 0; i < currentBytes.length && index < liste_fin.length; i++) {
				liste_fin[index++] = currentBytes[i];
			}
			current = current.get_next();
		}

		return liste_fin;
	}

	@Override
	public int getSize() {
		return compteur;
	}

	public void agrandir() {

		Chunk newChunk = new Chunk(buffer);
		if (head == null) {
			head = newChunk;
			tail = head;

		} else {

			tail.set_next(newChunk);
			tail.get_next().set_next(null);
			tail = tail.get_next();
		}
		buffer = new byte[buffer.length];
		position = 0;

	}
}
