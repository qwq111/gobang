package memento;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

/***
 * 棋盘类,负责展示棋盘，同时判断是否能够落子
 * 目标类，被观察者
 */
public class PieceBoard extends Observable {
    private final Position p = new Position(1,1,1);//棋子
    private final int[][] pieces;//棋盘状态,0代表为空，1代表黑棋，2代表白棋
    private final List<PositionMemento> chessManual;
    private boolean flag;// 0 表示游戏开始，1表示游戏结束

    /**
     * 初始化棋盘
     * @param size 棋盘大小
     */
    public PieceBoard(int size) {
        //棋盘大小
        pieces=new int[size][size];
        chessManual=new ArrayList<>();
        start();
    }

    /**
     * 检擦当前位置能否落子
     * @param x 逻辑位置横
     * @param y 逻辑位置竖
     * @return 成功返回true，失败返回false
     */
    public boolean check(int x,int y){
        //如果没有棋子
        return pieces[x][y] == 0;
    }



    /**
     * 清空棋盘状态
     */
    public void clear(){
        for(int[] p:pieces){
            Arrays.fill(p,0);
        }
        chessManual.clear();
        start();
    }



    /**
     * 放置棋子
     * @param p 棋子位置信息
     */
    public void setPiece(Position p){
        pieces[p.getX()][p.getY()]=p.getChess();
        chessManual.add(p.createMemento());
        //棋子状态改变，通知所有观察者
        setChanged();
        notifyObservers(p);
    }

    /**
     * 返回逻辑棋盘，交给裁判类去判断胜利。
     * @return 逻辑棋盘返回
     */
    public int[][] getPieces() {
        return pieces;
    }


    /**
     * @return 返回棋子数量
     */
    public int getPieceNums() {
        return chessManual.size();
    }

    /**
     * 棋盘的悔棋操作
     */
    public void remove() {
        if(chessManual.size()>0){
            PositionMemento memento = chessManual.get(chessManual.size()-1);
            //归位
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
