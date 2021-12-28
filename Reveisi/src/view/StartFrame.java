package view;

import view.GameFrame;

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {
    public static int a = 0;

    public StartFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLocation(380,25);
        this.setSize(800, 800);
        this.setLayout(null);

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\20694\\IdeaProjects\\untitled\\Project\\src\\背景.jpg");
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, 800, 800);
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        JPanel j = (JPanel) this.getContentPane();
        j.setOpaque(false);


        JLabel label1 = new JLabel();
        label1.setText("Othello");
        label1.setFont(new Font("Matura MT Script Capitals", Font.BOLD, 150));
        label1.setSize(800, 300);
        label1.setOpaque(false);
        label1.setForeground(new Color(230 ,230 ,250));
        label1.setLocation(110, 100);
        this.add(label1);

        JButton PVPButton = new JButton("PVP");
        PVPButton.setSize(200, 70);
        PVPButton.setLocation(300, 520);
        PVPButton.setContentAreaFilled(false);
        PVPButton.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
        PVPButton.setForeground(Color.white);
        this.add(PVPButton);
        PVPButton.addActionListener(e -> {
            a = 0;
            GameFrame mainFrame = new GameFrame(800);
            this.setVisible(false);
            mainFrame.setVisible(true);

        });

        JButton PVEButton1 = new JButton("PVE EASY");
        PVEButton1.setSize(200, 70);
        PVEButton1.setLocation(300, 600);
        PVEButton1.setContentAreaFilled(false);
        PVEButton1.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
        PVEButton1.setForeground(Color.white);
        this.add(PVEButton1);
        PVEButton1.addActionListener(e -> {
            a = 1;
            GameFrame mainFrame = new GameFrame(800);
            this.setVisible(false);
            mainFrame.setVisible(true);
        });

        JButton PVEButton2 = new JButton("PVE HARD");
        PVEButton2.setSize(200, 70);
        PVEButton2.setLocation(300, 680);
        PVEButton2.setContentAreaFilled(false);
        PVEButton2.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
        PVEButton2.setForeground(Color.white);
        this.add(PVEButton2);
        PVEButton2.addActionListener(e -> {
            a = 2;
            GameFrame mainFrame = new GameFrame(800);
            this.setVisible(false);
            mainFrame.setVisible(true);
        });

        JButton ExitButton = new JButton("EXIT");
        ExitButton.setSize(150,70);
        ExitButton.setLocation(650,680);
        ExitButton.setContentAreaFilled(false);
        ExitButton.setFont(new Font("Segoe UI Black", Font.BOLD, 25));
        ExitButton.setForeground(Color.white);
        this.add(ExitButton);
        ExitButton.addActionListener(e -> {
            System.exit(0);
        });
    }
}
