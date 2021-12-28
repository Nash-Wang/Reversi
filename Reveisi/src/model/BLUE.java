package model;

import java.awt.*;

public enum BLUE {
    BLUE(Color.black);

    private final Color color;

    BLUE(Color color) {
        this.color = color;
    }

    public Color getBLUE() {
        return color;
    }
}