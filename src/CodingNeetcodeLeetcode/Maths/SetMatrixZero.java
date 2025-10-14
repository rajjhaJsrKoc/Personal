package CodingNeetcodeLeetcode.Maths;

import java.util.Arrays;
import java.util.HashSet;

public class SetMatrixZero {
    public static void main(String[] args){
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        setZeroes(matrix);
        //System.out.println(Arrays.deepToString(matrix));
    }

    private static void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        HashSet<Integer> setRows = new HashSet<>();
        HashSet<Integer> setCols = new HashSet<>();
        // calculating indexing and putting in which all index in set where it should be zero
        for (int i =0 ; i <rows; i++){
            for (int j =0; j< cols; j ++){
                if (matrix[i][j]==0){
                    setCols.add(j);
                    setRows.add(i);
                }
            }
        }
        // whole row will be zero
        for (int i : setRows){
            Arrays.fill(matrix[i], 0);
        }
        // for columnn it will columnn plus row
        for (int i :setCols){
            for(int j =0; j< rows; j ++){
                matrix[j][i] = 0;
            }
        }
        Arrays.stream(matrix).map(Arrays::toString).forEach(System.out::println);
    }
}
