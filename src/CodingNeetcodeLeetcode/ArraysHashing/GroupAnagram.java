package CodingNeetcodeLeetcode.ArraysHashing;

import java.util.*;

public class GroupAnagram {
    public static void main(String[] args) {
        String[] input = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(input));
        System.out.println(groupAnagrams2(input));
    }

    private static List<List<String>> groupAnagrams(String[] input) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : input) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String key = new String(c);
            //map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] counts = new int[26];
            for (char c : s.toCharArray()) counts[c - 'a']++;
            StringBuilder sb = new StringBuilder();
            for (int cnt : counts) {
                sb.append('#').append(cnt); // e.g., "#1#0#2..."
            }
            String key = sb.toString();
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

}
