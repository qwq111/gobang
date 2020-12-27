package command;

import config.Setting;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * 音乐类，生成音乐产品
 */
public class Music {
    private File file ;
    private AudioClip music;
    public Music() {
        file = Setting.backgroundMusic[0];
    }

    public void play(){
        //音乐
        try{
            URI uri= file.toURI();
            URL url=uri.toURL();
            music = Applet.newAudioClip(url);
            music.play();
            music.loop();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void changeMusic(){
        music.stop();
        int i = 0;
        while (file!=Setting.backgroundMusic[0]){
            i++;
        }
        file = Setting.backgroundMusic[(i+1)%Setting.backgroundMusic.length];
        play();
    }

}
