package com.cassio.chess.exception;

/**
 * Created by Cassio on 12/02/2015.
 */
public class HorizontalMoveException extends IllegalChessMoveException {
    public HorizontalMoveException(String message) {
        super(message);
    }
}