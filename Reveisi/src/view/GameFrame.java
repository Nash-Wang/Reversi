package view;

import components.Step;
import controller.GameController;
import controller.GameControllerAIr;
import controller.GameControllerAIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class GameFrame extends JFrame {
    public static GameController controller;
    protected ChessBoardPanel chessBoardPanel;
    protected StatusPanel statusPanel;

    public GameFrame(int frameSize) {//frameSize = 800

        this.setTitle("2021F CS102A Project Reversi");
        this.setLayout(null);

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\20694\\IdeaProjects\\untitled\\Project\\src\\233.jpg");
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, frameSize, frameSize);
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        JPanel j = (JPanel) this.getContentPane();
        j.setOpaque(false);

        //获取窗口边框的长度，将这些值加到主窗口大小上，这能使窗口大小和预期相符
        Insets inset = this.getInsets();
        this.setSize(frameSize + inset.left + inset.right, frameSize + inset.top + inset.bottom);

        this.setLocationRelativeTo(null);

        chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7));
        chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() - chessBoardPanel.getHeight()) / 3);
//80,
        statusPanel = new StatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1));
        statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);
        if (StartFrame.a == 0) {
            controller = new GameController(chessBoardPanel, statusPanel);
        } else if (StartFrame.a == 1) {
            controller = new GameControllerAIr(chessBoardPanel, statusPanel);
        } else if (StartFrame.a == 2) {
            controller = new GameControllerAIs(chessBoardPanel, statusPanel);
        }
        controller.setGamePanel(chessBoardPanel);

        this.add(chessBoardPanel);
        this.add(statusPanel);
        Font font = new Font("Script MT Bold", Font.PLAIN, 40);
        chessBoardPanel.getGridAt(0,0).next();

        JButton restartBtn = new JButton("Restart");
        restartBtn.setSize(120, 50);
        restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() + chessBoardPanel.getHeight()) / 2);
        restartBtn.setContentAreaFilled(false);
        restartBtn.setFont(font);
        restartBtn.setForeground(Color.white);
        add(restartBtn);
        restartBtn.addActionListener(e -> {
            chessBoardPanel.resetChessGrids();
            controller.resetStatusPanel();
            StatusPanel.playerLabel.setForeground(Color.BLACK);
            controller.StepList.clear();
            chessBoardPanel.getGridAt(0,0).cancelNext();
            chessBoardPanel.getGridAt(0,0).next();
        });
        //2.1new
        JButton CheatButton = new JButton("Cheat");
        CheatButton.setSize(120, 60);
        CheatButton.setLocation(restartBtn.getX() - 121, restartBtn.getY() - 400);
        CheatButton.setContentAreaFilled(false);
        CheatButton.setFont(new Font("Script MT Bold", Font.BOLD, 25));
        CheatButton.setForeground(Color.white);
        add(CheatButton);
        CheatButton.addActionListener(e -> {
            controller.openCheatMod();
        });
        //2.1
        JButton CancelButton = new JButton("Cancel");
        CancelButton.setSize(120, 60);
        CancelButton.setLocation(restartBtn.getX() - 121, restartBtn.getY() - 290);
        CancelButton.setContentAreaFilled(false);
        CancelButton.setFont(new Font("Script MT Bold", Font.BOLD, 25));
        CancelButton.setForeground(Color.white);
        add(CancelButton);
        CancelButton.addActionListener(e -> {
            controller.closeCheatMod();
        });

        JButton loadGameBtn = new JButton("LoadGame");
        loadGameBtn.setSize(120, 50);
        loadGameBtn.setLocation(restartBtn.getX() + restartBtn.getWidth() + 30, restartBtn.getY());
        loadGameBtn.setContentAreaFilled(false);
        loadGameBtn.setForeground(Color.white);
        loadGameBtn.setFont(new Font("Script MT Bold", Font.BOLD, 25));
        add(loadGameBtn);
        if (StartFrame.a == 0) {
            loadGameBtn.addActionListener(e -> {
                //System.out.println("clicked Load Btn");
                String filePath = JOptionPane.showInputDialog("input the path here");
                List<Step> steps = controller.readFileData(filePath);
                chessBoardPanel.resetChessGrids();
                chessBoardPanel.getGridAt(0,0).cancelNext();
                for (Step s : steps) {
                    if (!chessBoardPanel.canClickGrid(s.getRowIndex(), s.getColumnIndex(), s.getChessPiece())) {
                        JOptionPane.showMessageDialog(null, "ERROR 105" + "\n" + "This Step" + "(" + s.getRowIndex() + "," + s.getColumnIndex() + ")" + " is Invalid", "WARNING", JOptionPane.WARNING_MESSAGE);
                    }
                    chessBoardPanel.getGridAt(s.getRowIndex(), s.getColumnIndex()).regret(s.getChessPiece());
                }
                controller.countScore();
            });
        }else{
            loadGameBtn.addActionListener(e -> {
                //System.out.println("clicked Load Btn");
                String filePath = JOptionPane.showInputDialog("input the path here");
                List<Step> steps = controller.readFileData(filePath);
                chessBoardPanel.resetChessGrids();
                controller.setCanAct(false);
                for (Step s : steps) {
                    if (!chessBoardPanel.canClickGrid(s.getRowIndex(), s.getColumnIndex(), s.getChessPiece())) {
                        JOptionPane.showMessageDialog(null, "ERROR 105" + "\n" + "This Step" + "(" + s.getRowIndex() + "," + s.getColumnIndex() + ")" + " is Invalid", "WARNING", JOptionPane.WARNING_MESSAGE);
                    }
                    chessBoardPanel.getGridAt(s.getRowIndex(), s.getColumnIndex()).regret(s.getChessPiece());
                }
                controller.countScore();
                controller.setCanAct(true);
                chessBoardPanel.getGridAt(0,0).next();
            });
        }

        JButton saveGameBtn = new JButton("SaveGame");
        saveGameBtn.setSize(120, 50);
        saveGameBtn.setLocation(loadGameBtn.getX() + restartBtn.getWidth() + 30, restartBtn.getY());
        saveGameBtn.setContentAreaFilled(false);
        saveGameBtn.setFont(new Font("Script MT Bold", Font.BOLD, 25));
        saveGameBtn.setForeground(Color.white);
        add(saveGameBtn);
        saveGameBtn.addActionListener(e -> {
            //System.out.println("clicked Save Btn");
            String filePath = JOptionPane.showInputDialog(this, "input the path here");
            controller.writeDataToFile(filePath);
        });

        JButton regretButton = new JButton("Regret");
        regretButton.setSize(120, 60);
        regretButton.setLocation(restartBtn.getX() - 156, restartBtn.getY() - 550);
        regretButton.setContentAreaFilled(false);
        regretButton.setFont(new Font("Script MT Bold", Font.BOLD, 25));
        regretButton.setForeground(Color.white);
        add(regretButton);
        if (StartFrame.a == 0) {
            regretButton.addActionListener(e -> {
                if (controller.StepList.size() == 0) {
                    JOptionPane.showMessageDialog(null, "You Can't Regret More", "WARNING", JOptionPane.WARNING_MESSAGE);
                }
                controller.StepList.remove(controller.StepList.size() - 1);
               // System.out.println(controller.StepList);
                chessBoardPanel.resetChessGrids();
                controller.resetStatusPanel();
                chessBoardPanel.getGridAt(0,0).cancelNext();
                for (Step s : controller.StepList) {
                    chessBoardPanel.getGridAt(s.getRowIndex(), s.getColumnIndex()).regret(s.getChessPiece());
                    repaint();
                }
                chessBoardPanel.getGridAt(0,0).next();
            });
        } else {
            regretButton.addActionListener(e -> {
                if (controller.StepList.size() == 0) {
                    JOptionPane.showMessageDialog(null, "You Can't Regret More", "WARNING", JOptionPane.WARNING_MESSAGE);
                }
                controller.StepList.remove(controller.StepList.size() - 1);
                controller.StepList.remove(controller.StepList.size() - 1);
                controller.changePlayer();
                chessBoardPanel.resetChessGrids();
                controller.resetStatusPanel();
                chessBoardPanel.getGridAt(0,0).cancelNext();
                for (Step s : controller.StepList) {
                    chessBoardPanel.getGridAt(s.getRowIndex(), s.getColumnIndex()).regret(s.getChessPiece());
                    repaint();
                    //System.out.println(controller.StepList);
                }
                chessBoardPanel.getGridAt(0,0).next();
            });
        }

        JButton Exit = new JButton("MainMenu");
        Exit.setSize(200, 70);
        Exit.setLocation(600, 10);
        Exit.setContentAreaFilled(false);
        Exit.setForeground(Color.white);
        Exit.setFont(new Font("Script MT Bold", Font.BOLD, 33));
        add(Exit);
        Exit.addActionListener(e -> {
            this.setVisible(false);
            StartFrame startFrame = new StartFrame("Start");
            startFrame.setVisible(true);
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                int width = getWidth();
                int height = getHeight();
                restartBtn.setBounds((int) ((width - chessBoardPanel.getWidth()) / 2), (int) (height * 0.85), 156, 52);
                loadGameBtn.setBounds((restartBtn.getX() + 203), (int) (height * 0.85), 156, 52);
                saveGameBtn.setBounds((loadGameBtn.getX() + 202), (int) (height * 0.85), 156, 52);
                regretButton.setBounds(restartBtn.getX() - 120, (int) (height * 0.22), 120, 60);
                CheatButton.setBounds(restartBtn.getX() - 120, (int) (height * 0.35), 120, 60);
                CancelButton.setBounds(restartBtn.getX() - 120, (int) (height * 0.48), 120, 60);
                Exit.setBounds(saveGameBtn.getX() + 75, 10, 200, 70);
                chessBoardPanel.setLocation(((width - chessBoardPanel.getWidth()) / 2), height / 8);
                statusPanel.setLocation(((width - chessBoardPanel.getWidth()) / 2), 0);
                label.setLocation(((width - 800) / 2), 0);
                // chessBoardPanel.setGridSize(Math.min(width, height) / 8);
            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
