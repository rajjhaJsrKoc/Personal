package CodingNeetcodeLeetcode.ArraysHashing;

public class MoveNonZeroToOneSide {

    // two pointers
    public static int[] moveZeros(int n, int []a) {
        int j = 0;
        /*//place the pointer j:
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                j = i;
                break;
            }
        }

        //no non-zero elements:
        if (j == -1) return a;
*/
        //Move the pointers i and j
        //and swap accordingly:
       /* for (int i = j ; i < n; i++) {
            if (a[i] != 0) {
                //swap a[i] & a[j]:
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                j++;
            }
        }*/
        //return a;


        int right = 0, left = 0;
        for (right = 0 ; right < a.length; right++) {
            if (a[right] != 0) {
                int tmp = a[right];
                a[right] = a[left];
                a[left] = tmp;
                left++;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 2, 3, 2, 0, 0, 4, 5, 1};
        int n = 10;
        int[] ans = moveZeros(n, arr);
        for (int i = 0; i < n; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println("");
    }
}