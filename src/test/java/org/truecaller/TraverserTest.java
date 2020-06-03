package org.truecaller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TraverserTest {

    private Tile[] pieceMoves = {new Tile(3,0), new Tile(2,-2), new Tile(0,-3),
            new Tile(-2,-2), new Tile(-3,0), new Tile(-2,2),
            new Tile(0,3), new Tile(2,2)};

    private int BOARD_SIZE = 10;
    private Traverser traverser;

    @BeforeAll
    void setUp() {
        Board board = new Board(10);
        traverser = new Traverser(board);
    }

    @Test
    void tour() {
        for(int i=0; i < BOARD_SIZE; i++) {
            for(int j=0; j< BOARD_SIZE; j++) {
                Tile org = new Tile(i,j);
                Board board = traverser.tour(pieceMoves, org);
                verifyBoard(board);
            }
        }
    }

    @Test
    void tourKnight() {
        Tile[] knightMoves = {new Tile(1,2), new Tile(1,-2), new Tile(2,1),
                new Tile(2,-1), new Tile(-1,2), new Tile(-1,-2),
                new Tile(-2,1), new Tile(-2,-1)};

        for(int i=0; i < BOARD_SIZE; i++) {
            for(int j=0; j< BOARD_SIZE; j++) {
                Tile org = new Tile(i,j);
                Board board = traverser.tour(knightMoves, org);
                verifyBoard(board);
            }
        }
    }

    @Test
    void tourPieceFromOrigin() {
        Tile org = new Tile(0,0);
        Board board = traverser.tour(pieceMoves, org);
        board.printBoard();
        verifyBoard(board);
    }

    private void verifyBoard(Board board) {
        int n = BOARD_SIZE * BOARD_SIZE;
        int sum = n * (n+1)/2, totalSum = 0;
        for(int i=0;i<BOARD_SIZE;i++) {
            for (int j=0;j<BOARD_SIZE;j++){
                totalSum+=board.getValue(i,j);
            }
        }
        if(sum != totalSum) {
            Assertions.assertEquals(sum, totalSum);
        }
    }
}