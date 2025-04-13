package oop.collections.producers;

import oop.collections.IList;
import oop.utils.collections.ArrayList;
import oop.utils.collections.LinkedList;

public class LinkedListProducer implements ListProducer {

	@Override
	public IList create() {
		return new LinkedList();
	}

	@Override
	public IList create(Object[] elements) {
		return new LinkedList(elements);
	}

	@Override
	public IList create(IList other) {
		return new LinkedList(other);
	}

}
