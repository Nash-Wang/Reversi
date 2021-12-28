package model;

import java.awt.*;

public enum ChessPiece {
    BLACK(Color.BLACK), WHITE(Color.WHITE);

    private final Color color;

    ChessPiece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    //new
    public Color getopColer() {
        if (this.getColor().equals(BLACK.getColor())) return Color.WHITE;
        if (this.getColor().equals(WHITE.getColor())) return Color.BLACK;
        else return null;
    }
}
