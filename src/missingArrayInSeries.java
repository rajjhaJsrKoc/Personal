import java.util.Arrays;

public class missingArrayInSeries {
    public static int missingNumber(int []a, int N) {

        for (int i = 1; i <= N; i++) {
            int flag = 0;
            for (int j = 0; j < N - 1; j++) {
                if (a[j] == i) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) return i;
        }
        return -1;
    }

    public static void main(String args[]) {
        int N = 5;
        int a[] = {1, 2, 4, 5};

        int ans = missingNumber(a, N);
        int missingNum = missingNumber2(a, N);
        System.out.println("New sum approach:"+ missingNum);
        System.out.println("The missing number is: " + ans);
    }

   /* Sum of first N numbers(S1) = (N*(N+1))/2
    Sum of all array elements(S2) = i = 0n-2a[i]
    The missing number = S1-S2*/

    public static int missingNumber2(int []a, int N) {
        int sum = 0;
        for(int i = 0; i < a.length; i++){
            sum +=a[i];
        }
        int missingNum = (N*(N+1))/2 - sum;
        return missingNum;
    }
}

