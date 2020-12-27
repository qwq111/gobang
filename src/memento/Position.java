package memento;

/***
 * 棋子坐标类,备忘录原发器
 * 用作参数传递
 */
public class Position{
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


    /**
     * 备忘录模式，生成备忘录
     * @return 生成备忘录
     */
    public PositionMemento createMemento(){
        PositionMemento memento=new PositionMemento();
        memento.setMemento(this);
        return memento;
    }
    public void restoreMemento(PositionMemento memento){
        this.chess=memento.getChess();
        this.x=memento.getX();
        this.y=memento.getY();
    }

    //下一个棋子的快速变换
    public void nextPosition(int x, int y) {
        this.x=x;
        this.y=y;
    }

    @Override
    public String toString() {
        return "memento.Position{" +
                "chess=" + chess +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
