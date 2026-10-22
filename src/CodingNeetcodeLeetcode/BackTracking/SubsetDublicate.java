package CodingNeetcodeLeetcode.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetDublicate {


        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();

            Arrays.sort(nums);

            backtrack(0, nums, new ArrayList<>(), result);

            return result;
        }

        private void backtrack(int start, int[] nums,
                               List<Integer> current,
                               List<List<Integer>> result) {
            result.add(new ArrayList<>(current));

            for (int i = start; i < nums.length; i++) {

                // Skip duplicate elements at the same recursion level
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }
                current.add(nums[i]);
                backtrack(i + 1, nums, current, result);

                // Backtrack
                current.remove(current.size() - 1);
            }
        }

        // Driver function
        public static void main(String[] args) {
            SubsetDublicate sol = new SubsetDublicate();

            int[] nums = {1, 2, 2};

            List<List<Integer>> result = sol.subsetsWithDup(nums);

            System.out.println(result);
        }
    }
