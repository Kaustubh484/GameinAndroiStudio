package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0 for red, 1 for yellow, 2 for empty
    int playercode = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    boolean active = true;
    int[][] winningpositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropdown(View view) {
        ImageView block = (ImageView) view;

        int tag = Integer.parseInt(block.getTag().toString());
        if (gameState[tag] == 2 && active) {
            gameState[tag] = playercode;

            block.setTranslationY(-2000);
            if (playercode == 0) {
                block.setImageResource(R.drawable.red);

                playercode = 1;

            } else if (playercode == 1) {
                block.setImageResource(R.drawable.yellow);
                playercode = 0;
            }
            block.animate().translationYBy(2000);


            String winner = null;
            for (int[] winnerposition : winningpositions) {

                if (gameState[winnerposition[0]] == gameState[winnerposition[1]] && gameState[winnerposition[1]] == gameState[winnerposition[2]] && gameState[winnerposition[0]] != 2) {

                    active = false;
                    if (playercode == 0) {
                        winner = "yellow";
                    } else if (playercode == 1) {
                        winner = "red";
                    }
                    Toast.makeText(this, winner + " won", Toast.LENGTH_LONG).show();
                }


            }


        }
    }
    public void playAgain(View view){
        GridLayout gridLayout= (GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView counter= (ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0; i<gameState.length;i++){
            gameState[i]=2;
        }
        active=true;

    }














    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}