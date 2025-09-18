package SortingAndSearching;

public class LinearSearch {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        linearSearch(arr,8);
        findMaximum(arr);
    }

    private static void findMaximum(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        System.out.println(max);
    }

    private static void linearSearch(int[] arr, int i) {
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == i) {
                System.out.println(arr[j]);
            }
        }
    }
}
