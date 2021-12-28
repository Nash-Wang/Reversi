package controller;

import components.ChessGridComponent;
import model.ChessPiece;
import view.ChessBoardPanel;
import view.StatusPanel;

import java.util.*;

public class GameControllerAIs extends GameController {
    HashMap<ChessGridComponent, Integer> effectMap = new HashMap<>();
    ArrayList<Integer> effectList = new ArrayList<>();
    ChessPiece AIPlayer = ChessPiece.WHITE;
    boolean AIFirst = false;

    public GameControllerAIs(ChessBoardPanel gamePanel, StatusPanel statusPanel) {
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
            if (currentPlayer == AIPlayer && CanAct && AvailableGrids().size() != 0) {
                for (ChessGridComponent grid : AvailableGrids()) {
                    effectMap.putIfAbsent(grid, gamePanel.all(grid.getRow(), grid.getCol(), AIPlayer).size());
                }
                Set<Map.Entry<ChessGridComponent, Integer>> entrySet = effectMap.entrySet();
                for (Map.Entry<ChessGridComponent, Integer> entry : entrySet) {
                    effectList.add(entry.getValue());
                }
                effectList.sort(Comparator.naturalOrder());
                for (ChessGridComponent key : effectMap.keySet()) {
                    if (effectList.get(effectList.size() - 1).equals(effectMap.get(key))) {
                        key.AIStep(AIPlayer);
                        System.out.println("AI step on (" + key.getRow() + "," + key.getCol() + ")");
                        break;
                    }
                }
                effectList.clear();
                effectMap.clear();
                super.swapPlayer();
            }
        } else super.swapPlayer();
    }
}