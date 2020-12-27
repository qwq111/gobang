package flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ӹ����࣬����ģʽ
 * ����ʵ�����ӵ�����
 */
public class PieceFactory {
    private static PieceFactory instance;
    private final List<Piece> pieces; // ����

    private PieceFactory() {
        this.pieces =new ArrayList<>();
        pieces.add(new BlackPiece());
        pieces.add(new WhitePiece());
    }

    public static PieceFactory getInstance() {
        if(instance==null){
            instance=new PieceFactory();
        }
        return instance;
    }
    //����ʵ������
    public Piece getPiece(int chess){
        return pieces.get(chess-1);
    }


    public void setSize(int size) {
        for(Piece piece:pieces){
            piece.setSize(size);
        }
    }
}
