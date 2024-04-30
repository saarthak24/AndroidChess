package edu.gmu.project3_ssethi20_anikku;

import static edu.gmu.project3_ssethi20_anikku.Constants.BOARD_SIZE;
import static edu.gmu.project3_ssethi20_anikku.Constants.BLANK_SQUARE;
import static edu.gmu.project3_ssethi20_anikku.Constants.WHITE_PAWN;
import static edu.gmu.project3_ssethi20_anikku.Constants.BLACK_PAWN;

// TODO: Make sure the pawn cannot capture pieces of its own color

public class Pawn {
    public static boolean movePiece(PlayComputer gameInstance, int[][] chessGrid, int startRow, int startCol,
            int endRow, int endCol, boolean isWhite) {
        if (isWhite) {
            if ((startRow == 6 && (startRow - endRow) == 2 && startCol == endCol)
                    && chessGrid[startRow][startCol] == WHITE_PAWN && chessGrid[endRow][endCol] == BLANK_SQUARE
                    && chessGrid[startRow - 1][startCol] == BLANK_SQUARE) {
                System.out.println("Moving white pawn from the beginning row.");
                gameInstance.addBlankTile(startRow, startCol);
                gameInstance.addWhitePawn(endRow, endCol);
            } else if ((startRow - endRow) == 1 && startCol == endCol && chessGrid[startRow][startCol] == WHITE_PAWN
                    && chessGrid[endRow][endCol] == BLANK_SQUARE) {
                if (endRow == 0) {
                    System.out.println("Promoting white pawn to queen.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhiteQueen(endRow, endCol);
                } else {
                    System.out.println("Moving white pawn.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhitePawn(endRow, endCol);
                }
            } else if (Math.abs(startCol - endCol) == 1 && (startRow - endRow) == 1
                    && chessGrid[startRow][startCol] == WHITE_PAWN && chessGrid[endRow][endCol] != BLANK_SQUARE) {
                if (endRow == 0) {
                    System.out.println("Promoting white pawn to queen after capturing.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhiteQueen(endRow, endCol);
                } else {
                    System.out.println("Capturing piece with white pawn.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhitePawn(endRow, endCol);
                }
            } else {
                System.out.println("Invalid move for white pawn.");
                return false;
            }
        } else {
            if ((startRow == 1 && (endRow - startRow) == 2 && startCol == endCol)
                    && chessGrid[startRow][startCol] == BLACK_PAWN && chessGrid[endRow][endCol] == BLANK_SQUARE
                    && chessGrid[startRow + 1][startCol] == BLANK_SQUARE) {
                System.out.println("Moving black pawn from the beginning row.");
                gameInstance.addBlankTile(startRow, startCol);
                gameInstance.addBlackPawn(endRow, endCol);
            } else if ((endRow - startRow) == 1 && startCol == endCol && chessGrid[startRow][startCol] == BLACK_PAWN
                    && chessGrid[endRow][endCol] == BLANK_SQUARE) {
                if (endRow == 7) {
                    System.out.println("Promoting black pawn to queen.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addBlackQueen(endRow, endCol);
                } else {
                    System.out.println("Moving black pawn.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addBlackPawn(endRow, endCol);
                }
            } else if (Math.abs(endCol - startCol) == 1 && (endRow - startRow) == 1
                    && chessGrid[startRow][startCol] == BLACK_PAWN && chessGrid[endRow][endCol] != BLANK_SQUARE) {
                if (endRow == 7) {
                    System.out.println("Promoting black pawn to queen after capturing.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addBlackQueen(endRow, endCol);
                } else {
                    System.out.println("Capturing piece with black pawn.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addBlackPawn(endRow, endCol);
                }
            } else {
                System.out.println("Invalid move for black pawn.");
                return false;
            }
        }
        System.out.println("Move executed successfully.");
        return true;
    }

    public static boolean movePiece(PlayFriend gameInstance, int[][] chessGrid, int startRow, int startCol,
                                    int endRow, int endCol, boolean isWhite) {
        // Moving a white pawn
        if (isWhite) {
            if ((startRow == 6 && (startRow - endRow) == 2 && startCol == endCol)
                    && chessGrid[startRow][startCol] == WHITE_PAWN && chessGrid[endRow][endCol] == BLANK_SQUARE
                    && chessGrid[startRow - 1][startCol] == BLANK_SQUARE) {
                System.out.println("Moving white pawn from the beginning row.");
                gameInstance.addBlankTile(startRow, startCol);
                gameInstance.addWhitePawn(endRow, endCol);
            } else if ((startRow - endRow) == 1 && startCol == endCol && chessGrid[startRow][startCol] == WHITE_PAWN
                    && chessGrid[endRow][endCol] == BLANK_SQUARE) {
                if (endRow == 0) {
                    System.out.println("Promoting white pawn to queen.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhiteQueen(endRow, endCol);
                } else {
                    System.out.println("Moving white pawn.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhitePawn(endRow, endCol);
                }
            } else if (Math.abs(startCol - endCol) == 1 && (startRow - endRow) == 1
                    && chessGrid[startRow][startCol] == WHITE_PAWN && chessGrid[endRow][endCol] != BLANK_SQUARE) {
                if (endRow == 0) {
                    System.out.println("Promoting white pawn to queen after capturing.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhiteQueen(endRow, endCol);
                } else {
                    System.out.println("Capturing piece with white pawn.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhitePawn(endRow, endCol);
                }
            } else {
                System.out.println("Invalid move for white pawn.");
                return false;
            }
        }
        // Moving a black pawn
        else {
            if ((startRow == 1 && (endRow - startRow) == 2 && startCol == endCol)
                    && chessGrid[startRow][startCol] == BLACK_PAWN && chessGrid[endRow][endCol] == BLANK_SQUARE
                    && chessGrid[startRow + 1][startCol] == BLANK_SQUARE) {
                System.out.println("Moving black pawn from the beginning row.");
                gameInstance.addBlankTile(startRow, startCol);
                gameInstance.addBlackPawn(endRow, endCol);
            } else if ((endRow - startRow) == 1 && startCol == endCol && chessGrid[startRow][startCol] == BLACK_PAWN
                    && chessGrid[endRow][endCol] == BLANK_SQUARE) {
                if (endRow == 7) {
                    System.out.println("Promoting black pawn to queen.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addBlackQueen(endRow, endCol);
                } else {
                    System.out.println("Moving black pawn.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addBlackPawn(endRow, endCol);
                }
            } else if (Math.abs(endCol - startCol) == 1 && (endRow - startRow) == 1
                    && chessGrid[startRow][startCol] == BLACK_PAWN && chessGrid[endRow][endCol] != BLANK_SQUARE) {
                if (endRow == 7) {
                    System.out.println("Promoting black pawn to queen after capturing.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addBlackQueen(endRow, endCol);
                } else {
                    System.out.println("Capturing piece with black pawn.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addBlackPawn(endRow, endCol);
                }
            } else {
                System.out.println("Invalid move for black pawn.");
                return false;
            }
        }
        System.out.println("Move executed successfully.");
        return true;
    }

}
