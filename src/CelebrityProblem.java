public class CelebrityProblem {
    public static void main(String[] args){
        int[][] mat = { { 1, 1, 0 },
                { 0, 1, 0 },
                { 0, 1, 1 } };

        int n = mat.length;
        int i =0,j =n-1;
        while (i<j){
            if(mat[i][j]==1){
                i++;
            } else {
                j--;
            }
        }
        int celeb =i;
        boolean isCeleb = true;
        for (int k = 0; k < n; k++) {
            if (k == celeb) continue;
            // celeb should not know anyone, and everyone should know celeb
            if (mat[celeb][k] == 1 || mat[k][celeb] == 0) {
                isCeleb = false;
                break;
            }
        }

        if (isCeleb)
            System.out.println("Celebrity found: " + celeb);
        else
            System.out.println("No celebrity found");
        }
    public static int celebrity(int[][] M) {
    // Size of the given matrix
    int n = M.length;

    // To store count of people who know person of index i
    int[] knowMe = new int[n];

    // To store count of people who the person of index i knows
    int[] Iknow = new int[n];

    // Traverse the matrix to calculate knowMe and Iknow
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {

            // If person i knows person j
            if (M[i][j] == 1) {
                knowMe[j]++;  // Person j is known by person i
                Iknow[i]++;   // Person i knows person j
            }
        }
    }

    // Traverse all persons to find the celebrity
    for (int i = 0; i < n; i++) {
        // If person i knows no one and is known by everyone else
        if (knowMe[i] == n - 1 && Iknow[i] == 0) {
            return i;  // Person i is the celebrity
        }
    }
        return -1;
    }
}
