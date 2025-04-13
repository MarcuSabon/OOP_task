package oop.utils.contacts;

import oop.contacts.IContacts.IPhoneNumber;
import oop.contacts.IContacts.IValue;

public class PhoneNumber implements IPhoneNumber{

	private int countrynumber;
	private String phonenumber;
	
	 public PhoneNumber(int country, String phone) {
		 this.countrynumber = country;
		 this.phonenumber = phone;
	 }
		 
	@Override
	public String value() {
		String val = number();
		if(countrynumber != 0) {
			val = "(" + country() + ") " + val;
		}
		return val;
	}

	@Override
	public String toString() {
		return value();
    }
	
	@Override
	public int country() {
		return countrynumber;
	}

	@Override
	public String number() {
		return phonenumber;
	}

	@Override
	public boolean equals(Object o) {
		return equals((IPhoneNumber)o);
	}
	
    @Override
    public boolean equals(IPhoneNumber o) {
    	if(o.country()==0||countrynumber==0) {
    		return phonenumber.equals(o.number());
    	}
        return countrynumber == o.country() && phonenumber.equals(o.number());
    }

	@Override
	public boolean equals(IValue o) {
		if (o == null) {
			return false;
		}
		IPhoneNumber phone = (IPhoneNumber) o;
		return equals(phone);
	}
	
	
	@Override
    public int hashCode() {
        if (countrynumber == 0) {
            return phonenumber.hashCode();
        }
        return countrynumber + phonenumber.hashCode();
        }


	

}
