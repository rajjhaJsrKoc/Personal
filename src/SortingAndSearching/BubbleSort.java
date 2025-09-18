package SortingAndSearching;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = { 5, 4, 3, 2, 1 };
        sortedArray(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    private static void sortedArray(int[] arr) {
        boolean found;
        for (int i = 0; i < arr.length - 1; i++) {
            found = false;
            for (int j = 0; j < (arr.length-i-1); j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    found = true;
                }
            }
            if (!found) {
                break;
            }
        }
    }
}
