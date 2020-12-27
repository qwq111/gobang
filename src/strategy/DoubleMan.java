package strategy;

import player.Player;

/**
 * 策略模式 双人模式
 */
public class DoubleMan implements Strategy{
    private boolean flag = true;//true棋手1下棋，false，棋手2下棋
    private Player player1,player2;

    public DoubleMan(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void playGame(int x,int y) {
        if(flag){
            player1.play(x,y);
        }else{
            player2.play(x,y);
        }
        flag = !flag;
    }

    @Override
    public void init() {
        flag = true;
    }
}
