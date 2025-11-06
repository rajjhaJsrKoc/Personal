package CodingNeetcodeLeetcode.ArraysHashing;

import java.util.HashMap;

public class findMaximumSubarrayLengthTarget {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 6, 7, 2, 2, 8, 9};
        int target = 4;
        int sum = 0;
        int left = 0, maxLength = 0;
        int startIdx = -1, endIdx = -1;
        HashMap<Integer, Integer> prefixsumIndex = new HashMap<>();

        prefixsumIndex.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
            if (prefixsumIndex.containsKey(sum - target)) {
                int prevIndex = prefixsumIndex.get(sum - target);
                int len = i - prevIndex;
                if (len > maxLength) {
                    maxLength = len;
                    startIdx = prevIndex + 1;
                    endIdx = i;
                }
            }
            prefixsumIndex.put(sum, i);
        }
// Minimum compute if absent
        if (startIdx != -1) {
            for (int k = startIdx; k <= endIdx; k++) {
                System.out.print(arr[k] + (k < endIdx ? ", " : ""));
            }
        }
    }
}
