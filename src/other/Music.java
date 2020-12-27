package other;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URI;
import java.net.URL;

/**
 * 音乐类，生成音乐产品
 */
public class Music {
    private AudioClip backgroundMusic;

    public Music() {
        //音乐
        try{
            URI uri=Setting.backgroundMusic[0].toURI();
            URL url=uri.toURL();
            backgroundMusic= Applet.newAudioClip(url);
            backgroundMusic.play();
            backgroundMusic.loop();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void play(){
        backgroundMusic.play();
        backgroundMusic.loop();
    }
    public void changeMusic(){

    }
}
