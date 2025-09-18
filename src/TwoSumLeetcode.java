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

    }

}
