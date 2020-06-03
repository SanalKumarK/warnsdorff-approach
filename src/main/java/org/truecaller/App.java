package org.truecaller;

/**
 * Application to tour all the cells in a chess board for a piece.
 */
public class App {
    private static int BOARD_SIZE = 10;

    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            System.err.println("Usage: java -jar App <start_row> <start_col> <interactive_mode>\n");
            System.exit(1);
        }
        boolean interactive = false;
        if(args.length > 2) {
            interactive = (args[2] != null) ? Boolean.parseBoolean(args[2]):false;
        }
        //Create starting tile from the input.
        Tile tile;
        if ((tile = createStartingTile(args)) != null) {
            Board board = new Board(BOARD_SIZE);
            Traverser traverser = new Traverser(board);
            board = traverser.tour(getDefaultMoves(), tile, interactive);
            board.printBoard();
        }
    }

    /**
     * Validate the given row & col arguments,
     * If valid return a Tile, else throw IllegalArgumentException.
     * @param args
     * @return
     */
    private static Tile createStartingTile(String[] args) {
        try {
            int row = Integer.parseInt(args[0]);
            int col = Integer.parseInt(args[1]);
            if(args.length > 2) {
                Boolean.parseBoolean(args[2]);
            }
            if(row < 0 || row >= BOARD_SIZE) {
                throw new IllegalArgumentException("Row value range from 0-9, given input is outside the board.");
            }
            if(col < 0 || col >= BOARD_SIZE) {
                throw new IllegalArgumentException("Col value range from 0-9, given input is outside the board.");
            }
            return new Tile(row, col);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Please provide valid input, " + ex.getMessage());
        }
    }

    /**
     * Default Moves of the Piece.
     * @return
     */
    private static Tile[] getDefaultMoves() {
        Tile[] pieceMoves = {new Tile(3, 0), new Tile(2, -2), new Tile(0, -3),
                new Tile(-2, -2), new Tile(-3, 0), new Tile(-2, 2),
                new Tile(0, 3), new Tile(2, 2)};
        return pieceMoves;
    }
}
