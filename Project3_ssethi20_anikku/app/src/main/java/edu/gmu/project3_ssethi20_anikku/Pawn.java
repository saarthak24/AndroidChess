package edu.gmu.project3_ssethi20_anikku;

import static edu.gmu.project3_ssethi20_anikku.Constants.BLANK_SQUARE;
import static edu.gmu.project3_ssethi20_anikku.Constants.WHITE_PAWN;
import static edu.gmu.project3_ssethi20_anikku.Constants.BLACK_PAWN;
import static edu.gmu.project3_ssethi20_anikku.Constants.getPieceName;

public class Pawn {
    public static boolean movePiece(PlayComputer gameInstance, int[][] chessGrid, int startRow, int startCol,
                                    int endRow, int endCol, boolean isWhite) {
        // Moving a white pawn
        if (isWhite) {
            if ((startRow == 6 && (startRow - endRow) == 2 && startCol == endCol)
                    && chessGrid[startRow][startCol] == WHITE_PAWN && chessGrid[endRow][endCol] == BLANK_SQUARE
                    && chessGrid[startRow - 1][startCol] == BLANK_SQUARE) {
                System.out.println("Moving white pawn from the beginning row.");
                gameInstance.addBlankTile(startRow, startCol);
                gameInstance.addWhitePawn(endRow, endCol);
                MainActivity.sounds.play(1, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
            } else if ((startRow - endRow) == 1 && startCol == endCol && chessGrid[startRow][startCol] == WHITE_PAWN
                    && chessGrid[endRow][endCol] == BLANK_SQUARE) {
                if (endRow == 0) {
                    System.out.println("Promoting white pawn to queen.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhiteQueen(endRow, endCol);
                    MainActivity.sounds.play(4, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece

                } else {
                    System.out.println("Moving white pawn.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhitePawn(endRow, endCol);
                    MainActivity.sounds.play(1, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
                }
            } else if (Math.abs(startCol - endCol) == 1 && (startRow - endRow) == 1
                    && chessGrid[startRow][startCol] == WHITE_PAWN && chessGrid[endRow][endCol] != BLANK_SQUARE && !getPieceName(chessGrid[endRow][endCol]).startsWith("White")) {
                if (endRow == 0) {
                    System.out.println("Promoting white pawn to queen after capturing.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhiteQueen(endRow, endCol);
                    MainActivity.sounds.play(2, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
                    MainActivity.sounds.play(4, 1, 1, 1, 0, 1.0f);//Plays sound when user promotes pawn
                } else {
                    System.out.println("Capturing piece with white pawn.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhitePawn(endRow, endCol);
                    MainActivity.sounds.play(2, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
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
                MainActivity.sounds.play(1, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
            } else if ((endRow - startRow) == 1 && startCol == endCol && chessGrid[startRow][startCol] == BLACK_PAWN
                    && chessGrid[endRow][endCol] == BLANK_SQUARE) {
                if (endRow == 7) {
                    System.out.println("Promoting black pawn to queen.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addBlackQueen(endRow, endCol);
                    MainActivity.sounds.play(4, 1, 1, 1, 0, 1.0f);//Plays sound when user promotes pawn chesspiece
                } else {
                    System.out.println("Moving black pawn.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addBlackPawn(endRow, endCol);
                    MainActivity.sounds.play(1, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
                }
            } else if (Math.abs(endCol - startCol) == 1 && (endRow - startRow) == 1
                    && chessGrid[startRow][startCol] == BLACK_PAWN && chessGrid[endRow][endCol] != BLANK_SQUARE && !getPieceName(chessGrid[endRow][endCol]).startsWith("Black")) {
                if (endRow == 7) {
                    System.out.println("Promoting black pawn to queen after capturing.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addBlackQueen(endRow, endCol);
                    MainActivity.sounds.play(2, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
                    MainActivity.sounds.play(4, 1, 1, 1, 0, 1.0f);//Plays sound when user promotes chesspiece

                } else {
                    System.out.println("Capturing piece with black pawn.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addBlackPawn(endRow, endCol);
                    MainActivity.sounds.play(2, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
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
                MainActivity.sounds.play(1, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
            } else if ((startRow - endRow) == 1 && startCol == endCol && chessGrid[startRow][startCol] == WHITE_PAWN
                    && chessGrid[endRow][endCol] == BLANK_SQUARE) {
                if (endRow == 0) {
                    System.out.println("Promoting white pawn to queen.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhiteQueen(endRow, endCol);
                    MainActivity.sounds.play(4, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece

                } else {
                    System.out.println("Moving white pawn.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhitePawn(endRow, endCol);
                    MainActivity.sounds.play(1, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
                }
            } else if (Math.abs(startCol - endCol) == 1 && (startRow - endRow) == 1
                    && chessGrid[startRow][startCol] == WHITE_PAWN && chessGrid[endRow][endCol] != BLANK_SQUARE && !getPieceName(chessGrid[endRow][endCol]).startsWith("White")) {
                if (endRow == 0) {
                    System.out.println("Promoting white pawn to queen after capturing.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhiteQueen(endRow, endCol);
                    MainActivity.sounds.play(2, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
                    MainActivity.sounds.play(4, 1, 1, 1, 0, 1.0f);//Plays sound when user promotes pawn
                } else {
                    System.out.println("Capturing piece with white pawn.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addWhitePawn(endRow, endCol);
                    MainActivity.sounds.play(2, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
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
                MainActivity.sounds.play(1, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
            } else if ((endRow - startRow) == 1 && startCol == endCol && chessGrid[startRow][startCol] == BLACK_PAWN
                    && chessGrid[endRow][endCol] == BLANK_SQUARE) {
                if (endRow == 7) {
                    System.out.println("Promoting black pawn to queen.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addBlackQueen(endRow, endCol);
                    MainActivity.sounds.play(4, 1, 1, 1, 0, 1.0f);//Plays sound when user promotes pawn chesspiece
                } else {
                    System.out.println("Moving black pawn.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addBlackPawn(endRow, endCol);
                    MainActivity.sounds.play(1, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
                }
            } else if (Math.abs(endCol - startCol) == 1 && (endRow - startRow) == 1
                    && chessGrid[startRow][startCol] == BLACK_PAWN && chessGrid[endRow][endCol] != BLANK_SQUARE && !getPieceName(chessGrid[endRow][endCol]).startsWith("Black")) {
                if (endRow == 7) {
                    System.out.println("Promoting black pawn to queen after capturing.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addBlackQueen(endRow, endCol);
                    MainActivity.sounds.play(2, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
                    MainActivity.sounds.play(4, 1, 1, 1, 0, 1.0f);//Plays sound when user promotes chesspiece

                } else {
                    System.out.println("Capturing piece with black pawn.");
                    gameInstance.addBlankTile(startRow, startCol);
                    gameInstance.addBlackPawn(endRow, endCol);
                    MainActivity.sounds.play(2, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
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
