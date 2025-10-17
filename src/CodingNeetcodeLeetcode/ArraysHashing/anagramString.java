package CodingNeetcodeLeetcode.ArraysHashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class anagramString {
    public static void main(String args[]) {

        String string2 = "rajta";
        String string1 = "rajat";
        Boolean ans = findiftwoStringAnagram(string1,string2);
        System.out.println("The maximum  consecutive 1's are " + ans);
        findIfTwoStringIsAnagram(string1,string2);
    }


    // This is hashing way we need frequency map
    private static Boolean findiftwoStringAnagram(String string1, String string2) {
        if(string1.length()!=string2.length()){
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (char c : string1.toCharArray()) {
                map.put(c, map.getOrDefault(c,0)+1);
        }
        for (char c : string2.toCharArray()) {
                map.put(c, map.getOrDefault(c,0)-1);

        }
        for (Map.Entry<Character,Integer> map1 : map.entrySet()) {
            if(map1.getValue()!=0){
                return false;
            }
        }
        return true;
    }

    public static void findIfTwoStringIsAnagram(String string1, String string2){
        int MAX_CHAR = 26;
        int [] index = new int[MAX_CHAR];

        for (char c : string1.toCharArray()) {
            index[c-'a']++;
        }
        for (char c : string2.toCharArray()) {
            index[c-'a']--;
        }

        boolean isAnagram = Arrays.stream(index).allMatch(x -> x == 0);

        if (isAnagram) {
            System.out.println("It is Anagram");
        } else {
            System.out.println("It is not Anagram");
        }
    }
}