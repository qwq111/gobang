/**
 * ������.״̬ģʽ
 * �洢ѡ����Ϣ
 * �������壬�������
 */
public abstract class Player {
    protected String name;//����
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
        String[] s=new String[]{"��","��"};
        return "����: "+getName()+"ִ"+s[getChess()-1];
    }
}
