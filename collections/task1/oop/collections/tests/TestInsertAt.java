package oop.collections.tests;

import oop.collections.IList;
import oop.utils.collections.ArrayList;

class TestInsertAt {
	public static void run(int t) {
		IList list = new ArrayList();
		list.insertAt(0, "A");
		list.insertAt(1, "C");
		list.insertAt(1, "B");
		if (!list.elementAt(0).equals("A")) {
			t++;
		}
		if (!list.elementAt(1).equals("B")) {
			t++;
		}
		if (!list.elementAt(2).equals("C")) {
			t++;

		} else {
			System.out.println("TestInsertAt OK");
		}
	}
}