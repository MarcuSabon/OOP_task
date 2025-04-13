package oop.collections.producers;

import oop.collections.IMap;

public interface MapProducer {

	IMap create();

	IMap create(Object[] keys, Object[] values);

	IMap create(IMap other);
}
