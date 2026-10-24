package CodingNeetcodeLeetcode.DynamicPrograming;

public class MaximumSubarrayProduct {

        public static int maxProduct(int[] nums) {

            // Maximum and minimum product ending at the current index
            int maxEnding = nums[0];
            int minEnding = nums[0];

            // Store the maximum product found so far
            int result = nums[0];

            for (int i = 1; i < nums.length; i++) {

                int num = nums[i];

                // A negative number reverses maximum and minimum roles
                if (num < 0) {
                    int temp = maxEnding;
                    maxEnding = minEnding;
                    minEnding = temp;
                }

                // Either start a new subarray or extend the previous one
                maxEnding = Math.max(num, maxEnding * num);

                // Track the minimum because it can become maximum
                // when multiplied by a future negative number
                minEnding = Math.min(num, minEnding * num);

                // Update the global maximum product
                result = Math.max(result, maxEnding);
            }

            return result;
        }

        // Driver function
        public static void main(String[] args) {

            int[] nums = {2, 3, -2, 4};

            int answer = maxProduct(nums);

            System.out.println("Maximum product: " + answer);
        }
    }

