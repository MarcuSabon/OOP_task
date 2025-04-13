package oop.utils.contacts;

<<<<<<< HEAD
public class Contacts {

=======
import oop.collections.ICollection.Iterator;
import oop.collections.IList;
import oop.collections.IMap;
import oop.contacts.IContacts;
import oop.utils.collections.HashTable;
import oop.utils.collections.LinkedList;

public class Contacts implements IContacts {

	private IMap m_contaxt;
	public Contacts() {
		this.m_contaxt = new HashTable();
	}
	
	@Override
	public IContact get(IPhoneNumber phone) {
		return (IContact) m_contaxt.get(phone);
	}

	@Override
	public void remove(IContact c) {
		m_contaxt.remove(c.phone());
	}

	@Override
	public IContact add(IName name, IPhoneNumber phone) {
	    if (m_contaxt.get(phone) != null) {
	        throw new IllegalArgumentException("This phone number is already taken.");
	    }

	    IContact contact = new Contact(name, phone);
	    m_contaxt.put(phone, contact);
	    return contact;
	}


	@Override
	public void update(IContact c, IList names, IList values) {
		    IList.Iterator namesIt = names.iterator();
		    IList.Iterator valuesIt = values.iterator();

		    while (namesIt.hasNext() && valuesIt.hasNext()) {
		        String contname = (String) namesIt.next();
		        IValue value = (IValue) valuesIt.next();

		        c.field(contname, value);
		    }
		
	}

	@Override
	public Iterator select(String name, String filter) {
	    IList res = new LinkedList(); 
	    Iterator it = m_contaxt.values();

	    while (it.hasNext()) {
	        IContact c = (IContact) it.next();
	        IValue v = c.field(name);
	        if (v != null && match(v.value(), filter)) {
	            res.insertAt(res.length(), c); 
	        }
	    }

	    return res.iterator();
	}
	
	private boolean match(String value, String filter) {
		 if (filter.equals("*")) {
		        return true;}
	    if (filter.startsWith("*") && filter.endsWith("*")) {
	        String sub = filter.substring(1, filter.length() - 1);
	        return value.contains(sub);
	    }

	    if (filter.startsWith("*")) {
	        String suffix = filter.substring(1);
	        return value.endsWith(suffix);
	    }

	    if (filter.endsWith("*")) {
	        String prefix = filter.substring(0, filter.length() - 1);
	        return value.startsWith(prefix);
	    }
	    else {
	    return value.equals(filter);
	    }
	}


	
	@Override
	public IName newName(String last, String first) {
		return new Name(last,first);
	}

	@Override
	public IPhoneNumber newPhoneNumber(int country, String number) {
		return new PhoneNumber(country,number);
	}

	@Override
	public IValue newValue(String value) {
		return new Value(value);
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	//								contact											 //
	///////////////////////////////////////////////////////////////////////////////////
	
	public static class Contact implements IContact{
		private IMap m_contaxt;
		private IName name;
	    private IPhoneNumber phone;

		public Contact(IName name, IPhoneNumber phone) {
			this.phone = phone;
	        this.name = name;
	        m_contaxt=new HashTable();
	        m_contaxt.put("name", name);
	        m_contaxt.put("phone", phone);
		}
		
		@Override
	    public IPhoneNumber phone() {
	        return phone;
	    }

	    @Override
	    public IName name() {
	        return name;
	    }

	    @Override
	    public Iterator fields() {
	    	return m_contaxt.keys();
	    }

		@Override
	    public IValue field(String name) {
			IValue val = (IValue) m_contaxt.get(name);
			if (val == null) {
				throw new IllegalArgumentException("Field '" + name + "' not found in contact.");
			}
			return val;
	    }

	    @Override
	    public void field(String name, IValue value) {
	        m_contaxt.put(name, value);
	        if(name.equals("name")) {
	        	this.name=(IName)value;
	        }
	        if(name.equals("phone")) {
	        	this.phone=(IPhoneNumber)value;
	        }
			
		}

	}
	
>>>>>>> ea7c843 (t4 finish alltests passed)
}
