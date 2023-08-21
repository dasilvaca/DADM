package com.dasilvaca.tictactoegame;

/* TicTacToeGame.java
 * By Frank McCown (Harding University)
 *
 * This is a tic-tac-toe game that runs in the console window.  The human
 * is X and the computer is O.
 */

import java.util.ArrayList;
import java.util.Random;

public class TicTacToeGame {

private static class Coordinate{
    public int i;
    public int j;
    Coordinate(int x, int y){
        this.i = x;
        this.j = y;
    }

}

    enum Symbol {
        X,
        O,
        OPEN_SPOT,
    }


    private final Symbol[][] mBoard = new Symbol[3][3]; //! I made it final, but I suspect it is not
    private static final int ROWS = 3;
    private static final int COLUMNS = 3;

    public static final Symbol HUMAN_PLAYER = Symbol.X;
    public static final Symbol COMPUTER_PLAYER = Symbol.O;


    private final Random mRand;

    /**
     * Clear the board of all X's and O's by setting all spots to OPEN_SPOT.
     */
    public void clearBoard() {
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++) {
                mBoard[i][j] = Symbol.OPEN_SPOT;
            }
        }
    }

    /**
     * Set the given player at the given location on the game board.
     * The location must be available, or the board will not be changed.
     *
     * @param player   - The HUMAN_PLAYER or COMPUTER_PLAYER
     * @param location - The Coordinates corresponding to location (i,j) to place the move
     */
    public void setMove(Symbol player, Coordinate location) {
        mBoard[location.i][location.j] = player;
    }

    /**
     * Return the best move for the computer to make. You must call setMove()
     * to actually make the computer move to that location.
     *
     * @return The best move for the computer to make (0-8).
     */
    public Coordinate getComputerMove() {

        // Check if in a single move O could win
        for(int i = 0; i < ROWS; i ++){
            for(int j = 0; j < COLUMNS; j++){
                if(mBoard[i][j].equals(Symbol.OPEN_SPOT)){
                    Coordinate possibleMove = new Coordinate(i,j);
                    setMove(COMPUTER_PLAYER, possibleMove);
                    if(checkForWinner() == 3){
                        return possibleMove;
                    }
                }
            }
        }

        // Check if can prevent X to win
        for(int i = 0; i < ROWS; i ++){
            for(int j = 0; j < COLUMNS; j++){
                if(mBoard[i][j].equals(Symbol.OPEN_SPOT)){
                    Coordinate possibleMove = new Coordinate(i,j);
                    setMove(HUMAN_PLAYER, possibleMove);
                    if(checkForWinner() == 2){
                        setMove(COMPUTER_PLAYER, possibleMove);
                        return possibleMove;
                    }
                }
            }
        }

        // Make a random move
        // Keep the free possible movements
        ArrayList<Coordinate> possibleMoves = new ArrayList<>();
        for (int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                if(mBoard[i][j].equals(Symbol.OPEN_SPOT)){
                    possibleMoves.add(new Coordinate(i,j));
                }
            }
        }
        int moveSelected = mRand.nextInt(possibleMoves.size());
        setMove(COMPUTER_PLAYER, possibleMoves.get(moveSelected));
        return possibleMoves.get(moveSelected);

    }

    /**
     * Check for a winner and return a status value indicating who has won.
     *
     * @return Return 0 if no winner or tie yet, 1 if it's a tie, 2 if X won,
     * or 3 if O won.
     */
    public int checkForWinner() {
        // Checks for a winner at rows and columns
        for(int i = 0; i < ROWS; i++){
            if(!mBoard[i][0].equals(Symbol.OPEN_SPOT) &&
            mBoard[i][0].equals(mBoard[i][1]) &&
            mBoard[i][1].equals(mBoard[i][2])
            ){
                return mBoard[i][0].equals(Symbol.X)?2:3;
            }
            else if(!mBoard[0][i].equals(Symbol.OPEN_SPOT) &&
                    mBoard[0][i].equals(mBoard[1][i]) &&
                    mBoard[1][i].equals(mBoard[2][i])
            ){
                return mBoard[0][i].equals(Symbol.X)?2:3;
            }
        }
        // Checks for a winner on diagonals
        Symbol mainDiagonalSymbol = mBoard[0][0];
        Symbol secondaryDiagonalSymbol = mBoard[0][ROWS - 1];
        // We say that, since every Diagonal starts with a non empty cell,
        // The symbol on it is a winner until the opposite is demonstrated
        boolean mainDiagonalWinner = !(mainDiagonalSymbol.equals(Symbol.OPEN_SPOT));
        boolean secondaryDiagonalWinner = !(secondaryDiagonalSymbol.equals(Symbol.OPEN_SPOT));
        for(int i = 0; i < ROWS && (mainDiagonalWinner || secondaryDiagonalWinner); i++){
            for(int j = 0; j < COLUMNS; j++){
                if(i == j && mainDiagonalWinner) {
                    if(!(mBoard[i][j].equals(mainDiagonalSymbol))) mainDiagonalWinner = false;
                }
                else if(i == ((ROWS - 1) - j) && secondaryDiagonalWinner){
                    if(!(mBoard[i][j].equals(secondaryDiagonalSymbol))) secondaryDiagonalWinner = false;
                }
            }
        }
        if (mainDiagonalWinner) return mainDiagonalSymbol.equals(Symbol.X) ? 2:3;
        if (secondaryDiagonalWinner) return secondaryDiagonalSymbol.equals(Symbol.X) ? 2:3;

        // Check for a tie
        for(int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLUMNS; j++){
                if(mBoard[i][j].equals(Symbol.OPEN_SPOT)) return  0;
            }
        }
        return 1;
    }

    public TicTacToeGame() {

        // Seed the random number generator
        mRand = new Random();

    }


}

