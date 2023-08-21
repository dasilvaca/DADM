package com.dasilvaca.tictactoegame;

/* TicTacToeGame.java
 * By Frank McCown (Harding University)
 *
 * This is a tic-tac-toe game that runs in the console window.  The human
 * is X and the computer is O.
 */

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

    private char[] mBoard = {'1','2','3','4','5','6','7','8','9'};

    private Symbol[][] aBoard = new Symbol[3][3];
    private static final int ROWS = 3;
    private static final int COLUMNS = 3;

    public static final Symbol HUMAN_PLAYER = Symbol.X;
    public static final Symbol COMPUTER_PLAYER = Symbol.O;

    public static final char OPEN_SPOT = ' ';

    private final Random mRand;

    /**
     * Clear the board of all X's and O's by setting all spots to OPEN_SPOT.
     */
    public void clearBoard() {
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++) {
                aBoard[i][j] = Symbol.OPEN_SPOT;
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
        aBoard[location.i][location.j] = player;
    }

    /**
     * Return the best move for the computer to make. You must call setMove()
     * to actually make the computer move to that location.
     *
     * @return The best move for the computer to make (0-8).
     */
    public int getComputerMove() {

        return 0;
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
            if(!aBoard[i][0].equals(Symbol.OPEN_SPOT) &&
            aBoard[i][0].equals(aBoard[i][1]) &&
            aBoard[i][1].equals(aBoard[i][2])
            ){
                return aBoard[i][0].equals(Symbol.X)?2:3;
            }
            else if(!aBoard[0][i].equals(Symbol.OPEN_SPOT) &&
                    aBoard[0][i].equals(aBoard[1][i]) &&
                    aBoard[1][i].equals(aBoard[2][i])
            ){
                return aBoard[0][i].equals(Symbol.X)?2:3;
            }
        }
        // Checks for a winner on diagonals
        Symbol mainDiagonalSymbol = aBoard[0][0];
        Symbol secondaryDiagonalSymbol = aBoard[0][ROWS - 1];
        // We say that, since every Diagonal starts with a non empty cell,
        // The symbol on it is a winner until the opposite is demonstrated
        boolean mainDiagonalWinner = !(mainDiagonalSymbol.equals(Symbol.OPEN_SPOT));
        boolean secondaryDiagonalWinner = !(secondaryDiagonalSymbol.equals(Symbol.OPEN_SPOT));
        for(int i = 0; i < ROWS && (mainDiagonalWinner || secondaryDiagonalWinner); i++){
            for(int j = 0; j < COLUMNS; j++){
                if(i == j && mainDiagonalWinner) {
                    if(!(aBoard[i][j].equals(mainDiagonalSymbol))) mainDiagonalWinner = false;
                }
                else if(i == ((ROWS - 1) - j) && secondaryDiagonalWinner){
                    if(!(aBoard[i][j].equals(secondaryDiagonalSymbol))) secondaryDiagonalWinner = false;
                }
            }
        }
        if (mainDiagonalWinner) return mainDiagonalSymbol.equals(Symbol.X) ? 2:3;
        if (secondaryDiagonalWinner) return secondaryDiagonalSymbol.equals(Symbol.X) ? 2:3;

        // Check for a tie
        for(int i = 0; i < ROWS; i++){
            for (int j = 0; j < COLUMNS; j++){
                if(aBoard[i][j].equals(Symbol.OPEN_SPOT)) return  0;
            }
        }
        return 1;
    }

    public TicTacToeGame() {

        // Seed the random number generator
        mRand = new Random();

    }

    private void displayBoard()	{
        System.out.println();
        System.out.println(mBoard[0] + " | " + mBoard[1] + " | " + mBoard[2]);
        System.out.println("-----------");
        System.out.println(mBoard[3] + " | " + mBoard[4] + " | " + mBoard[5]);
        System.out.println("-----------");
        System.out.println(mBoard[6] + " | " + mBoard[7] + " | " + mBoard[8]);
        System.out.println();
    }

}

