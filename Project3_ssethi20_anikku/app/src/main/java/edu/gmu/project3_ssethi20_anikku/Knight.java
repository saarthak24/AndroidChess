package edu.gmu.project3_ssethi20_anikku;

import static edu.gmu.project3_ssethi20_anikku.Constants.BLANK_SQUARE;
import static edu.gmu.project3_ssethi20_anikku.Constants.getPieceName;

public class Knight {
    public static boolean movePiece(PlayComputer gameInstance, int[][] chessGrid, int startRow, int startCol, int endRow, int endCol, boolean isWhite) {
        if(!((Math.abs(endRow - startRow) == 2 && Math.abs(endCol - startCol) == 1) || (Math.abs(endCol - startCol) == 2 && Math.abs(endRow - startRow) == 1))) {
            return false; // Not diagonal movement
        }
        
        if(isWhite) {
            if(getPieceName(chessGrid[endRow][endCol]).startsWith("White")) {
                System.out.println("Illegal Move: You are attempting to capture one of your own pieces with your knight.");
                return false;
            }
            System.out.println("moving your knight");
            gameInstance.addBlankTile(startRow, startCol);
            gameInstance.addWhiteKnight(endRow, endCol);
            return true;
        }
        else {
            gameInstance.addBlankTile(startRow, startCol);
            gameInstance.addBlackKnight(endRow, endCol);
            return true;
        }
    }
}
