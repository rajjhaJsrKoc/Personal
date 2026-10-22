package CodingNeetcodeLeetcode.DynamicPrograming;

public class LongestPalindromicSubstring {
        public String longestPalindrome(String s) {
            int n = s.length();
            boolean[][] dp = new boolean[n][n];

            int start = 0;
            int maxLen = 1;

            for (int i = n - 1; i >= 0; i--) {
                dp[i][i] = true;

                for (int j = i + 1; j < n; j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        if (j - i == 1 || dp[i + 1][j - 1]) {
                            dp[i][j] = true;

                            int len = j - i + 1;
                            if (len > maxLen) {
                                maxLen = len;
                                start = i;
                            }
                        }
                    }
                }
            }

            return s.substring(start, start + maxLen);
        }
    }
