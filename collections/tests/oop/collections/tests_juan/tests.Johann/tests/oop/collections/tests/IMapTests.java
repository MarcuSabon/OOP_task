package tests.oop.collections.tests;

import java.util.Objects;

import oop.collections.ICollection.Iterator;
import oop.collections.IMap;
import oop.collections.producers.MapProducer;

public class IMapTests {

	public static boolean runTest(MapProducer mapProducer) {
		boolean result = true;
		result &= test1(mapProducer);
		result &= test2(mapProducer);
		result &= test3(mapProducer);
		result &= test4(mapProducer);
		result &= test5(mapProducer);
		result &= test6(mapProducer);
		result &= test7(mapProducer);
		return result;
	}

// 	 ================== Utility Methods ==================
	private static Object[] initializeMap(IMap map, Object[] keys, Object[] values) {
		for (int i = 0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
		}
		return keys;
	}

	private static boolean test(boolean condition, String error) {
		if (condition) {
			System.out.println(error);
			return false;
		}
		return true;
	}

	private static boolean checkEquality(Object[] keysArray, Object[] keys) {
		if (keysArray.length != keys.length) {
			return false;
		}

		for (int i = 0; i < keysArray.length; i++) {
			for (int j = 0; j < keys.length; j++) {
				if (Objects.equals(keysArray[j], keys[i])) {
					return false;
				}
			}
		}
		return true;

	}

// 	======================= Tests =======================
	private static boolean test1(MapProducer mapProducer) {
		boolean testResult = true;

		// Test 1: Default constructor
		IMap map1 = mapProducer.create();
		testResult = test(map1.length() != 0, "Test 1: Default constructor");

		// Test 1: Second constructor
		Object[] keys = { "A", 10, "B" };
		Object[] values = { "Apple", "Ten", "Banana" };
		IMap map2 = null;

		map2 = mapProducer.create(keys, values);
		testResult = test(map2.length() != 3, "Test 1: Array constructor - length mismatch.");

		boolean contentOk = Objects.equals(map2.get("A"), "Apple") && Objects.equals(map2.get(10), "Ten")
				&& Objects.equals(map2.get("B"), "Banana");
		testResult = test(!contentOk, "Test 1: Array constructor - content mismatch.");
		if (!testResult) {
			map2 = mapProducer.create();
			initializeMap(map2, keys, values);

		}

		// Test 1: Third constructor
		IMap map3 = mapProducer.create(map2);

		testResult = test(map3.length() != 3, "Test 1: Copy constructor - length mismatch.");
		contentOk = Objects.equals(map3.get("A"), "Apple") && Objects.equals(map3.get(10), "Ten")
				&& Objects.equals(map3.get("B"), "Banana");

		testResult = test(!contentOk, "Test 1: Copy constructor - content mismatch.");

		String resultString = (testResult) ? "passed" : "failed";
		System.out.println("Test constructors: " + resultString + "\n");

		return testResult;
	}

	private static boolean test2(MapProducer mapProducer) {
		boolean testResult = true;
		IMap map = mapProducer.create();

		// Test 2: put
		Object prev0 = map.put("putKey", "test");
		testResult &= test(prev0 != null, "Test 2: first put returned not null value");

		testResult &= test(map.length() != 1, "Test 2: length mismatch after first put");

		// Test 2: get
		Object val1 = map.get("putKey");
		testResult &= test(!Objects.equals(val1, "test"), "Test 2 : value mismatch after first put");

		// Test 2: put more elements
		map.put("Key2", 100);
		map.put(3, "Trois");

		testResult &= test(map.length() != 3, "Test 2: length mismatch after more puts");

		// Test 2: get missing key
		Object val2 = map.get("MissingKey");
		testResult &= test(val2 != null, "Test 2 : get MissingKey returned not null");

		String resultString = (testResult) ? "passed" : "failed";
		System.out.println("Test put, get: " + resultString + "\n");
		return testResult;
	}

	private static boolean test3(MapProducer mapProducer) {
		boolean testResult = true;
		IMap map = mapProducer.create();

		// Test 3: update
		map.put("UpdateKey", "Initial");
		Object prev = map.put("UpdateKey", "Updated");

		testResult &= test(map.length() != 1, "Test 3: length mismatch after update");

		testResult &= test(prev != "Initial", "Test 3: update returned not null value");

		Object val = map.get("UpdateKey");
		testResult &= test(!Objects.equals(val, "Updated"), "Test 3 : value mismatch after update");

		String resultString = (testResult) ? "passed" : "failed";
		System.out.println("Test update: " + resultString + "\n");

		return testResult;
	}

