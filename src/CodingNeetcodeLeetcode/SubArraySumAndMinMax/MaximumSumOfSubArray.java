package CodingNeetcodeLeetcode.SubArraySumAndMinMax;

public class MaximumSumOfSubArray {
    public static void main(String[] args) {
        int [] array = {1,4,9,-3,2,-6,1};
        int sum = array[0];
        int maximum = 0;
        for (int i = 1; i < array.length; i++) {
            sum=Math.max(array[i]+sum, array[i]);
            maximum=Math.max(maximum,sum);
        }
        System.out.println(maximum);
    }
}
