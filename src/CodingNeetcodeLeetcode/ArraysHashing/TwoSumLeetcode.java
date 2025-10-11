package CodingNeetcodeLeetcode.ArraysHashing;

import java.util.*;

public class TwoSumLeetcode {
    public static String twoSum(int n, int []arr, int target) {
        Arrays.sort(arr);
        int left = 0, right = n - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return "YES";
            } else if (sum < target) left++;
            else right--;
        }
        return "NO";
    }


    public static String twoSumBruteForce(int n, int []arr, int target) {
        for (int i = 0; i < n; i++) {
            // this not exactly sub array it is 2 sum thats why i+1
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == target) return "YES";
            }
        }
        return "NO";
    }

    public static boolean twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (seen.containsKey(complement)) {
                // return 1-based indices as per LeetCode requirement
                return true;
                // return new int[]{seen.get(complement) + 1, i + 1};
            }
            seen.put(nums[i], i);
        }
        return false; // not found
    }

    public static int prefixSum(int[] nums, int target) {
        Map frequency = new HashMap();
        frequency.put(0, 1);
        // so there can be a case where it starts with 0 and has value
        int prefixSum = 0,count = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (frequency.containsKey(prefixSum - target)) {
                count =  count + (int)frequency.get(prefixSum - target);
            }
            frequency.put(prefixSum, ((int) frequency.getOrDefault(prefixSum,0) + 1));
        }
        return count;
    }

        public static void main(String args[]) {
        int n = 5;
        int[] arr = {10, 2, -2, -20, 10};
        int target = -10;
        int prefixSum = prefixSum(arr, target);
        System.out.println("Prefix Sum:" + prefixSum);
        String ans = twoSum(n, arr, target);
        boolean ans2 = twoSum(arr, target);
        System.out.println("This is the answer for variant 1: " + ans);
        returnSubarraysIndexs(arr,target);
        returnSubarrays(arr,target);

    }

    private static void returnSubarraysIndexs(int[] arr, int target) {
        Map<Integer,List<Integer>> map = new HashMap<>();
        ArrayList<int []> result = new ArrayList<>();

        int prefix =0;
        map.put(0, new ArrayList<>(List.of(-1)));
        for (int i = 0; i < arr.length; i ++){
            prefix += arr[i];
            if (map.get(prefix- target)!= null){
                for (int startIndex : map.get(prefix - target)) {
                    result.add(new int[]{startIndex + 1, i});
                }
            }
            if (!map.containsKey(prefix)){
                map.put(prefix, new ArrayList<>());
            }
            map.get(prefix).add(i);
        }
        result.stream()
                .forEach(arr1 -> System.out.println(Arrays.toString(arr1)));
    }
    private static void returnSubarrays(int[] arr, int target) {
        Map<Integer,List<Integer>> map = new HashMap<>();
        ArrayList<int []> result = new ArrayList<>();

        int prefix =0;
        map.put(0, new ArrayList<>(List.of(-1)));
        for (int i = 0; i < arr.length; i ++){
            prefix += arr[i];
            if (map.get(prefix- target)!= null){
                for (int startIndex : map.get(prefix - target)) {
                    int k =0;
                    int [] arr2 = new int[i-startIndex];
                    for (int start = startIndex+1; start< i ;start++ ){
                        arr2[k++] =arr[start];
                    }
                    result.add(arr2);
                }
            }

            if (!map.containsKey(prefix)){
                map.put(prefix, new ArrayList<>());
            }
            map.get(prefix).add(i);
        }
        result.stream()
                .forEach(arr1 -> System.out.println(Arrays.toString(arr1)));
    }

}
