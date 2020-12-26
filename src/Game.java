/*
 * Created by JFormDesigner on Thu Dec 17 22:33:24 CST 2020
 */

import java.awt.event.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.*;

/**
 * @author whl
 */

public class Game extends JFrame implements Observer {
    private BackGround backGround = new BackGround(); //创建背景
    private PieceBoard board = new PieceBoard(Setting.BOARD_SIZE);// 棋盘
    private Judge judge = new Judge(board);//裁判
    private Player player,player1,player2; // 玩家创建
    private static int pattern = 2 ;//模式判断，0人人，1人机
    private int flag = 0;// 0 表示游戏开始，1表示游戏结束

    /**
     * 游戏初始化
     */
    public void init(){
        int x= new Random().nextInt(1)+1;
        int y=x==2?1:2;
        //逻辑层构建
        if(pattern==0){
            player1 = new Person("小王",board,x);
            player2 = new Person("小黑",board,y);
            label4.setText("双人模式");

        }else if(pattern==1){
            player1 = new Person("小王",board,x);
            player2 = new Machine("机器",board,y);
            label4.setText("单人模式");
        }else {
            player1 = new Machine("机器1",board,x);
            player2 = new Machine("机器2",board,y);
            label4.setText("机器队长");
        }
        player = player1;
        board.clear();
        repaint();
        flag = 0;
        if(pattern==2){
            while (flag!=1){
                System.out.println("人机下棋");
                player1.play(0,0);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                player2.play(0,0);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public Game() {
        getLayeredPane().add(backGround, new Integer(Integer.MIN_VALUE));
        ((JPanel)getContentPane()).setOpaque(false);

        initComponents();
        // 构建逻辑层
        pieceboard.setBoard(board);
        board.addObserver(judge);
        //设置游戏为裁判的观察者
        judge.addObserver(this);
        //设置面板为棋盘的观察者
        board.addObserver(pieceboard);


        //设置图标
        this.setIconImage(Setting.black.getImage());

        this.setSize(new Dimension(1000,900));
        this.setLocation(460,30);

        this.footer.setOpaque(false);
        this.setVisible(true);
        init();
    }



    // 裁判通知游戏已经结束
    @Override
    public void update(Observable o, Object arg) {
        //如果是裁判的动作
        if(o.getClass()== Judge.class) {
            Player player = null;
            if ((int) arg == player1.getChess())
                player = player1;
            else if((int) arg == player2.getChess())
                player = player2;
            flag = 1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if((int) arg !=0 ) {
                JOptionPane.showConfirmDialog(null, player + "获得了胜利", "游戏结束提示",
                        JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
            }else{
                JOptionPane.showConfirmDialog(null, "游戏结束，没有人获得胜利", "游戏结束提示",
                        JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
            }
        }

    }


    /**
     * 模式选择： 双人，自动重新开始
     * @param e
     */
    private void menuItem1MousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            //点了双人模式
            pattern=0;
            init();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        backGround.setBounds(0,0,getWidth(),getHeight());
    }

    
    /**
     * 切换背景
     * @param e
     */
    private void menuItem5MousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            //点了切换背景
            backGround.changeBackground();
        }
    }

    /***
     * 重新开始按钮监听
     * @param e
     */
    private void menuItem3MousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            init();
        }

    }

    /**
     * 悔棋
     * @param e
     */

