package command;

import config.Setting;

import javax.swing.*;
import java.awt.*;

/**
 * 背景类
 * 用于切换背景，切换主题
 */
public class BackGround extends JPanel {
    private ImageIcon imageIcon;
    public BackGround() {
        imageIcon = Setting.background[0];
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imageIcon.getImage(),0,0,getWidth(),getHeight(),null);//图片自适应
        //设置透明色的方法
        g.setColor(new Color(255, 255, 255, 100));
        g.fillRect(0,0,getWidth(),getHeight());
    }

    public void changeBackground() {
        int size = 0;
        while (imageIcon!=Setting.background[size]){
            size++;
        }
        imageIcon = Setting.background[(size+1)%Setting.background.length];
        repaint();
    }
}