package org.truecaller;

public class Board {

    private int[][] board;

    public Board(int size) {
        this.board = new int[size][size];
    }

    /**
     * Initialize the board.
     *
     * @param
     */
    public void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
    }

    /**
     * Return true if cell is empty.
     * @param row
     * @param col
     * @return
     */
    public boolean isEmpty(int row, int col) {
        return isValidTile(row, col) && (board[row][col] == 0);
    }

    /**
     * Check if the row & col is bounded in the board.
     * @param row
     * @param col
     * @return
     */
    private boolean isValidTile(int row, int col) {
        return ((row > -1 && row < board.length) && (col > -1 && col < board.length)) ? true : false;
    }

    /**
     * Get the value at given row & col.
     * @param row
     * @param col
     * @return
     */
    public int getValue(int row, int col) {
        return board[row][col];
    }

    /**
     * Set the value at given row & col.
     * @param row
     * @param col
     * @param val
     */
    public void setValue(int row, int col, int val) {
        board[row][col] = val;
    }

    /**
     * Print the board.
     */
    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }

    /**
     * Return the size of the board.
     * @return
     */
    public int getSize() {
        return board.length;
    }
}
