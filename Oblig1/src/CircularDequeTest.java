public class CircularDequeTest {
    // Testklasse generert med KI.
    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError(
                "Expected " + expected + ", but got " + actual
            );
        }
    }

    private static void assertContents(
        CircularDeque deque,
        int... expected
    ) {
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], deque.get(i));
        }
    }

    private static void testDefaultConstructor() {
        CircularDeque deque = new CircularDeque();

        deque.push_back(2);
        deque.push_front(1);
        deque.push_back(3);

        assertContents(deque, 1, 2, 3);
    }

    private static void testCapacityConstructor() {
        CircularDeque deque = new CircularDeque(2);

        deque.push_back(1);
        deque.push_back(2);
        deque.push_back(3);

        assertContents(deque, 1, 2, 3);
    }

    private static void testArrayConstructor() {
        CircularDeque deque =
            new CircularDeque(new int[]{2, 3, 4});

        deque.push_front(1);
        deque.push_back(5);

        assertContents(deque, 1, 2, 3, 4, 5);
    }

    private static void testMixedPushes() {
        CircularDeque deque = new CircularDeque(4);

        deque.push_back(3);
        deque.push_front(2);
        deque.push_back(4);
        deque.push_front(1);
        deque.push_back(5);

        assertContents(deque, 1, 2, 3, 4, 5);
    }

    private static void testResizeAfterWrapping() {
        CircularDeque deque = new CircularDeque(4);

        deque.push_back(1);
        deque.push_back(2);
        deque.push_back(3);
        deque.push_front(0);

        // Buffers er full og start ligger sist i den fysiske arrayen.
        // Neste innsetting utloeser resize av en wrappet buffer.
        deque.push_back(4);

        assertContents(deque, 0, 1, 2, 3, 4);
    }

    public static void main(String[] args) {
        testDefaultConstructor();
        testCapacityConstructor();
        testArrayConstructor();
        testMixedPushes();
        testResizeAfterWrapping();

        System.out.println("All CircularDeque tests passed.");
    }
}
