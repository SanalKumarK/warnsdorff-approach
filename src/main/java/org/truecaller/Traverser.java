package org.truecaller;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Traverser class to perform the moves.
 */
public class Traverser {
    private Board board;

    public Traverser(Board board) {
        this.board = board;
    }

    public Board tour(Tile[] moves, Tile tile) {
        return tour(moves, tile, false);
    }

    /**
     * Traverse the board with the possible moves from the starting position
     * using Warnsdorff's heuristic approach.
     *
     * @param moves possible moves of the piece on the board.
     * @param tile  Starting position of the tile.
     * @param interactive
     * @return Board after visit.
     */
    public Board tour(Tile[] moves, Tile tile, boolean interactive) {
        //traverse through the board, until all tiles are visited.
        while (!traverse(moves, tile, interactive)) {
            ;
        }
        return board;
    }

    /**
     * Perform tour operation with the given piece, from the starting position.
     *
     * @param moves possible moves of piece on the board.
     * @param start Starting position of the piece on the board.
     * @param interactive
     * @return True if piece traversed all the tiles in the board, or False
     */
    private boolean traverse(Tile[] moves, Tile start, boolean interactive) {
        int moveCount = 1; //Keep the count of each step

        board.initializeBoard();
        //Set the first position in the board.
        board.setValue(start.getRow(), start.getCol(), moveCount);

        //Do a traverse on every tile, until a valid tile exist from each position.
        Tile position = start;
        for (int i = 0; i < (board.getSize() * board.getSize() - 1); i++) {
            position = getNextTile(moves, position);
            if (position == null) {
                return false;
            }
            board.setValue(position.getRow(), position.getCol(), ++moveCount);
            if(interactive) {
                board.printBoard();
                waitToProceed();
            }
        }
        //Check if all the cells are visited.
        if (moveCount == 100) {
            return true;
        }
        return false;
    }

    private void waitToProceed() {
        Scanner in = new Scanner(System.in);
        in.nextLine();
        System.out.println("Proceeding to next move");
    }

    /**
     * Get the next tile from the current tile based on the given moves.
     * Tile is selected using Warnsdorff's heuristic approach.
     * Next tile is having minimum number of empty cells compared to the other tiles.
     *
     * @param moves possible moves.
     * @param tile  current tile.
     * @return
     */
    private Tile getNextTile(Tile[] moves, Tile tile) {
        int minEmptyCount = moves.length + 1, tempCount;
        Tile validTile = null;
        int N = moves.length;
        int start = ThreadLocalRandom.current().nextInt(1000) % N;
        for (int count = 0; count < N; ++count) {
            int i = (start + count) % N;
            int xMove = tile.getRow() + moves[i].getRow();
            int yMove = tile.getCol() + moves[i].getCol();
            if (board.isEmpty(xMove, yMove)) {
                tempCount = getEmptyTileCount(moves, xMove, yMove);
                if (tempCount < minEmptyCount) {
                    minEmptyCount = tempCount;
                    validTile = new Tile(xMove, yMove);
                }
            }
        }
        return validTile;
    }

    /**
     * Get the number of empty tile counts from the given row & col using the provided moves.
     *
     * @param moves possible moves.
     * @param row   row position
     * @param col   col position
     * @return number of empty cells.
     */
    private int getEmptyTileCount(Tile[] moves, int row, int col) {
        int emptyCellCount = 0;
        for (int i = 0; i < moves.length; i++) {
            int xMove = row + moves[i].getRow(), yMove = col + moves[i].getCol();
            if (board.isEmpty(xMove, yMove)) {
                emptyCellCount++;
            }
        }
        return emptyCellCount;
    }
}
