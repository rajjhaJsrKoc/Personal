import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {

    public static List<List<Integer>> generatePascalsTriangle(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        if (numRows <= 0) {
            return triangle;
        }

        // First row
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangle.add(firstRow);

        for (int i = 1; i < numRows; i++) {
            List<Integer> prevRow = triangle.get(i - 1);
            List<Integer> currentRow = new ArrayList<>();

            // First element of each row is 1
            currentRow.add(1);

            // Calculate intermediate elements
            for (int j = 1; j < i; j++) {
                currentRow.add(prevRow.get(j - 1) + prevRow.get(j));
            }

            // Last element of each row is 1
            currentRow.add(1);
            triangle.add(currentRow);
        }
        return triangle;
    }

    public static void printPascalsTriangle(List<List<Integer>> triangle) {
        for (List<Integer> row : triangle) {
            // Optional: Add spacing for a triangular shape
            // For example, to center the triangle
            // int numSpaces = triangle.size() - row.size();
            // for (int i = 0; i < numSpaces; i++) {
            //     System.out.print(" ");
            // }

            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int numRows = 5; // Number of rows to generate
        List<List<Integer>> pascalTriangle = generatePascalsTriangle(numRows);
        printPascalsTriangle(pascalTriangle);
    }
}
