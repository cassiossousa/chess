package com.cassio.chess.library.moves;

import com.cassio.chess.library.board.Board;
import com.cassio.chess.library.board.Square;

/**
 * Created by Cassio on 18/02/2015.
 */
public class BishopMoveSet extends MoveSet {
    /**
     * Creates a new bishop move set having a square, a board and a color as reference.
     *
     * @param referenceSquare the square to be taken as reference for relative moves.
     * @param referenceBoard  the board to be taken as reference for moves.
     * @param colorChoice     the color choice of the piece, in case a piece has color limitations.
     */
    public BishopMoveSet(Square referenceSquare, Board referenceBoard, boolean colorChoice) {
        super(referenceSquare, referenceBoard, colorChoice);
    }

    /**
     * Process the piece-specific algorithms to acquire the allowed squares to move to. In the case of a bishop,
     */
    protected void learnMoveSet() {
        learnDiagonalMoves();
    }

    /**
     * Goes through all the squares diagonally too see which ones allow a valid move.
     */
    private void learnDiagonalMoves() {
        learnDiagonalMovesAt(1, 1);
        learnDiagonalMovesAt(1, -1);
        learnDiagonalMovesAt(-1, 1);
        learnDiagonalMovesAt(-1, -1);
    }

    /**
     * Goes through all the diagonal squares in a certain direction, according to a pace in each direction.
     *
     * @param xPace how the X-coordinate changes with time. (-1) means left, and (+1) means right.
     * @param yPace how the Y-coordinate changes with time. (-1) means downwards, and (+1) means upwards.
     */
    private void learnDiagonalMovesAt(int xPace, int yPace) {
        boolean foundPiece = false;
        int xPos = getRefX() + xPace, yPos = getRefY() + yPace;
        for (; outOfBounds(xPos, yPos) || !foundPiece; ) {
            if (referenceBoard.hasPieceAt(xPos, getRefY())) {
                foundPiece = true;
                if (opponentPieceAt(xPos, getRefY()))
                    addSquareAt(xPos, getRefY());
            } else addSquareAt(xPos, getRefY());
            xPos += xPace;
            yPos += yPace;
        }
    }

    /**
     * Checks both instances of outOfBounds(int) - the X-axis and the Y-axis - at the same time.
     *
     * @param xPos X-coordinate to be verified.
     * @param yPos Y-coordinate to be verified.
     * @return {@code true} if any of the coordinates is out of bounds on the referenced chessboard.
     */
    private boolean outOfBounds(int xPos, int yPos) {
        return referenceBoard.xPosOutOfBounds(xPos) ||
                referenceBoard.yPosOutOfBounds(yPos);
    }
}
