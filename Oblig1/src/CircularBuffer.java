public class CircularBuffer {
    // Implementerer logisk indeksering på en sirkulær buffer.
    // OBS: Konvertering skjer med bitmaske.
    // Dermed må størrelsen på buffer alltid være en toerpotens.
    private int[] buffer;
    private int start;
    private int size;
    private int capacity;
    
    public CircularBuffer(int initCapacity) {
        start = 0;
        size = 0;
        capacity = 1;
        while (capacity < initCapacity) {
            capacity *= 2;
        }
        buffer = new int[capacity];
    }

    public CircularBuffer() {
        // Konstruerer en tom ringbuffer med kapasitet 8
        this(8);
    }

    public CircularBuffer(int array[]) {
        start = 0;
        size = array.length;
        capacity = 1;
        while (capacity < array.length) {
            capacity *= 2;
        }
        buffer = new int[capacity];
        for (int i = start; i < size; i += 1) {
            buffer[i] = array[i];
        }
    }

    void resize (){
        // dobler kapasiteten til bufferen ved behov
        // shrink er ikke implementert
        int[] temp = new int[capacity*2];
        for(int i = 0; i < size; i++){
            temp[i] = buffer[(start + i) & (capacity - 1)]; 
        }
        start = 0;
        buffer = temp;
        capacity *= 2;
    }

    public int get(int i) {
        return buffer[(start + i) & (capacity - 1)];
    }

    public void set(int i, int value) {
        buffer[(start + i) & capacity - 1] = value;
    }

    void push_back(int value){
        // Hvis kapasiteten allerede er nådd
        if(size == capacity) {
            // dobler vi kapasiteten
            resize();
        }
        buffer[(start + size) & (capacity - 1)] = value;
        size += 1;
    }

    void push_front(int value){
        // Hvis kapasiteten allerede er nådd
        if(size == capacity) {
            // dobler vi kapasiteten
            resize();
        }
        if (start == 0) {
            start = capacity - 1;
        } else {
            start -= 1;
        }
        buffer[start] = value;
        size += 1;
    }

    public int length() {
        return size;
    }

    public int pop_front() {
        int value = buffer[start];
        start = (start + 1) & (capacity - 1);
        size -= 1;
        return value;
    }

    public int pop_back() {
        int value = buffer[(start + size - 1) & (capacity - 1)];
        size -= 1;
        return value;
    }
}
