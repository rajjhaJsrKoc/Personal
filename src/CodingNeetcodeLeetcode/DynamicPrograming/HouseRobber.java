package CodingNeetcodeLeetcode.DynamicPrograming;

public class HouseRobber {

        public int rob(int[] nums) {
            int n = nums.length;

            if (n == 1) {
                return nums[0];
            }

            int[] dp = new int[n];

            // Base cases
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);

            for (int i = 2; i < n; i++) {
                int robCurrent = nums[i] + dp[i - 2];
                int skipCurrent = dp[i - 1];

                dp[i] = Math.max(robCurrent, skipCurrent);
            }

            return dp[n - 1];
        }

        public static void main(String[] args) {
            HouseRobber sol = new HouseRobber();

            int[] nums = {2, 7, 9, 3, 1};

            System.out.println(sol.rob(nums));
        }
    }
