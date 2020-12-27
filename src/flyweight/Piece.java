package flyweight;

import java.awt.*;

/**
 * ������
 * �������ӵĴ�С��ͼƬ
 * �����ⲿ״̬������չʾ����
 */
public abstract class Piece {
    protected int size;
    protected Image image;


    public Piece(Image image) {
        this.image = image;
    }

    /**
     * ��������
     * @param g ����
     * @param X �滭λ��x
     * @param Y �滭λ��y
     */
    public abstract void paint(Graphics g,int X,int Y);

    public void setSize(int size) {
        this.size = size;
    }
}
