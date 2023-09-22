package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class DashboradActivity extends AppCompatActivity {

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public static int counter = 0;

    boolean gameActive = true;

    TextView textView;
    Button resetBtn;
    ImageView imageView0, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashborad);
        textView= findViewById(R.id.turn_txt);
        imageView0= findViewById(R.id.imageView0);
        imageView1= findViewById(R.id.imageView1);
        imageView2= findViewById(R.id.imageView2);
        imageView3= findViewById(R.id.imageView3);
        imageView4= findViewById(R.id.imageView4);
        imageView5= findViewById(R.id.imageView5);
        imageView6= findViewById(R.id.imageView6);
        imageView7= findViewById(R.id.imageView7);
        imageView8= findViewById(R.id.imageView8);
        resetBtn= findViewById(R.id.reset_btn);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameReset(view);
            }
        });
    }

    public void playerTapped(View view) {
        ImageView imageView= (ImageView) view;
        int tappedImg= Integer.parseInt(imageView.getTag().toString());
        if(!gameActive){
            gameReset(view);
            counter=0;
        }
        if(gameState[tappedImg]==2){
            counter++;
            if(counter==9){
                gameActive= false;
            }
            gameState[tappedImg] = activePlayer;
            imageView.setTranslationY(-1000f);

            if(activePlayer==0){
                imageView.setImageResource(R.drawable.cross);
                activePlayer=1;
                textView.setText("Turn: O");
            }else{
                imageView.setImageResource(R.drawable.nought);
                activePlayer=0;
                textView.setText("Turn: X");
            }
            imageView.animate().translationYBy(1000f).setDuration(300);
        }
        int flag=0;
        if(counter > 4){
            for (int[] winPosition: winPositions){
                if(gameState[winPosition[0]]== gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]] != 2){
                    flag=1;
                    String winnerStr;
                    gameActive = false;
                    if (gameState[winPosition[0]] == 0) {
                        winnerStr = "X has won";
                    } else {
                        winnerStr = "O has won";
                    }
                    AlertDialog.Builder builder= new AlertDialog.Builder(DashboradActivity.this);
                    builder.setMessage(winnerStr);
                    builder.setTitle("Game Ended");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    // Show the Alert Dialog box
                    alertDialog.show();
                    gameReset(view);
                }
            }
        }
        if(counter==9 && flag==0){
            AlertDialog.Builder builder= new AlertDialog.Builder(DashboradActivity.this);
            builder.setMessage("Match Draw");
            builder.setTitle("Game Ended");
            builder.setCancelable(false);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();
            gameReset(view);
        }
    }

    private void gameReset(View view) {

        gameActive= true;
        activePlayer=0;
        Arrays.fill(gameState, 2);
        counter=0;

        imageView0.setImageResource(0);
        imageView1.setImageResource(0);
        imageView2.setImageResource(0);
        imageView3.setImageResource(0);
        imageView4.setImageResource(0);
        imageView5.setImageResource(0);
        imageView6.setImageResource(0);
        imageView7.setImageResource(0);
        imageView8.setImageResource(0);

        textView.setText("Turn: X");

    }
}