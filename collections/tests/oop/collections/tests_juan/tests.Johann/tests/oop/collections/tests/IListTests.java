package tests.oop.collections.tests;

import oop.collections.ICollection.Iterator;
import oop.collections.producers.ListProducer;
import oop.collections.IList;

public class IListTests {

	public static boolean runTest(ListProducer listProducer) {
		boolean result = true;
		result &= test1(listProducer);
		result &= test2(listProducer);
		result &= test3(listProducer);
		result &= test4(listProducer);
		result &= test5(listProducer);
		result &= test6(listProducer);
		return result;
	}
	
//	==================== Utility Methods ==================
	
	private static Object[] initializeValues(int length) {
		Object[] values = new Object[length];
		for (int i = 0; i < values.length; i++) {
			values[i] = i;
		}
		return values;
	}

	private static Object[] initializeList(IList list, int length) {
		Object[] values = new Object[length];
		for (int i = 0; i < values.length; i++) {
			values[i] = i;
			list.insertAt(i, i);
		}
		return values;
	}

	private static boolean equals(Object value1, Object value2) {
		return value1 == value2 || (value1 != null && value1.equals(value2));
	}
	
	private static boolean isEqual(IList list, Object[] values) {
		Iterator it = list.iterator();
		for (int i = 0; i < values.length; i++) {
			if (!it.hasNext()) {
				System.out.println("Test failed: list too short");
				return false;
			}
			if (!equals(it.next(),values[i])) {
				System.out.println("Test failed: wrong value");
				return false;
			}
		}
		if (it.hasNext()) {
			System.out.println("Test failed: list too long");
			return false;
		}
		return true;
	}

	private static boolean isEqual(IList list1, IList list2) {
		if (list1.length() != list2.length()) {
			return false;
		}
		Iterator it1 = list1.iterator();
		Iterator it2 = list2.iterator();
		while (it1.hasNext()) {
			if (!it2.hasNext()) {
				return false;
			}
			
			if (!equals(it1.next(),it2.next())) {
				return false;
			}
		}
		return true;
	}

	private static boolean isEqual(Object[] values, Object[] values2) {
		if (values.length != values2.length) {
			return false;
		}
		for (int i = 0; i < values.length; i++) {
			if (!equals(values[i],values2[i])) {
				return false;
			}
		}
		return true;
	}

	private static boolean test(boolean condition, String errorMessage) {
		if (condition) {
			System.out.println(errorMessage);
			return false;
		}
		return true;
	}

//	======================= Tests ==================
	
	private static boolean test1(ListProducer listProducer) {
		boolean testResult = true;
		// Test 1: first constructor
		IList list = listProducer.create();

		testResult &= test(list.length() != 0, "Test 1: first constructor failed");

		// Test 1: second constructor
		Object[] values = initializeValues(10);
		IList list2 = listProducer.create(values);

		testResult &= test(!isEqual(list2, values), "Test 1: second constructor failed");

		// Test 1: third constructor
		IList list3 = listProducer.create(list2);

		testResult &= test(!isEqual(list2, list3), "Test 1: third constructor failed");

		String resultString = (testResult) ? "passed" : "failed";
		System.out.println("Test constructors: " + resultString + "\n");

		// TODO: Test 1: fourth constructor

		return testResult;
	}

	private static boolean test2(ListProducer listProducer) {
		boolean testResult = true;
		// Test 2: insertAt
		IList list = listProducer.create();
		Object[] values = initializeList(list, 10);

		// tableau de taille 11 car on va décaler de 1
		Object[] values2 = new Object[11];
		for (int i = 0; i < 5; i++) {
			values2[i] = values[i];
		}

		// on insère un élément à l'index 5 et on décale les éléments
		values2[5] = 100;
		list.insertAt(5, 100);

		for (int i = 5; i < 10; i++) {
			values2[i + 1] = values[i];
		}

		testResult &= test(!isEqual(list, values2), "Test 2: insertAt failed");

		// Test 2: updateAt
		list.updateAt(0, 11);
		list.updateAt(2, 22);

		values2[0] = 11;
		values2[2] = 22;

		// On vérifie les valeurs et la taille est censé être la même
		testResult &= test(!isEqual(list, values2), "Test 2: updateAt failed");

		String resultString = (testResult) ? "passed" : "failed";
		System.out.println("Test insertion & update: " + resultString + "\n");

		return testResult;
	}

	private static boolean test3(ListProducer listProducer) {
		boolean testResult = true;
		// Test 3: removeAt
		IList list = listProducer.create();
		Object[] values = initializeList(list, 10);

		Object values2[] = new Object[9];
		for (int i = 0; i < 5; i++) {
			values2[i] = values[i];
		}

		// on supprime l'élément à l'index 5 et on décale les éléments
		list.removeAt(5);

		for (int i = 5; i < 9; i++) {
			values2[i] = values[i + 1];
		}

		test(!isEqual(list, values2), "Test 3: removeAt failed");

		// Test 3: remove
		list.remove(0); // [1, 2, 3, 4, 6, 7, 8, 9]
		list.remove(2); // [1, 3, 4, 6, 7, 8, 9]

		Object[] values3 = { 1, 3, 4, 6, 7, 8, 9 };

		// On vérifie les valeurs et la taille est censé être la même
		testResult &= test(!isEqual(list, values3), "Test 3: remove failed");

		String resultString = (testResult) ? "passed" : "failed";
		System.out.println("Test removeAt, remove: " + resultString + "\n");

		return testResult;
	}

	private static boolean test4(ListProducer listProducer) {
		boolean testResult = true;
		// Test 4: contains
		IList list = listProducer.create();
		// on initialise la liste avec 10 éléments
		initializeList(list, 10);
		String errorMessage = "Test 4: contains failed";

		testResult &= test(!list.contains(0), errorMessage + ": 0");

		testResult &= test(!list.contains(5), errorMessage + ": 5");

		testResult &= test(!list.contains(9), errorMessage + ": 9");

		testResult &= test(list.contains(10), errorMessage + ": 10");

		String resultString = (testResult) ? "passed" : "failed";
		System.out.println("Test contains: " + resultString + "\n");

		return testResult;
	}

	private static boolean test5(ListProducer listProducer) {
		boolean testResult = true;
		// Test 5: agrandir la liste
		IList list = listProducer.create();
		Object[] values = initializeList(list, 10);

		// on insère un élément à l'index 65
		list.insertAt(65, 100);

		Object[] values2 = new Object[66];
		for (int i = 0; i < 10; i++) {
			values2[i] = values[i];
		}

		values2[65] = 100;

		testResult &= test(!isEqual(list, values2), "Test 5: insertAt + increase failed");

		String resultString = (testResult) ? "passed" : "failed";
		System.out.println("Test insertAt + increase: " + resultString + "\n");

		return testResult;
	}

	private static boolean test6(ListProducer listProducer) {
		boolean testResult = true;
		// Test 6: toArray
		IList list = listProducer.create();
		initializeList(list, 10);

		Object[] values = new Object[list.length()];
		for (int i = 0; i < 10; i++) {
			values[i] = i;
		}

		Object[] values2 = new Object[list.length()];
		list.toArray(values2);

		testResult &= test(!isEqual(values, values2),"Test 6: toArray failed");

		String resultString = (testResult) ? "passed" : "failed";
		System.out.println("Test toArray: " + resultString + "\n");

		return testResult;
	}
}
