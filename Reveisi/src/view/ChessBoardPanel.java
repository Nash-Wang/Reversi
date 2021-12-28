package view;

import components.ChessGridComponent;
import model.ChessPiece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class ChessBoardPanel extends JPanel {
    private final int CHESS_COUNT = 8;
    public ChessGridComponent[][] chessGrids;


    public ChessGridComponent[][] getChessGrids() {
        return chessGrids;
    }

    public ChessGridComponent getGridAt(int row, int col) {
        return chessGrids[row][col];
    }

    public ChessBoardPanel(int width, int height) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setOpaque(false);
        int length = Math.min(width, height);
        this.setSize(length, length);
        ChessGridComponent.gridSize = length / CHESS_COUNT;
        ChessGridComponent.chessSize = (int) (ChessGridComponent.gridSize * 0.8);
        //System.out.printf("width = %d height = %d gridSize = %d chessSize = %d\n", width, height, ChessGridComponent.gridSize, ChessGridComponent.chessSize);

        initialChessGrids();//return empty chessboard
        initialGame();//add initial four chess
        repaint();
    }

    /**
     * set an empty chessboard
     */
    public int w8(int row, int col, ChessPiece currentPlayer) {
        int count = 0;
        int current = row - 1;
        if (row == 0) return count;
        else {
            while (this.chessGrids[current][col].getChessPiece() != null && Objects.equals(currentPlayer.getopColer(), this.chessGrids[current][col].getChessPiece().getColor())) {
                count++;
                current--;
                if (current == -1 || current == 8) {
                    current++;
                    break;
                }
            }
            if (this.chessGrids[current][col].getChessPiece() == null || !Objects.equals(currentPlayer.getColor(), this.chessGrids[current][col].getChessPiece().getColor()))
                count = 0;
        }
        return count;
    }

    public int d6(int row, int col, ChessPiece currentPlayer) {
        int count = 0;
        int current = col + 1;
        if (col == 7) return count;
        else {
            while (this.chessGrids[row][current].getChessPiece() != null && Objects.equals(this.chessGrids[row][current].getChessPiece().getColor(), currentPlayer.getopColer())) {
                count++;
                current++;
                if (current == 8) {
                    current--;
                    break;
                }
            }
            if (this.chessGrids[row][current].getChessPiece() == null || !Objects.equals(this.chessGrids[row][current].getChessPiece().getColor(), currentPlayer.getColor()))
                count = 0;
        }
        return count;
    }

    public int x2(int row, int col, ChessPiece currentPlayer) {
        int count = 0;
        int current = row + 1;
        if (row == 7) return count;
        else {
            while (this.chessGrids[current][col].getChessPiece() != null && Objects.equals(this.chessGrids[current][col].getChessPiece().getColor(), currentPlayer.getopColer())) {
                count++;
                current++;
                if (current == 8) {
                    current--;
                    break;
                }
            }
            if (this.chessGrids[current][col].getChessPiece() == null || !Objects.equals(this.chessGrids[current][col].getChessPiece().getColor(), currentPlayer.getColor()))
                count = 0;
        }
        return count;
    }

    public int a4(int row, int col, ChessPiece currentPlayer) {
        int count = 0;
        int current = col - 1;
        if (col == 0) return count;
        else {
            while (this.chessGrids[row][current].getChessPiece() != null && Objects.equals(this.chessGrids[row][current].getChessPiece().getColor(), currentPlayer.getopColer())) {
                count++;
                current = current - 1;
                if (current == -1 || current == 8) {
                    current++;
                    break;
                }
            }
            if (this.chessGrids[row][current].getChessPiece() == null || !Objects.equals(this.chessGrids[row][current].getChessPiece().getColor(), currentPlayer.getColor()))
                count = 0;
        }
        return count;
    }

    public int e9(int row, int col, ChessPiece currentPlayer) {
        int count = 0;
        int currentx = row - 1;
        int currenty = col + 1;
        if (row == 0 || col == 7) return count;
        else {
            while (this.chessGrids[currentx][currenty].getChessPiece() != null && Objects.equals(this.chessGrids[currentx][currenty].getChessPiece().getColor(), currentPlayer.getopColer())) {
                count++;
                currentx = currentx - 1;
                currenty = currenty + 1;
                if (currentx == -1 || currentx == 8 || currenty == 8) {
                    currentx++;
                    currenty--;
                    break;
                }
            }
            if (this.chessGrids[currentx][currenty].getChessPiece() == null || !Objects.equals(this.chessGrids[currentx][currenty].getChessPiece().getColor(), currentPlayer.getColor()))
                count = 0;
        }
        return count;
    }

    public int c3(int row, int col, ChessPiece currentPlayer) {
        int count = 0;
        int currentx = row + 1;
        int currenty = col + 1;
        if (row == 7 || col == 7) return count;
        else {
            while (this.chessGrids[currentx][currenty].getChessPiece() != null && Objects.equals(this.chessGrids[currentx][currenty].getChessPiece().getColor(), currentPlayer.getopColer())) {
                count++;
                currentx = currentx + 1;
                currenty = currenty + 1;
                if (currentx == 8 || currenty == 8) {
                    currentx--;
                    currenty--;
                    break;
                }
            }
            if (this.chessGrids[currentx][currenty].getChessPiece() == null || !Objects.equals(this.chessGrids[currentx][currenty].getChessPiece().getColor(), currentPlayer.getColor()))
                count = 0;
        }
        return count;
    }

    public int z1(int row, int col, ChessPiece currentPlayer) {
        int count = 0;
        int currentx = row + 1;
        int currenty = col - 1;
        if (row == 7 || col == 0) return count;
        else {
            while (this.chessGrids[currentx][currenty].getChessPiece() != null && Objects.equals(this.chessGrids[currentx][currenty].getChessPiece().getColor(), currentPlayer.getopColer())) {
                count++;
                currentx = currentx + 1;
                currenty = currenty - 1;
                if (currenty == -1 || currentx == 8 || currenty == 8) {
                    currentx--;
                    currenty++;
                    break;
                }
            }
            if (this.chessGrids[currentx][currenty].getChessPiece() == null || !Objects.equals(this.chessGrids[currentx][currenty].getChessPiece().getColor(), currentPlayer.getColor()))
                count = 0;
        }
        return count;
    }

    public int q7(int row, int col, ChessPiece currentPlayer) {
        int count = 0;
        int currentx = row - 1;
        int currenty = col - 1;
        if (row == 0 || col == 0) return count;
        else {
            while (this.chessGrids[currentx][currenty].getChessPiece() != null && Objects.equals(this.chessGrids[currentx][currenty].getChessPiece().getColor(), currentPlayer.getopColer())) {
                count++;
                currentx = currentx - 1;
                currenty = currenty - 1;
                if (currentx == -1 || currenty == -1 || currentx == 8 || currenty == 8) {
                    currentx++;
                    currenty++;
                    break;
                }
            }
            if (this.chessGrids[currentx][currenty].getChessPiece() == null || !Objects.equals(this.chessGrids[currentx][currenty].getChessPiece().getColor(), currentPlayer.getColor()))
                count = 0;
        }
        return count;
    }

    public ArrayList<ChessGridComponent> w8ex(int row, int col, ChessPiece currentPlayer) {
        ArrayList<ChessGridComponent> w8gird = new ArrayList<>();
        int current = row - 1;
        if (row == 0) return w8gird;
        else {
            while (this.chessGrids[current][col].getChessPiece() != null && Objects.equals(currentPlayer.getopColer(), this.chessGrids[current][col].getChessPiece().getColor())) {
                w8gird.add(chessGrids[current][col]);
                current--;
                if (current == -1 || current == 8) {
                    current++;
                    break;
                }
            }
            if (this.chessGrids[current][col].getChessPiece() == null || !Objects.equals(currentPlayer.getColor(), this.chessGrids[current][col].getChessPiece().getColor())) {
                w8gird.clear();
            }
        }
        return w8gird;
    }

    public ArrayList<ChessGridComponent> d6ex(int row, int col, ChessPiece currentPlayer) {
        int current = col + 1;
        ArrayList<ChessGridComponent> d6grids = new ArrayList<>();
        if (col == 7) return d6grids;
        else {
            while (this.chessGrids[row][current].getChessPiece() != null && Objects.equals(this.chessGrids[row][current].getChessPiece().getColor(), currentPlayer.getopColer())) {
                d6grids.add(chessGrids[row][current]);
                current++;
                if (current == 8) {
                    current--;
                    break;
                }
            }
            if (this.chessGrids[row][current].getChessPiece() == null || !Objects.equals(this.chessGrids[row][current].getChessPiece().getColor(), currentPlayer.getColor())) {
                d6grids.clear();
            }
        }
        return d6grids;
    }

    public ArrayList<ChessGridComponent> x2ex(int row, int col, ChessPiece currentPlayer) {
        ArrayList<ChessGridComponent> x2grids = new ArrayList<>();
        int current = row + 1;
        if (row == 7) return x2grids;
        else {
            while (this.chessGrids[current][col].getChessPiece() != null && Objects.equals(this.chessGrids[current][col].getChessPiece().getColor(), currentPlayer.getopColer())) {
                x2grids.add(chessGrids[current][col]);
                current++;
                if (current == 8) {
                    current--;
                    break;
                }
            }
            if (this.chessGrids[current][col].getChessPiece() == null || !Objects.equals(this.chessGrids[current][col].getChessPiece().getColor(), currentPlayer.getColor())) {
                x2grids.clear();
            }
        }
        return x2grids;
    }

    public ArrayList<ChessGridComponent> a4ex(int row, int col, ChessPiece currentPlayer) {
        ArrayList<ChessGridComponent> a4grids = new ArrayList<>();
        int current = col - 1;
        if (col == 0) return a4grids;
        else {
            while (this.chessGrids[row][current].getChessPiece() != null && Objects.equals(this.chessGrids[row][current].getChessPiece().getColor(), currentPlayer.getopColer())) {
                a4grids.add(chessGrids[row][current]);
                current = current - 1;
                if (current == -1 || current == 8) {
                    current++;
                    break;
                }
            }
            if (this.chessGrids[row][current].getChessPiece() == null || !Objects.equals(this.chessGrids[row][current].getChessPiece().getColor(), currentPlayer.getColor())) {
                a4grids.clear();
            }
        }
        return a4grids;
    }

    public ArrayList<ChessGridComponent> e9ex(int row, int col, ChessPiece currentPlayer) {
        ArrayList<ChessGridComponent> e9grids = new ArrayList<>();
        int currentx = row - 1;
        int currenty = col + 1;
        if (row == 0 || col == 7) return e9grids;
        else {
            while (this.chessGrids[currentx][currenty].getChessPiece() != null && Objects.equals(this.chessGrids[currentx][currenty].getChessPiece().getColor(), currentPlayer.getopColer())) {
                e9grids.add(chessGrids[currentx][currenty]);
                currentx = currentx - 1;
                currenty = currenty + 1;
                if (currentx == -1 || currentx == 8 || currenty == 8) {
                    currentx++;
                    currenty--;
                    break;
                }
            }
            if (this.chessGrids[currentx][currenty].getChessPiece() == null || !Objects.equals(this.chessGrids[currentx][currenty].getChessPiece().getColor(), currentPlayer.getColor())) {
                e9grids.clear();
            }
        }
        return e9grids;
    }

    public ArrayList<ChessGridComponent> c3ex(int row, int col, ChessPiece currentPlayer) {
        ArrayList<ChessGridComponent> c3grids = new ArrayList<>();
        int currentx = row + 1;
        int currenty = col + 1;
        if (row == 7 || col == 7) return c3grids;
        else {
            while (this.chessGrids[currentx][currenty].getChessPiece() != null && Objects.equals(this.chessGrids[currentx][currenty].getChessPiece().getColor(), currentPlayer.getopColer())) {
                c3grids.add(chessGrids[currentx][currenty]);
                currentx = currentx + 1;
                currenty = currenty + 1;
                if (currentx == 8 || currenty == 8) {
                    currentx--;
                    currenty--;
                    break;
                }
            }
            if (this.chessGrids[currentx][currenty].getChessPiece() == null || !Objects.equals(this.chessGrids[currentx][currenty].getChessPiece().getColor(), currentPlayer.getColor())) {
                c3grids.clear();
            }
        }
        return c3grids;
    }

    public ArrayList<ChessGridComponent> z1ex(int row, int col, ChessPiece currentPlayer) {
        ArrayList<ChessGridComponent> z1grids = new ArrayList<>();
        int currentx = row + 1;
        int currenty = col - 1;
        if (row == 7 || col == 0) return z1grids;
        else {
            while (this.chessGrids[currentx][currenty].getChessPiece() != null && Objects.equals(this.chessGrids[currentx][currenty].getChessPiece().getColor(), currentPlayer.getopColer())) {
                z1grids.add(chessGrids[currentx][currenty]);
                currentx = currentx + 1;
                currenty = currenty - 1;
                if (currenty == -1 || currentx == 8 || currenty == 8) {
                    currentx--;
                    currenty++;
                    break;
                }
            }
            if (this.chessGrids[currentx][currenty].getChessPiece() == null || !Objects.equals(this.chessGrids[currentx][currenty].getChessPiece().getColor(), currentPlayer.getColor())) {
                z1grids.clear();
            }
        }
        return z1grids;
    }

    public ArrayList<ChessGridComponent> q7ex(int row, int col, ChessPiece currentPlayer) {
        ArrayList<ChessGridComponent> q7grids = new ArrayList<>();
        int currentx = row - 1;
        int currenty = col - 1;
        if (row == 0 || col == 0) return q7grids;
        else {
            while (this.chessGrids[currentx][currenty].getChessPiece() != null && Objects.equals(this.chessGrids[currentx][currenty].getChessPiece().getColor(), currentPlayer.getopColer())) {
                q7grids.add(chessGrids[currentx][currenty]);
                currentx = currentx - 1;
                currenty = currenty - 1;
                if (currentx == -1 || currenty == -1 || currentx == 8 || currenty == 8) {
                    currentx++;
                    currenty++;
                    break;
                }
            }
            if (this.chessGrids[currentx][currenty].getChessPiece() == null || !Objects.equals(this.chessGrids[currentx][currenty].getChessPiece().getColor(), currentPlayer.getColor())) {
                q7grids.clear();
            }
        }
        return q7grids;
    }

    public ArrayList<ChessGridComponent> all(int row, int col, ChessPiece currentPlayer) {
        ArrayList<ChessGridComponent> all = new ArrayList<>();
        all.addAll(w8ex(row, col, currentPlayer));
        all.addAll(d6ex(row, col, currentPlayer));
        all.addAll(x2ex(row, col, currentPlayer));
        all.addAll(a4ex(row, col, currentPlayer));
        all.addAll(e9ex(row, col, currentPlayer));
        all.addAll(c3ex(row, col, currentPlayer));
        all.addAll(z1ex(row, col, currentPlayer));
        all.addAll(q7ex(row, col, currentPlayer));
        return all;
    }

    public void initialChessGrids() {
        //创建棋盘数组
        chessGrids = new ChessGridComponent[CHESS_COUNT][CHESS_COUNT];
        //draw all chess grids
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                ChessGridComponent gridComponent = new ChessGridComponent(i, j);
                gridComponent.setLocation(j * ChessGridComponent.gridSize, i * ChessGridComponent.gridSize);
                chessGrids[i][j] = gridComponent;
                this.add(chessGrids[i][j]);
            }
        }
    }

    /**
     * initial origin four chess
     */
    public void initialGame() {
        chessGrids[3][3].setChessPiece(ChessPiece.WHITE);
        chessGrids[3][4].setChessPiece(ChessPiece.BLACK);
        chessGrids[4][3].setChessPiece(ChessPiece.BLACK);
        chessGrids[4][4].setChessPiece(ChessPiece.WHITE);

    }

    public void resetChessGrids() {
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                chessGrids[i][j].setChessPiece(null);
            }
        }
        chessGrids[3][3].setChessPiece(ChessPiece.WHITE);
        chessGrids[3][4].setChessPiece(ChessPiece.BLACK);
        chessGrids[4][3].setChessPiece(ChessPiece.BLACK);
        chessGrids[4][4].setChessPiece(ChessPiece.WHITE);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        //g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public boolean canClickGrid(int row, int col, ChessPiece currentPlayer) {
        if (chessGrids[row][col].getChessPiece() == null )
            if (!(q7(row, col, currentPlayer) == 0 && w8(row, col, currentPlayer) == 0 && e9(row, col, currentPlayer) == 0 && d6(row, col, currentPlayer) == 0 && c3(row, col, currentPlayer) == 0 && x2(row, col, currentPlayer) == 0 && z1(row, col, currentPlayer) == 0 && a4(row, col, currentPlayer) == 0)) {
                return true;
            }
        return false;
    }

    public int[][] readGamePanel() {
        int[][] ints = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessGrids[i][j].getChessPiece() == (ChessPiece.BLACK)) {
                    ints[i][j] = -1;
                } else if (chessGrids[i][j].getChessPiece() == (ChessPiece.WHITE)) {
                    ints[i][j] = 1;
                } else if (chessGrids[i][j].getChessPiece() == (null)) {
                    ints[i][j] = 0;
                }
            }
        }
        return ints;
    }

    public int countBlack() {
        int black = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessGrids[i][j].getChessPiece() == (ChessPiece.BLACK)) {
                    black++;
                }
            }
        }
        return black;
    }

    public int countWhite() {
        int white = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessGrids[i][j].getChessPiece() == (ChessPiece.WHITE)) {
                    white++;
                }
            }
        }
        return white;
    }

    public boolean isEmpty(int row, int col) {
        return chessGrids[row][col].getChessPiece() == null;
    }
}
