package player;

import memento.PieceBoard;

public class Person extends Player{

    public Person(String name, PieceBoard board, int chess) {
        super(name,board, chess);
    }

    @Override
    public void play(int x, int y) {
        super.play(x,y);
        piece.nextPosition(x,y);
        board.setPiece(piece);
    }
}
