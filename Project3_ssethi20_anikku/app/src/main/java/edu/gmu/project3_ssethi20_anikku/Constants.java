package edu.gmu.project3_ssethi20_anikku;

public class Constants {
    public static final int BOARD_SIZE = 8;

    public static final int BLACK = 1;
    public static final int WHITE = 0;

    public static final int BLANK_SQUARE = 0;

    public static final int BLACK_ROOK = 1;
    public static final int BLACK_KNIGHT = 2;
    public static final int BLACK_BISHOP = 3;
    public static final int BLACK_QUEEN = 4;
    public static final int BLACK_KING = 5;
    public static final int BLACK_PAWN = 6;

    public static final int WHITE_ROOK = 7;
    public static final int WHITE_KNIGHT = 8;
    public static final int WHITE_BISHOP = 9;
    public static final int WHITE_QUEEN = 10;
    public static final int WHITE_KING = 11;
    public static final int WHITE_PAWN = 12;

    public static String getPieceName(int piece) {
        String names[] = {"BLANK SQUARE", "Black Rook", "Black Knight", "Black Bishop", "Black Queen", "Black King", "Black Pawn", "White Rook", "White Knight", "White Bishop", "White Queen", "White King", "White Pawn"};
        return names[piece];
    }
}
