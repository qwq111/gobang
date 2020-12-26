import java.sql.Time;
import java.util.Random;

public class Machine extends Player{
    private int[][] score=new int[Setting.BOARD_SIZE][Setting.BOARD_SIZE];
    private Random random=new Random();
    public Machine(String name, PieceBoard board,int chess) {
        super(name,board, chess);
    }

    public Position searchPosition(){
        int[][] chessboard=board.getPieces();
        //ÿ�ζ���ʼ����score��������
        for(int i = 0; i  < Setting.BOARD_SIZE; i++){
            for(int j = 0; j < Setting.BOARD_SIZE; j++){
                score[i][j] = 0;
            }
        }

        //ÿ�λ�����Ѱ����λ�ã����ֶ�������һ�飨��Ȼ���˺ܶ����ģ���Ϊ�ϴ�����ʱ����Ĵ�඼û�䣩
        //�ȶ���һЩ����
        int humanChessmanNum = 0;//��Ԫ���еĺ�������
        int machineChessmanNum = 0;//��Ԫ���еİ�������
        int tupleScoreTmp = 0;//��Ԫ��÷���ʱ����
        int goalX = -1;//Ŀ��λ��x����
        int goalY = -1;//Ŀ��λ��y����
        int maxScore = -1;//������

        //1.ɨ������ȫ������
        for(int i = 0; i < Setting.BOARD_SIZE; i++){
            for(int j = 0; j < Setting.BOARD_SIZE-4; j++){
                int k = j;
                while(k < j + 5){

                    if(chessboard[i][k] == getChess()) machineChessmanNum++;
                    else if(chessboard[i][k] != 0)humanChessmanNum++;

                    k++;
                }
                tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                //Ϊ����Ԫ���ÿ��λ����ӷ���
                for(k = j; k < j + 5; k++){
                    score[i][k] += tupleScoreTmp;
                }
                //����
                humanChessmanNum = 0;//��Ԫ���еĺ�������
                machineChessmanNum = 0;//��Ԫ���еİ�������
                tupleScoreTmp = 0;//��Ԫ��÷���ʱ����
            }
        }

        //2.ɨ������15��
        for(int i = 0; i < Setting.BOARD_SIZE; i++){
            for(int j = 0; j < Setting.BOARD_SIZE-4; j++){
                int k = j;
                while(k < j + 5){
                    if(chessboard[k][i] == getChess()) machineChessmanNum++;
                    else if(chessboard[k][i] != 0)humanChessmanNum++;

                    k++;
                }
                tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                //Ϊ����Ԫ���ÿ��λ����ӷ���
                for(k = j; k < j + 5; k++){
                    score[k][i] += tupleScoreTmp;
                }
                //����
                humanChessmanNum = 0;//��Ԫ���еĺ�������
                machineChessmanNum = 0;//��Ԫ���еİ�������
                tupleScoreTmp = 0;//��Ԫ��÷���ʱ����
            }
        }

        //3.ɨ�����Ͻǵ����½��ϲಿ��
        for(int i = Setting.BOARD_SIZE-1; i >= 4; i--){
            for(int k = i, j = 0; j < Setting.BOARD_SIZE && k >= 0; j++, k--){
                int m = k;
                int n = j;
                while(m > k - 5 && k - 5 >= -1){
                    if(chessboard[m][n] == getChess()) machineChessmanNum++;
                    else if(chessboard[m][n] !=0 )humanChessmanNum++;

                    m--;
                    n++;
                }
                //ע��б���жϵ�ʱ�򣬿��ܹ�������Ԫ�飨�����ĸ����䣩�������������Ҫ���Ե�
                if(m == k-5){
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //Ϊ����Ԫ���ÿ��λ����ӷ���
                    for(m = k, n = j; m > k - 5 ; m--, n++){
                        score[m][n] += tupleScoreTmp;
                    }
                }

                //����
                humanChessmanNum = 0;//��Ԫ���еĺ�������
                machineChessmanNum = 0;//��Ԫ���еİ�������
                tupleScoreTmp = 0;//��Ԫ��÷���ʱ����

            }
        }

        //4.ɨ�����Ͻǵ����½��²ಿ��
        for(int i = 1; i < Setting.BOARD_SIZE; i++){
            for(int k = i, j = Setting.BOARD_SIZE-1; j >= 0 && k < Setting.BOARD_SIZE; j--, k++){
                int m = k;
                int n = j;
                while(m < k + 5 && k + 5 <= Setting.BOARD_SIZE){
                    if(chessboard[n][m] == getChess()) machineChessmanNum++;
                    else if(chessboard[n][m] != 0)humanChessmanNum++;

                    m++;
                    n--;
                }
                //ע��б���жϵ�ʱ�򣬿��ܹ�������Ԫ�飨�����ĸ����䣩�������������Ҫ���Ե�
                if(m == k+5){
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //Ϊ����Ԫ���ÿ��λ����ӷ���
                    for(m = k, n = j; m < k + 5; m++, n--){
                        score[n][m] += tupleScoreTmp;
                    }
                }
                //����
                humanChessmanNum = 0;//��Ԫ���еĺ�������
                machineChessmanNum = 0;//��Ԫ���еİ�������
                tupleScoreTmp = 0;//��Ԫ��÷���ʱ����

            }
        }

        //5.ɨ�����Ͻǵ����½��ϲಿ��
        for(int i = 0; i < Setting.BOARD_SIZE-4; i++){
            for(int k = i, j = 0; j < Setting.BOARD_SIZE && k < Setting.BOARD_SIZE; j++, k++){
                int m = k;
                int n = j;
                while(m < k + 5 && k + 5 <= Setting.BOARD_SIZE){
                    if(chessboard[m][n] == getChess()) machineChessmanNum++;
                    else if(chessboard[m][n] != 0)humanChessmanNum++;

                    m++;
                    n++;
                }
                //ע��б���жϵ�ʱ�򣬿��ܹ�������Ԫ�飨�����ĸ����䣩�������������Ҫ���Ե�
                if(m == k + 5){
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //Ϊ����Ԫ���ÿ��λ����ӷ���
                    for(m = k, n = j; m < k + 5; m++, n++){
                        score[m][n] += tupleScoreTmp;
                    }
                }

                //����
                humanChessmanNum = 0;//��Ԫ���еĺ�������
                machineChessmanNum = 0;//��Ԫ���еİ�������
                tupleScoreTmp = 0;//��Ԫ��÷���ʱ����

            }
        }

        //6.ɨ�����Ͻǵ����½��²ಿ��
        for(int i = 1; i < Setting.BOARD_SIZE-4; i++){
            for(int k = i, j = 0; j < Setting.BOARD_SIZE && k < Setting.BOARD_SIZE; j++, k++){
                int m = k;
                int n = j;
                while(m < k + 5 && k + 5 <= Setting.BOARD_SIZE){
                    if(chessboard[n][m] == getChess()) machineChessmanNum++;
                    else if(chessboard[n][m] != 0)humanChessmanNum++;

                    m++;
                    n++;
                }
                //ע��б���жϵ�ʱ�򣬿��ܹ�������Ԫ�飨�����ĸ����䣩�������������Ҫ���Ե�
                if(m == k + 5){
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //Ϊ����Ԫ���ÿ��λ����ӷ���
                    for(m = k, n = j; m < k + 5; m++, n++){
                        score[n][m] += tupleScoreTmp;
                    }
                }

                //����
                humanChessmanNum = 0;//��Ԫ���еĺ�������
                machineChessmanNum = 0;//��Ԫ���еİ�������
                tupleScoreTmp = 0;//��Ԫ��÷���ʱ����

            }
        }

        //�ӿ�λ��������ҵ��÷�����λ��
        {
            int maxNums = 0;//Ȩ����������
            //��ȡȨ������ֵ��������
            for (int i = 0; i < Setting.BOARD_SIZE; i++) {
                for (int j = 0; j < Setting.BOARD_SIZE; j++) {
                    if (chessboard[i][j] == 0) {
                        if (score[i][j] > maxScore) {
                            goalX = i;
                            goalY = j;
                            maxScore = score[i][j];
                            maxNums = 1;
                        } else if (score[i][j] == maxScore) {
                            maxNums++;
                        }
                    }
                }
            }
            //�����ȡһ��
            System.out.println("��������:"+maxNums);
            int maxN = random.nextInt(maxNums)+1;
            System.out.println("ѡ�񷽰�:"+maxNums);

            for (int i = 0; i < Setting.BOARD_SIZE && maxN>0; i++) {
                for (int j = 0; j < Setting.BOARD_SIZE&& maxN>0; j++) {
                    if (chessboard[i][j] == 0&&score[i][j] == maxScore) {
                        goalX = i;
                        goalY = j;
                        maxScore = score[i][j];
                        maxN--;
                    }
                }
            }
        }

        if(goalX != -1 && goalY != -1){
            return new Position( getChess(),goalX, goalY);
        }

        //û�ҵ�����˵��ƽ���ˣ����߲�����ƽ��
        return new Position(0, -1, -1);
    }

