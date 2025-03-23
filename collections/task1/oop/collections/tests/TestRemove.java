package oop.collections.tests;

import oop.collections.IList;
import oop.utils.collections.ArrayList;

class TestRemove {
	public static void run(int t) {
		IList list = new ArrayList(new Object[] { "darius", "garen" });
		if (!list.remove("darius")) {
			t++;
		}
		if (list.contains("darius")) {
			t++;

		} else {
			System.out.println("TestRemove OK");
		}
	}
}