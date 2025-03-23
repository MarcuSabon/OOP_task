package oop.collections.tests;

import oop.collections.IList;
import oop.utils.collections.ArrayList;

class TestLength {
	public static void run(int t) {
		IList list = new ArrayList();
		if (list.length() != 0) {
			t++;
			list.insertAt(0, "gragas");
		}
		if (list.length() != 1) {
			t++;
			
		}
		else {
			System.out.println("TestLength OK");
		}
		
	}
}