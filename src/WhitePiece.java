import java.awt.*;

/**
 * °×Æå
 */
public class WhitePiece extends Piece{

    public WhitePiece() {
        super(Setting.white.getImage());
    }

    @Override
    public void paint(Graphics g, int X, int Y) {
        g.drawImage(image,X-size/2,Y-size/2,size,size,null);
    }
}
