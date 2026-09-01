public class CircularBuffer {
    // Implementerer logisk indeksering på en sirkulær buffer.
    // Konvertering mellom logisk og absolutt indeksering skjer mod operatoren
    // Denne instruksjonen(basert på idiv) er normalt krevende i forhold til 
    // bit-manipulering, men brukes inntil videre pga. lesbarhet.
    private
        int[] buffer;
        int start;
        int size;
        int capacity;

    public CircularBuffer(int initCapacity) {
        start = 0;
        size = 0;
        capacity = 1;
        while (capacity < initCapacity) {
            capacity *= 2;
        }
        buffer = new int[capacity];
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
        for(int i = start; i < size; i++){
            temp[i] = buffer[(start + i) % capacity]; 
        }
        buffer = temp;
        capacity *= 2;
    }

    public int get(int i) {
        return buffer[(start + i) % capacity];
    }

    public void set(int i, int value) {
        buffer[(start + i) % capacity] = value;
    }

    void push_back(int value){
        // Hvis kapasiteten allerede er nådd
        if(size == capacity) {
            // dobler vi kapasiteten
            resize();
        }
        buffer[(start + size) % capacity] = value;
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
}
