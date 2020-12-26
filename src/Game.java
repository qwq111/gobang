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
    private BackGround backGround = new BackGround(); //��������
    private PieceBoard board = new PieceBoard(Setting.BOARD_SIZE);// ����
    private Judge judge = new Judge(board);//����
    private Player player,player1,player2; // ��Ҵ���
    private static int pattern = 2 ;//ģʽ�жϣ�0���ˣ�1�˻�
    private int flag = 0;// 0 ��ʾ��Ϸ��ʼ��1��ʾ��Ϸ����

    /**
     * ��Ϸ��ʼ��
     */
    public void init(){
        int x= new Random().nextInt(1)+1;
        int y=x==2?1:2;
        //�߼��㹹��
        if(pattern==0){
            player1 = new Person("С��",board,x);
            player2 = new Person("С��",board,y);
            label4.setText("˫��ģʽ");

        }else if(pattern==1){
            player1 = new Person("С��",board,x);
            player2 = new Machine("����",board,y);
            label4.setText("����ģʽ");
        }else {
            player1 = new Machine("����1",board,x);
            player2 = new Machine("����2",board,y);
            label4.setText("�����ӳ�");
        }
        player = player1;
        board.clear();
        repaint();
        flag = 0;
        if(pattern==2){
            while (flag!=1){
                System.out.println("�˻�����");
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
        // �����߼���
        pieceboard.setBoard(board);
        board.addObserver(judge);
        //������ϷΪ���еĹ۲���
        judge.addObserver(this);
        //�������Ϊ���̵Ĺ۲���
        board.addObserver(pieceboard);


        //����ͼ��
        this.setIconImage(Setting.black.getImage());

        this.setSize(new Dimension(1000,900));
        this.setLocation(460,30);

        this.footer.setOpaque(false);
        this.setVisible(true);
        init();
    }



    // ����֪ͨ��Ϸ�Ѿ�����
    @Override
    public void update(Observable o, Object arg) {
        //����ǲ��еĶ���
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
                JOptionPane.showConfirmDialog(null, player + "�����ʤ��", "��Ϸ������ʾ",
                        JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
            }else{
                JOptionPane.showConfirmDialog(null, "��Ϸ������û���˻��ʤ��", "��Ϸ������ʾ",
                        JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
            }
        }

    }


    /**
     * ģʽѡ�� ˫�ˣ��Զ����¿�ʼ
     * @param e
     */
    private void menuItem1MousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            //����˫��ģʽ
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
     * �л�����
     * @param e
     */
    private void menuItem5MousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            //�����л�����
            backGround.changeBackground();
        }
    }

    /***
     * ���¿�ʼ��ť����
     * @param e
     */
    private void menuItem3MousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            init();
        }

    }

    /**
     * ����
     * @param e
     */

    private void menuItem4MousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            System.out.println("����");
            judge.remove();
            repaint();
        }
    }


    private void menuItem2MousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            //����˫��ģʽ
            pattern=1;
            init();
        }
    }

    /**
     * �������ļ���
     * @param e
     */
    private void pieceboardMouseClicked(MouseEvent e) {
        // TODO add your code here
        //��ȡ���̵���ʼ����
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
                    System.out.println("��������");
                }
            }
        }

    }


    private void menuItem7MousePressed(MouseEvent e) {
        // TODO add your code here
        if(e.getButton()==MouseEvent.BUTTON1){
            //���˹�ս�˻�
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
     * ������
     * �����л��������л�����
     */
    class BackGround extends JPanel {
        private ImageIcon imageIcon;
        public BackGround() {
            imageIcon = Setting.background[0];
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(imageIcon.getImage(),0,0,getWidth(),getHeight(),null);//ͼƬ����Ӧ
            //����͸��ɫ�ķ���
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
