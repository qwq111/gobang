package strategy;

import memento.Position;
import player.Player;

/**
 * ����ģʽ��������ԣ���������
 */
public class MachineMachine implements Strategy {
    private Player player1,player2;

    public MachineMachine(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void playGame(int x,int y) {
        boolean flag = true;
        Player player = player1;
        while(player.isPlay()){//��Ϸδ����
            player.play(0, 0);
            //�˻�����
            if(flag) {
                player= player2;
            }else{
                player = player1;
            }
            flag=!flag;
            try{
                Thread.sleep(500);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }

    @Override
    public void init() {

    }

}
