package components;

import model.*;
import view.GameFrame;


import java.awt.*;

public class ChessGridComponent extends BasicComponent {
    public static int chessSize;
    public static int gridSize;
    public static Color gridColor = new Color(211, 211, 211);

    private ChessPiece chessPiece;
    private BLUE blue;
    private int row;
    private int col;

    public ChessGridComponent(int row, int col) {
        this.setSize(gridSize, gridSize);
        this.row = row;
        this.col = col;
    }


    public void setBlue(BLUE blue) {
        this.blue = blue;
    }

    @Override
    public void onMouseClicked() {
        //System.out.printf("%s clicked (%d, %d)\n", GameFrame.controller.getCurrentPlayer(), row, col);

        //todo: complete mouse click method
        if (GameFrame.controller.canClick(row, col)) {
            if (this.chessPiece == null) {
                cancelNext();
                this.chessPiece = GameFrame.controller.getCurrentPlayer();
                GameFrame.controller.flipChessPieces(row, col);
                Step step = new Step(row, col, chessPiece);
                GameFrame.controller.StepList.add(step);
                GameFrame.controller.swapPlayer();
                next();
                //System.out.println(GameFrame.controller.StepList);
            }
            repaint();
        }
    }

    public void regret(ChessPiece chessPiece) {
        cancelNext();
        GameFrame.controller.setCanAct(false);
        this.chessPiece = chessPiece;
        GameFrame.controller.flipChessPieces(row, col);
        GameFrame.controller.swapPlayer();
        //System.out.println(row + " " + col);
        GameFrame.controller.setCanAct(true);
        next();
        repaint();
    }

    public void next() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GameFrame.controller.getGamePanel().chessGrids[i][j].getChessPiece() == null) {
                    if (GameFrame.controller.getGamePanel().canClickGrid(i, j, GameFrame.controller.getCurrentPlayer())) {
                        GameFrame.controller.getGamePanel().chessGrids[i][j].setBlue(BLUE.BLUE);
                        GameFrame.controller.getGamePanel().chessGrids[i][j].repaint();
                    }
                }
            }
        }
    }

    public void cancelNext() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GameFrame.controller.getGamePanel().chessGrids[i][j].blue == BLUE.BLUE) {
                    GameFrame.controller.getGamePanel().chessGrids[i][j].setBlue(null);
                    GameFrame.controller.getGamePanel().chessGrids[i][j].repaint();
                }
            }
        }
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void drawBLUE(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1.0f));
        //g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        if (this.blue == BLUE.BLUE) {
            g.setColor(BLUE.BLUE.getBLUE());
            g.fillOval((int) (gridSize / 2.65), (int) (gridSize / 2.55), chessSize / 3, chessSize / 3);
        }
    }

    public void drawPiece(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.7f));
        g.setColor(gridColor);
        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        if (this.chessPiece != null) {
            g.setColor(chessPiece.getColor());
            g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        drawBLUE(g);
        drawPiece(g);
    }

    public void AIStep(ChessPiece chessPiece) {
        this.chessPiece = GameFrame.controller.getCurrentPlayer();
        GameFrame.controller.flipChessPieces(row, col);
        Step step = new Step(row, col, chessPiece);
        GameFrame.controller.StepList.add(step);
        //System.out.println(GameFrame.controller.StepList);
        repaint();
    }//3.0
}
