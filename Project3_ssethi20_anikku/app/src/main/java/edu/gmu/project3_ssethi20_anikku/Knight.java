package edu.gmu.project3_ssethi20_anikku;

import static edu.gmu.project3_ssethi20_anikku.Constants.BLANK_SQUARE;
import static edu.gmu.project3_ssethi20_anikku.Constants.getPieceName;

public class Knight {
    public static boolean movePiece(PlayComputer gameInstance, int[][] chessGrid, int startRow, int startCol,
                                    int endRow, int endCol, boolean isWhite) {
        if(!((Math.abs(endRow - startRow) == 2 && Math.abs(endCol - startCol) == 1)
                || (Math.abs(endCol - startCol) == 2 && Math.abs(endRow - startRow) == 1))) {
            return false; // Not diagonal movement
        }

        if(getPieceName(chessGrid[endRow][endCol]).startsWith(isWhite ? "White" : "Black")) {
            System.out.println(
                    "Illegal Move: You are attempting to capture one of your own pieces with your knight.");
            return false;
        }

        if (isWhite) {
            System.out.println("moving your knight");
            gameInstance.addBlankTile(startRow, startCol);
            gameInstance.addWhiteKnight(endRow, endCol);
            return true;
        } else {
            gameInstance.addBlankTile(startRow, startCol);
            gameInstance.addBlackKnight(endRow, endCol);
            return true;
        }
    }

    public static boolean movePiece(PlayFriend gameInstance, int[][] chessGrid, int startRow, int startCol,
            int endRow, int endCol, boolean isWhite) {
        if(!((Math.abs(endRow - startRow) == 2 && Math.abs(endCol - startCol) == 1)
                || (Math.abs(endCol - startCol) == 2 && Math.abs(endRow - startRow) == 1))) {
            return false; // Not diagonal movement
        }

        if(getPieceName(chessGrid[endRow][endCol]).startsWith(isWhite ? "White" : "Black")) {
            System.out.println(
                    "Illegal Move: You are attempting to capture one of your own pieces with your knight.");
            return false;
        }

        if (isWhite) {
            System.out.println("moving your knight");
            gameInstance.addBlankTile(startRow, startCol);
            gameInstance.addWhiteKnight(endRow, endCol);
            MainActivity.sounds.play(1, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
            return true;
        } else {
            gameInstance.addBlankTile(startRow, startCol);
            gameInstance.addBlackKnight(endRow, endCol);
            MainActivity.sounds.play(1, 1, 1, 1, 0, 1.0f);//Plays sound when user moves chesspiece
            return true;
        }
    }
}
