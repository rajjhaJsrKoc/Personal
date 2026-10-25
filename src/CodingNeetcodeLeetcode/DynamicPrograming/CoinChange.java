package CodingNeetcodeLeetcode.DynamicPrograming;

import java.util.Arrays;

public class CoinChange {
        public static int coinChange(int[] coins, int amount) {

            // dp[i] = minimum coins needed to make amount i
            int[] dp = new int[amount + 1];

            // Initialize with an impossible large value
            Arrays.fill(dp, amount + 1);

            // Zero coins are needed to make amount 0
            dp[0] = 0;

            // Calculate the minimum coins for every amount
            for (int i = 1; i <= amount; i++) {

                // Try every available coin
                for (int coin : coins) {

                    if (coin <= i) {

                        // Use this coin and solve the remaining amount
                        dp[i] = Math.min(
                                dp[i],
                                dp[i - coin] + 1
                        );
                    }
                }
            }

            // If amount is still unreachable, return -1
            return dp[amount] > amount ? -1 : dp[amount];
        }

        // Driver function
        public static void main(String[] args) {

            int[] coins = {1, 2, 5};
            int amount = 11;

            System.out.println(coinChange(coins, amount));
        }
    }
