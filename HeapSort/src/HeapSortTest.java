
import student.TestCase;

public class HeapSortTest extends TestCase {

    /**
     * An artificial test to get initial coverage for the
     * main method. Delete or modify this test.
     */

    public void testMain() {
        HeapSort dum = new HeapSort();
        assertNotNull(dum);
        HeapSort.main(new String[3]);
        assertEquals(systemOut().getHistory(), ""); // check that nothing was
                                                    // printed out
    }

}
