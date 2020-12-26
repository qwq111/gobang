import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URI;
import java.net.URL;

/**
 * ������.״̬ģʽ
 * �洢ѡ����Ϣ
 * �������壬�������
 */
public abstract class Player {
    protected String name;//����
    protected Position piece;
    protected PieceBoard board;
    protected static AudioClip music; //���������

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

        //����
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
        String[] s=new String[]{"��","��"};
        return "����: "+getName()+"ִ"+s[getChess()-1];
    }
}
