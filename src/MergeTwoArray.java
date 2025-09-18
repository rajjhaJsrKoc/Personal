import java.util.Arrays;

public class MergeTwoArray {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 5, 12};
        int[] arr2 = {6, 7, 8, 9, 10,11};
        int[] arr3 = new int[arr1.length + arr2.length];

        int i = 0, j = 0, k = 0;
        while (i<arr1.length) {
            arr3[k++] = arr1[i++];
        }
        while (j<arr2.length) {
            arr3[k++] = arr2[j++];
        }
        Arrays.stream(arr3).forEach(System.out::println);


        // this will work for sorted array also
        int l =0, m =0;
        k =0;
        int[] arr4 = new int[arr1.length + arr2.length];
        while (l<arr1.length && m<arr2.length) {
            if (arr1[l] < arr2[m]) {
                arr4[k++] = arr1[l++];
            }else {
                arr4[k++] = arr2[m++];
            }
        }
        while (l<arr1.length) {
            arr4[k++] = arr1[l++];
        }
        while (m<arr2.length) {
            arr4[k++] = arr2[m++];
        }
        Arrays.stream(arr4).forEach(System.out::println);
    }
}
