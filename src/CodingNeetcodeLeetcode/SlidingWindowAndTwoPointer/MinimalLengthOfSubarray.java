package CodingNeetcodeLeetcode.SlidingWindowAndTwoPointer;

public class MinimalLengthOfSubarray {
    public static void main(String[] args){
        int[] arrr = {1,1,1,1,1,1,1,1};
        int target = 11;
        int count= Integer.MAX_VALUE ;
        //int lenght
        BruteForce(arrr, target, count);
        Optimum(arrr,target,count);
    }

    private static void Optimum(int[] arrr, int target, int count) {
        int left =0, sum = 0;
        for(int right = 0; right<arrr.length;right++){
            sum = sum+ arrr[right];
            while (sum>=target){
                count = Math.min(right-left+1,count);
                sum= sum -arrr[left];
                left++;
            }
        }
        System.out.println(count == Integer.MAX_VALUE ? 0 : count);
    }

    private static void BruteForce(int[] arrr, int target, int count) {
        for (int i = 0; i < arrr.length; i++){
            int sum = arrr[i];
            if (sum>= target){
                count = Math.min(count,1);
                break;
            }
            for (int j = i+1; j< arrr.length; j++){
                sum = sum + arrr[j];
                if (sum >= target) {
                    count = Math.min(j-i+1, count);
                }
            }
        }
        if (count == Integer.MAX_VALUE){
            System.out.println(0);
        }else
            System.out.println(count);
    }
}
