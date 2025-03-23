package oop.collections.tests;

import oop.collections.IList;
import oop.utils.collections.ArrayList;

class TestElementAt {
	public static void run(int t) {
		IList list = new ArrayList();
		list.insertAt(0, "teemo");
		if (!list.elementAt(0).equals("teemo")) {
			t++;
			
		}
		else {
			System.out.println("TestElementAt OK");
		}
	}
}