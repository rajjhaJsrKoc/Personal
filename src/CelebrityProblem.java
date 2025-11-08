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
}
