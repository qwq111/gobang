package memento;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

/***
 * ������,����չʾ���̣�ͬʱ�ж��Ƿ��ܹ�����
 * Ŀ���࣬���۲���
 */
public class PieceBoard extends Observable {
    private Position p = new Position(1,1,1);//����
    private int size;//���̴�С
    private int[][] pieces;//����״̬,0����Ϊ�գ�1������壬2�������
    private List<PositionMemento> chessManual;
    private boolean flag;// 0 ��ʾ��Ϸ��ʼ��1��ʾ��Ϸ����

    /**
     * ��ʼ������
     * @param size
     */
    public PieceBoard(int size) {
        this.size = size;
        pieces=new int[size][size];
        chessManual=new ArrayList<>();
        start();
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



    /**
     * �������״̬
     */
    public void clear(){
        for(int[] p:pieces){
            Arrays.fill(p,0);
        }
        chessManual.clear();
        start();
    }



    /**
     * ��������
     * @param p
     */
    public void setPiece(Position p){
        pieces[p.getX()][p.getY()]=p.getChess();
        chessManual.add(p.createMemento());
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
            PositionMemento memento = chessManual.get(chessManual.size()-1);
            //��λ
            pieces[memento.getX()][memento.getY()]=0;
            chessManual.remove(memento);
        }
        start();
    }

    public boolean isPlay() {
        return flag;
    }
    public void start(){
        flag = true;
    }
    public void stop(){
        flag = false;
    }

    public Position getPiece(int i) {
        p.restoreMemento(chessManual.get(i));
        return p;
    }
}
