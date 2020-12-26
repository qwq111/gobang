/*
 * Created by JFormDesigner on Fri Dec 18 00:15:43 CST 2020
 */


import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

/**
 * 棋盘面板类,逻辑棋盘的观察者，负责绘制棋盘
 */
public class PieceBoardPanel extends JPanel implements Observer{
    //对象类参数
    private PieceBoard board;//棋盘控制类
    private Position p=null; //当前棋子
    //基础属性参数
    private int boardX;
    private int boardY; //X,Y棋盘的起始坐标
    private int cellSize;//棋盘格子的宽度

    public int getBoardX() {
        return boardX;
    }

    public int getBoardY() {
        return boardY;
    }

    public int getCellSize() {
        return cellSize;
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        this.cellSize =( height > width?width:height)/ Setting.BOARD_SIZE;
        this.boardX=(width-(Setting.BOARD_SIZE-1)*this.cellSize)/2;
        this.boardY=(height-(Setting.BOARD_SIZE-1)*this.cellSize)/2;
    }

    public void setBoard(PieceBoard board){
        this.board = board;
    }
    public PieceBoardPanel(){
        super(true);
        this.setOpaque(false);//设置面板透明
        initComponents();
    }


    /***
     * 绘制棋盘
     * @param g 画笔
     */
    public void paintBoard(Graphics g){
        g.setColor(Color.BLACK);
        for(int i=0;i<Setting.BOARD_SIZE;i++){
            g.drawLine(boardX,boardY+i*cellSize,boardX+(Setting.BOARD_SIZE-1)*cellSize,boardY+i*cellSize);
            g.drawLine(boardX+i*cellSize,boardY,boardX+i*cellSize,boardY+(Setting.BOARD_SIZE-1)*cellSize);
        }
    }

    /**
     * 绘制棋子
     * @param g
     */
    public void paintPieces(Graphics g){
        PieceFactory factory= PieceFactory.getInstance();//获得棋子工厂
        factory.setSize(cellSize);//设置棋子属性
        List<Position> pieces = board.getChessManual();//获得棋谱
        p = null;
        for(int i=0;i<pieces.size();i++) {
            p = pieces.get(i);
            factory.getPiece(p.getChess()).paint(g, boardX+p.getX()*cellSize, boardY+p.getY()*cellSize);
        }
        //绘制当前棋子的标记
        g.setColor(Color.RED);
        if(p!=null)
            g.fillOval(boardX+p.getX()*cellSize-cellSize/8,
                    boardY+p.getY()*cellSize-cellSize/8,cellSize/4,cellSize/4);
    }


    @Override
    public void paint(Graphics g) {
        System.out.println("绘制棋盘");
        System.out.println(boardX+","+boardY+","+cellSize);
        super.paint(g);
        paintBoard(g);
        paintPieces(g);
    }

    private void initComponents() {
        setLayout(null);
        {
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        paintPieces(getGraphics());
        System.out.println("棋盘状态改变");
    }

}
