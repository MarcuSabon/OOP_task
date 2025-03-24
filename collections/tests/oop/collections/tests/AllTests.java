package oop.collections.tests;

import oop.collections.IList;

import oop.utils.collections.ArrayList;
import oop.utils.collections.LinkedList;

public class AllTests {
	public static void main(String[] args) {
		IList list = new ArrayList();
		IList listLink = new LinkedList(new Object[] { "Xin xao" });
		int total = 0;
		
		System.out.println("\n<============ TEST ARRAY =============>\n");
		TestLength(total,list);
		TestElementAt(total,list);
		TestUpdateAt(total,list);
		TestInsertAt(total,list);
		TestRemoveAt(total,list);
		TestRemove(total,list);
		TestContains(total,list);
		TestToArray(total,list);
		
		System.out.println("\n<============ TEST LINKED =============>\n");
		
		TestLength(total,listLink);
		TestElementAt(total,listLink);
		TestUpdateAt(total,listLink);
		TestInsertAt(total,listLink);
		TestRemoveAt(total,listLink);
		TestRemove(total,listLink);
		TestContains(total,listLink);
		TestToArray(total,listLink);
		if (total == 0) {
			System.out.println("\nALL TESTS PASSED !!!");
		} else {
			System.out.println("TEST FAILED: " + total + " errors !!!");
		}
	}

	public static void TestContains(int t,IList list) {
		list.insertAt(0,"Xin xao");
		if (!list.contains("Xin xao") || list.contains("Yi")) {
			t++;
		} else {
			System.out.println("TestContains OK");
		}
	}

	public static void TestElementAt(int t,IList list) {
		
		list.insertAt(0, "teemo");
		if (!list.elementAt(0).equals("teemo")) {
			t++;

		} else {
			System.out.println("TestElementAt OK");
		}

	}

	public static void TestInsertAt(int t,IList list) {
		
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

	public static void TestLength(int t,IList list) {
		
		if (list.length() != 0) {
			t++;
			list.insertAt(0, "gragas");
		}
		if (list.length() != 1) {
			t++;

		} else {
			System.out.println("TestLength OK");
		}

	}

	public static void TestRemove(int t,IList list) {
		list.insertAt(0, "darius");
		list.insertAt(1, "garen");
		if (!list.remove("darius")) {
			t++;
		}
		if (list.contains("darius")) {
			t++;

		} else {
			System.out.println("TestRemove OK");
		}
	}

	public static void TestRemoveAt(int t,IList list) {
		list.insertAt(0,"braum");
		list.insertAt(1, "xayah");
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

	public static void TestToArray(int t,IList list) {
		
		list.insertAt(0, 1);
		list.insertAt(1, 2);
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

	public static void TestUpdateAt(int t,IList list) {
		
		list.insertAt(0, "ashe");
		if (!list.updateAt(0, "ezreal").equals("ashe")) {
			t++;
		}
		if (!list.elementAt(0).equals("ezreal")) {
			t++;

		} else {
			System.out.println("TestUpdateAt OK");
		}
	}
}
