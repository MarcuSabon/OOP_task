package oop.collections.tests;

import oop.collections.IList;
import oop.utils.collections.ArrayList;

class TestRemoveAt {
	public static void run(int t) {
		IList list = new ArrayList(new Object[] { "braum", "xayah" });
		if (!list.removeAt(0).equals("braum")) {
			t++;
		}
		if (list.length() != 1) {
			t++;
		}
		if (!list.elementAt(0).equals("xayah")) {
			t++;

		} else {
			System.out.println("TestRemoveAt OK");
		}

	}
}
