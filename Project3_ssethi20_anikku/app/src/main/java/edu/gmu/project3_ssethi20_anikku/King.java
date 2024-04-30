package edu.gmu.project3_ssethi20_anikku;

import static edu.gmu.project3_ssethi20_anikku.Constants.BLANK_SQUARE;
import static edu.gmu.project3_ssethi20_anikku.Constants.getPieceName;

public class King {
    public static boolean movePiece(PlayComputer gameInstance, int[][] chessGrid, int startRow, int startCol,
                                    int endRow, int endCol, boolean isWhite) {
        // Check if the destination square is directly adjacent to the starting square
        if (Math.abs(startRow - endRow) > 1 || Math.abs(startCol - endCol) > 1) {
            return false; // King can only move to adjacent squares
        }

        if(getPieceName(chessGrid[endRow][endCol]).startsWith(isWhite ? "White" : "Black")) {
            System.out.println("Illegal Move: You are attempting to capture one of your own pieces with your king.");
            return false;
        }
        gameInstance.addBlankTile(startRow, startCol);
        if(isWhite) {
            gameInstance.addWhiteKing(endRow, endCol);
        }
        else {
            gameInstance.addBlackKing(endRow, endCol);
        }
        return true;
    }
}
