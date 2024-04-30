package edu.gmu.project3_ssethi20_anikku;

import static edu.gmu.project3_ssethi20_anikku.Constants.BOARD_SIZE;
import static edu.gmu.project3_ssethi20_anikku.Constants.BLANK_SQUARE;
import static edu.gmu.project3_ssethi20_anikku.Constants.WHITE_PAWN;

// TODO: Make sure the pawn cannot capture pieces of its own color

public class Pawn {
    public static boolean movePiece(PlayComputer gameInstance, int[][] chessGrid, int startRow, int startCol, int endRow, int endCol, boolean isWhite) {
        if(isWhite) {
            // If the pawn hasn't been moved yet (i.e. is still in the starting row), we can move two steps forward (without capturing a piece)
            if ((startRow == 6 && ((startRow - endRow) == 2) && (startCol == endCol))
                    && chessGrid[startRow][startCol] == WHITE_PAWN && chessGrid[endRow][endCol] == BLANK_SQUARE && chessGrid[startRow-1][startCol] == BLANK_SQUARE) {
                System.out.println("beginning row pawn");
                gameInstance.addBlankTile(startRow, startCol);
                gameInstance.addWhitePawn(endRow, endCol);
            }

            // otherwise, we move 1 step forward (not capturing piece, moving forward only)
            else if (((startRow - endRow) == 1 && (startCol == endCol)) && chessGrid[startRow][startCol] == WHITE_PAWN
                    && chessGrid[endRow][endCol] == BLANK_SQUARE) {
                if(endRow == 0) {
                    // Automatically promote the pawn to a Queen
                    System.out.println("promoting pawn to a queen");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhiteQueen(endRow, endCol);
                }
                else {
                    System.out.println("moving pawn that has already moved at least once");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhitePawn(endRow, endCol);
                }
            }

            // TODO: implement en passant logic

            // capturing pieces (the difference between the row is a positive number for
            // white pieces capturing movement from start to end row
            else if (Math.abs(startCol - endCol) == 1 && ((startRow - endRow) == 1)
                    && chessGrid[startRow][startCol] == WHITE_PAWN && chessGrid[endRow][endCol] != BLANK_SQUARE) {
                if(endRow == 0) {
                    // Automatically promote the pawn to a Queen
                    System.out.println("promoting pawn to a queen");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhiteQueen(endRow, endCol);
                }
                else {
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhitePawn(endRow, endCol);
                }
            }

            else {
                return false;
            }
        }

        if(!isWhite) {
            // If the pawn hasn't been moved yet (i.e. is still in the starting row), we can
            // move two steps forward (without capturing a piece)
            // TODO: Make sure the path is clear
            if ((startRow == 1 && ((endRow - startRow) == 2) && (startCol == endCol))
                    && chessGrid[startRow][startCol] == WHITE_PAWN && chessGrid[endRow][endCol] == BLANK_SQUARE) {
                System.out.println("beginning row pawn");
                gameInstance.addBlankTile(startRow, startCol);
                gameInstance.addWhitePawn(endRow, endCol);
            }
            // TODO: Complete this logic by mirroring the logic for isWhite
        }
        return true;
    }
}
