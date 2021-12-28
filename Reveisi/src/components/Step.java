package components;

import model.ChessPiece;

import java.awt.*;

public class Step {
    private int rowIndex;
    private int columnIndex;
    private ChessPiece chessPiece;


    public Step(int rowIndex, int columnIndex, ChessPiece chessPiece) {
        setRowIndex(rowIndex);
        setColumnIndex(columnIndex);
        setChessPiece(chessPiece);
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String toString() {
        return String.format("rowIndex: %d, columnIndex: %d, color: %s",  getRowIndex(), getColumnIndex(), getChessPiece());
    }
}
