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

    public static void main(String[] args) {
        testConstructor();
        testPushBack();
        testPushFront();
        testResizeAfterWrapping();

        System.out.println("All tests passed.");
    }
}