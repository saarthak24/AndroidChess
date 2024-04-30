package edu.gmu.project3_ssethi20_anikku;

import static edu.gmu.project3_ssethi20_anikku.Constants.BOARD_SIZE;
import static edu.gmu.project3_ssethi20_anikku.Constants.BLANK_SQUARE;
import static edu.gmu.project3_ssethi20_anikku.Constants.getPieceName;

public class Queen {
    public static boolean movePiece(PlayComputer gameInstance, int[][] chessGrid, int startRow, int startCol,
            int endRow, int endCol, boolean isWhite) {
        // Ensure movement is diagonal
        if(!((startCol == endCol && startRow != endRow) || (startCol != endCol && startRow == endRow) || (Math.abs(endCol - startCol) == Math.abs(endRow - startRow)))) {
            return false; // Not moving the queen in a legal direction (horizontally, vertically, or diagonally)
        }
        if(isWhite) {
            // Make sure the path is clear
            if(isPathClear(chessGrid, startRow, startCol, endRow, endCol)) {
                System.out.println("Attempting to capture: " + getPieceName((chessGrid[endRow][endCol])));
                if(getPieceName(chessGrid[endRow][endCol]).startsWith("White")) {
                    System.out.println("Illegal Move: You are attempting to capture one of your own pieces with your queen.");
                    return false;
                }
                System.out.println("moving your queen");
                gameInstance.addBlankTile(startRow, startCol);
                gameInstance.addWhiteQueen(endRow, endCol);
            }
            else {
                return false;
            }
        }
        return true;
    }

    public static boolean movePiece(PlayFriend gameInstance, int[][] chessGrid, int startRow, int startCol,
            int endRow, int endCol, boolean isWhite) {
        // Ensure movement is diagonal
        if (!((startCol == endCol && startRow != endRow) || (startCol != endCol && startRow == endRow)
                || (Math.abs(endCol - startCol) == Math.abs(endRow - startRow)))) {
            return false; // Not moving the queen in a legal direction (horizontally, vertically, or
                          // diagonally)
        }
        // Make sure the path is clear
        if (isPathClear(chessGrid, startRow, startCol, endRow, endCol)) {
            System.out.println("Attempting to capture: " + getPieceName((chessGrid[endRow][endCol])));
            if (getPieceName(chessGrid[endRow][endCol]).startsWith(isWhite ? "White" : "Black")) {
                System.out.println(
                        "Illegal Move: You are attempting to capture one of your own pieces with your queen.");
                return false;
            }
            System.out.println("moving your queen");
            gameInstance.addBlankTile(startRow, startCol);
            if(isWhite) {
                gameInstance.addWhiteQueen(endRow, endCol);
            }
            else {
                gameInstance.addBlackQueen(endRow, endCol);
            }
        } else {
            return false;
        }
        return true;
    }

    private static boolean isPathClear(int[][] chessGrid, int startRow, int startCol, int endRow, int endCol) {
        // Calculate the direction of movement, both horizontally and vertically
        int rowDirection = endRow > startRow ? 1 : (endRow < startRow ? -1 : 0);
        int colDirection = endCol > startCol ? 1 : (endCol < startCol ? -1 : 0);

        // Ensure movement is either horizontal, vertical, or diagonal
        if (rowDirection != 0 && colDirection != 0 && Math.abs(endRow - startRow) != Math.abs(endCol - startCol)) {
            return false; // Not horizontal, vertical, or diagonal movement
        }

        // Start from the starting position
        int currentRow = startRow;
        int currentCol = startCol;

        // Continue moving in the direction of the end position until reaching the end position
        while (currentRow != (endRow-rowDirection) || currentCol != (endCol-colDirection)) {
            // Move to the next square in the direction of the end position
            currentRow += rowDirection;
            currentCol += colDirection;

            // If any square along the path is not BLANK_SQUARE, return false
            if (chessGrid[currentRow][currentCol] != BLANK_SQUARE) {
                return false;
            }
        }

        // The path is clear!
        return true;
    }
}