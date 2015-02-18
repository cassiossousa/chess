package com.cassio.chess.library.moves;

import com.cassio.chess.library.board.Board;
import com.cassio.chess.library.board.Square;

/**
 * {@code RookMoveSet} class - implementation of MoveSet class for rooks. Rooks can move horizontally or vertically as
 * many squares as possible. The move set stops its updates in a certain direction if it finds a piece (which can be
 * captured if it is an opponent's piece) or the limits of the board.
 *
 * @author Cassio dos Santos Sousa
 * @version 1.0
 * @see com.cassio.chess.library.moves.MoveSet
 */
public class RookMoveSet extends MoveSet {

    /**
     * Creates a new rook move set having a square, a board and a color as reference.
     *
     * @param referenceSquare the square to be taken as reference for relative moves.
     * @param referenceBoard  the board to be taken as reference for moves.
     * @param colorChoice     the color choice of the piece, in case a piece has color limitations.
     */
    public RookMoveSet(Square referenceSquare, Board referenceBoard, boolean colorChoice) {
        super(referenceSquare, referenceBoard, colorChoice);
    }

    /**
     * Process the piece-specific algorithms to acquire the allowed squares to move to.
     */
    protected void learnMoveSet() {
        learnVerticalMoves();
        learnHorizontalMoves();
    }

    /**
     * Goes through all the squares in the vertical direction too see which ones allow a valid move.
     */
    private void learnVerticalMoves() {
        learnVerticalMovesWithDirection(1);
        learnVerticalMovesWithDirection(-1);
    }

    /**
     * Goes through all the squares in the horizontal direction too see which ones allow a valid move.
     */
    private void learnHorizontalMoves() {
        learnHorizontalMovesWithDirection(1);
        learnHorizontalMovesWithDirection(-1);
    }

    /**
     * Goes through all the vertical squares in a certain direction, according to a pace.
     *
     * @param pace how the Y-coordinate changes with time. (-1) means downwards, and (+1) means upwards.
     */
    private void learnVerticalMovesWithDirection(int pace) {
        boolean foundPiece = false;
        for (int yPos = getRefY() + pace; !referenceBoard.yPosOutOfBounds(yPos) && !foundPiece; yPos += pace) {
            if (referenceBoard.hasPieceAt(getRefX(), yPos)) {
                foundPiece = true;
                if (opponentPieceAt(getRefX(), yPos)) {
                    addSquareAt(getRefX(), yPos);
                }
            } else addSquareAt(getRefX(), yPos);
        }
    }


    /**
     * Goes through all the horizontal squares in a certain direction, according to a pace.
     *
     * @param pace how the X-coordinate changes with time. (-1) means left, and (+1) means right.
     */
    private void learnHorizontalMovesWithDirection(int pace) {
        boolean foundPiece = false;
        for (int xPos = getRefX() + pace; !(referenceBoard.xPosOutOfBounds(xPos) || foundPiece); xPos += pace) {
            if (referenceBoard.hasPieceAt(xPos, getRefY())) {
                foundPiece = true;
                if (opponentPieceAt(xPos, getRefY()))
                    addSquareAt(xPos, getRefY());
            } else addSquareAt(xPos, getRefY());
        }
    }

}
