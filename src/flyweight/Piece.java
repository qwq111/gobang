package flyweight;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.URI;
import java.net.URL;

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
     * @param g
     * @param X
     * @param Y
     */
    public abstract void paint(Graphics g,int X,int Y);

    public void setSize(int size) {
        this.size = size;
    }
}
