package LLD.TicTacToe;

import LLD.TicTacToe.PieceType.PieceType;
import LLD.TicTacToe.PieceType.PlayingPiece;

public class TicTacToe {
    public static void main(String[] args) {
        Player p1 = new Player("Rajat", new PlayingPiece(PieceType.X));
        Player p2 = new Player("AI", new PlayingPiece(PieceType.O));

        Game game = new Game(p1.getName(), p2.getName());
        game.startGame();
    }
}
