package com.dasilvaca.tictactoegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

public class BoardView extends android.view.View {
    // Width of the board grid lines
    public static final int GRID_WIDTH = 12;

    private TicTacToeGame mGame;

    public void setGame(TicTacToeGame game) {
        mGame = game;
    }

    private Bitmap mHumanBitmap;
    private Bitmap mComputerBitmap;

    private Paint mPaint;

    public void initialize() {
        mHumanBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.x);
        mComputerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.o);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public BoardView(Context context) {
        super(context);
        initialize();
    }

    public BoardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
// Determine the width and height of the View
        int boardWidth = getWidth();
// Make thick, light gray lines
        mPaint.setColor(Color.LTGRAY);
        mPaint.setStrokeWidth(GRID_WIDTH);
// Draw the two vertical board lines
        int cellWidth = boardWidth / 3;
        canvas.drawLine(cellWidth, 0, cellWidth, boardWidth, mPaint);
        canvas.drawLine(0, cellWidth, boardWidth, cellWidth, mPaint);
        canvas.drawLine(cellWidth * 2, 0, cellWidth * 2, boardWidth, mPaint);
        canvas.drawLine(0, cellWidth * 2, boardWidth, cellWidth * 2, mPaint);
        // Draw all the X and O images
        for (int i = 0; i < TicTacToeGame.COLUMNS; i++) {

            for (int j = 0; j < TicTacToeGame.ROWS; j++) {
                System.out.println("===================================");

                TicTacToeGame.Coordinate coordinate = new TicTacToeGame.Coordinate(i, j);
                if (mGame != null) System.out.println(mGame.getOccupied(coordinate));
// Define the boundaries of a destination rectangle for the image
                int left = cellWidth * i;
                int top = cellWidth * j;
                int right = cellWidth * (i + 1);
                int bottom = cellWidth * (j + 1);

                if (mGame != null && mGame.getOccupied(coordinate) == TicTacToeGame.HUMAN_PLAYER) {

                    System.out.println(mGame.getOccupied(coordinate).toString());
                    canvas.drawBitmap(mHumanBitmap,
                            null, // src
                            new Rect(left, top, right, bottom), // dest
                            null);

                } else if (mGame != null && mGame.getOccupied(coordinate) == TicTacToeGame.COMPUTER_PLAYER) {
                    canvas.drawBitmap(mComputerBitmap,
                            null, // src
                            new Rect(left, top, right, bottom), // dest
                            null);

                }

            }

        }
    }

    public int getBoardCellWidth() {
        return getWidth() / 3;
    }

    public int getBoardCellHeight() {
        return getWidth() / 3;
    }
}
