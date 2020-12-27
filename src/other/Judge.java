package other;

import memento.PieceBoard;
import memento.Position;

import java.util.Observable;
import java.util.Observer;

/***
 * 裁判类
 * 负责对棋盘进行操作，包括下棋，悔棋
 */
public class Judge extends Observable implements Observer {
    private PieceBoard board;// 所观察的棋盘

    public Judge(PieceBoard board) {
        super();
        this.board = board;
    }


    /**
     * 通知裁判执行悔棋操作，单次悔棋悔2子
     * @return
     */
    public void remove(){
        System.out.println("悔棋");
        board.remove();
        board.remove();
    }

    /***
     * 检查棋盘，判断是否胜利,然后通知观察者
     */
    private void checkBoard(Position p){
        if(board.getPieceNums()== Setting.BOARD_SIZE*Setting.BOARD_SIZE) {
            setChanged();
            notifyObservers(0);
        }
        if(check(board.getPieces(),p)){
            board.stop();//裁判调整棋盘状态，设置为不可以下棋
            setChanged();//通知观察者
            notifyObservers(p.getChess());
        }
    }

    /**
     * 判断当前棋子是否为赢家棋子
     * 返回布尔值
     * true为胜利, false为非胜利情况
     * count为 count+1子棋 的意思，调皮ing，比如是五子棋的话， count = 4
     */
    private boolean check(int[][] board, Position position){

        int count = 4;
        if(checkLine(board, position, 0, 1)+checkLine(board, position, 0, -1)>=count)
        {
            // 0°方向 和 180°方向 查找
            System.out.println("0°方向 和 180°方向 查找");
            return true;
        }
        else if(checkLine(board, position, -1, 1)+checkLine(board, position, 1, -1)>=count)
        {
            // 45°方向 和 225°方向 查找
            System.out.println("45°方向 和 225°方向 查找");
            return true;
        }
        else if(checkLine(board, position, -1, 0)+checkLine(board, position, 1, 0)>=count)
        {
            // 90°方向 和 270°方向 查找
            System.out.println("90°方向 和 270°方向 查找");
            return true;
        }
        else if(checkLine(board, position, -1, -1)+checkLine(board, position, 1, 1)>=count)
        {
            // 135°方向 和 315°方向 查找
            System.out.println("135°方向 和 315°方向 查找");
            return true;
        }
        return false;
    }

    private int checkLine(int[][] board, Position position, int targetX, int targetY)
    {
        /*该函数用来检查某方向直线上是否有连续的相同颜色五颗的棋子
         * position 传入的是第一颗棋子
         * targetX, targetY 代表查找的方向
         * 返回值 count 表示在该方向上与第一颗棋子连续且颜色相同的棋子数
         *  */
        int count = 0;
        // 在该方向上找与第一个棋子连续的第一个棋子作为目标棋子 , (x, y)是该目标棋子的坐标
        int x = position.getX()+targetX;
        int y = position.getY()+targetY;

        // 在该方向上最多查找4次即可
        for(int i=0; i<4; i++)
        {
            if((0<=x&&x<board.length)&&(0<=y&&y<board.length)) // 所查找的目标棋子是合法的棋子（即在棋盘范围内）
            {
                // 得到目标棋子的颜色
                int chess = board[x][y];

                // 判断颜色是否和第一颗棋子相同, 如果不相同，则结束查找
                if(chess != position.getChess())
                {
                    break;
                }
                else
                {
                    // 如果相同, 则计数加一, 将目标棋子的坐标改为下一个是我期望颜色的棋子的坐标
                    count++;
                    x += targetX;
                    y += targetY;
                }
            }
        }
        return count;
    }


    /**
     * 棋盘通知裁判进行检查
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass()==PieceBoard.class){
            Position p = (Position) arg;
            checkBoard(p);
        }
    }
}
