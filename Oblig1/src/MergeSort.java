import java.util.Arrays;
public class MergeSort {
    static public int[] sort(int[] array) {
        if(array.length <= 1) {
            return array;
        }
        int len = array.length;
        int levels = 1;
        // finner levels = ceil(log_2(N))
        while (1 << levels < len) {
            levels++;
        }
        int[][] mergeArea = new int[levels][len];
        for(int i = 0; i < array.length; i+=2){
            merge(mergeArea[0], array, i, 1);
        }
        for(int level = 1; level < levels; level++) {
            int divide = 2 << level;
            for(int i = 0; i < len; i+=divide)
                merge(mergeArea[level], mergeArea[level - 1], i, divide/2);
        }
        return mergeArea[levels - 1];
    }
    static void merge(int[] to, int[] from, int left, int N){
        // samler to like lange arrays med lengde N
        // |to| = 2|from| = 2N
        int i = 0;
        int j = 0;
        // algoritme fra boken skrevet om til indeksering
        while (i < N && j < N) {
            if (from[left + i] < from[left + N + j]) {
                to[left + i + j] = from[left + i];
                i++;
            } else {
                to[left + i + j] = from[left + N + j];
                j++;
            }
        }
        while (i < N) {
            to[left + i + j] = from[left + i];
            i++;
        }
        while (j < N) {
            to[left + i + j] = from[left + N + j];
            j++;
        }
    }

    public static void main (String[] args) {
        System.out.println(Arrays.toString(sort(new int[]{4,1,2,3})));
    }
}
