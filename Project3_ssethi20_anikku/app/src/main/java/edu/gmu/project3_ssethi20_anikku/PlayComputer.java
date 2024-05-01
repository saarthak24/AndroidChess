package edu.gmu.project3_ssethi20_anikku;

import static edu.gmu.project3_ssethi20_anikku.Constants.BOARD_SIZE;
import static edu.gmu.project3_ssethi20_anikku.Constants.BLACK;
import static edu.gmu.project3_ssethi20_anikku.Constants.WHITE;
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
import static edu.gmu.project3_ssethi20_anikku.Constants.WHITE_KING;
import static edu.gmu.project3_ssethi20_anikku.Constants.WHITE_BISHOP;
import static edu.gmu.project3_ssethi20_anikku.Constants.WHITE_ROOK;
import static edu.gmu.project3_ssethi20_anikku.Constants.getPieceName;

import android.annotation.TargetApi;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlayComputer extends AppCompatActivity
{
    TextView statusText;
    int chessGrid[][] = new int[BOARD_SIZE][BOARD_SIZE];

    private boolean computerTurn=false;

    int selectedPieceRow=-1;
    int selectedPieceCol=-1;

    int destinationTileRow=-1;
    int destinationTileCol=-1;

    private boolean chessPieceSelected = false;
    public boolean chessPieceSelected() {
        boolean result = false;
        if(selectedPieceRow != -1 && selectedPieceCol != -1) {
            result = true;
        }
        return result;
    }

    public int[] getSquareIndices(String notation) {
        // Ensure the notation is exactly two characters long
        if (notation.length() != 2) {
            throw new IllegalArgumentException("Invalid notation: " + notation);
        }

        // Extract the column and row from the notation
        char columnChar = notation.charAt(0);
        char rowChar = notation.charAt(1);

        // Convert the column letter to an index (0-7)
        int col = columnChar - 'a';
        // Convert the row number to an index (0-7), adjusting for the reverse order
        int row = '8' - rowChar;

        // Return the row and column as a 2x1 int array
        return new int[]{row, col};
    }

    public String getSquareNotation(int row, int col) {
        char[] columnNotation = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        char[] rowNotation = {'8', '7', '6', '5', '4', '3', '2', '1'};

        return String.valueOf(columnNotation[col]) + rowNotation[row];
    }

    public void selectPieceToMove(int row, int col) {
        if(getPieceName(chessGrid[row][col]).startsWith("Black")) {
            updateStatusText("You cannot move the opponent's piece!");
            return;
        }
        if(!chessPieceSelected()) {
            System.out.println("There is currently no chess piece selected.");
        }

        if(chessPieceSelected() == false && computerTurn == false && chessGrid[row][col] == BLANK_SQUARE) {
            Toast.makeText(getApplicationContext(), "Please select a chess piece", Toast.LENGTH_SHORT).show();
        }

        else if(computerTurn == false && chessPieceSelected() == false) {
            selectedPieceRow = row;
            selectedPieceCol = col;
            chessPieceSelected = true;
            editStartingLocationText(selectedPieceRow, selectedPieceCol);
            System.out.println("Now there is. You selected the " + getPieceName(chessGrid[row][col]) + " on " + getSquareNotation(row, col));
            updateStatusText("You selected the " + getPieceName(chessGrid[row][col]) + " on " + getSquareNotation(row, col));
        }
    }

    public void updateStatusText(String newStatus) {
        statusText.setText(newStatus);
    }

    public void selectMoveDestination(int row, int col) {
        if(!chessPieceSelected()) {
            System.out.println("There is currently no chess piece selected.");
        }
        else {
            System.out.println("You currently have a chess piece selected.");
        }
        System.out.println("Selected row: " + (selectedPieceRow+1) + ", col: " + (selectedPieceCol+1));
        if(chessPieceSelected == true && (row != selectedPieceRow || col != selectedPieceCol)) {
            System.out.println("here2dest");
            destinationTileRow = row;
            destinationTileCol = col;
            editEndingLocationText(row, col);

            int selectedPiece = chessGrid[selectedPieceRow][selectedPieceCol];
            if(movePiece(selectedPiece, selectedPieceRow, selectedPieceCol, row, col)) {
                updateStatusText("You moved the " + getPieceName(selectedPiece) + " on " +  getSquareNotation(selectedPieceRow, selectedPieceCol) + " to " + getSquareNotation(row, col));
            }
            else {
                updateStatusText("That is an illegal move!");
            }

            selectedPieceRow=-1;
            selectedPieceCol=-1;
            chessPieceSelected=false;
        }
    }

    public boolean movePiece(int chessPiece, int startRow, int startCol, int endRow, int endCol) {
        if(chessPiece==WHITE_PAWN) {
            System.out.println("in white pawn move condition");
            return Pawn.movePiece(this, chessGrid, startRow, startCol, endRow, endCol, true);
        }
        else if(chessPiece==WHITE_BISHOP) {
            System.out.println("in white bishop move condition");
            return Bishop.movePiece(this, chessGrid, startRow, startCol, endRow, endCol, true);
        }
        else if(chessPiece==WHITE_KNIGHT) {
            System.out.println("in white knight move condition");
            return Knight.movePiece(this, chessGrid, startRow, startCol, endRow, endCol, true);
        }
        else if(chessPiece==WHITE_ROOK) {
            System.out.println("in white rook move condition");
            return Rook.movePiece(this, chessGrid, startRow, startCol, endRow, endCol, true);
        }
        else if(chessPiece==WHITE_QUEEN) {
            System.out.println("in white queen move condition");
            return Queen.movePiece(this, chessGrid, startRow, startCol, endRow, endCol, true);
        }
        else if(chessPiece==WHITE_KING) {
            System.out.println("in white king move condition");
            return King.movePiece(this, chessGrid, startRow, startCol, endRow, endCol, true);
        }
        return false;
    }

    public void editStartingLocationText(int row, int col) {
        TextView selectedPieceStarting = findViewById(R.id.startingLocation);
        selectedPieceStarting.setText("row: " + (row+1) + " col: " + (col+1) + " (" + getSquareNotation(row, col) + ")");
    }
    public void editEndingLocationText(int row, int col) {
        TextView selectedPieceEnding=findViewById(R.id.endingLocation);
        selectedPieceEnding.setText("row: " + (row+1) + " col: " + (col+1) + " (" + getSquareNotation(row, col) + ")");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_computer);

        statusText = findViewById(R.id.statusText);

        //ImageView kingSideTest=findViewById(R.id.imageView53);
        //kingSideTest.setImageResource(R.drawable.white_pawn_darkbg);

        resetGame();

        for(int row = 0; row < BOARD_SIZE; row++) {
            for(int col = 0; col < BOARD_SIZE; col++) {
                // Construct the ID based on the naming convention
                String idName = getSquareNotation(row, col);
                int resId = getResources().getIdentifier(idName, "id", getPackageName());

                // Find the view by its dynamically constructed ID
                View square = findViewById(resId);

                final int[] currentPosition = {row, col};

                // Set the OnClickListener for the square
                square.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Perform the action based on the clicked square's position
                        selectPieceToMove(currentPosition[0], currentPosition[1]);
                        selectMoveDestination(currentPosition[0], currentPosition[1]);
                    }
                });

                // Set a tag on the view to store its row and column for easy retrieval
                square.setTag(idName);
            }
        }
    }

    public ImageView returnViewById(int row, int col) {
        // Construct the resource name based on the row and column
        String resourceName = getSquareNotation(row, col);

        // Use getIdentifier to get the resource ID
        int resId = getResources().getIdentifier(resourceName, "id", getPackageName());

        // Find the view by its dynamically constructed ID
        ImageView result = findViewById(resId);

        return result;
    }

    //used when resetting the board, moving a piece selected, and when a piece gets captured by the opponent
    public void addBlankTile(int row, int col)
    {
        //if we are in an even spot, we put the object with a black border (since it was a dark shaded chess piece)
        if(((row+col)%2)==0)
        {
            chessGrid[row][col] = BLANK_SQUARE;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_d45);
        }

        //otherwise, we put the object with a light border (since it is a light shaded chess location on the board)
        else
        {
            chessGrid[row][col]=BLANK_SQUARE;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_l45);
        }
    }

    public void addBlackRook(int row, int col)
    {
        //if we are in an even spot, we put the object with a black border (since it was a dark shaded chess piece)
        if(((row+col)%2)==0)
        {
            chessGrid[row][col] = BLACK_ROOK;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_rdd45);
        }

        //otherwise, we put the object with a light border (since it is a light shaded chess location on the board)
        else
        {
            chessGrid[row][col] = BLACK_ROOK;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_rdl45);
        }
    }

    public void addBlackKnight(int row, int col)
    {
        //if we are in an even spot, we put the object with a black border (since it was a dark shaded chess piece)
        if(((row+col)%2)==0)
        {
            chessGrid[row][col] = BLACK_KNIGHT;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_ndd45);
        }

        //otherwise, we put the object with a light border (since it is a light shaded chess location on the board)
        else
        {
            chessGrid[row][col]= BLACK_KNIGHT;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_ndl45);
        }
    }

    public void addBlackBishop(int row, int col)
    {
        //if we are in an even spot, we put the object with a black border (since it was a dark shaded chess piece)
        if(((row+col)%2)==0)
        {
            chessGrid[row][col] = BLACK_BISHOP;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_bdd45);
        }

        //otherwise, we put the object with a light border (since it is a light shaded chess location on the board)
        else
        {
            chessGrid[row][col]=BLACK_BISHOP;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_bdl45);
        }
    }

    public void addBlackQueen(int row, int col)
    {
        //if we are in an even spot, we put the object with a black border (since it was a dark shaded chess piece)
        if(((row+col)%2)==0)
        {
            chessGrid[row][col] = BLACK_QUEEN;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_qdd45);
        }

        //otherwise, we put the object with a light border (since it is a light shaded chess location on the board)
        else
        {
            chessGrid[row][col] = BLACK_QUEEN;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_qdl45);
        }
    }

    public void addBlackKing(int row, int col)
    {
        //if we are in an even spot, we put the object with a black border (since it was a dark shaded chess piece)
        if(((row+col)%2)==0)
        {
            chessGrid[row][col] = BLACK_KING;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_kdd45);
        }

        //otherwise, we put the object with a light border (since it is a light shaded chess location on the board)
        else
        {
            chessGrid[row][col] = BLACK_KING;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_kdl45);
        }
    }

    public void addBlackPawn(int row, int col)
    {
        //if we are in an even spot, we put the object with a black border (since it was a dark shaded chess piece)
        if(((row+col)%2)==0)
        {
            chessGrid[row][col] = BLACK_PAWN;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_pdd45);
        }

        //otherwise, we put the object with a light border (since it is a light shaded chess location on the board)
        else
        {
            chessGrid[row][col] = BLACK_PAWN;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_pdl45);
        }
    }

    //for white chess pieces
    //====================================
    public void addWhiteRook(int row, int col)
    {
        //if we are in an even spot, we put the object with a black border (since it was a dark shaded chess piece)
        if(((row+col)%2)==0)
        {
            chessGrid[row][col] = WHITE_ROOK;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_rld45);
        }

        //otherwise, we put the object with a light border (since it is a light shaded chess location on the board)
        else
        {
            chessGrid[row][col]=WHITE_ROOK;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_rll45);
        }
    }

    public void addWhiteKnight(int row, int col)
    {
        //if we are in an even spot, we put the object with a black border (since it was a dark shaded chess piece)
        if(((row+col)%2)==0)
        {
            chessGrid[row][col] = WHITE_KNIGHT;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_nld45);
        }

        //otherwise, we put the object with a light border (since it is a light shaded chess location on the board)
        else
        {
            chessGrid[row][col]=WHITE_KNIGHT;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_nll45);
        }
    }

    public void addWhiteBishop(int row, int col)
    {
        //if we are in an even spot, we put the object with a black border (since it was a dark shaded chess piece)
        if(((row+col)%2)==0)
        {
            chessGrid[row][col] = WHITE_BISHOP;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_bld45);
        }

        //otherwise, we put the object with a light border (since it is a light shaded chess location on the board)
        else
        {
            chessGrid[row][col]=WHITE_BISHOP;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_bll45);
        }
    }

    public void addWhiteQueen(int row, int col)
    {
        //if we are in an even spot, we put the object with a black border (since it was a dark shaded chess piece)
        if(((row+col)%2)==0)
        {
            chessGrid[row][col]=WHITE_QUEEN;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_qld45);
        }

        //otherwise, we put the object with a light border (since it is a light shaded chess location on the board)
        else
        {
            chessGrid[row][col]=WHITE_QUEEN;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_qll45);
        }
    }

    public void addWhiteKing(int row, int col)
    {
        //if we are in an even spot, we put the object with a black border (since it was a dark shaded chess piece)
        if(((row+col)%2)==0)
        {
            chessGrid[row][col]=WHITE_KING;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_kld45);
        }

        //otherwise, we put the object with a light border (since it is a light shaded chess location on the board)
        else
        {
            chessGrid[row][col]=WHITE_KING;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_kll45);
        }
    }

    public void addWhitePawn(int row, int col)
    {
        //if we are in an even spot, we put the object with a black border (since it was a dark shaded chess piece)
        if(((row+col)%2)==0)
        {
            chessGrid[row][col]=WHITE_PAWN;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_pld45);
        }

        //otherwise, we put the object with a light border (since it is a light shaded chess location on the board)
        else
        {
            chessGrid[row][col]=WHITE_PAWN;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_pll45);
        }
    }

    //This method is used to span pawns from the left to right (used when starting a new game)
    private void spanPawns(int colorStatus, int startRow) {
        if(colorStatus==BLACK) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                addBlackPawn(startRow, col);
            }
        }

        if(colorStatus==WHITE) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                addWhitePawn(startRow, col);
            }
        }
    }

    public void spanBlankTiles() {
        for(int i=0; i<chessGrid.length; i++)
        {
            for(int j=0; j<chessGrid[i].length; j++)
            {
                addBlankTile(i, j);
            }
        }
    }

    public void resetGame() {
        //adding blank tiles (removing existing pieces from old game, and initializing grid data structure)
        spanBlankTiles();

        //setting up the pieces for black
        addBlackRook(0, 0);
        addBlackRook(0, 7);
        addBlackKnight(0, 1);
        addBlackKnight(0, 6);
        addBlackBishop(0, 2);
        addBlackBishop(0, 5);
        addBlackQueen(0, 3);
        addBlackKing(0, 4);
        spanPawns(BLACK, 1);

        //setting up the pieces for white
        addWhiteRook(7, 0);
        addWhiteRook(7, 7);
        addWhiteKnight(7,1);
        addWhiteKnight(7,6);
        addWhiteBishop(7,2);
        addWhiteBishop(7,5);
        addWhiteQueen(7,3);
        addWhiteKing(7,4);
        spanPawns(WHITE, 6);

        selectedPieceRow=-1;
        selectedPieceCol=-1;
        destinationTileRow=-1;
        destinationTileCol=-1;

    }

    public void clearSelection(View view) {
        selectedPieceRow = -1;
        selectedPieceCol = -1;

        updateStatusText("Please select a piece to move!");

        TextView selectedPieceStarting = findViewById(R.id.startingLocation);
        selectedPieceStarting.setText("Starting Piece");

        TextView selectedPieceEnding = findViewById(R.id.endingLocation);
        selectedPieceEnding.setText("Ending Location");
    }

    public void returnToMainMenu(View v) {
        finish();
    }
}
