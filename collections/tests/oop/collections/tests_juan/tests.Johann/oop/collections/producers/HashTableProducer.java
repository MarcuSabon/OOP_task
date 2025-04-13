package oop.collections.producers;

import oop.collections.IMap;
import oop.utils.collections.HashTable;

public class HashTableProducer implements MapProducer {

	@Override
	public IMap create() {
		return new HashTable();
	}

	@Override
	public IMap create(Object[] keys, Object[] values) {
		HashTable hashTable = new HashTable();
		for (int i = 0; i < keys.length; i++) {
			hashTable.put(keys[i], values[i]);
		}
		return hashTable;
	}

	@Override
	public IMap create(IMap other) {
		HashTable hashTable = new HashTable();
		IMap.Iterator keys = other.keys();
		IMap.Iterator values = other.values();
		while (keys.hasNext()) {
			Object key = keys.next();
			Object value = values.next();
			hashTable.put(key, value);
		}
		return hashTable;
	}

}
