package SortingAndSearching;

import java.util.Arrays;

public class DutchNationalFlag {
    public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                // put 0s to the left
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // keep 1s in the middle
                mid++;
            } else { // nums[mid] == 2
                // put 2s to the right
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 0, 2, 1, 2};
        sortColors(arr);
        System.out.println(Arrays.toString(arr)); // [0, 0, 1, 1, 1, 2, 2]
    }
}
