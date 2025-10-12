package CodingNeetcodeLeetcode.TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args){
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }

    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length-2; i++) {
            if (i>0 && nums[i] == nums[i - 1]){
                continue;
            }
            int start = i+1, end = nums.length - 1;
            while (start < end) {
                while (start<end && nums[start]==nums[start+1]){
                    start++;
                }
                while (start<end && nums[end]==nums[end-1]){
                    end--;
                }
                int sum= nums[i]+ nums[start]+ nums[end];
                if (sum== 0){
                    result.add(List.of(nums[i],nums[start],nums[end]));
                    start++;
                    end--;
                }
                if (sum<0)start++;
                if (sum>0)end--;
            }
        }
        return result;
    }
}
