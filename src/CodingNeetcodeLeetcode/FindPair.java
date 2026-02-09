package CodingNeetcodeLeetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class FindPair {
    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 1, 2};
        int left = 0;
        int right = arr.length - 1;
        //int [] result = new int[10];
        ArrayBlockingQueue<Integer> arr1 = new ArrayBlockingQueue<>(7);
        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 7; i++) {
                    arr1.put(i);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        );

        System.out.print(findpair(arr));
    }

    public static List<List<Integer>> findpair(int[] arr) {
        int right = arr.length - 1;
        int left = 0;
        List<List<Integer>> pairNumber = new ArrayList();
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == 2) {
                pairNumber.add(List.of(arr[left]));
                //  pairNumber.add(arr[right]);
                left++;
                right--;
            } else if (sum < 2) {
                left++;
            } else {
                right--;
            }

        }
        return pairNumber;
        // [1,2,3,1,2,3]
        //[2,2,3,2,3]
        //[6,3,3]
        //[6,6]
        //[12]
    }
}
