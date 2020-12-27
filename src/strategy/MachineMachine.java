package strategy;

import player.Player;

/**
 * 策略模式：具体策略，机器对弈
 */
public class MachineMachine implements Strategy {
    private final Player player1;
    private final Player player2;

    public MachineMachine(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void playGame(int x,int y) {
        boolean flag = true;
        Player player = player1;
        while(player.isPlay()){//游戏未结束
            player.play(0, 0);
            //人机对弈
            if(flag) {
                player= player2;
            }else{
                player = player1;
            }

            for(int i=0;i<100010;i++){
                if(i==100009){
                    System.out.println("等待完成");
                }
            }
            flag=!flag;
        }
    }

    @Override
    public void init() {

    }

}
