package oop.utils.collections;

import oop.collections.ICollection;
import oop.collections.IList;

public class ArrayList implements IList {

	public Object[] obj;
	public int thai;
	public int defaut = 32;

	public static class Iterator implements ICollection.Iterator {

		private int index = 0;
		private Object[] obj;
		private int thai;

		public Iterator(ArrayList m_list) {
			this.obj = m_list.obj;
			this.thai = m_list.thai;
		}

		@Override
		public boolean hasNext() {
			return index < thai;
		}

		@Override
		public Object next() {
			if (!hasNext()) {
				throw new IndexOutOfBoundsException("No elements anymore");
			}
			return obj[index++];
		}
	}

	/**
	 * Constructs an empty list.
	 */
	public ArrayList() {
		this.obj = new Object[defaut];
		this.thai = 0;
	}

	/**
	 * Constructs a list, initialized with the elements from the given array.
	 */
	public ArrayList(Object array[]) {
		this.obj = new Object[array.length];
		System.arraycopy(array, 0, this.obj, 0, array.length);
		this.thai = array.length;

	}

	/**
	 * Constructs a list, initialized with the elements from the given list.
	 */
	public ArrayList(ArrayList v) {
		this.obj = new Object[v.thai];
		System.arraycopy(v.obj, 0, this.obj, 0, v.thai);
		this.thai = v.thai;
	}

	/**
	 * Constructs a list, initialized with the elements from the given collection.
	 */
	public ArrayList(ICollection c) {
		this.obj = new Object[c.length()];
		c.toArray(obj);
		this.thai = c.length();
	}

	@Override
	public Iterator iterator() {
		return new Iterator(this);
	}

	@Override
	public int length() {
		return thai;
	}

	@Override
	public Object elementAt(int index) {
		if (index < 0 || index >= thai) {
			throw new IndexOutOfBoundsException("Invalid index");
		} else {
			return obj[index];
		}
	}

	@Override
	public Object updateAt(int index, Object obg) {
		if (index < 0 || index >= thai) {
			throw new IndexOutOfBoundsException("Invalid index");

		}
		Object old = obj[index];
		obj[index] = obg;
		return old;

	}

	@Override
	public void insertAt(int index, Object elem) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Invalid index");
		}
		if (thai >= obj.length) {
		    int newSize = Math.max(obj.length * 2, defaut);
		    Object[] newElements = new Object[newSize];
		    System.arraycopy(obj, 0, newElements, 0, thai);
		    obj = newElements;
		}

		System.arraycopy(obj, index, obj, index + 1, thai - index);
		obj[index] = elem;
		thai++;

	}

	@Override
	public Object removeAt(int index) {
		if (index < 0 || index >= thai) {
			throw new IndexOutOfBoundsException("Invalid index");

		} else {
			Object obj_suppr = obj[index];
			for (int i = index; i < thai - 1; i++) {
				obj[i] = obj[i + 1];
			}
			thai--;
			return obj_suppr;
		}
	}

	@Override
	public boolean remove(Object elem) {
		for (int i = 0; i < thai; i++) {
			if (obj[i].equals(elem)) {
				removeAt(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean contains(Object elem) {
		for (int i = 0; i < thai; i++) {
			if (obj[i].equals(elem)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void toArray(Object elems[]) {
		System.arraycopy(obj, 0, elems, 0, thai);
	}

}