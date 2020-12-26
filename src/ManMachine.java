/**
 * ����ģʽ���˻���ս
 */
public class ManMachine implements Strategy {
    private Player player1,player2;

    public ManMachine(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void playGame(Position p) {
        player1.play(p.getX(),p.getY());
        try{
            Thread.sleep(200);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        player2.play(p.getX(),p.getY());
    }
}
