package controller;

import components.ChessGridComponent;
import components.Step;
import model.ChessPiece;
import view.ChessBoardPanel;
import view.GameFrame;
import view.StatusPanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class GameController {


    ChessBoardPanel gamePanel;
    StatusPanel statusPanel;
    ChessPiece currentPlayer;
    int blackScore;
    int whiteScore;
    int cantActCount = 0;
    boolean cheatSwitch = false;
    public ArrayList<String> Steps = new ArrayList<>();
    public ArrayList<Step> StepList = new ArrayList<>();//2.0
    boolean CanAct = true;//3.1

    public void setCanAct(boolean canAct) {
        CanAct = canAct;
    }

    public GameController(ChessBoardPanel gamePanel, StatusPanel statusPanel) {
        this.gamePanel = gamePanel;
        this.statusPanel = statusPanel;
        this.currentPlayer = ChessPiece.BLACK;

        blackScore = 2;
        whiteScore = 2;
    }

    public void openCheatMod() {
        cheatSwitch = true;
    }//2.1

    public void closeCheatMod() {
        cheatSwitch = false;
    }//2.1

    public void changePlayer(){
        this.currentPlayer = ChessPiece.BLACK;
    }

    public void swapPlayer() {
        blackScore = gamePanel.countBlack();
        whiteScore = gamePanel.countWhite();
        boolean canAct = false;
        currentPlayer = (currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
        if (!cheatSwitch) {
            for (ChessGridComponent[] allCols : gamePanel.getChessGrids()) {
                for (ChessGridComponent allGrids : allCols) {
                    if (allGrids.getChessPiece() == null && !(gamePanel.q7(allGrids.getRow(), allGrids.getCol(), currentPlayer) == 0 && gamePanel.w8(allGrids.getRow(), allGrids.getCol(), currentPlayer) == 0 && gamePanel.e9(allGrids.getRow(), allGrids.getCol(), currentPlayer) == 0 && gamePanel.d6(allGrids.getRow(), allGrids.getCol(), currentPlayer) == 0 && gamePanel.c3(allGrids.getRow(), allGrids.getCol(), currentPlayer) == 0 && gamePanel.x2(allGrids.getRow(), allGrids.getCol(), currentPlayer) == 0 && gamePanel.z1(allGrids.getRow(), allGrids.getCol(), currentPlayer) == 0 && gamePanel.a4(allGrids.getRow(), allGrids.getCol(), currentPlayer) == 0)) {
                        canAct = true;
                        cantActCount = 0;
                    }
                }
            }
        } else {
            for (ChessGridComponent[] allCols : gamePanel.getChessGrids()) {
                for (ChessGridComponent allGrids : allCols) {
                    if (allGrids.getChessPiece() == null) {
                        canAct = true;
                        cantActCount = 0;
                        break;
                    }
                }
            }
        }
        if (!canAct && cantActCount == 2) isWin();
        if (!canAct && cantActCount < 2) {
            cantActCount++;
            swapPlayer();
            System.out.println("swap player failed");
        }
        if (currentPlayer.name().equals("BLACK")) {
            StatusPanel.playerLabel.setForeground(Color.BLACK);
        } else if (currentPlayer.name().equals("WHITE")) {
            StatusPanel.playerLabel.setForeground(Color.white);
        }
        statusPanel.setPlayerText(currentPlayer.name());
        statusPanel.setScoreText(blackScore, whiteScore);
    }//2.1

    public boolean canClick(int row, int col) {
        if (cheatSwitch){
            return gamePanel.isEmpty(row, col);
        }
        else {
            return gamePanel.canClickGrid(row, col, currentPlayer);
        }
    }//2.1

    //2.0

    public ArrayList<ChessGridComponent> AvailableGrids() {
        ArrayList<ChessGridComponent> Grids = new ArrayList<>();
        for (ChessGridComponent[] allCols : gamePanel.getChessGrids()) {
            for (ChessGridComponent allGrids : allCols) {
                if (allGrids.getChessPiece() == null && !(gamePanel.q7(allGrids.getRow(), allGrids.getCol(), currentPlayer) == 0 && gamePanel.w8(allGrids.getRow(), allGrids.getCol(), currentPlayer) == 0 && gamePanel.e9(allGrids.getRow(), allGrids.getCol(), currentPlayer) == 0 && gamePanel.d6(allGrids.getRow(), allGrids.getCol(), currentPlayer) == 0 && gamePanel.c3(allGrids.getRow(), allGrids.getCol(), currentPlayer) == 0 && gamePanel.x2(allGrids.getRow(), allGrids.getCol(), currentPlayer) == 0 && gamePanel.z1(allGrids.getRow(), allGrids.getCol(), currentPlayer) == 0 && gamePanel.a4(allGrids.getRow(), allGrids.getCol(), currentPlayer) == 0)) {
                    Grids.add(allGrids);
                }
            }
        }
        //System.out.println(Grids);
        return Grids;
    }

    //new
    public void flipChessPieces(int row, int col) {
        for (ChessGridComponent pieces : gamePanel.w8ex(row, col, currentPlayer)) {
            pieces.setChessPiece(currentPlayer);
            pieces.repaint();
        }
        for (ChessGridComponent pieces : gamePanel.d6ex(row, col, currentPlayer)) {
            pieces.setChessPiece(currentPlayer);
            pieces.repaint();
        }
        for (ChessGridComponent pieces : gamePanel.x2ex(row, col, currentPlayer)) {
            pieces.setChessPiece(currentPlayer);
            pieces.repaint();
        }
        for (ChessGridComponent pieces : gamePanel.a4ex(row, col, currentPlayer)) {
            pieces.setChessPiece(currentPlayer);
            pieces.repaint();
        }
        for (ChessGridComponent pieces : gamePanel.q7ex(row, col, currentPlayer)) {
            pieces.setChessPiece(currentPlayer);
            pieces.repaint();
        }
        for (ChessGridComponent pieces : gamePanel.e9ex(row, col, currentPlayer)) {
            pieces.setChessPiece(currentPlayer);
            pieces.repaint();
        }
        for (ChessGridComponent pieces : gamePanel.c3ex(row, col, currentPlayer)) {
            pieces.setChessPiece(currentPlayer);
            pieces.repaint();
        }
        for (ChessGridComponent pieces : gamePanel.z1ex(row, col, currentPlayer)) {
            pieces.setChessPiece(currentPlayer);
            pieces.repaint();
        }
    }

    //new
    public void isWin() {
        if (blackScore > whiteScore) {
            JOptionPane.showMessageDialog(null, "Player BLACK WIN", "Message", JOptionPane.NO_OPTION);
        } else if (blackScore < whiteScore) {
            JOptionPane.showMessageDialog(null, "Player WHITE WIN", "Message", JOptionPane.NO_OPTION);
        } else {
            JOptionPane.showMessageDialog(null, "DRAW", "Message", JOptionPane.NO_OPTION);
        }
        //System.exit(0);//需要修改
    }

    //new
    public void countScore() {
        for (ChessGridComponent[] allCols : gamePanel.getChessGrids()) {
            for (ChessGridComponent allGrids : allCols) {
                if (allGrids.getChessPiece() != null) {
                    if (allGrids.getChessPiece().getColor().equals(Color.BLACK)) blackScore++;
                    else whiteScore++;
                }
            }
        }
    }


    public ChessPiece getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessBoardPanel getGamePanel() {
        return gamePanel;
    }


    public void setGamePanel(ChessBoardPanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    public List<Step> readFileData(String fileName) {
        //todo: read date from file
        List<String> fileData = new ArrayList<>();
        List<String> panelData = new ArrayList<>();
        ArrayList<Step> steps = new ArrayList<>();
        int[][] ints = new int[8][8];
        String filePath = "C:\\Users\\20694\\IdeaProjects\\untitled\\Project\\src\\" + fileName;
        String[] strings1 = fileName.split("\\.");
        if (!strings1[1].equals("txt")) {
            JOptionPane.showMessageDialog(null, "ERROR 104", "ERROR", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        try {
            File file = new File(filePath);
            if (!file.exists()){
                JOptionPane.showMessageDialog(null,"ERROR 106","WARNING",JOptionPane.WARNING_MESSAGE);
                return null;
            }
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileData.add(line);
            }
            for (int i = 0; i < 8; i++) {
                String[] strings = fileData.get(0).split(" ");
                if (strings.length != 8) {
                    JOptionPane.showMessageDialog(null, "ERROR 101", "ERROR", JOptionPane.WARNING_MESSAGE);
                    return null;
                }
                panelData.add(fileData.get(0));
                fileData.remove(0);
            }
            for (String fileDatum : panelData) {
                int a = 0;
                String[] strings = fileDatum.split("\\s+");
                for (int i = 0; i < 8; i++) {
                    if (Integer.parseInt(strings[i]) != 1 && Integer.parseInt(strings[i]) != -1 && Integer.parseInt(strings[i]) != 0) {
                        JOptionPane.showMessageDialog(null, "ERROR 102", "ERROR", JOptionPane.WARNING_MESSAGE);
                        return null;
                    }
                    ints[a][i] = Integer.parseInt(strings[i]);
                }
                a++;
            }
            for (String fileDatum : fileData) {
                String[] strings = fileDatum.split(" ");
                if (strings.length != 3) {
                    JOptionPane.showMessageDialog(null, "ERROR 103", "ERROR", JOptionPane.WARNING_MESSAGE);
                    return null;
                }
                if (strings[2].equals("BLACK")) {
                    Step step = new Step(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), ChessPiece.BLACK);
                    steps.add(step);
                } else if (strings[2].equals("WHITE")) {
                    Step step = new Step(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), ChessPiece.WHITE);
                    steps.add(step);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR 103", "ERROR", JOptionPane.WARNING_MESSAGE);
                    return null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        StepList = steps;
        return steps;
    }

    public void writeDataToFile(String fileName) {
        //todo: write data into file
        String path = "C:\\Users\\20694\\IdeaProjects\\untitled\\Project\\src\\" + fileName;
        int[][] gameFile = gamePanel.readGamePanel();
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int[] a : gameFile) {
                for (int b : a) {
                    bufferedWriter.write(String.valueOf(b));
                    bufferedWriter.write(" ");
                }
                bufferedWriter.newLine();
            }
            for (Step s : StepList) {
                Steps.add((s.getRowIndex()) + " " + s.getColumnIndex() + " " + s.getChessPiece());
            }
            for (String s : Steps) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetStatusPanel() {
        blackScore = 2;
        whiteScore = 2;
        currentPlayer = ChessPiece.BLACK;
        statusPanel.setPlayerText("BLACK");
        statusPanel.setScoreText(blackScore, whiteScore);
    }
}
