package CodingNeetcodeLeetcode.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationNoDublicate {

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(candidates);

            backtrack(0, candidates, target, new ArrayList<>(), result);
            return result;
        }

        private void backtrack(int start, int[] candidates, int remaining,
                               List<Integer> current, List<List<Integer>> result) {
            if (remaining == 0) {
                result.add(new ArrayList<>(current));
                return;
            }

            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i - 1]) {
                    continue;
                }

                if (candidates[i] > remaining) {
                    break;
                }

                current.add(candidates[i]);

                backtrack(i + 1, candidates, remaining - candidates[i],
                        current, result);

                current.remove(current.size() - 1);
            }
        }

        // Driver function
        public static void main(String[] args) {
            CombinationNoDublicate solution = new CombinationNoDublicate();

            int[] candidates = {10, 1, 2, 7, 6, 1, 5};
            int target = 8;

            List<List<Integer>> result =
                    solution.combinationSum2(candidates, target);

            System.out.println(result);
        }
    }
