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
    private final Position p = new Position(1,1,1);//����
    private final int[][] pieces;//����״̬,0����Ϊ�գ�1������壬2�������
    private final List<PositionMemento> chessManual;
    private boolean flag;// 0 ��ʾ��Ϸ��ʼ��1��ʾ��Ϸ����

    /**
     * ��ʼ������
     * @param size ���̴�С
     */
    public PieceBoard(int size) {
        //���̴�С
        pieces=new int[size][size];
        chessManual=new ArrayList<>();
        start();
    }

    /**
     * �����ǰλ���ܷ�����
     * @param x �߼�λ�ú�
     * @param y �߼�λ����
     * @return �ɹ�����true��ʧ�ܷ���false
     */
    public boolean check(int x,int y){
        //���û������
        return pieces[x][y] == 0;
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
     * @param p ����λ����Ϣ
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
     * @return �߼����̷���
     */
    public int[][] getPieces() {
        return pieces;
    }


    /**
     * @return ������������
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
