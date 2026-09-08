public class CircularBufferTest {
    // Testklasse genenert med KI.
    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError(
                "Expected " + expected + ", but got " + actual
            );
        }
    }

    private static void assertContents(
        CircularBuffer buffer,
        int... expected
    ) {
        assertEquals(expected.length, buffer.length());

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], buffer.get(i));
        }
    }

    private static void testConstructor() {
        CircularBuffer buffer =
            new CircularBuffer(new int[]{1, 2, 3});

        assertContents(buffer, 1, 2, 3);
    }

    private static void testPushBack() {
        CircularBuffer buffer =
            new CircularBuffer(new int[]{1, 2, 3});

        buffer.push_back(4);

        assertContents(buffer, 1, 2, 3, 4);
    }

    private static void testPushFront() {
        CircularBuffer buffer =
            new CircularBuffer(new int[]{1, 2, 3});

        buffer.push_front(0);

        assertContents(buffer, 0, 1, 2, 3);
    }

    private static void testResizeAfterWrapping() {
        CircularBuffer buffer =
            new CircularBuffer(new int[]{1, 2, 3});

        // Kapasiteten er 4. push_front flytter start til slutten.
        buffer.push_front(0);

        // Buffers er full og wrappet. Dette utløser resize.
        buffer.push_back(4);

        assertContents(buffer, 0, 1, 2, 3, 4);
    }
    // Egenimplementere tester
    private static void testPopBackPopFront() {
        CircularBuffer buffer =
            new CircularBuffer(new int[]{1, 2, 3});


        // Tester Pop på begge sider av buffer.
        assertEquals(buffer.pop_back(), 3);
        assertEquals(buffer.pop_front(), 1);

        assertContents(buffer, 2);
    }

    public static void main(String[] args) {
        //KI-implementerte tester
        testConstructor();
        testPushBack();
        testPushFront();
        testResizeAfterWrapping();
        //Egen-implementerte tester
        testPopBackPopFront();
        
        System.out.println("All tests passed.");
    }
}