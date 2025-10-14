package CodingNeetcodeLeetcode.Maths;

import java.util.Arrays;

public class PlusOneNeetcode {
    public static void main(String[] args){
        int[] digits = {2, 9, 9};

         Arrays.stream(plusOne(digits)).forEach(System.out::println);
    }

    private static int[] plusOne(int[] digits) {
        for (int i = digits.length-1; i>=0; i--){
            if(digits[i]<9){
                ++digits[i];
                return digits;
            }
            digits[i]=0;
        }
        int[] ans = new int[digits.length+1];
        ans[0]= 1;
        return ans;
    }
}
