package CodingNeetcodeLeetcode.ArraysHashing;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        int[] ars = {1, 2, 3, 4};
        productOfArray(ars);
        productOfArrayWith1Space(ars);
    }
    private static void productOfArrayWith1Space(int[] ars){
        int zeros =0,indx= 0, product= 1;
        for (int i = 0; i < ars.length;i ++){
            if (ars[i]==0){
                zeros++;
                indx =i;
            }else {
                product = product*ars[i];
            }
        }
        int[] res = new int[ars.length];
        Arrays.fill(res, 0);
        if(zeros == 0){
            for (int i = 0; i< ars.length; i ++){
                res[i] = product/ars[i];
            }
        }else if (zeros==1){
            res[indx] = product;
        }
        Arrays.stream(res).forEach(System.out::println);
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
