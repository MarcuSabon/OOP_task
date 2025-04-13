package oop.collections.producers;

import oop.collections.IList;
import oop.utils.collections.ArrayList;

public class ArrayListProducer implements ListProducer {

	@Override
	public IList create() {
		return new ArrayList();
	}

	@Override
	public IList create(Object[] elements) {
		return new ArrayList(elements);
	}

	@Override
	public IList create(IList other) {
		return new ArrayList(other);
	}

}
