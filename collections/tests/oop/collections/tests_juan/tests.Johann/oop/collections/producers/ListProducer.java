package oop.collections.producers;

import oop.collections.IList;

public interface ListProducer {

	IList create();

	IList create(Object[] elements);

	IList create(IList other);
}