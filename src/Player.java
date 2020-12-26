/**
 * 棋手类.状态模式
 * 存储选手信息
 * 负责下棋，提出悔棋
 */
public abstract class Player {
    protected String name;//姓名
    protected Position piece;
    protected PieceBoard board;

    public String getName() {
        return name;
    }

    public int getChess() {
        return piece.getChess();
    }

    public Player(String name, PieceBoard board,int chess) {
        this.name = name;
        this.board = board;
        piece=new Position(chess,-1,-1);
    }

    public abstract void play(int x, int y);

    @Override
    public String toString() {
        String[] s=new String[]{"黑","白"};
        return "棋手: "+getName()+"执"+s[getChess()-1];
    }
}