	private static boolean test4(MapProducer mapProducer) {
		boolean testResult = true;
		IMap map = mapProducer.create();

		// Test 4: remove
		map.put("RemoveKey", "RemoveValue");
		Object removed = map.remove("RemoveKey");

		testResult &= test(map.length() != 0, "Test 4: length mismatch after remove");

		testResult &= test(removed != "RemoveValue", "Test 4: removed value mismatch");

		Object val = map.get("RemoveKey");
		testResult &= test(val != null, "Test 4: get removed key returned not null");

		map.put("RemoveKey", "RemoveValue");
		map.put("RemoveKey2", "RemoveValue2");
		map.put("RemoveKey3", "RemoveValue3");

		removed = map.remove("RemoveKey2");

		testResult &= test(map.length() != 2, "Test 4: length mismatch after remove");

		testResult &= test(removed != "RemoveValue2", "Test 4: removed value mismatch");

		// Test 4: get removed key
		val = map.get("RemoveKey2");

		testResult &= test(val != null, "Test 4: get removed key returned not null");

		// Test 4: remove missing key
		removed = map.remove("MissingKey");

		testResult &= test(removed != null, "Test 4: remove missing key returned not null");

		String resultString = (testResult) ? "passed" : "failed";
		System.out.println("Test remove: " + resultString + "\n");

		return testResult;

	}

	private static boolean test5(MapProducer mapProducer) {
		boolean testResult = true;
		IMap map = mapProducer.create();

		// Test 5: contains
		map.put("ContainsKey", "ContainsValue");

		boolean contains = map.contains("ContainsKey");
		testResult &= test(!contains, "Test 5: key not found");

		contains = map.contains("MissingKey");
		testResult &= test(contains, "Test 5: contains missing key");

		map.remove("ContainsKey");
		contains = map.contains("ContainsKey");
		testResult &= test(contains, "Test 5: contains removed key");

		testResult &= test(map.length() != 0, "Test 5: length mismatch after contains");

		String resultString = (testResult) ? "passed" : "failed";
		System.out.println("Test contains: " + resultString + "\n");

		return testResult;
	}

	private static boolean test6(MapProducer mapProducer) {
		boolean testResult = true;
		IMap map = mapProducer.create();

		// Test 6: iterator
		Object[] keys = { "A", 10, "B", -1, 'a' };
		Object[] values = { "Apple", "Ten", "Banana", 10, "test" };
		initializeMap(map, keys, values);

		Iterator keyIterator = map.keys();
		Iterator valueIterator = map.values();

		int count = 0;
		while (keyIterator.hasNext()) {
			Object key = keyIterator.next();
			Object value = valueIterator.next();

			testResult &= test(!Objects.equals(map.get(key), value), "Test 6: iterator mismatch");
			count++;
		}

		testResult &= test(count != map.length(), "Test 6: iterator count mismatch");

		// Test 6: iterator after remove
		map.remove(-1);
		Iterator keyIterator2 = map.keys();
		Iterator valueIterator2 = map.values();
		while (keyIterator2.hasNext()) {
			Object key = keyIterator2.next();
			Object value = valueIterator2.next();

			testResult &= test(!Objects.equals(map.get(key), value), "Test 6: iterator mismatch after remove");
		}

		String resultString = (testResult) ? "passed" : "failed";
		System.out.println("Test iterator: " + resultString + "\n");

		return testResult;
	}

	private static boolean test7(MapProducer mapProducer) {
		boolean testResult = true;
		IMap map = mapProducer.create();

		// Test 7: toArray
		Object[] keys = { "A", 10, "B", -1, 'a' };
		Object[] values = { "Apple", "Ten", "Banana", 10, "test" };
		initializeMap(map, keys, values);

		Object[] keysArray = new Object[map.length()];
		Object[] valuesArray = new Object[map.length()];
		map.keysToArray(keysArray);
		map.valuesToArray(valuesArray);

		for (int i = 0; i < keysArray.length; i++) {

			testResult &= test(checkEquality(keysArray, keys), "Test 7: keys array mismatch");
			testResult &= test(checkEquality(valuesArray, values), "Test 7: values array mismatch");
		}

		Object[] mapArray = new Object[map.length()];
		map.toArray(mapArray);
		for (int i = 0; i < mapArray.length; i++) {
			testResult &= test(checkEquality(mapArray, values), "Test 7: map array mismatch");
		}

		String resultString = (testResult) ? "passed" : "failed";
		System.out.println("Test toArray: " + resultString + "\n");

		return testResult;
	}
}
