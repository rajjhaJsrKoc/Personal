package CodingNeetcodeLeetcode.ArraysHashing;

import java.util.*;

public class findTheNumberThatAppearOnceAndOtherAppearTwice {
    public static int getSingleElement(int []arr) {
        int n = arr.length;
        HashMap<Integer, Integer> mpp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int value = mpp.getOrDefault(arr[i], 0);
            mpp.put(arr[i], value + 1);
        }

        for (Map.Entry<Integer, Integer> it : mpp.entrySet()) {
            if (it.getValue() == 1) {
                return it.getKey();
            }
        }
        return -1;
    }

    public static int getSingleElemettwoPointer(int [] arr){
        Arrays.sort(arr);
        int n = arr.length;
        int l =0;
        while ( l < n-1){
            if (arr[l] != arr[l + 1]){
                return arr[l];
            }
            l = l+2;
        }
        return arr[n-1];
    }

    public static void main(String args[]) {
        int[] arr = {4, 1, 2, 1, 2};
        int ans = getSingleElement(arr);
        int ans2 = getSingleElemettwoPointer(arr);
        System.out.println("The single element is: " + ans);
        System.out.println("The single element is e: " + ans2);
    }
}