    private void menuItem4MousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            System.out.println("悔棋");
            judge.remove();
            repaint();
        }
    }


    private void menuItem2MousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            //点了双人模式
            pattern=1;
            init();
        }
    }

    /**
     * 棋盘面板的监听
     * @param e
     */
    private void pieceboardMouseClicked(MouseEvent e) {
        // TODO add your code here
        //获取棋盘的起始坐标
        if(e.getButton() == MouseEvent.BUTTON1) {
            int boardX = pieceboard.getBoardX();
            int boardY = pieceboard.getBoardY();
            int size = pieceboard.getCellSize();
            int X=e.getX(),Y=e.getY();
            int x=(X-boardX+size/2)/size;
            int y=(Y-boardY+size/2)/size;
            System.out.println(x+","+y);
            if(x>=0&&x<=Setting.BOARD_SIZE&&y>=0&&y<=Setting.BOARD_SIZE){
                if(board.getChess(x,y)==0){
                    System.out.println("可以下棋");
                }
            }
        }

    }


    private void menuItem7MousePressed(MouseEvent e) {
        // TODO add your code here
        if(e.getButton()==MouseEvent.BUTTON1){
            //点了观战人机
            pattern=2;
            init();
        }
    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        manu = new JPanel();
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem7 = new JMenuItem();
        menu3 = new JMenu();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menu2 = new JMenu();
        menuItem5 = new JMenuItem();
        menuItem6 = new JMenuItem();
        top = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        label4 = new JLabel();
        label2 = new JLabel();
        label5 = new JLabel();
        label3 = new JLabel();
        label6 = new JLabel();
        pieceboard = new PieceBoardPanel();
        footer = new JPanel();
        label8 = new JLabel();

        //======== this ========
        setTitle("\u88c5\u6a21\u505a\u6837\u7684\u4e94\u5b50\u68cb");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== manu ========
        {
            manu.setLayout(new GridLayout(1, 3));

            //======== menuBar1 ========
            {

                //======== menu1 ========
                {
                    menu1.setText("\u6a21\u5f0f\u9009\u62e9");
                    menu1.setFont(menu1.getFont().deriveFont(menu1.getFont().getStyle() | Font.BOLD, menu1.getFont().getSize() + 1f));

                    //---- menuItem1 ----
                    menuItem1.setText("\u53cc\u4eba\u6a21\u5f0f");
                    menuItem1.setFont(menuItem1.getFont().deriveFont(menuItem1.getFont().getStyle() | Font.BOLD, menuItem1.getFont().getSize() + 1f));
                    menuItem1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuItem1MousePressed(e);
                        }
                    });
                    menu1.add(menuItem1);

                    //---- menuItem2 ----
                    menuItem2.setText("\u4eba\u673a\u6a21\u5f0f");
                    menuItem2.setFont(menuItem2.getFont().deriveFont(menuItem2.getFont().getStyle() | Font.BOLD, menuItem2.getFont().getSize() + 1f));
                    menuItem2.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuItem2MousePressed(e);
                        }
                    });
                    menu1.add(menuItem2);

                    //---- menuItem7 ----
                    menuItem7.setText("\u89c2\u6218\u4eba\u673a");
                    menuItem7.setFont(menuItem7.getFont().deriveFont(menuItem7.getFont().getStyle() | Font.BOLD, menuItem7.getFont().getSize() + 1f));
                    menuItem7.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuItem7MousePressed(e);
                        }
                    });
                    menu1.add(menuItem7);
                }
                menuBar1.add(menu1);

                //======== menu3 ========
                {
                    menu3.setText("\u6e38\u620f\u529f\u80fd");
                    menu3.setFont(menu3.getFont().deriveFont(menu3.getFont().getStyle() | Font.BOLD, menu3.getFont().getSize() + 1f));

                    //---- menuItem3 ----
                    menuItem3.setText("\u91cd\u65b0\u5f00\u59cb");
                    menuItem3.setFont(menuItem3.getFont().deriveFont(menuItem3.getFont().getStyle() | Font.BOLD, menuItem3.getFont().getSize() + 1f));
                    menuItem3.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuItem3MousePressed(e);
                        }
                    });
                    menu3.add(menuItem3);

                    //---- menuItem4 ----
                    menuItem4.setText("\u6094\u68cb");
                    menuItem4.setFont(menuItem4.getFont().deriveFont(menuItem4.getFont().getStyle() | Font.BOLD, menuItem4.getFont().getSize() + 1f));
                    menuItem4.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuItem4MousePressed(e);
                        }
                    });
                    menu3.add(menuItem4);
                }
                menuBar1.add(menu3);

                //======== menu2 ========
                {
                    menu2.setText("\u5207\u6362\u4e3b\u9898");
                    menu2.setFont(menu2.getFont().deriveFont(menu2.getFont().getStyle() | Font.BOLD, menu2.getFont().getSize() + 1f));

                    //---- menuItem5 ----
                    menuItem5.setText("\u5207\u6362\u80cc\u666f");
                    menuItem5.setFont(menuItem5.getFont().deriveFont(menuItem5.getFont().getStyle() | Font.BOLD, menuItem5.getFont().getSize() + 1f));
                    menuItem5.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            menuItem5MousePressed(e);
                        }
                    });
                    menu2.add(menuItem5);

                    //---- menuItem6 ----
                    menuItem6.setText("\u5207\u6362\u97f3\u4e50");
                    menuItem6.setFont(menuItem6.getFont().deriveFont(menuItem6.getFont().getStyle() | Font.BOLD, menuItem6.getFont().getSize() + 1f));
                    menu2.add(menuItem6);
                }
                menuBar1.add(menu2);
            }
            manu.add(menuBar1);

            //======== top ========
            {
                top.setLayout(new FlowLayout());
            }
            manu.add(top);

            //======== panel1 ========
            {
                panel1.setLayout(new FlowLayout());

                //---- label1 ----
                label1.setText("\u5f53\u524d\u6a21\u5f0f");
                label1.setForeground(new Color(255, 51, 102));
                panel1.add(label1);
                panel1.add(label4);

                //---- label2 ----
                label2.setText("\u6267\u68cb\u65b9");
                label2.setForeground(new Color(255, 51, 102));
                panel1.add(label2);
                panel1.add(label5);

                //---- label3 ----
                label3.setText("\u7528\u65f6");
                label3.setForeground(new Color(255, 51, 102));
                panel1.add(label3);
                panel1.add(label6);
            }
            manu.add(panel1);
        }
        contentPane.add(manu, BorderLayout.NORTH);

        //---- pieceboard ----
        pieceboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pieceboardMouseClicked(e);
            }
        });
        contentPane.add(pieceboard, BorderLayout.CENTER);

        //======== footer ========
        {
            footer.setLayout(new FlowLayout());

            //---- label8 ----
            label8.setText("\u7248\u6743\u6240\u6709 -\u5565\u90fd\u505a\u4e0d\u51fa\u6765\u5c0f\u961f");
            label8.setFont(label8.getFont().deriveFont(Font.BOLD|Font.ITALIC, label8.getFont().getSize() + 3f));
            footer.add(label8);
        }
        contentPane.add(footer, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel manu;
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem7;
    private JMenu menu3;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private JMenu menu2;
    private JMenuItem menuItem5;
    private JMenuItem menuItem6;
    private JPanel top;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label4;
    private JLabel label2;
    private JLabel label5;
    private JLabel label3;
    private JLabel label6;
    private PieceBoardPanel pieceboard;
    private JPanel footer;
    private JLabel label8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables



    /**
     * 背景类
     * 用于切换背景，切换主题
     */
    class BackGround extends JPanel {
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
}
