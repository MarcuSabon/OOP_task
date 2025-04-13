package oop.collections.tests_yahya;
import oop.utils.collections.ArrayList;
import oop.collections.IList;

public class TestArrayList extends ListTestBase {
    @Override
    protected IList createList() {
        return new ArrayList();
    }
}
