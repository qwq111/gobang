/**
 * 策略模式：具体策略，机器对弈
 */
public class MachineMachine implements Strategy {
    private Player player1,player2;

    public MachineMachine(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void playGame(Position p) {
        boolean flag = true;
        while(PieceBoard.flag==0){//游戏未结束
            //人机对弈
            if(flag) {
                player1.play(0, 0);
            }else{
                player2.play(0,0);
            }
            flag=!flag;
            try{
                Thread.sleep(500);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }
}
