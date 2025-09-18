import java.util.HashSet;

public class RemoveDublicateAndRemoveElementInArray {
    public static void main(String[] args) {
        int[] arr = {1,1,2,2, 4,2,3,3};
        int[] arr1 = {1,1,2,4,2,3,3};
        int k = removeElement(arr, 1);
        int x = newremoveDuplicates(arr1);
        System.out.println("Testing brute force :" +x);
        System.out.println("The array after removing duplicate elements is :" + k);
        for (int i = 0; i < k; i++) {
            System.out.print("Rajat new " + arr[i] + " ");
        }
        for (int i = 0; i < x; i++) {
            System.out.print("test" + arr1[i] + " ");
        }
    }
    static int removeElement(int[] arr, int value) {
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] != value) {
                arr[i] = arr[j];
                i++;
            }
        }
        return i;
    }
    static int newremoveDuplicates(int[] arr) {
        HashSet< Integer > set = new HashSet < > ();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        int k = set.size();
        int j = 0;
        for (int x: set) {
            arr[j++] = x;
        }
        return k;
    }
}

