/*
 * Created by JFormDesigner on Fri Dec 18 00:15:43 CST 2020
 */


import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

/**
 * ���������,�߼����̵Ĺ۲��ߣ������������
 */
public class PieceBoardPanel extends JPanel implements Observer{
    //���������
    private PieceBoard board;//���̿�����
    private Position p=null; //��ǰ����
    //�������Բ���
    private int boardX;
    private int boardY; //X,Y���̵���ʼ����
    private int cellSize;//���̸��ӵĿ��

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
        this.setOpaque(false);//�������͸��
        initComponents();
    }


    /***
     * ��������
     * @param g ����
     */
    public void paintBoard(Graphics g){
        g.setColor(Color.BLACK);
        for(int i=0;i<Setting.BOARD_SIZE;i++){
            g.drawLine(boardX,boardY+i*cellSize,boardX+(Setting.BOARD_SIZE-1)*cellSize,boardY+i*cellSize);
            g.drawLine(boardX+i*cellSize,boardY,boardX+i*cellSize,boardY+(Setting.BOARD_SIZE-1)*cellSize);
        }
    }

    /**
     * ��������
     * @param g
     */
    public void paintPieces(Graphics g){
        PieceFactory factory= PieceFactory.getInstance();//������ӹ���
        factory.setSize(cellSize);//������������
        List<Position> pieces = board.getChessManual();//�������
        p = null;
        for(int i=0;i<pieces.size();i++) {
            p = pieces.get(i);
            factory.getPiece(p.getChess()).paint(g, boardX+p.getX()*cellSize, boardY+p.getY()*cellSize);
        }
        //���Ƶ�ǰ���ӵı��
        g.setColor(Color.RED);
        if(p!=null)
            g.fillOval(boardX+p.getX()*cellSize-cellSize/8,
                    boardY+p.getY()*cellSize-cellSize/8,cellSize/4,cellSize/4);
    }


    @Override
    public void paint(Graphics g) {
        System.out.println("��������");
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
        System.out.println("����״̬�ı�");
    }

}
