import javafx.geometry.Pos;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

/***
 * ������,����չʾ���̣�ͬʱ�ж��Ƿ��ܹ�����
 * Ŀ���࣬���۲���
 */
public class PieceBoard extends Observable {
    private int size;//���̴�С
    private int[][] pieces;//����״̬,0����Ϊ�գ�1������壬2�������
    private List<Position> chessManual;
    public static int flag = 0;// 0 ��ʾ��Ϸ��ʼ��1��ʾ��Ϸ����

    /**
     * ��ʼ������
     * @param size
     */
    public PieceBoard(int size) {
        this.size = size;
        pieces=new int[size][size];
        chessManual=new ArrayList<>();
    }

    /**
     * �����ǰλ���ܷ�����
     * @param x
     * @param y
     * @return boolean
     */
    public boolean check(int x,int y){
        //���û������
        if(pieces[x][y]==0) return true;
        return false;
    }



    public Position getLast(){
        Position p=null;
        try{
            if(chessManual.size()>0){
                p= (Position) chessManual.get(getPieceNums()-1).clone();
            }
        }catch (Exception e){
            System.out.println("���ƴ���");
        }
        return p;
    }

    public void clear(){
        for(int[] p:pieces){
            Arrays.fill(p,0);
        }
        chessManual.clear();
        flag = 0;
    }



    /**
     * ��������
     * @param p
     */
    public void setPiece(Position p){
        pieces[p.getX()][p.getY()]=p.getChess();
        chessManual.add(p);
        //����״̬�ı䣬֪ͨ���й۲���
        setChanged();
        notifyObservers(p);
    }

    /**
     * �����߼����̣�����������ȥ�ж�ʤ����
     * @return
     */
    public int[][] getPieces() {
        return pieces;
    }


    /**
     * ���ص�ǰ��������
     * @return
     */
    public int getPieceNums() {
        return chessManual.size();
    }

    /**
     * ���̵Ļ������
     */
    public void remove() {
        if(chessManual.size()>0){
            Position p = chessManual.get(chessManual.size()-1);
            //��λ
            pieces[p.getX()][p.getY()]=0;
            chessManual.remove(p);
        }
        flag = 0;
    }

    /**
     * ��������
     * @return
     */
    public final List<Position> getChessManual() {
        return chessManual;
    }
}
