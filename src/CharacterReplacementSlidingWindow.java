public class CharacterReplacementSlidingWindow {
    public static void main(String[] args) {
        String s1 = "AABABBA";
        int k1 = 1;
        System.out.println("Input: " + s1 + ", k = " + k1);
        System.out.println("Output: " + characterReplacement(s1, k1)); // Expected: 4

        String s2 = "ABAB";
        int k2 = 2;
        System.out.println("Input: " + s2 + ", k = " + k2);
        System.out.println("Output: " + characterReplacement(s2, k2)); // Expected: 4

        String s3 = "ABCDE";
        int k3 = 1;
        System.out.println("Input: " + s3 + ", k = " + k3);
        System.out.println("Output: " + characterReplacement(s3, k3)); // Expected: 2
    }

    public static int characterReplacement(String s, int k) {
        int[] count = new int[26]; // count of characters in window
        int maxCount = 0; // max frequency in current window
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'A']++;
            maxCount = Math.max(maxCount, count[s.charAt(right) - 'A']);

            // If replacements needed > k, shrink window
            while (right - left + 1 - maxCount > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
