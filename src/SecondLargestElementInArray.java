

class SecondLargestElementInArray
{
     private static void getElements(int[] arr, int n)
    {
        if (n == 0 || n==1)
        {
            System.out.print(-1);
            System.out.print(" ");
            System.out.print(-1);
            System.out.print("\n");
        }
        int small = Integer.MAX_VALUE;
        int second_small = Integer.MAX_VALUE;
        int large = Integer.MIN_VALUE;
        int second_large = Integer.MIN_VALUE;
        int i;
        for (i = 0;i < n;i++)
        {
            small = Math.min(small,arr[i]);
            large = Math.max(large,arr[i]);
        }
        for (i = 0;i < n;i++)
        {
            if (arr[i] < second_small && arr[i] != small)
            {
                second_small = arr[i];
            }
            if (arr[i] > second_large && arr[i] != large)
            {
                second_large = arr[i];
            }
        }

        System.out.println("Second smallest is "+second_small);
        System.out.println("Second largest is "+second_large);
    }

    static private int secondLargest(int[] arr, int n)
    {
        if(n<2)
            return -1;
        int large = Integer.MIN_VALUE;
        int second_large = Integer.MIN_VALUE;
        int i;
        for (i = 0; i < n; i++)
        {
            if (arr[i] > large)
            {
                second_large = large;
                large = arr[i];
            }
            // this is for the unsorted array like 3, 3, 5, 4
            else if (arr[i] > second_large && arr[i] != large)
            {
                second_large = arr[i];
            }
        }
        System.out.println("Second largest is "+second_large);
        return second_large;
    }

    public static double findMaxAverageSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        // Compute the sum of the first 'k' elements
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        // Initialize maxSum as the sum of the first window
        int maxSum = sum;

        // Slide the window across the array
        for (int i = k; i < n; i++) {
            sum += nums[i];      // Add new element entering window
            sum -= nums[i - k];  // Remove element leaving window
            maxSum = Math.max(maxSum, sum); // Update maxSum
        }

        // Return maximum average
        return (double) maxSum / k;
    }

    public static void main(String[] args)
    {
        int[] arr = {1, 2, 4, 5, 7, 7, 6};
        int n = arr.length;
        // general
        getElements(arr, n);
        // optimized way to do it
        secondLargest(arr, n);

        // Sliding window average of subarray
        System.out.println(findMaxAverageSlidingWindow(arr, 3));

        //writing by my own
        getSecondElement(arr, arr.length);

    }

    public static void getSecondElement(int [] arr, int n){
         int largest = Integer.MIN_VALUE;
         int secondlarge = Integer.MIN_VALUE;
         for (int i = 0; i < n; i++) {
             if(arr[i] > largest){
               secondlarge = largest;
               largest = arr[i];
             }
             else if(arr[i] > secondlarge && arr[i] != largest) {
                 secondlarge = arr[i];
             }
         }
         System.out.println("Second largest is "+secondlarge);
    }
}
