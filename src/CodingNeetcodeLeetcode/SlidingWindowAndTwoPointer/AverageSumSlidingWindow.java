package CodingNeetcodeLeetcode.SlidingWindowAndTwoPointer;

public class AverageSumSlidingWindow {
    public static void main(String[] args){
        int[] arr = {1,2,3,4,5};
        //int target = 7;
        int k = 3;
        int sum = 0;
        int average = 0;
        for (int i =0; i < k;i++){
            sum = sum + arr[i];
        }
        average = sum/k;
        for (int j = k ; j < arr.length; j++){
            sum = sum + arr[j];
            sum = sum - arr[j-k];
            average = Math.max(sum/k,average);
        }
        System.out.println(average);
    }
}
