package strategy;
import player.Player;

/**
 * 策略模式，人机对战
 */
public class ManMachine implements Strategy {
    private final Player player1;
    private final Player player2;

    public ManMachine(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void init() {

    }

    @Override
    public void playGame(int x,int y) {
        if(player1.isPlay()) {
            player1.play(x, y);
        }
        try{
            Thread.sleep(200);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(player2.isPlay()) {
            player2.play(x, y);
        }
    }
}
