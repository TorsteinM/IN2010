import java.util.Arrays;
public class InsertionSort {
    static public int[] sort(int[] array) {
        // Betrakt alt til venstre for posisjonen pos som sortert
        for (int pos = 1; pos < array.length; pos++){
            // Ta vare på verdien som skal settes inn
            int posValue = array[pos];
            // Tell ned indeks
            int i = pos;
            // så lenge innenfor bounds og posValue er større
            while(--i >= 0 && posValue < array[i]) {
                //
                array[i + 1] =  array[i];
            }
            // indekset som brøt løkken er ett for langt, så legg til en
            array[i + 1] = posValue;
        }
        return array;
    }

    public static void main (String[] args) {
        System.out.println(Arrays.toString(sort(new int[]{4,2,3})));
    }
}
