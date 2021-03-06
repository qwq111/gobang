package flyweight;

import java.awt.*;

/**
 * 棋子类
 * 保存棋子的大小、图片
 * 给定外部状态，负责展示棋子
 */
public abstract class Piece {
    protected int size;
    protected Image image;


    public Piece(Image image) {
        this.image = image;
    }

    /**
     * 绘制棋子
     * @param g 画笔
     * @param X 绘画位置x
     * @param Y 绘画位置y
     */
    public abstract void paint(Graphics g,int X,int Y);

    public void setSize(int size) {
        this.size = size;
    }
}
