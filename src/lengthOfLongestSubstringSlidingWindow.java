import java.util.HashSet;

public class lengthOfLongestSubstringSlidingWindow {

    public static void main(String[] args) {
        String subArray = "abcddcfda";
        System.out.println(lengthOfLongestSubstringSlidingWindow(subArray));
        System.out.println(longestSubstring(subArray));

    }

    public static int lengthOfLongestSubstringSlidingWindow(String s) {
        int n = s.length();
        HashSet<Character> seen = new HashSet<>(); // Store characters in the current window
        int maxLength = 0;
        int left = 0;

        // Expand window by moving 'right'
        for (int right = 0; right < n; right++) {
            while (seen.contains(s.charAt(right))) {
                seen.remove(s.charAt(left));
                left++;
            }
            seen.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public  static String longestSubstring(String s) {
        int n = s.length();
        HashSet<Character> seen = new HashSet<>();
        int maxLength = 0;
        int left = 0;
        int start = 0;
        for (int right = 0; right < n; right++) {
            while (seen.contains(s.charAt(right))) {
                seen.remove(s.charAt(left));
                left++;
            }
            seen.add(s.charAt(right));
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                start = left; // update best window start
            }
        }
        return s.substring(start, start + maxLength);
    }

// JUST RETRUN THE SUBSTRING
    /*
            if (right - left + 1 > maxLength) {
        maxLength = right - left + 1;
        start = left; // update best window start
    }
}

    return s.substring(start, start + maxLength);
*/


}
