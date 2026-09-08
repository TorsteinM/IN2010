public class Deque {
    // Implementerer en double ended queue rundt en sirkulær buffer
    private CircularBuffer buffer;

    public Deque() {
        buffer = new CircularBuffer(16);
    }

    public Deque(int initCapacity) {
        buffer = new CircularBuffer(initCapacity);
    }
    
    public Deque(int[] array) {
        buffer = new CircularBuffer(array);
    }

    public void push_back(int value){
        buffer.push_back(value);
    }

    public void push_front(int value){
        buffer.push_front(value);
    }

    public int get(int i){
        return buffer.get(i);
    }
}
