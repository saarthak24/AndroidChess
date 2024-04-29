package edu.gmu.project3_ssethi20_anikku;

import static edu.gmu.project3_ssethi20_anikku.Constants.BOARD_SIZE;
import static edu.gmu.project3_ssethi20_anikku.Constants.BLANK_SQUARE;
import static edu.gmu.project3_ssethi20_anikku.Constants.BLACK_BISHOP;
import static edu.gmu.project3_ssethi20_anikku.Constants.BLACK_KING;
import static edu.gmu.project3_ssethi20_anikku.Constants.BLACK_KNIGHT;
import static edu.gmu.project3_ssethi20_anikku.Constants.BLACK_PAWN;
import static edu.gmu.project3_ssethi20_anikku.Constants.BLACK_QUEEN;
import static edu.gmu.project3_ssethi20_anikku.Constants.BLACK_ROOK;
import static edu.gmu.project3_ssethi20_anikku.Constants.WHITE_QUEEN;
import static edu.gmu.project3_ssethi20_anikku.Constants.WHITE_PAWN;
import static edu.gmu.project3_ssethi20_anikku.Constants.WHITE_KNIGHT;
import static edu.gmu.project3_ssethi20_anikku.Constants.WHITE_BISHOP;
import static edu.gmu.project3_ssethi20_anikku.Constants.WHITE_ROOK;

import android.widget.Toast;

public class Pawn {
    public static boolean movePiece(PlayComputer gameInstance, int[][] chessGrid, int startRow, int startCol, int endRow, int endCol, boolean isWhite) {
        // if the starting row is 6 (or 1 for black), we can move two steps (moving
        // forward, not capturing any piece)
        if ((startRow == 6 && ((startRow - endRow) == 2) && (startCol == endCol))
                && chessGrid[startRow][startCol] == WHITE_PAWN && chessGrid[endRow][endCol] == BLANK_SQUARE) {
            System.out.println("beginning row pawn");
            gameInstance.addBlankTile(startRow, startCol);
            gameInstance.addWhitePawn(endRow, endCol);
        }

        // otherwise, we move 1 step forward (not capturing piece, moving forward only)
        else if (((startRow - endRow) == 1 && (startCol == endCol)) && chessGrid[startRow][startCol] == WHITE_PAWN
                && chessGrid[endRow][endCol] == BLANK_SQUARE) {
            System.out.println("pawn past row 1 or 6");
            gameInstance.addBlankTile(startRow, startCol);
            gameInstance.addWhitePawn(endRow, endCol);
        }

        // capturing pieces (the difference between the row is a positive number for
        // white pieces capturing movement from start to end row
        else if (Math.abs(startCol - endCol) == 1 && ((startRow - endRow) == 1)
                && chessGrid[startRow][startCol] == WHITE_PAWN && chessGrid[endRow][endCol] != BLANK_SQUARE
                && chessGrid[endRow][endCol] != BLACK_KING) {
            gameInstance.addBlankTile(startRow, startCol);
            gameInstance.addWhitePawn(endRow, endCol);
        }

        // otherwise, it is an illegal move
        else {
            Toast.makeText(gameInstance.getApplicationContext(), "The move you done is an illegal move", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
