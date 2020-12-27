package flyweight;

import config.Setting;

import java.awt.*;

/**
 * ºÚÆå
 */
public class BlackPiece extends Piece{
    public BlackPiece() {
        super(Setting.black.getImage());
    }

    @Override
    public void paint(Graphics g, int X, int Y) {

        g.drawImage(image,X-size/2,Y-size/2,size,size,null);
    }
}
