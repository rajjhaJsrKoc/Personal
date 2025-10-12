package CodingNeetcodeLeetcode.TwoPointers;

public class CheckPalindrom {
    public static void main(String[] args){

        String[] tests = {
                "A man, a plan, a canal: Panama",
                "race a car",
                "",
                "0P",
                ".,",
                null
        };
        for (String t : tests) {
            System.out.printf("'%s' -> %s%n", t, isPalindrome(t));
        }

    }

    private static boolean isPalindrome(String t) {
        if (t==null){
            return  false;
        }
        if (t.isEmpty()){
            return true;
        }
        String lowercase = t.toLowerCase();
        int start =0;
        int end = lowercase.length()-1;
        while (start<end){
            while (start<end && !Character.isLetterOrDigit(lowercase.charAt(end))){
                end--;
            }
            while (start<end && !Character.isLetterOrDigit(lowercase.charAt(start))){
                start++;
        }
            if (lowercase.charAt(start)== lowercase.charAt(end)){
                start++;
                end--;
            }else return false;
        }
        return true;
    }
}
