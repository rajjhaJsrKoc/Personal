import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {
    public static int[] nextGreater(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1); // default is -1 (no greater element)

        Stack<Integer> stack = new Stack<>(); // will store indices

        for (int i = 0; i < n; i++) {
            // While current element is greater than element at stack top
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                int idx = stack.pop();
                result[idx] = nums[i]; // nums[i] is next greater for nums[idx]
            }
            stack.push(i);
        }
        return result;
    }



    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 3, 6};
        int[] ans = nextGreater(nums);
        System.out.println(Arrays.toString(ans));
    }
}
