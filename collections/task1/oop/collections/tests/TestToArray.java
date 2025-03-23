package oop.collections.tests;

import oop.collections.IList;
import oop.utils.collections.ArrayList;

class TestToArray {
	public static void run(int t) {
		IList list = new ArrayList(new Object[] { "1", "2" });
		Object[] arr = new Object[list.length()];
		list.toArray(arr);
		if (arr.length != 2) {
			t++;
		}
		if (!arr[0].equals("1")) {
			t++;
		}
		if (!arr[1].equals("2")) {
			t++;
		} else {
			System.out.println("TestToArray OK");
		}
	}
}
