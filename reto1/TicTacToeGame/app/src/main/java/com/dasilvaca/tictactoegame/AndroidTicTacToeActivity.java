package com.dasilvaca.tictactoegame;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class AndroidTicTacToeActivity extends Activity {
    // Represents the internal state of the game
    TicTacToeGame mGame;
    // Buttons on the grid
    private Button[][] mBoardButtons;
    // Different kind of informative texts
    private TextView mInfoTextView;

    private Button mstartNewGameButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe_layout);

        mBoardButtons = new Button[TicTacToeGame.ROWS][TicTacToeGame.COLUMNS];
        mBoardButtons[0][0] = findViewById(R.id.one);
        mBoardButtons[0][1] = findViewById(R.id.two);
        mBoardButtons[0][2] = findViewById(R.id.three);
        mBoardButtons[1][0] = findViewById(R.id.four);
        mBoardButtons[1][1] = findViewById(R.id.five);
        mBoardButtons[1][2] = findViewById(R.id.six);
        mBoardButtons[2][0] = findViewById(R.id.seven);
        mBoardButtons[2][1] = findViewById(R.id.eight);
        mBoardButtons[2][2] = findViewById(R.id.nine);

        mInfoTextView = findViewById(R.id.information);

        mstartNewGameButton = findViewById(R.id.newGame);
        mstartNewGameButton.setText("New Game");
        mstartNewGameButton.setOnClickListener(new NewGameClickListener());

        mGame = new TicTacToeGame();

        startNewGame();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add("New Game");
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        startNewGame();
        return true;
    }


    private void startNewGame(){
        // setup state of the game
        mGame.clearBoard();
        // Reset all text on the buttons
        for (int i = 0; i < TicTacToeGame.ROWS; i++){
            for (int j = 0; j < TicTacToeGame.COLUMNS; j++){
                mBoardButtons[i][j].setText((""));
                mBoardButtons[i][j].setEnabled(true);
                mBoardButtons[i][j].setOnClickListener(new ButtonClickListener(new TicTacToeGame.Coordinate(i, j)));

            }
        }

        // Indicate Humans turn
        mInfoTextView.setText("You go first");
        mInfoTextView.setTextColor(Color.GRAY);
    }
    private class NewGameClickListener implements View.OnClickListener{
        public  void onClick(View view){
            startNewGame();
        }
    }

// Handles clicks on the game board buttons
private class ButtonClickListener implements View.OnClickListener{
        TicTacToeGame.Coordinate location;
        public ButtonClickListener(TicTacToeGame.Coordinate location) {
            this.location = location;
        }

//    @Override
    public void onClick(View view) {
            if (mBoardButtons[location.i][location.j].isEnabled()){
                setMove(TicTacToeGame.HUMAN_PLAYER, location);
                // state variable for the winner
                int winner = mGame.checkForWinner();
                if (winner == 0){
                    mInfoTextView.setText("Now is Android Turn to play!");
                    TicTacToeGame.Coordinate move = mGame.getComputerMove();
                    setMove(TicTacToeGame.COMPUTER_PLAYER, move);
                    winner = mGame.checkForWinner();
                }
                if (winner == 0){
                    mInfoTextView.setText("Now is your turn to play!");
                    return;
                } else if (winner == 1) {
                    mInfoTextView.setText("It's a tie!");
                } else if (winner == 2) {
                    mInfoTextView.setText("Congratulations! You won!");
                }
                else {
                    mInfoTextView.setText("Android won this tima D: Better luck next time!");
                }
                for(int i = 0; i < TicTacToeGame.ROWS; i++){
                    for (int j = 0; j < TicTacToeGame.COLUMNS; j++){
                        mBoardButtons[i][j].setEnabled(false);
                    }
                }
            }


    }

    private void setMove(TicTacToeGame.Symbol player, TicTacToeGame.Coordinate location){
            mGame.setMove(player, location);
            mBoardButtons[location.i][location.j].setEnabled(false);
            mBoardButtons[location.i][location.j].setText(player.name()); // Lets see if name is equal to toString in this context
            if (player.equals(TicTacToeGame.HUMAN_PLAYER)) {
                mBoardButtons[location.i][location.j].setTextColor(Color.GREEN);
            }
            else {
                mBoardButtons[location.i][location.j].setTextColor(Color.RED);
            }
    }


}

}


