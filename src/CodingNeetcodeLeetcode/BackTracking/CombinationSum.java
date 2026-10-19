package CodingNeetcodeLeetcode.BackTracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {
        CombinationSum sol = new CombinationSum();

        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> result = sol.combinationSum(candidates, target);

        System.out.println(result);
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        BackTrack(0,candidates,target, new ArrayList<>(),result);
        return  result;
    }

    private void BackTrack(int index, int[] candidates, int remainings, ArrayList<Integer> current, List<List<Integer>> result) {
        if(remainings==0){
            result.add(new ArrayList<>(current));
            return;
        }
        if (remainings<0){
            return;
        }
        for (int i = index; i< candidates.length; i ++){
            current.add(candidates[i]);
            BackTrack(i,candidates,remainings-candidates[i],current,result);
            current.remove(current.size()-1);
        }
    }
}
