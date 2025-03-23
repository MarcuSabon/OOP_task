package oop.collections.tests;

import oop.collections.IList;
import oop.utils.collections.ArrayList;

class TestUpdateAt {
	public static void run(int t) {
		IList list = new ArrayList();
		list.insertAt(0, "ashe");
		if (!list.updateAt(0, "ezreal").equals("ashe")) {
			t++;
		}
		if (!list.elementAt(0).equals("ezreal")) {
			t++;
			
		}
		else {
			System.out.println("TestUpdateAt OK");
		}
	}
}
