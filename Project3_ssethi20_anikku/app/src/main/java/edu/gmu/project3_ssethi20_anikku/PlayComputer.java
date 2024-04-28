package edu.gmu.project3_ssethi20_anikku;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlayComputer extends AppCompatActivity
{

    public static final int BOARD_SIZE=8;
    int chessGrid[][]=new int[BOARD_SIZE][BOARD_SIZE];

    public static final int BLACK=1;
    public static final int WHITE=0;

    public static final int BLANK_SQUARE=0;

    public static final int BLACK_ROOK=1;
    public static final int BLACK_KNIGHT=2;

    public static final int BLACK_BISHOP=3;

    public static final int BLACK_QUEEN=4;

    public static final int BLACK_KING=5;

    public static final int BLACK_PAWN=6;


    public static final int WHITE_ROOK=7;
    public static final int WHITE_KNIGHT=8;

    public static final int WHITE_BISHOP=9;

    public static final int WHITE_QUEEN=10;

    public static final int WHITE_KING=11;

    public static final int WHITE_PAWN=12;

    private boolean computerTurn=false;

    int selectedPieceRow=-1;
    int selectedPieceCol=-1;

    int destinationTileRow=-1;
    int destinationTileCol=-1;

    private boolean chessPieceSelected=false;
    public boolean chessPieceSelected()
    {
        boolean result=false;
        if(selectedPieceRow!=-1 && selectedPieceCol!=-1)
        {
            result=true;
        }
        return result;
    }

    public boolean chessPieceDestination()
    {
        boolean result=false;
        if(chessPieceSelected()==true)
        {
            if(selectedPieceRow!=destinationTileRow && selectedPieceCol!=destinationTileCol)
            {
                result=true;
            }
        }
        return result;
    }

    public void selectChessPiece(int row, int col)
    {
        System.out.println("The chess piece selected: " + chessPieceSelected());
        if(chessPieceSelected()==false && computerTurn==false && chessGrid[row][col]==BLANK_SQUARE)
        {
            Toast.makeText(getApplicationContext(), "Please select a chess piece", Toast.LENGTH_SHORT).show();
        }

        else if(chessPieceSelected()==false && computerTurn==false && chessGrid[row][col]==BLACK_ROOK || chessGrid[row][col]==BLACK_KNIGHT || chessGrid[row][col]==BLACK_BISHOP || chessGrid[row][col]==BLACK_QUEEN ||chessGrid[row][col]==BLACK_KING || chessGrid[row][col]==BLACK_PAWN)
        {
           Toast.makeText(getApplicationContext(), "You need to select your chess piece", Toast.LENGTH_SHORT).show();
        }

        else if(computerTurn==false && chessPieceSelected()==false)
        {
            selectedPieceRow=row;
            selectedPieceCol=col;
            chessPieceSelected=true;
            editPieceStartingLocationInApp(selectedPieceRow, selectedPieceCol);
        }
    }

    public void selectDestinationLocation(int row, int col)
    {
        System.out.println("Chess status: " + chessPieceSelected());
        System.out.println("selected row: " + selectedPieceRow + ", col: " + selectedPieceCol);
        if(chessPieceSelected==true && (row!=selectedPieceRow || col!=selectedPieceCol))
        {
            System.out.println("here2dest");
            destinationTileRow=row;
            destinationTileCol=col;
            editPieceEndingLocationInApp(row, col);
            movePiece(WHITE_PAWN, selectedPieceRow, selectedPieceCol, row, col);
            selectedPieceRow=-1;
            selectedPieceCol=-1;
            chessPieceSelected=false;
        }
    }

    public void movePiece(int chessPiece, int startRow, int startCol, int endRow, int endCol)
    {
        //addBlankTile(startRow, startCol);
        if(chessPiece==WHITE_PAWN)
        {
            System.out.println("in white pawn if condition");
            //if the starting row is 1 or 6, we can move two steps (moving forward, not capturing any piece)
            if((startRow==1 || startRow==6) && (startCol==endCol) && chessGrid[startRow][startCol]==WHITE_PAWN && chessGrid[endRow][endCol]==BLANK_SQUARE)
            {
                System.out.println("beginning row pawn");
                addBlankTile(startRow, startCol);
                addWhitePawn(endRow, endCol);
            }

            //otherwise, we move 1 step forward (not capturing piece, moving forward only)
            else if(startRow!=1 && endRow!=6 && chessGrid[startRow][startCol]==WHITE_PAWN && chessGrid[endRow][endCol]==BLANK_SQUARE)
            {
                System.out.println("pawn past row 1 or 6");
                addBlankTile(startRow, startCol);
                addWhitePawn(endRow, endCol);
            }

        }

    }

    public void editPieceStartingLocationInApp(int row, int col)
    {
        TextView selectedPieceStarting=findViewById(R.id.startingLocation);
        selectedPieceStarting.setText("Row: " + row + " Col: " + col);
    }
    public void editPieceEndingLocationInApp(int row, int col)
    {
        TextView selectedPieceEnding=findViewById(R.id.endingLocation);
        selectedPieceEnding.setText("Row: " + row + " Col: " + col);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_computer);
        //ImageView kingSideTest=findViewById(R.id.imageView53);
        //kingSideTest.setImageResource(R.drawable.white_pawn_darkbg);
        resetGame();

    }

    public ImageView returnViewById(int row, int col)
    {
        ImageView result=null;

        //first four tiles in row 0
        if(row==0 && col==0)
        {
            result=findViewById(R.id.imageView);
        }

        else if(row==0 && col==2)
        {
            result=findViewById(R.id.imageView3);
        }

        else if(row==0 && col==4)
        {
            result=findViewById(R.id.imageView5);
        }

        else if(row==0 && col==6)
        {
            result=findViewById(R.id.imageView7);
        }

        //other four tiles in row 0 (will have a different color)
        else if(row==0 && col==1)
        {
            result=findViewById(R.id.imageView2);
        }

        else if(row==0 && col==3)
        {
            result=findViewById(R.id.imageView4);
        }

        else if(row==0 && col==5)
        {
            result=findViewById(R.id.imageView6);
        }

        else if(row==0 && col==7)
        {
            result=findViewById(R.id.imageView8);
        }

        //first four tiles in row 1
        else if(row==1 && col==0)
        {
            result=findViewById(R.id.imageView9);
        }

        else if(row==1 && col==2)
        {
            result=findViewById(R.id.imageView11);
        }

        else if(row==1 && col==4)
        {
            result=findViewById(R.id.imageView13);
        }

        else if(row==1 && col==6)
        {
            result=findViewById(R.id.imageView15);
        }

        //other four tiles in row 1 (will have a different color)
        else if(row==1 && col==1)
        {
            result=findViewById(R.id.imageView10);
        }

        else if(row==1 && col==3)
        {
            result=findViewById(R.id.imageView12);
        }

        else if(row==1 && col==5)
        {
            result=findViewById(R.id.imageView14);
        }

        else if(row==1 && col==7)
        {
            result=findViewById(R.id.imageView16);
        }

        //first four tiles in row 2
        else if(row==2 && col==0)
        {
            result=findViewById(R.id.imageView17);
        }

        else if(row==2 && col==2)
        {
            result=findViewById(R.id.imageView19);
        }

        else if(row==2 && col==4)
        {
            result=findViewById(R.id.imageView21);
        }

        else if(row==2 && col==6)
        {
            result=findViewById(R.id.imageView23);
        }

        //other four tiles in row 2
        else if(row==2 && col==1)
        {
            result=findViewById(R.id.imageView18);
        }

        else if(row==2 && col==3)
        {
            result=findViewById(R.id.imageView20);
        }

        else if(row==2 && col==5)
        {
            result=findViewById(R.id.imageView22);
        }

        else if(row==2 && col==7)
        {
            result=findViewById(R.id.imageView24);
        }

        //first four tiles in row 3
        else if(row==3 && col==0)
        {
            result=findViewById(R.id.imageView25);
        }

        else if(row==3 && col==2)
        {
            result=findViewById(R.id.imageView27);
        }

        else if(row==3 && col==4)
        {
            result=findViewById(R.id.imageView29);
        }

        else if(row==3 && col==6)
        {
            result=findViewById(R.id.imageView31);
        }

        //other four tiles in row 3
        else if(row==3 && col==1)
        {
            result=findViewById(R.id.imageView26);
        }

        else if(row==3 && col==3)
        {
            result=findViewById(R.id.imageView28);
        }

        else if(row==3 && col==5)
        {
            result=findViewById(R.id.imageView30);
        }

        else if(row==3 && col==7)
        {
            result=findViewById(R.id.imageView32);
        }

        //first four tiles in row 4
        else if(row==4 && col==0)
        {
            result=findViewById(R.id.imageView33);
        }

        else if(row==4 && col==2)
        {
            result=findViewById(R.id.imageView35);
        }

        else if(row==4 && col==4)
        {
            result=findViewById(R.id.imageView37);
        }

        else if(row==4 && col==6)
        {
            result=findViewById(R.id.imageView39);
        }

        //second four tiles in row 4
        else if(row==4 && col==1)
        {
            result=findViewById(R.id.imageView34);
        }

        else if(row==4 && col==3)
        {
            result=findViewById(R.id.imageView36);
        }

        else if(row==4 && col==5)
        {
            result=findViewById(R.id.imageView38);
        }

        else if(row==4 && col==7)
        {
            result=findViewById(R.id.imageView40);
        }

        //first four tiles in row 5
        else if(row==5 && col==0)
        {
            result=findViewById(R.id.imageView41);
        }

        else if(row==5 && col==2)
        {
            result=findViewById(R.id.imageView43);
        }

        else if(row==5 && col==4)
        {
            result=findViewById(R.id.imageView45);
        }

        else if(row==5 && col==6)
        {
            result=findViewById(R.id.imageView47);
        }

        //other four tiles in row 5
        else if(row==5 && col==1)
        {
            result=findViewById(R.id.imageView42);
        }

        else if(row==5 && col==3)
        {
            result=findViewById(R.id.imageView44);
        }

        else if(row==5 && col==5)
        {
            result=findViewById(R.id.imageView46);
        }

        else if(row==5 && col==7)
        {
            result=findViewById(R.id.imageView48);
        }

        //first four tiles in row 6
        else if(row==6 && col==0)
        {
            result=findViewById(R.id.imageView49);
        }

        else if(row==6 && col==2)
        {
            result=findViewById(R.id.imageView51);
        }

        else if(row==6 && col==4)
        {
            result=findViewById(R.id.imageView53);
        }

        else if(row==6 && col==6)
        {
            result=findViewById(R.id.imageView55);
        }

        //other four tiles in row 6
        else if(row==6 && col==1)
        {
            result=findViewById(R.id.imageView50);
        }

        else if(row==6 && col==3)
        {
            result=findViewById(R.id.imageView52);
        }

        else if(row==6 && col==5)
        {
            result=findViewById(R.id.imageView54);
        }

        else if(row==6 && col==7)
        {
            result=findViewById(R.id.imageView56);
        }

        //first four tiles in row 7
        else if(row==7 && col==0)
        {
            result=findViewById(R.id.imageView57);
        }

        else if(row==7 && col==2)
        {
            result=findViewById(R.id.imageView59);
        }

        else if(row==7 && col==4)
        {
            result=findViewById(R.id.imageView61);
        }

        else if(row==7 && col==6)
        {
            result=findViewById(R.id.imageView63);
        }

        //other four tiles in row 7
        else if(row==7 && col==1)
        {
            result=findViewById(R.id.imageView58);
        }

        else if(row==7 && col==3)
        {
            result=findViewById(R.id.imageView60);
        }

        else if(row==7 && col==5)
        {
            result=findViewById(R.id.imageView62);
        }

        else if(row==7 && col==7)
        {
            result=findViewById(R.id.imageView64);
        }

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
            chessGrid[row][col]=BLACK_ROOK;
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
            chessGrid[row][col]=BLACK_KNIGHT;
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
            chessGrid[row][col]=BLACK_QUEEN;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_qdd45);
        }

        //otherwise, we put the object with a light border (since it is a light shaded chess location on the board)
        else
        {
            chessGrid[row][col]=BLACK_QUEEN;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_qdl45);
        }
    }

    public void addBlackKing(int row, int col)
    {
        //if we are in an even spot, we put the object with a black border (since it was a dark shaded chess piece)
        if(((row+col)%2)==0)
        {
            chessGrid[row][col]=BLACK_KING;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_kdd45);
        }

        //otherwise, we put the object with a light border (since it is a light shaded chess location on the board)
        else
        {
            chessGrid[row][col]=BLACK_KING;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_kdl45);
        }
    }

    public void addBlackPawn(int row, int col)
    {
        //if we are in an even spot, we put the object with a black border (since it was a dark shaded chess piece)
        if(((row+col)%2)==0)
        {
            chessGrid[row][col]=BLACK_PAWN;
            ImageView blackRookCorner = returnViewById(row, col);
            blackRookCorner.setImageResource(R.drawable.chess_pdd45);
        }

        //otherwise, we put the object with a light border (since it is a light shaded chess location on the board)
        else
        {
            chessGrid[row][col]=BLACK_PAWN;
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
    private void spanPawns(int colorStatus, int startRow)
    {
        if(colorStatus==BLACK)
        {
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                addBlackPawn(startRow, col);
            }
        }

        if(colorStatus==WHITE)
        {
            for (int col = 0; col < BOARD_SIZE; col++)
            {
                addWhitePawn(startRow, col);
            }
        }
    }

    public void spanBlankTiles()
    {
        for(int i=0; i<chessGrid.length; i++)
        {
            for(int j=0; j<chessGrid[i].length; j++)
            {
                addBlankTile(i, j);
            }
        }
    }

    public void resetGame()
    {
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

    //checking first row of chessboard (last row in array layout)
    public void a1Clicked(View view)
    {
        selectChessPiece(7, 0);
        selectDestinationLocation(7,0);
    }

    public void b1Clicked(View view)
    {
        selectChessPiece(7,1);
        selectDestinationLocation(7,1);
    }

    public void c1Clicked(View view)
    {
        selectChessPiece(7,2);
        selectDestinationLocation(7,2);
    }

    public void d1Clicked(View view)
    {
        selectChessPiece(7,3);
        selectDestinationLocation(7,3);
    }

    public void e1Clicked(View view)
    {
        selectChessPiece(7,4);
        selectDestinationLocation(7,4);
    }

    public void f1Clicked(View view)
    {
        selectChessPiece(7,5);
        selectDestinationLocation(7,5);
    }

    public void g1Clicked(View view)
    {
        selectChessPiece(7,6);
        selectDestinationLocation(7,6);
    }

    public void h1Clicked(View view)
    {
        selectChessPiece(7,7);
        selectDestinationLocation(7,7);
    }


    //checking second row of chessboard (second to last row in array layout)
    public void a2Clicked(View view)
    {
        selectChessPiece(6, 0);
        selectDestinationLocation(6,0);
        System.out.println("White kingside pawn clicked");

    }

    public void b2Clicked(View view)
    {
        selectChessPiece(6, 1);
        selectDestinationLocation(6,1);
    }

    public void c2Clicked(View view)
    {
        selectChessPiece(6, 2);
        selectDestinationLocation(6,2);
    }

    public void d2Clicked(View view)
    {
        selectChessPiece(6, 3);
        selectDestinationLocation(6,3);
    }

    public void e2Clicked(View view)
    {
        selectChessPiece(6, 4);
        selectDestinationLocation(6,4);
    }

    public void f2Clicked(View view)
    {
        selectChessPiece(6, 5);
        selectDestinationLocation(6,5);
    }

    public void g2Clicked(View view)
    {
        selectChessPiece(6, 6);
        selectDestinationLocation(6,6);
    }

    public void h2Clicked(View view)
    {
        selectChessPiece(6, 7);
        selectDestinationLocation(6,7);
    }

    //checking third row of chess board
    public void a3Clicked(View view)
    {
        selectChessPiece(5, 0);
        selectDestinationLocation(5, 0);
    }

    public void b3Clicked(View view)
    {
        selectChessPiece(5, 1);
        selectDestinationLocation(5, 1);
    }

    public void c3Clicked(View view)
    {
        selectChessPiece(5, 2);
        selectDestinationLocation(5, 2);
    }

    public void d3Clicked(View view)
    {
        selectChessPiece(5, 3);
        selectDestinationLocation(5, 3);
    }

    public void e3Clicked(View view)
    {
        selectChessPiece(5, 4);
        selectDestinationLocation(5, 4);
    }

    public void f3Clicked(View view)
    {
        selectChessPiece(5, 5);
        selectDestinationLocation(5, 5);
    }

    public void g3Clicked(View view)
    {
        selectChessPiece(5, 6);
        selectDestinationLocation(5, 6);
    }

    public void h3Clicked(View view)
    {
        selectChessPiece(5, 7);
        selectDestinationLocation(5, 7);
    }

    //checking fourth row of chess board
    public void a4Clicked(View view)
    {
        selectChessPiece(4,0);
        selectDestinationLocation(4, 0);
    }

    public void b4Clicked(View view)
    {
        selectChessPiece(4,1);
        selectDestinationLocation(4,1);
    }

    public void c4Clicked(View view)
    {
        selectChessPiece(4,2);
        selectDestinationLocation(4,2);
    }

    public void d4Clicked(View view)
    {
        selectChessPiece(4,3);
        selectDestinationLocation(4,3);
    }

    public void e4Clicked(View view)
    {
        selectChessPiece(4,4);
        selectDestinationLocation(4,4);
    }

    public void f4Clicked(View view)
    {
        selectChessPiece(4,5);
        selectDestinationLocation(4,5);
    }

    public void g4Clicked(View view)
    {
        selectChessPiece(4,6);
        selectDestinationLocation(4,6);
    }

    public void h4Clicked(View view)
    {
        selectChessPiece(4,7);
        selectDestinationLocation(4,7);
    }

    //checking the fifth row of the chessboard
    public void a5Clicked(View view)
    {
        selectChessPiece(3,0);
        selectDestinationLocation(3, 0);
    }

    public void b5Clicked(View view)
    {
        selectChessPiece(3,1);
        selectDestinationLocation(3, 1);
    }

    public void c5Clicked(View view)
    {
        selectChessPiece(3,2);
        selectDestinationLocation(3, 2);
    }

    public void d5Clicked(View view)
    {
        selectChessPiece(3,3);
        selectDestinationLocation(3, 3);
    }

    public void e5Clicked(View view)
    {
        selectChessPiece(3,4);
        selectDestinationLocation(3, 4);
    }

    public void f5Clicked(View view)
    {
        selectChessPiece(3,5);
        selectDestinationLocation(3, 5);
    }

    public void g5Clicked(View view)
    {
        selectChessPiece(3,6);
        selectDestinationLocation(3, 6);
    }

    public void h5Clicked(View view)
    {
        selectChessPiece(3,7);
        selectDestinationLocation(3, 7);
    }

    //checking the sixth row of the chess board
    public void a6Clicked(View view)
    {
        selectChessPiece(2,0);
        selectDestinationLocation(2, 0);
    }

    public void b6Clicked(View view)
    {
        selectChessPiece(2,1);
        selectDestinationLocation(2, 1);
    }

    public void c6Clicked(View view)
    {
        selectChessPiece(2,2);
        selectDestinationLocation(2, 2);
    }

    public void d6Clicked(View view)
    {
        selectChessPiece(2,3);
        selectDestinationLocation(2, 3);
    }

    public void e6Clicked(View view)
    {
        selectChessPiece(2,4);
        selectDestinationLocation(2, 4);
    }

    public void f6Clicked(View view)
    {
        selectChessPiece(2,5);
        selectDestinationLocation(2, 5);
    }

    public void g6Clicked(View view)
    {
        selectChessPiece(2,6);
        selectDestinationLocation(2, 6);
    }

    public void h6Clicked(View view)
    {
        selectChessPiece(2,7);
        selectDestinationLocation(2, 7);
    }

    //checking the 7th row of the chessboard (which is the second to first row in the array representation)
    public void a7Clicked(View view)
    {
        selectChessPiece(1, 0);
        selectDestinationLocation(1, 0);
    }

    public void b7Clicked(View view)
    {
        selectChessPiece(1, 1);
        selectDestinationLocation(1, 1);
    }

    public void c7Clicked(View view)
    {
        selectChessPiece(1, 2);
        selectDestinationLocation(1, 2);
    }

    public void d7Clicked(View view)
    {
        selectChessPiece(1, 3);
        selectDestinationLocation(1, 3);
    }

    public void e7Clicked(View view)
    {
        selectChessPiece(1, 4);
        selectDestinationLocation(1, 4);
    }

    public void f7Clicked(View view)
    {
        selectChessPiece(1, 5);
        selectDestinationLocation(1, 5);
    }

    public void g7Clicked(View view)
    {
        selectChessPiece(1, 6);
        selectDestinationLocation(1, 6);
    }

    public void h7Clicked(View view)
    {
        selectChessPiece(1, 7);
        selectDestinationLocation(1, 7);
    }

    //checking the 8th row of the chessboard, which is the first row in the array representation)
    public void a8Clicked(View view)
    {
        selectChessPiece(0,0);
        selectDestinationLocation(0,0);
    }

    public void b8Clicked(View view)
    {
        selectChessPiece(0,1);
        selectDestinationLocation(0,1);
    }

    public void c8Clicked(View view)
    {
        selectChessPiece(0,2);
        selectDestinationLocation(0,2);
    }

    public void d8Clicked(View view)
    {
        selectChessPiece(0,3);
        selectDestinationLocation(0,3);
    }

    public void e8Clicked(View view)
    {
        selectChessPiece(0,4);
        selectDestinationLocation(0,4);
    }

    public void f8Clicked(View view)
    {
        selectChessPiece(0,5);
        selectDestinationLocation(0,5);
    }

    public void g8Clicked(View view)
    {
        selectChessPiece(0,6);
        selectDestinationLocation(0,6);
    }

    public void h8Clicked(View view)
    {
        selectChessPiece(0,7);
        selectDestinationLocation(0,7);
    }

    public void clearSelection(View view)
    {
        selectedPieceRow = -1;
        selectedPieceCol = -1;

        TextView selectedPieceStarting = findViewById(R.id.startingLocation);
        selectedPieceStarting.setText("");

        TextView selectedPieceEnding = findViewById(R.id.endingLocation);
        selectedPieceEnding.setText("");
    }

    public void returnToMainMenu(View v)
    {
        finish();
    }

}
