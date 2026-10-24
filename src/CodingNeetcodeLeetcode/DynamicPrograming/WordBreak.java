package CodingNeetcodeLeetcode.DynamicPrograming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

        public static boolean wordBreak(String s, List<String> wordDict) {

            Set<String> dict = new HashSet<>(wordDict);
            int n = s.length();

            // dp[i] = true if s.substring(0, i) can be segmented
            // into words from the dictionary
            boolean[] dp = new boolean[n + 1];

            // Empty string can always be segmented
            dp[0] = true;

            for (int i = 1; i <= n; i++) {

                // Try every possible last word ending at index i - 1
                for (int j = 0; j < i; j++) {

                    // Prefix before j must be segmentable,
                    // and substring from j to i must be a dictionary word
                    if (dp[j] && dict.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }

            return dp[n];
        }

        // Driver function
        public static void main(String[] args) {

            String s = "leetcode";

            List<String> wordDict =
                    Arrays.asList("leet", "code");

            System.out.println(wordBreak(s, wordDict));
        }
    }

