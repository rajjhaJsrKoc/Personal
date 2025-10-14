package CodingNeetcodeLeetcode.Maths;

import java.util.HashSet;

public class HappyNumber {
    public static void main(String[] args){
        int n = 19;

        HashSet<Integer> seen = new HashSet<>();

        while (n!=1 && !seen.contains(n)){
            int square=0;
            seen.add(n);
            while (n!=0){
               int prefix = n %10;
               square=square + prefix * prefix;
               n = n/10;
            }
            n =square;
        }
        if (n==1)
            System.out.println(true);
        else
            System.out.println(false);
    }
}
