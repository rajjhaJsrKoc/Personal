package CodingNeetcodeLeetcode.BackTracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartition {
    public static void main(String[] args) {
        PalindromePartition sol = new PalindromePartition();

        String s = "aab";

        List<List<String>> result = sol.partition(s);

        System.out.println(result);
    }

    private List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(0,s,new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int i, String s, ArrayList current, List<List<String>> result) {
        if (s.length()==i){
            result.add(new ArrayList<>(current));
            return;
        }
        for (int end = i;end<s.length();end++){
            if (isPalindrome(s,i,end)){
                current.add(s.substring(i,end+1));
                backtrack(end+1,s,current,result);
                current.remove(current.size()-1);
            }
    }
    }

    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
