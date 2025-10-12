package CodingNeetcodeLeetcode.TwoPointers;

import java.util.Arrays;

public class RotateElementByKthElement {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        int length = arr.length;
        int k =3;
        rotateTheArray(arr,0,k-1);
        rotateTheArray(arr,k,length-1);
        rotateTheArray(arr,0,length-1);
        Arrays.stream(arr).forEach(System.out::println);

    }

    private static void rotateTheArray(int[] arr, int i, int i1) {
        while (i<=i1) {
            int temp = arr[i];
            arr[i] = arr[i1];
            arr[i1] = temp;
            i++;
            i1--;
        }
    }
}
