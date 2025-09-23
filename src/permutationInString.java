public class permutationInString {
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        for (char c : s1.toCharArray()) {
            s1Count[c - 'a']++;
        }

        int window = s1.length();

        for (int i = 0; i < s2.length(); i++) {
            s2Count[s2.charAt(i) - 'a']++;
            // removing the elements when
            if (i >= window) {
                s2Count[s2.charAt(i - window) - 'a']--;
            }

            if (matches(s1Count, s2Count)) {
                return true;
            }
        }
        return false;
    }

    private static boolean matches(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "ooao", s2 = "eidbaooo";
        System.out.println(checkInclusion(s1, s2)); // true
    }
}
