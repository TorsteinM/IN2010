import java.util.Arrays;
public class SelectionSort {
    static public int[] sort(int[] array) {
        for (int pos = 0; pos < array.length - 1; pos++){
            int minValue = array[pos];
            int minIndex = pos;
            // Finner den minste verdien
            for(int i = pos; i < array.length; i++){
                if (array[i] < minValue) {
                    minValue = array[i];
                    minIndex = i;
                }
            }
            // Bytt med nåværende posisjon hvis mindre verdi er funnet
            if (pos != minIndex) {
                int temp = array[pos];
                array[pos] = minValue;
                array[minIndex] = temp;
            }
        }
        return array;
    }

    public static void main (String[] args) {
        System.out.println(Arrays.toString(sort(new int[]{4,2,3})));
    }
}