    //������Ԫ��������ֱ�
    public int tupleScore(int humanChessmanNum, int machineChessmanNum){
        //1.�����������ӣ����л������ӣ��з�Ϊ0
        if(humanChessmanNum > 0 && machineChessmanNum > 0){
            return 0;
        }
        //2.ȫ��Ϊ�գ�û�����ӣ��з�Ϊ7
        if(humanChessmanNum == 0 && machineChessmanNum == 0){
            return 7;
        }
        //3.������1�ӣ��з�Ϊ35
        if(machineChessmanNum == 1){
            return 35;
        }
        //4.������2�ӣ��з�Ϊ800
        if(machineChessmanNum == 2){
            return 800;
        }
        //5.������3�ӣ��з�Ϊ15000
        if(machineChessmanNum == 3){
            return 15000;
        }
        //6.������4�ӣ��з�Ϊ800000
        if(machineChessmanNum == 4){
            return 800000;
        }
        //7.������1�ӣ��з�Ϊ15
        if(humanChessmanNum == 1){
            return 15;
        }
        //8.������2�ӣ��з�Ϊ400
        if(humanChessmanNum == 2){
            return 400;
        }
        //9.������3�ӣ��з�Ϊ1800
        if(humanChessmanNum == 3){
            return 1800;
        }
        //10.������4�ӣ��з�Ϊ100000
        if(humanChessmanNum == 4){
            return 100000;
        }
        return -1;//������������϶������ˡ����д������������ִ��
    }

    @Override
    public void play(int x, int y) {
        Position p = searchPosition();
        board.setPiece(p);
    }
}
