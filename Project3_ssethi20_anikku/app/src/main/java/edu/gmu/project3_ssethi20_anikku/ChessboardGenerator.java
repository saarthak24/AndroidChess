package edu.gmu.project3_ssethi20_anikku;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Button;

public class ChessboardGenerator {

    public static GridLayout generateChessboard(Context context) {
        GridLayout chessboard = new GridLayout(context);
        chessboard.setColumnCount(8);
        chessboard.setRowCount(8);
        chessboard.setOrientation(GridLayout.HORIZONTAL);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Button square = new Button(context);
                square.setLayoutParams(new GridLayout.LayoutParams(
                        GridLayout.spec(row, GridLayout.FILL, 1f),
                        GridLayout.spec(col, GridLayout.FILL, 1f)));

                // Set the background color based on the position
                if ((row + col) % 2 == 0) {
                    square.setBackgroundColor(Color.WHITE);
                } else {
                    square.setBackgroundColor(Color.BLACK);
                }

                // Optionally, set the tag for each square
                square.setTag(String.format("%s%d", (char) ('a' + col), 8 - row));

                chessboard.addView(square);
            }
        }

        return chessboard;
    }
}
