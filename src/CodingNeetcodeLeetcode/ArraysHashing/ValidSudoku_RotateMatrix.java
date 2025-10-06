package CodingNeetcodeLeetcode.ArraysHashing;

import java.util.Arrays;
import java.util.HashSet;

public class ValidSudoku_RotateMatrix {

    public static void main(String[] args) {

        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        System.out.println(isValidSudoku(board)); // Expected: true

        // Example with invalid sudoku
        char[][] invalidBoard = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','5','.','1','9','5','.','.','.'}, // duplicate '5' in same row
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        int[][] arr = {{1,2,3},
                {4,5,6},
                {7,8,9}
        };
        rotate(arr);
        Arrays.stream(arr).map(Arrays::toString).forEach(System.out::println);
        System.out.println(isValidSudoku(invalidBoard)); // Expected: false
    }

    private static boolean isValidSudoku(char[][] board) {
        HashSet<String> validSudoku = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char number = board[i][j];
                if ((number!= '.')) {
                    if(!validSudoku.add(number + "row" + i) ||
                            !validSudoku.add(number + "column" + j)||
                         !validSudoku.add(number + "inner matrix of 3*3" + i/3 + "-" +j/3)
                        ){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;


        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }
    public static int countConnections(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int totalConnections = 0;

        int prevCount = 0; // no. of 1s in last valid row

        for (int i = 0; i < m; i++) {
            int currCount = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) currCount++;
            }

            if (currCount > 0) {
                if (prevCount > 0) {
                    totalConnections += prevCount * currCount;
                }
                prevCount = currCount; // update last valid row
            }
        }
        return totalConnections;
    }

}
