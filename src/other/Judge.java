package other;

import memento.PieceBoard;
import memento.Position;

import java.util.Observable;
import java.util.Observer;

/***
 * ������
 * ��������̽��в������������壬����
 */
public class Judge extends Observable implements Observer {
    private PieceBoard board;// ���۲������

    public Judge(PieceBoard board) {
        super();
        this.board = board;
    }


    /**
     * ֪ͨ����ִ�л�����������λ����2��
     * @return
     */
    public void remove(){
        System.out.println("����");
        board.remove();
        board.remove();
    }

    /***
     * ������̣��ж��Ƿ�ʤ��,Ȼ��֪ͨ�۲���
     */
    private void checkBoard(Position p){
        if(board.getPieceNums()== Setting.BOARD_SIZE*Setting.BOARD_SIZE) {
            setChanged();
            notifyObservers(0);
        }
        if(check(board.getPieces(),p)){
            board.stop();//���е�������״̬������Ϊ����������
            setChanged();//֪ͨ�۲���
            notifyObservers(p.getChess());
        }
    }

    /**
     * �жϵ�ǰ�����Ƿ�ΪӮ������
     * ���ز���ֵ
     * trueΪʤ��, falseΪ��ʤ�����
     * countΪ count+1���� ����˼����Ƥing��������������Ļ��� count = 4
     */
    private boolean check(int[][] board, Position position){

        int count = 4;
        if(checkLine(board, position, 0, 1)+checkLine(board, position, 0, -1)>=count)
        {
            // 0�㷽�� �� 180�㷽�� ����
            System.out.println("0�㷽�� �� 180�㷽�� ����");
            return true;
        }
        else if(checkLine(board, position, -1, 1)+checkLine(board, position, 1, -1)>=count)
        {
            // 45�㷽�� �� 225�㷽�� ����
            System.out.println("45�㷽�� �� 225�㷽�� ����");
            return true;
        }
        else if(checkLine(board, position, -1, 0)+checkLine(board, position, 1, 0)>=count)
        {
            // 90�㷽�� �� 270�㷽�� ����
            System.out.println("90�㷽�� �� 270�㷽�� ����");
            return true;
        }
        else if(checkLine(board, position, -1, -1)+checkLine(board, position, 1, 1)>=count)
        {
            // 135�㷽�� �� 315�㷽�� ����
            System.out.println("135�㷽�� �� 315�㷽�� ����");
            return true;
        }
        return false;
    }

    private int checkLine(int[][] board, Position position, int targetX, int targetY)
    {
        /*�ú����������ĳ����ֱ�����Ƿ�����������ͬ��ɫ��ŵ�����
         * position ������ǵ�һ������
         * targetX, targetY ������ҵķ���
         * ����ֵ count ��ʾ�ڸ÷��������һ��������������ɫ��ͬ��������
         *  */
        int count = 0;
        // �ڸ÷����������һ�����������ĵ�һ��������ΪĿ������ , (x, y)�Ǹ�Ŀ�����ӵ�����
        int x = position.getX()+targetX;
        int y = position.getY()+targetY;

        // �ڸ÷�����������4�μ���
        for(int i=0; i<4; i++)
        {
            if((0<=x&&x<board.length)&&(0<=y&&y<board.length)) // �����ҵ�Ŀ�������ǺϷ������ӣ��������̷�Χ�ڣ�
            {
                // �õ�Ŀ�����ӵ���ɫ
                int chess = board[x][y];

                // �ж���ɫ�Ƿ�͵�һ��������ͬ, �������ͬ�����������
                if(chess != position.getChess())
                {
                    break;
                }
                else
                {
                    // �����ͬ, �������һ, ��Ŀ�����ӵ������Ϊ��һ������������ɫ�����ӵ�����
                    count++;
                    x += targetX;
                    y += targetY;
                }
            }
        }
        return count;
    }


    /**
     * ����֪ͨ���н��м��
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
