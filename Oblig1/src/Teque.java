public class Teque {
    private CircularBuffer L;
    private CircularBuffer R;

    public Teque() {
        L = new CircularBuffer();
        R = new CircularBuffer();
    }

    public void push_back(int value) {
        R.push_back(value);
        balance();
    }
    
    public void push_front(int value) {
        L.push_front(value);
        balance();
    }

    public void push_middle(int value) {
        // Oppgaven er tvetydig angående posisjon og indeks.
        // Velger å vokse L i det tilfelle |L| = |R|  
        if (L.length() > R.length()) {
            R.push_front(value);
        } else {
            L.push_back(value);
        }
    }

    public int length() {
        return L.length() + R. length();
    }

    public int get(int i) {
        if (i < L.length()){
            return L.get(i);
        } else {
            return R.get(i - L.length());
        }
    }

    private void balance() {
        // balanserer lengden på arrays og godtar at |L| = |R| + 1
        while(R.length() > L.length()) {
            L.push_back(R.pop_front());
        }
        while(L.length() > R.length() + 1) {
            R.push_front(L.pop_back());
        }
    }
}
