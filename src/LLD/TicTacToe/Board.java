package LLD.TicTacToe;

import LLD.TicTacToe.PieceType.PieceType;

public class Board {
    private PieceType[][] grid;
    private static final int SIZE = 3;

    public Board() {
        grid = new PieceType[SIZE][SIZE];
    }

    public void printBoard() {
        System.out.println("\nCurrent Board:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print((grid[i][j] == null ? "-" : grid[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public boolean placeMark(int row, int col, PieceType pieceType) {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && grid[row][col] == null) {
            grid[row][col] = pieceType;
            return true;
        }
        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == null) return false;
            }
        }
        return true;
    }

    public boolean checkWin(PieceType pieceType) {
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if ((grid[i][0] == pieceType && grid[i][1] == pieceType && grid[i][2] == pieceType) ||
                    (grid[0][i] == pieceType && grid[1][i] == pieceType && grid[2][i] == pieceType)) {
                return true;
            }
        }

        // Check diagonals
        if ((grid[0][0] == pieceType && grid[1][1] == pieceType && grid[2][2] == pieceType) ||
                (grid[0][2] == pieceType && grid[1][1] == pieceType && grid[2][0] == pieceType)) {
            return true;
        }

        return false;
    }
}
