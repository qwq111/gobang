import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URI;
import java.net.URL;

/**
 * 棋手类.状态模式
 * 存储选手信息
 * 负责下棋，提出悔棋
 */
public abstract class Player {
    protected String name;//姓名
    protected Position piece;
    protected PieceBoard board;
    protected static AudioClip music; //下棋的音乐

    public String getName() {
        return name;
    }

    public int getChess() {
        return piece.getChess();
    }

    public Player(String name, PieceBoard board,int chess) {
        this.name = name;
        this.board = board;
        piece=new Position(chess,-1,-1);

        //音乐
        try{
            URI uri=Setting.chessMusic.toURI();
            URL url=uri.toURL();
            music = Applet.newAudioClip(url);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void play(int x, int y){
        music.play();
    }

    @Override
    public String toString() {
        String[] s=new String[]{"黑","白"};
        return "棋手: "+getName()+"执"+s[getChess()-1];
    }
}
