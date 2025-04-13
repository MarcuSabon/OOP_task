package oop.utils.contacts;

import oop.contacts.IContacts.IValue;

public class Value implements IValue{

	private String value;

    public Value(String value) {
        this.value = value;
    }

	@Override
	public boolean equals(IValue o) {
		 if (o == null) {
			 return false;
		 }
	     return value.equals(o.value());
	}

	@Override
	public String value() {
		return value;
	}
	
	@Override
	public String toString() {
		return value;
	}

}
