package view;

import model.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    public static JLabel playerLabel;
    private JLabel scoreLabel;

    public StatusPanel(int width, int height) {
        this.setSize(width, height);
        this.setLayout(null);
        this.setVisible(true);

        playerLabel = new JLabel();
        playerLabel.setLocation(0, 10);
        playerLabel.setSize((int) (width * 0.6), height);
        playerLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        this.setPlayerText(ChessPiece.BLACK.name());
        this.setOpaque(false);
        add(playerLabel);

        this.scoreLabel = new JLabel();
        this.scoreLabel.setLocation((int) (width * 0.35), 10);
        this.scoreLabel.setSize((int) (width * 0.7), height);
        this.scoreLabel.setFont(new Font("Calibri", Font.ITALIC, 30));
        scoreLabel.setForeground(new Color(	255 ,182 ,193,200));
        this.setScoreText(2, 2);
        add(scoreLabel);

    }

    public void setScoreText(int black, int white) {
        this.scoreLabel.setText(String.format("BLACK: %d\tWHITE: %d", black, white));
    }

    public void setPlayerText(String playerText) {
        playerLabel.setText(playerText + "'s Turn");
    }
}
