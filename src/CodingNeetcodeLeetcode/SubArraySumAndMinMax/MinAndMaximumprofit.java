package CodingNeetcodeLeetcode.SubArraySumAndMinMax;

public class MinAndMaximumprofit {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Max Profit: " + maxProfit(prices)); // Output: 7
    }

    private static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return -1;
        }
        int currentPrice = prices[0];
        int maxProfit = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            currentPrice = Math.min(currentPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - currentPrice);
        }
        return maxProfit;
    }

}
