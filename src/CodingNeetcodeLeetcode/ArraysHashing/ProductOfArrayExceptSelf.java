package CodingNeetcodeLeetcode.ArraysHashing;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        int[] ars = {1, 2, 3, 4};
        productOfArray(ars);
    }

    private static void productOfArray(int[] ars) {
        int[] prefix = new int[ars.length];
        prefix[0]=1;
        for (int i = 1; i < ars.length; i++) {
            prefix[i] = ars[i-1]*prefix[i-1];
        }
        Arrays.stream(prefix).forEach(e -> System.out.print(e + " "));
        int[] suffix = new int[ars.length];
        suffix[ars.length-1]=1;
        for (int i = ars.length-2; i >= 0; i--) {
            suffix[i] = ars[i+1]*suffix[i+1];
        }
        int[] product = new int[ars.length];
        for (int i = 0; i < ars.length; i++) {
            product[i] = prefix[i]*suffix[i];
        }
        Arrays.stream(product).forEach(e -> System.out.print(e + " "));
    }
}
