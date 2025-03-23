package oop.collections.tests;

import oop.collections.IList;
import oop.utils.collections.ArrayList;

class TestContains {
	public static void run(int t) {
		IList list = new ArrayList(new Object[] { "Xin xao" });
		if (!list.contains("Xin xao") || list.contains("Yi")) {
			t++;
		}
		else {
			System.out.println("TestContains OK");
		}
	}
}