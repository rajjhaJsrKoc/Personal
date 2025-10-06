package CodingNeetcodeLeetcode.SubArraySumAndMinMax;

public class MaximumStolenThief {
    public static void main(String[] args) {
        int[] hval = {6, 7, 1, 3, 8, 2, 4};
        System.out.println(maxLoot(hval));
    }

    private static String maxLoot(int[] hval) {
        if (hval == null || hval.length == 0) {
            return null;
        }

        int secondLast = 0, last = hval[0];
        int res = 0;
        for (int i = 1; i < hval.length; i++) {
            res = Math.max(hval[i] + secondLast, last);
            secondLast = last;
            last = res;
        }
        return String.valueOf(res);
    }
}
