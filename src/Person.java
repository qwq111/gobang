public class Person extends Player{

    public Person(String name, PieceBoard board,int chess) {
        super(name,board, chess);
    }

    @Override
    public void play(int x, int y) {
        Position p = null;
        piece.setX(x);
        piece.setY(y);
        try{
             p= (Position) piece.clone();
        }catch (Exception e){
            System.out.println("���Ӹ��ƴ���");
        }
        board.setPiece(p);
    }
}
