package flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * 棋子工厂类，单例模式
 * 控制实际棋子的数量
 */
public class PieceFactory {
    private static PieceFactory instance;
    private final List<Piece> pieces; // 棋子

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
    //返回实际棋子
    public Piece getPiece(int chess){
        return pieces.get(chess-1);
    }


    public void setSize(int size) {
        for(Piece piece:pieces){
            piece.setSize(size);
        }
    }
}
