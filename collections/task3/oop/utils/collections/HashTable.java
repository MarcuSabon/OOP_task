package oop.utils.collections;

import oop.collections.ICollection;
import oop.collections.IMap;



public class HashTable implements IMap {
	
	
	
	private LinkedList[] buckets;
	private int size;

	public class Pair {

		public Object key;
		public Object value;

		Pair(Object key, Object value) {
			this.key = key;
			this.value = value;
		}
	}
	
	
	public HashTable(){
		buckets = new LinkedList[10];
		for(int i =0; i<10;i++) {
			buckets[i] = new LinkedList();
		}
		size = 0;
	}
	

	@Override
	public Iterator iterator() {
		return new Iterator(this);
	}

	@Override
	public int length() {
		return size;
	}
	
	@Override
	public Iterator values() {
		return new ValueIterator(this);
		
	}

	@Override
	public Iterator keys() {
		
		return new KeyIterator(this);
	}



	@Override
	public Object put(Object key, Object value) {
		if(key==null) return null;
		int index = (key.hashCode() % buckets.length + buckets.length) % buckets.length;
		LinkedList bucket = buckets[index];
		
		for (int i = 0; i < bucket.length(); i++) {
			Pair pair = (Pair) bucket.elementAt(i);
			
			if (pair.key.equals(key)) {
				Object val = pair.value;
				pair.value = value;
				return val;
			}
			
		}
		
		bucket.insertAt(bucket.length(), new Pair(key, value));
		size++;
		return null;
	}


	@Override
	public Object get(Object key) {
		if(key==null) return null;
		int index = (key.hashCode() % buckets.length + buckets.length) % buckets.length;
		LinkedList bucket = buckets[index];
		
		for (int i = 0; i < bucket.length(); i++) {
			Pair pair = (Pair) bucket.elementAt(i);
			
			if (pair.key.equals(key)) {
				return pair.value;
			}
		}

		return null;
	}


	@Override
	public boolean contains(Object key) {
		int index = (key.hashCode() % buckets.length + buckets.length) % buckets.length;
		LinkedList bucket = buckets[index];
		
		for (int i = 0; i < bucket.length(); i++) {
			Pair pair = (Pair) bucket.elementAt(i);
			
			if (pair.key.equals(key)) {
				return true;
			}
		}

		return false;
	}

	
	@Override
	public Object remove(Object key) {
		if(key==null) return null;
		int index = (key.hashCode() % buckets.length + buckets.length) % buckets.length;
		LinkedList bucket = buckets[index];
		
		for (int i = 0; i < bucket.length(); i++) {
			Pair pair = (Pair) bucket.elementAt(i);
			
			if (pair.key.equals(key)) {
				bucket.removeAt(i);
				size--;
				
				return pair.value;
			}
		}
		return null;
	}


	@Override
	public void keysToArray(Object[] elems) {
		int index = 0;
		
		
		for (LinkedList ld : buckets) {
			for (int i = 0; i < ld.length(); i++) {
				elems[index] = ((Pair) ld.elementAt(i)).key;
				index++;
			}
		}

	}

	@Override
	public void valuesToArray(Object[] elems) {
		int index = 0;
		
		
		for (LinkedList ld : buckets) {
			for (int i = 0; i < ld.length(); i++) {
				elems[index] = ((Pair) ld.elementAt(i)).value;
				index++;
			}
		}

	}

	@Override
	public void toArray(Object[] elems) {
		int index = 0;
		
		
		for (LinkedList ld : buckets) {
			for (int i = 0; i < ld.length(); i++) {
				Pair pair = (Pair) ld.elementAt(i);
				elems[index] = new Pair(pair.key, pair.value);
				;
				index++;
			}
		}


	}

	public class Iterator implements ICollection.Iterator {

		private int bucketIndex;
		private int elementIndex;

		public Iterator(HashTable m_list) {
			this.bucketIndex = 0; // index linkedlist*
			this.elementIndex = 0; // index paires
			while (bucketIndex < buckets.length && buckets[bucketIndex].length() == 0) {
				bucketIndex++;
			}
		}

		@Override
		public boolean hasNext() {
			return bucketIndex < buckets.length;
		}

		@Override
		public Object next() {
			if (!hasNext()) {
				throw new IndexOutOfBoundsException("No more elements");
			}
			Pair pair = (Pair) buckets[bucketIndex].elementAt(elementIndex);
			elementIndex++;
			
			if (elementIndex >= buckets[bucketIndex].length()) {
				bucketIndex++; 
				elementIndex = 0;
				
				while (bucketIndex < buckets.length && buckets[bucketIndex].length() == 0) {
					bucketIndex++;
				}
			}
			return pair;
		}

	}
	
	public class ValueIterator extends Iterator {
		public ValueIterator(HashTable m_list) {
			super(m_list);
		}

		@Override
		public Object next() {
			return ((Pair) super.next()).value;
		}
	}
	public class KeyIterator extends Iterator {

		public KeyIterator(HashTable m_list) {
			super(m_list);
		}

		@Override
		public Object next() {
			return ((Pair) super.next()).key;
		}
	}

	

}
