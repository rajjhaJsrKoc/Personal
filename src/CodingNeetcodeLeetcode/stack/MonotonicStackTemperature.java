package CodingNeetcodeLeetcode.stack;

import java.util.Arrays;
import java.util.Stack;

public class MonotonicStackTemperature {
    public static void main(String[] args){
        int[] temp ={73, 74, 75, 71, 69, 72, 76, 73};
        int n = temp.length;
        int[] res = new int[n];
        Arrays.fill(res, 0);
        Stack<Integer> stack= new Stack<>();

        for (int i = 0; i<n ; i++){
            while (!stack.isEmpty() && temp[i]>temp[stack.peek()]){
                int index = stack.pop();
                res[index] = i-index;
            }
            stack.push(i);
        }
        Arrays.stream(res).forEach(System.out::print);
    }

}
