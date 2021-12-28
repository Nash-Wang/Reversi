package controller;
//3.0new

import model.ChessPiece;
import view.ChessBoardPanel;
import view.StatusPanel;

import java.awt.*;
import java.util.Timer;

import static java.lang.Math.random;

public class GameControllerAIr extends GameController {
    int randomNo = 0;
    boolean AIFirst = false;
    ChessPiece AIPlayer = ChessPiece.WHITE;

    public GameControllerAIr(ChessBoardPanel gamePanel, StatusPanel statusPanel) {
        super(gamePanel, statusPanel);
        if (AIFirst) {
            this.currentPlayer = ChessPiece.WHITE;
            AIPlayer = ChessPiece.BLACK;
            blackScore = 4;
            whiteScore = 1;
        }
    }

    @Override
    public void swapPlayer() {
        if (CanAct) {
            super.swapPlayer();
            if (currentPlayer == AIPlayer && AvailableGrids().size() != 0) {
                randomNo = (int) (random() * (AvailableGrids().size()));
                AvailableGrids().get(randomNo).AIStep(AIPlayer);
            }
            super.swapPlayer();
        } else super.swapPlayer();
    }
}

