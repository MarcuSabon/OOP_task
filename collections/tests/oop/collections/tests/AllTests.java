package oop.collections.tests;

import oop.collections.ICollection.Iterator;
import oop.collections.IList;
import oop.collections.IMap;
<<<<<<< HEAD
import oop.utils.collections.ArrayList;
import oop.utils.collections.LinkedList;
=======
import oop.contacts.IContacts;
import oop.contacts.IContacts.IContact;
import oop.contacts.IContacts.IName;
import oop.contacts.IContacts.IPhoneNumber;
import oop.utils.collections.ArrayList;
import oop.utils.collections.LinkedList;
import oop.utils.contacts.Contacts;
>>>>>>> collections.t4
import oop.utils.collections.HashTable;

public class AllTests {
	public static void main(String[] args) {
		IList list = new ArrayList();
		IList listLink = new LinkedList(new Object[] { "Xin xao" });
		IMap map = new HashTable();
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
		
		System.out.println("\n<============ TEST HASHTABLE =============>\n");
		//a implementer
		TestPut(total, map);
        TestGet(total, map);
        TestRemove(total, map);
        TestContains(total, map);
        TestKeysToArray(total, map);
        TestValuesToArray(total, map);
<<<<<<< HEAD
		
=======
        System.out.println("\n<============ TEST CONTACTS COMMANDS =============>\n");
        TestContactsCommands(total);

>>>>>>> collections.t4
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
		list.insertAt(2, "C");
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
		Object[] arr = { 1, 2 } ;
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
	
	//////////////////////////////////////////////////////////////////////////////
	////////////////////////////// HASHTABLE /////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////
	
	public static void TestPut(int t, IMap map) {
        map.put("uno", "mark");
        map.put("dos", "axel");
        if (!map.get("uno").equals("mark")) t++;
        if (!map.get("dos").equals("axel")) t++;
        map.put("tres", "frost");
       
        if (!map.get("tres").equals("frost")) t++;
       
        System.out.println("TestPut OK");
    }

    public static void TestGet(int t, IMap map) {
        if (!map.get("uno").equals("mark")) t++;
        if (map.get("muchachos") != null) t++;
        System.out.println("TestGet OK");
    }

    public static void TestRemove(int t, IMap map) {
    	map.put("cuatro", "the wall");
        if (!map.remove("cuatro").equals("the wall")) t++;
        if (map.contains("cuatro")) t++;
        System.out.println("TestRemove OK");
    }

    public static void TestContains(int t, IMap map) {
        if (!map.contains("uno")) t++;
        if (map.contains("5_en_espagnol")) t++;
        System.out.println("TestContains OK");
    }

    public static void TestKeysToArray(int t, IMap map) {
        Object[] keys = new Object[map.length()];
        map.keysToArray(keys);
        if (keys.length != map.length()) t++;
        System.out.println("TestKeysToArray OK");
    }

    public static void TestValuesToArray(int t, IMap map) {
        Object[] values = new Object[map.length()];
        map.valuesToArray(values);
        if (values.length != map.length()) t++;
        System.out.println("TestValuesToArray OK");
    }
<<<<<<< HEAD
	
=======
    
    
	//////////////////////////////////////////////////////////////////////////////
	////////////////////////////// CONTACT ///////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////
    
    
    
    
    public static void TestContactsCommands(int t) {
        IContacts contacts = new Contacts();


        IName ezrealName = contacts.newName("Ezreal", "");
        IPhoneNumber ezrealPhone = contacts.newPhoneNumber(1, "212 555 1234");
        IContact ezreal = contacts.add(ezrealName, ezrealPhone);
        ezreal.field("email", contacts.newValue("ezreal@league.com"));

        IName luxName = contacts.newName("Lux", "");
        IPhoneNumber luxPhone = contacts.newPhoneNumber(1, "212 555 5678");
        IContact lux = contacts.add(luxName, luxPhone);
        lux.field("email", contacts.newValue("lux@league.com"));


        if (!ezreal.name().value().equals("Ezreal") || !lux.phone().value().contains("5678")) t++;
        else System.out.println("TestContacts Add OK");


        ezreal.field("country", contacts.newValue("Demacia"));
        if (!ezreal.field("country").value().equals("Demacia")) t++;
        else System.out.println("TestContacts Update Country OK");


        Iterator it = contacts.select("name", "E*");
        boolean foundEzreal = false;
        while (it.hasNext()) {
            IContact c = (IContact) it.next();
            if (c.name().value().equals("Ezreal")) {
                foundEzreal = true;
            }
        }
        if (!foundEzreal) t++;
        else System.out.println("TestContacts Select by name OK");


        ezreal.field("phone", contacts.newPhoneNumber(1, "212 555 1234"));
        if (!ezreal.field("phone").value().contains("1234")) t++;
        else System.out.println("TestContacts Update Phone OK");


        contacts.remove(lux);
        Iterator itLux = contacts.select("name", "Lux");
        if (itLux.hasNext()) t++;
        else System.out.println("TestContacts Remove OK");

        
    }

>>>>>>> collections.t4
}
