package tests.oop.collections.tests;

import oop.collections.producers.ArrayListProducer;
import oop.collections.producers.HashTableProducer;
import oop.collections.producers.LinkedListProducer;


public class AllTests {

	public static void main(String[] args) {
		System.out.println("==== Starting ArrayList tests ====\n");
		test(IListTests.runTest(new ArrayListProducer()), "ArrayList test");
		System.out.println("\n==== Starting LinkedList tests ====\n");
		test(IListTests.runTest(new LinkedListProducer()), "LinkedList test");
		System.out.println("\n==== Starting HashTable tests ====\n");
		test(IMapTests.runTest(new HashTableProducer()), "HashTable test");
	}

	private static void test(boolean condition, String testName) {
		if (condition) {
			System.out.println(testName + ": passed");
		} else {
			System.out.println(testName + ": failed");
		}
	}

}
