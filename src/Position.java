/***
 * ����������
 * ������������
 */
public class Position implements Cloneable{
    private int chess;
    private int x;
    private int y;

    public Position(int chess, int x, int y) {
        this.chess = chess;
        this.x = x;
        this.y = y;
    }

    public void setChess(int chess) {
        this.chess = chess;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getChess() {
        return chess;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //��һ�����ӵĿ��ٱ任
    public void nextPosition(int x, int y) {
        chess = chess==1?2:1;
        this.x=x;
        this.y=y;
    }
}
