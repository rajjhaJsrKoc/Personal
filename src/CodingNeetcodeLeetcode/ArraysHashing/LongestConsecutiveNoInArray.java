package CodingNeetcodeLeetcode.ArraysHashing;

import java.util.HashSet;

public class LongestConsecutiveNoInArray {
    public static void main(String[] args) {
        int[] input = {1,2,3,4,5,6,7,9,10};

        HashSet set = new HashSet();
        for(int i = 0; i < input.length; i++) {
            set.add(input[i]);
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < input.length; i++) {
            // if current element is the starting point of sequence
            if (!set.contains(input[i] - 1)) {
                int counter = 0;
                // value plus counter will give the same input
                while(set.contains(input[i] + counter)) {
                    counter++;
                }
                max = Math.max(max, counter);
            }
        }
        System.out.println(max);
    }
}
