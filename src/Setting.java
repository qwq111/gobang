import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;

public class Setting {
    public final static ImageIcon[] background=new ImageIcon[]
        {
            new ImageIcon("static/image/1.png"),
            new ImageIcon("static/image/2.png")
        };

    public final static ImageIcon white=new ImageIcon("static/image/white.png");
    public final static ImageIcon black=new ImageIcon("static/image/black.png");

    public final static File[] backgroundMusic=new File[]{
            new File("static/music/1.wav"),
    };
    public static final int BOARD_SIZE = 19;
    public static File chessMusic = new File("static/music/chess.wav");
}
