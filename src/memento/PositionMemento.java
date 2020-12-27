package memento;

/**
 * 备忘录模式，备忘录类
 */
class PositionMemento {
    private int chess;
    private int x;
    private int y;

    public int getChess() {
        return chess;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setMemento(Position p){
        this.chess=p.getChess();
        this.x = p.getX();
        this.y=p.getY();
    }
}
