package CodingNeetcodeLeetcode.BackTracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateSubset {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int index, int[] nums,
                           List<Integer> current,
                           List<List<Integer>> result) {

        // Every current combination is a valid subset
        result.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {
            // Choose
            current.add(nums[i]);

            // Explore
            backtrack(i + 1, nums, current, result);

            // Undo choice (backtrack)
            current.remove(current.size() - 1);
        }
    }
}
