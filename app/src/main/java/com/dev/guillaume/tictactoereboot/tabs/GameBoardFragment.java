package com.dev.guillaume.tictactoereboot.tabs;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dev.guillaume.tictactoereboot.R;

import java.util.ArrayList;

public class GameBoardFragment extends Fragment implements View.OnClickListener {
    private ArrayList<Button> buttonList;
    private TextView currentPlayerView;
    private Button resetButton;
    private boolean playerX;
    private HistoryFragment historyFragment;

    // Multi-dimensional array representing the game board.
    // Cell values :
    // -1 : empty cell (not played yet)
    // 0 : played by X
    // 1 : played by O
    private int gameBoard[][] = new int[3][3];

    public void setHistoryFragment(HistoryFragment hf) {
        historyFragment = hf;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);

        currentPlayerView = (TextView) rootView.findViewById(R.id.textView);

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                gameBoard[i][j] = -1;

        resetButton = (Button) rootView.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);

        buttonList = new ArrayList<Button>();
        buttonList.add((Button) rootView.findViewById(R.id.button1));
        buttonList.add((Button) rootView.findViewById(R.id.button2));
        buttonList.add((Button) rootView.findViewById(R.id.button3));
        buttonList.add((Button) rootView.findViewById(R.id.button4));
        buttonList.add((Button) rootView.findViewById(R.id.button5));
        buttonList.add((Button) rootView.findViewById(R.id.button6));
        buttonList.add((Button) rootView.findViewById(R.id.button7));
        buttonList.add((Button) rootView.findViewById(R.id.button8));
        buttonList.add((Button) rootView.findViewById(R.id.button9));
        for (Button b : buttonList)
            b.setOnClickListener(this);

        playerX = true;

        return rootView;
    }

    // Checks the game board to see if there is a winner
    // returns :
    // 2 : if game is a draw
    // 0 : if player X has a winning line
    // 1 : if player O has a winning line
    // -1 : if no player has a winning line
    private int findWinner()
    {
        // Check for a winner in columns and lines
        for (int i = 0; i < gameBoard.length; i++)
        {
            if (gameBoard[0][i] != -1 && gameBoard[0][i] == gameBoard[1][i] && gameBoard[0][i] == gameBoard[2][i])
            {
                return gameBoard[0][i];
            }
            else if (gameBoard[i][0] != -1 && gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][0] == gameBoard[i][2])
            {
                return gameBoard[i][0];
            }
        }

        // Check for a winner in diagonals
        if (gameBoard[0][0] != -1 && gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2])
        {
            return gameBoard[0][0];
        }

        else if (gameBoard[0][2] != -1 && gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0])
        {
            return gameBoard[0][2];
        }

        // Check for a draw
        for (int[] lin : gameBoard)
        {
            for (int cell: lin)
            {
                if (cell == -1)
                {
                    return -1;
                }
            }
        }

        return 2;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void updateButton(Button button) {
        if (playerX) {
            button.setText("X");
            button.setBackground(Drawable.createFromPath("@drawable/player_x"));
            currentPlayerView.setText("Next player: O");
        }
        else {
            button.setText("O");
            currentPlayerView.setText("Next player: X");
        }

        button.setEnabled(false);
        playerX = !playerX;

    }

    public void reset_game() {
        for (Button b : buttonList) {
            b.setEnabled(true);
            b.setText("");
            playerX = true;
            currentPlayerView.setText("Next player: X");
        }
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                gameBoard[i][j] = -1;
    }

    public void show_popup(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        AlertDialog popup = builder.create();
        popup.setTitle("Tic Tac Toe Winner");
        popup.setMessage(msg);
        popup.show();
    }

    public void endGame(String msg, int winner) {
        show_popup(msg);
        historyFragment.add_winner(winner);
        reset_game();
    }

    public void update_game(int i, int j) {
        if (playerX)
            gameBoard[i][j] = 1;
        else
            gameBoard[i][j] = 0;

        int state = findWinner();
        if (state == 2)
            endGame("The game is a draw", 0);
        else {
            if (state == 0)
                endGame("The winner is X", 1);
            else {
                if (state == 1)
                    endGame("The winner is O", 2);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                updateButton(buttonList.get(0));
                update_game(0, 0);
                break;
            case R.id.button2:
                updateButton(buttonList.get(1));
                update_game(0, 1);
                break;
            case R.id.button3:
                updateButton(buttonList.get(2));
                update_game(0, 2);
                break;
            case R.id.button4:
                updateButton(buttonList.get(3));
                update_game(1, 0);
                break;
            case R.id.button5:
                updateButton(buttonList.get(4));
                update_game(1, 1);
                break;
            case R.id.button6:
                updateButton(buttonList.get(5));
                update_game(1, 2);
                break;
            case R.id.button7:
                updateButton(buttonList.get(6));
                update_game(2, 0);
                break;
            case R.id.button8:
                updateButton(buttonList.get(7));
                update_game(2, 1);
                break;
            case R.id.button9:
                updateButton(buttonList.get(8));
                update_game(2, 2);
                break;
            case R.id.reset_button:
                reset_game();
                break;
        }
    }
}
