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
import android.view.MotionEvent;
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
    // private Button[][] mBoardButtons;
    // Different kind of informative texts
    private TextView mInfoTextView;

    // private final String[] mDifficulties = getResources().getStringArray(R.array.difficulty_options);

    private BoardView mBoardView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe_layout);
        mGame = new TicTacToeGame();
        mBoardView = (BoardView) findViewById(R.id.board);
        mBoardView.setOnTouchListener(mTouchListener);
        mBoardView.setGame(mGame);

        mInfoTextView = findViewById(R.id.information);

        Button mstartNewGameButton = findViewById(R.id.newGame);
        mstartNewGameButton.setText("New Game");
        mstartNewGameButton.setOnClickListener(new NewGameClickListener());
        mGame.clearBoard();

        startNewGame();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    private void notifyChangeAIDifficult() {
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
        } else if (itemSelected == R.id.quit) {
            endGameDialog().show();
            return true;
        }
        return false;

    }

    ;


    private void startNewGame() {
        // setup state of the game
        mGame.clearBoard();
        // Redraw the board
        mBoardView.invalidate();

        // Indicate Humans turn
        mInfoTextView.setText(R.string.new_game_dialogue);
        mInfoTextView.setTextColor(Color.GRAY);
    }

    ;

    private class NewGameClickListener implements View.OnClickListener {
        public void onClick(View view) {
            startNewGame();
        }
    }

    // Listen for touches on the board
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {


        public boolean onTouch(View v, MotionEvent event) {
            // Determine which cell was touched
            int col = (int) event.getX() / mBoardView.getBoardCellWidth();
            int row = (int) event.getY() / mBoardView.getBoardCellHeight();
            System.out.println(col);
            System.out.println(row);
            TicTacToeGame.Coordinate pos = new TicTacToeGame.Coordinate(col, row);
            int winner = mGame.checkForWinner();
            if (winner == 0 && setMove(TicTacToeGame.HUMAN_PLAYER, pos)) {
                System.out.println("+++++++++++++++++++++++++++++++");
                mGame.setMove(TicTacToeGame.COMPUTER_PLAYER, mGame.getComputerMove());
                winner = mGame.checkForWinner();
            }

            if (winner == 0) {
                mInfoTextView.setText(R.string.users_turn);
            } else if (winner == 1) {
                mInfoTextView.setText(R.string.game_tie);
            } else if (winner == 2) {
                mInfoTextView.setText(R.string.user_won);
            } else {
                mInfoTextView.setText(R.string.bot_won);
            }
            mBoardView.invalidate();
            // So we aren't notified of continued events when finger is moved
            return false;
        }
    };

    private boolean setMove(TicTacToeGame.Symbol player, TicTacToeGame.Coordinate location) {
        if (mGame.getOccupied(location) == TicTacToeGame.Symbol.OPEN_SPOT) {
            mGame.setMove(player, location);
            mBoardView.invalidate(); // Redraw the board
            return true;
        }
        return false;
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
                if (which == 0) {
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
        builder.setNegativeButton(R.string.no, (dialog, id) -> {
        });
        return builder.create();
    }


}




