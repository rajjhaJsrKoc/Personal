package CodingNeetcodeLeetcode.binarySearch;

public class SearchInSorted2dArray {
    public static void main(String[] args){
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target = 3;
        int colsize =matrix[0].length, rowSize =matrix.length;
        int low=0,high = colsize *rowSize -1;
        while (low<= high){
            int mid =(high+low)/2;
            int row = mid/rowSize;
            int col = mid%colsize;
            if (matrix[row][col]== target){
                System.out.println(matrix[row][col]);
            }

            if (matrix[row][col]<target){
                low = mid +1;
            }else
                high = mid-1;

        }
    }
}
