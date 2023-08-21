

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
        OPEN_SPOT,
        X,
        O,
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
                System.out.println(Symbol.X.ordinal());
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
        
        return 0;
    }

    public TicTacToeGame() {

        // Seed the random number generator
        mRand = new Random();
        clearBoard();

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

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}

