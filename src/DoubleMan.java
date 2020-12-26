/**
 * 策略模式 双人模式
 */
public class DoubleMan implements Strategy{
    private Player player1,player2;

    public DoubleMan(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void playGame(Position p) {
        if(p.getChess()==player2.getChess()){
            player2.play(p.getX(),p.getY());
        }else{
            player1.play(p.getX(),p.getY());
        }
    }
}
