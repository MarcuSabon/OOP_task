package oop.utils;

import oop.streams.OutputStream;
import oop.tasks.Task;
import java.util.Arrays;
import oop.runtime.EventPump;
import oop.streams.InputStream;

public class ByteStreamTest {

	private static byte[] m_bytes = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private static byte[] m_word = { 'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd' };
	private static byte[] m_spec = { '\n', (byte) 'é', '&', '#', (byte) 'ç', '/', '*', (byte) 'µ', '^', (byte) '£',
			'~' };
	private static byte[] m_failed = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private static byte[] m_failed2 = { 0, 0, 1, 0, 1, 0, 1, 1, 0 };
	private static byte[] m_bytestofill = new byte[m_bytes.length];
	private static byte[] m_wordtofill = new byte[m_word.length];
	private static byte[] m_spectofill = new byte[m_spec.length];
	private static byte[] m_failedtofill = new byte[20];
	private static byte[] m_failedtofill2 = { 1, 0, 1, 0, 1, 0, 1, 1, 0 };

	private static byte[] m_bytes3 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private static byte[] m_word3 = { 'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd' };
	private static byte[] m_spec3 = { '\n', (byte) 'é', '&', '#', (byte) 'ç', '/', '*', (byte) 'µ', '^', (byte) '£',
			'~' };

	private static byte[] m_bytestofill3 = new byte[m_bytes.length];
	private static byte[] m_wordtofill3 = new byte[m_word.length];
	private static byte[] m_spectofill3 = new byte[m_spec.length];

	private static byte[] m_word4 = { 'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd' };
	private static byte[] m_wordtofill4 = new byte[m_word4.length];
	private static byte[] m_bytes4 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private static byte[] m_bytestofill4 = new byte[m_bytes4.length];

	private static int variable = 0;
	private static int passed = 0;

	public static void main(String args[]) {
		EventPump ep = new EventPump();
		ep.boot(new Runnable() {
			public void run() {
				test1();
				test2();
				test3();
				test4();
				test5();
				test6();
				test7();
				test8();
				test9();
				test10();
				verif_all();
				shutdown(ep);
			}
		});

	}

	static void verif(byte[] m_test, byte[] m_tofill) {
		Task t = Task.task();
		Task verif = t.newTask("verifTask");

		verif.post(() -> {
			variable++;
			System.out.println("\n----------------------");
			System.out.println("\tTest Numero " + variable);
			System.out.println("----------------------");

			if (Arrays.equals(m_test, m_tofill)) {
				System.out.println("\n" + Arrays.toString(m_tofill));
				System.out.println("\n\tTest PASSED");
				passed++;
			} else {
				System.out.println("\n\tTest FAILED");
			}
			if (variable == 10) {
				System.out.println("\n----------------------------------" + "\n\t PASSED : " + passed + " /10"
						+ "\n(two are meant to fail)");
			}
			verif.terminate();
		});
	}

	static void test(int capacity, byte[] m_bytes, byte[] m_tofill) {

		ByteOutputStream m_os = new ByteOutputStream(capacity);
		ByteInputStream m_is = new ByteInputStream(m_os);

		Task t = Task.task();
		Task producer = t.newTask("producerTask");
		Task consumer = t.newTask("consumerTask");

		producer.post(new Runnable() {
			public void run() {
				for (byte b : m_bytes) {
					m_os.write(b);
					System.out.println("Producer: Wrote " + b);
				}

				m_os.close();
				producer.terminate();

				System.out.println("Closed for producer");
			}
		});

		consumer.post(() -> {
			int idx = 0;
			while (!m_is.closed() || m_is.available()) {
				if (m_is.available()) {
					byte readByte = m_is.read();
					m_tofill[idx++] = readByte;
					System.out.println("Consumer: Read " + readByte);
				} else if (idx == capacity - 1) {
					break;
				} else {
					idx = capacity - 1;
				}

			}

			m_is.close();
			consumer.terminate();

			System.out.println("Closed for consumer");

		});

	}

//////////////////////////////////////////////////////////////	

	// Step 2

//////////////////////////////////////////////////////////////

	static void test2(int capacity, byte[] m_bytes, byte[] m_tofill) {

		ByteOutputStream m_os = new ByteOutputStream(capacity);
		ByteInputStream m_is = new ByteInputStream(m_os);
		BufferedByteOutputStream m_bufferd_stm = new BufferedByteOutputStream(capacity, m_os);

		Task t = Task.task();
		Task producer2 = t.newTask("producer2Task");
		Task consumer2 = t.newTask("consumer2Task");
		int[] idx = {0};
		int[] variable2 = {0};
		
		producer2.post(new Runnable() {
	        public void run() {
	            for (byte b : m_bytes) {
	                if (m_bufferd_stm.full()) {
	                    System.out.println("Buffer full, notifying consumer...");
	                    
	                    
	                    	m_bufferd_stm.flush();
	                        while (m_is.available()) {
	                            byte readByte = m_is.read();
	                            m_tofill[idx[0]++] = readByte;
	                            System.out.println("Consumer : Read " + readByte);
	                        }
	                      
	                    
	                }
	                
	                m_bufferd_stm.write(b);
	                variable2[0]++;
	                System.out.println("Producer: Wrote " + b);
	            }

	            
	            m_bufferd_stm.close();

	            producer2.terminate();
	            System.out.println("Closed for producer");
	        }
	    });

		consumer2.post(() -> {
			
			while (!m_is.closed() || m_is.available()) {
				if (m_is.available()) {
					byte readByte = m_is.read();
					m_tofill[idx[0]++] = readByte;

					System.out.println("Consumer: Read " + readByte );
				} else if (variable2[0] <= idx[0]) {
					break;
				}

			}
			m_os.close();
			m_is.close();
			consumer2.terminate();

			System.out.println("Closed for consumer");
		});
	
	}

	
	static void shutdown(EventPump ep) {
		Task t = Task.task();
		Task shutdown = t.newTask("shutdown");

		shutdown.post(() -> {
			ep.shutdown();
		});
	}

	static void test1() {
		test(10, m_bytes, m_bytestofill);
	}

	static void test2() {
		test(20, m_word, m_wordtofill);
	}

	static void test3() {
		test(20, m_spec, m_spectofill);
	}

	static void test4() {
		test(10, m_failed, m_failedtofill);
	}

	static void test5() {
		test(10, m_failed2, m_failedtofill);
	}

	static void test6() {
		test2(10, m_bytes3, m_bytestofill3);
	}

	static void test7() {
		test2(20, m_word3, m_wordtofill3);
	}

	static void test8() {
		test2(2, m_spec3, m_spectofill3);
	}

	static void test9() {
		test2(4, m_word4, m_wordtofill4);
	}

	static void test10() {
		test2(3, m_bytes4, m_bytestofill4);
	}

	static void verif_all() {
		// test step 1
		verif(m_bytes, m_bytestofill);
		verif(m_word, m_wordtofill);
		verif(m_spec, m_spectofill);
		// test create to fail
		verif(m_failed, m_failedtofill);
		verif(m_failed2, m_failedtofill2);
		// test step 2
		verif(m_bytes3, m_bytestofill3);
		verif(m_word3, m_wordtofill3);
		verif(m_spec3, m_spectofill3);
		verif(m_word4, m_wordtofill4);
		verif(m_bytes4, m_bytestofill4);

	}

}
