package com.dasilvaca.tictactoegame;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class AndroidTicTacToeActivity extends AppCompatActivity {

    //Start Game Dialog
//    StartGameDialogFragment startGameDialog;
    // Represents the internal state of the game
    private TicTacToeGame mGame;
    // Buttons on the grid
    private Button[][] mBoardButtons;
    // Different kind of informative texts
    private TextView mInfoTextView;

//    private final String[] mDifficulties = getResources().getStringArray(R.array.difficulty_options);

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

        Button mstartNewGameButton = findViewById(R.id.newGame);
        mstartNewGameButton.setText("New Game");
        mstartNewGameButton.setOnClickListener(new NewGameClickListener());

        mGame = new TicTacToeGame();

        startNewGame();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    private void notifyChangeAIDifficult(){
        Toast.makeText(this,
                getResources().getString(R.string.difficulty_set) +
                        " " + mGame.getDifficultyLevel().toString()
                , Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();

        if (itemSelected == R.id.new_game) {
            startGameDialog().show();
            return true;
        } else if (itemSelected == R.id.ai_difficulty) {
            setDifficultyDialog().show();
            return true;
        }
        else if (itemSelected == R.id.quit){
            endGameDialog().show();
            return true;
        }
        return false;

    };


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
        mInfoTextView.setText(R.string.new_game_dialogue);
        mInfoTextView.setTextColor(Color.GRAY);
    };
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
                    mInfoTextView.setText(R.string.bots_turn);
                    TicTacToeGame.Coordinate move = mGame.getComputerMove();
                    setMove(TicTacToeGame.COMPUTER_PLAYER, move);
                    winner = mGame.checkForWinner();
                }
                if (winner == 0){
                    mInfoTextView.setText(R.string.users_turn);
                    return;
                } else if (winner == 1) {
                    mInfoTextView.setText(R.string.game_tie);
                } else if (winner == 2) {
                    mInfoTextView.setText(R.string.user_won);
                }
                else {
                    mInfoTextView.setText(R.string.bot_won);
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

    private AlertDialog startGameDialog() {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_start_game);
        builder.setPositiveButton(R.string.start, (dialog, id) -> {
            Toast.makeText(this, R.string.started_game, Toast.LENGTH_SHORT).show();
            startNewGame();
        });
        builder.setNegativeButton(R.string.cancel, (dialog, id) -> {

        });
        return builder.create();
    }

    private AlertDialog setDifficultyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(R.string.selector_game_difficulty);

        builder.setItems(R.array.difficulty_options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0){
                            mGame.setDifficultyLevel(TicTacToeGame.DifficultyLevel.Easy);
                        } else if (which == 1) {
                            mGame.setDifficultyLevel(TicTacToeGame.DifficultyLevel.Harder);
                        } else if (which == 2) {
                            mGame.setDifficultyLevel(TicTacToeGame.DifficultyLevel.Expert);
                        }
                        notifyChangeAIDifficult();
                    }
                });

        return builder.create();
    }

    private AlertDialog endGameDialog() {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_end_game);
        builder.setPositiveButton(R.string.yes, (dialog, id) -> {
            AndroidTicTacToeActivity.this.finish();
        });
        builder.setNegativeButton(R.string.no, (dialog, id) -> {});
        return builder.create();
    }



}




