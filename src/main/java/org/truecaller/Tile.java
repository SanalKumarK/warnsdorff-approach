package org.truecaller;

/**
 * Wrapper class holding the row, and column index of the board.
 */
public class Tile {
    private int row;
    private int col;

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
