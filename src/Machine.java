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
        //每次都初始化下score评分数组
        for(int i = 0; i  < Setting.BOARD_SIZE; i++){
            for(int j = 0; j < Setting.BOARD_SIZE; j++){
                score[i][j] = 0;
            }
        }

        //每次机器找寻落子位置，评分都重新算一遍（虽然算了很多多余的，因为上次落子时候算的大多都没变）
        //先定义一些变量
        int humanChessmanNum = 0;//五元组中的黑棋数量
        int machineChessmanNum = 0;//五元组中的白棋数量
        int tupleScoreTmp = 0;//五元组得分临时变量
        int goalX = -1;//目标位置x坐标
        int goalY = -1;//目标位置y坐标
        int maxScore = -1;//最大分数

        //1.扫描横向的全部个行
        for(int i = 0; i < Setting.BOARD_SIZE; i++){
            for(int j = 0; j < Setting.BOARD_SIZE-4; j++){
                int k = j;
                while(k < j + 5){

                    if(chessboard[i][k] == getChess()) machineChessmanNum++;
                    else if(chessboard[i][k] != 0)humanChessmanNum++;

                    k++;
                }
                tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                //为该五元组的每个位置添加分数
                for(k = j; k < j + 5; k++){
                    score[i][k] += tupleScoreTmp;
                }
                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
            }
        }

        //2.扫描纵向15行
        for(int i = 0; i < Setting.BOARD_SIZE; i++){
            for(int j = 0; j < Setting.BOARD_SIZE-4; j++){
                int k = j;
                while(k < j + 5){
                    if(chessboard[k][i] == getChess()) machineChessmanNum++;
                    else if(chessboard[k][i] != 0)humanChessmanNum++;

                    k++;
                }
                tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                //为该五元组的每个位置添加分数
                for(k = j; k < j + 5; k++){
                    score[k][i] += tupleScoreTmp;
                }
                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
            }
        }

        //3.扫描右上角到左下角上侧部分
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
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if(m == k-5){
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for(m = k, n = j; m > k - 5 ; m--, n++){
                        score[m][n] += tupleScoreTmp;
                    }
                }

                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        //4.扫描右上角到左下角下侧部分
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
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if(m == k+5){
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for(m = k, n = j; m < k + 5; m++, n--){
                        score[n][m] += tupleScoreTmp;
                    }
                }
                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        //5.扫描左上角到右下角上侧部分
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
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if(m == k + 5){
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for(m = k, n = j; m < k + 5; m++, n++){
                        score[m][n] += tupleScoreTmp;
                    }
                }

                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        //6.扫描左上角到右下角下侧部分
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
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if(m == k + 5){
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for(m = k, n = j; m < k + 5; m++, n++){
                        score[n][m] += tupleScoreTmp;
                    }
                }

                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        //从空位置中随机找到得分最大的位置
        {
            int maxNums = 0;//权重最大的数量
            //获取权重最大的值，和数量
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
            //随机获取一个
            System.out.println("方案个数:"+maxNums);
            int maxN = random.nextInt(maxNums)+1;
            System.out.println("选择方案:"+maxNums);

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

        //没找到坐标说明平局了，笔者不处理平局
        return new Position(0, -1, -1);
    }

    //各种五元组情况评分表
    public int tupleScore(int humanChessmanNum, int machineChessmanNum){
        //1.既有人类落子，又有机器落子，判分为0
        if(humanChessmanNum > 0 && machineChessmanNum > 0){
            return 0;
        }
        //2.全部为空，没有落子，判分为7
        if(humanChessmanNum == 0 && machineChessmanNum == 0){
            return 7;
        }
        //3.机器落1子，判分为35
        if(machineChessmanNum == 1){
            return 35;
        }
        //4.机器落2子，判分为800
        if(machineChessmanNum == 2){
            return 800;
        }
        //5.机器落3子，判分为15000
        if(machineChessmanNum == 3){
            return 15000;
        }
        //6.机器落4子，判分为800000
        if(machineChessmanNum == 4){
            return 800000;
        }
        //7.人类落1子，判分为15
        if(humanChessmanNum == 1){
            return 15;
        }
        //8.人类落2子，判分为400
        if(humanChessmanNum == 2){
            return 400;
        }
        //9.人类落3子，判分为1800
        if(humanChessmanNum == 3){
            return 1800;
        }
        //10.人类落4子，判分为100000
        if(humanChessmanNum == 4){
            return 100000;
        }
        return -1;//若是其他结果肯定出错了。这行代码根本不可能执行
    }

    @Override
    public void play(int x, int y) {
        Position p = searchPosition();
        board.setPiece(p);
    }
}
