package oop.collections.tests;

public class AllTests {
    public static void main(String[] args) {
        int total = 0;
        TestLength.run(total);
        TestElementAt.run(total);
        TestUpdateAt.run(total);
        TestInsertAt.run(total);
        TestRemoveAt.run(total);
        TestRemove.run(total);
        TestContains.run(total);
        TestToArray.run(total);
        if (total == 0) {
            System.out.println("ALL TESTS PASSED !!!");
        } else {
            System.out.println("TEST FAILED: " + total + " errors !!!");
        }
    }
}