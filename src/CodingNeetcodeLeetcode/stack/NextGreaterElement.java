package CodingNeetcodeLeetcode.stack;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {
    public static void main(String[] args){
        int[] nums = {2, 1, 5, 3, 6};
        int length = nums.length;
        int [] res = new int[length];
        Arrays.fill(res,-1);

        Stack<Integer> stack = new Stack<>();
        for (int i =0; i<length; i++ ){
            while (!stack.isEmpty() && nums[i]>nums[stack.peek()]){
                int index = stack.pop();
                res[index]= nums[i];
            }
            stack.push(i);
        }
        Arrays.stream(res).forEach(System.out::println);
    }
}
