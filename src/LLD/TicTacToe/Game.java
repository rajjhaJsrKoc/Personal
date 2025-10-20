package LLD.TicTacToe;

import LLD.TicTacToe.PieceType.PieceType;
import LLD.TicTacToe.PieceType.PlayingPiece;
import LLD.TicTacToe.PieceType.PlayingPieceO;

import java.util.Scanner;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Scanner scanner;

    public Game(String playerOneName, String playerTwoName) {
        board = new Board();
        player1 = new Player(playerOneName, new PlayingPieceO(PieceType.O));
        player2 = new Player(playerTwoName, new PlayingPiece(PieceType.X));
        currentPlayer = player1;
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("🎮 Welcome to Tic Tac Toe!");
        board.printBoard();

        while (true) {
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getPlayingPiece().pieceType + ")");
            System.out.print("Enter row and column (0, 1, or 2): ");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!board.placeMark(row, col, currentPlayer.getPlayingPiece().pieceType)) {
                System.out.println("❌ Invalid move! Try again.");
                continue;
            }

            board.printBoard();

            if (board.checkWin(currentPlayer.getPlayingPiece().pieceType)) {
                System.out.println("🎉 " + currentPlayer.getName() + " wins!");
                break;
            } else if (board.isFull()) {
                System.out.println("🤝 It's a draw!");
                break;
            }

            switchPlayer();
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
}
