public class RotateAnElementByDSpace {
    public static void Rotatetoleft(int[] arr, int n, int k) {
        // n == 7 and k is rotation 2
        if (n == 0)
            return;
        k = k % n;
        // Taking out mode to make it even suppose 16 then 16/7 bacha 2
        if (k > n)
            return;
        int[] temp = new int[k];
        // putting everything in temp
        for (int i = 0; i < k; i++) {
            temp[i] = arr[i];
        }

        for (int i = 0; i < n - k; i++) {
            arr[i] = arr[i + k];
        }
        // 5 se chala 7 tak
        for (int i = n - k; i < n; i++) {
            // 5 index mai temp kaa 0 aaye gaa like that
            arr[i] = temp[i - n + k];
        }
    }
    public static void main(String args[]) {

        int[] arr = {1,2,3,4,5,6,7};
        int k = 2;
        Rotatetoleft(arr, arr.length, k);
        System.out.println("After Rotating the elements to left ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}