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


    public void clear(){
        for(int[] p:pieces){
            Arrays.fill(p,0);
        }
        chessManual.clear();
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
     * ���ص�ǰλ�õ�������ɫ
     * @param x
     * @param y
     * @return
     */
    public int getChess(int x, int y) {
        return pieces[x][y];
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
    }

    /**
     * ��������
     * @return
     */
    public final List<Position> getChessManual() {
        return chessManual;
    }
}
