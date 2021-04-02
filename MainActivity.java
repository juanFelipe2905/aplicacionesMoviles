package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    RadioButton rbX, rbO;
    String square [][]=
            {
                    {"0","1","2"},
                    {"3","4","5"},
                    {"6","7","8"}
            };
    int statusGame = -1;
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbX = findViewById(R.id.rbX);
        rbO = findViewById(R.id.rbO);
        table = findViewById(R.id.table);
    }

    public void  onClickButton (View v) {
        ImageButton ibtn = (ImageButton) v;

        int resource = 0;
        if(updateImage(ibtn,rbX.isChecked()))
        {
            if (rbX.isChecked()) {
                resource = R.mipmap.cruz;
                rbO.setChecked(true);
            }
            else {
                resource = R.mipmap.cero;
                rbX.setChecked(true);

            }
            ibtn.setImageResource(resource);
            statusGame = checkWinner();
            if(statusGame == 1)
                Toast.makeText(this,"el ganador es" + (rbX.isChecked() ? "O" : "X"),
            Toast.LENGTH_LONG).show();
            else if(statusGame == 0)
            {
                Toast.makeText(this,"es empate",
                        Toast.LENGTH_LONG).show();
            }
        }

    }
    private boolean updateImage(ImageButton b, boolean isX)
    {
        for (int i = 0; i < square[0].length; i++)
            for (int j = 0; j < square[1].length; j++)
                if(b.getContentDescription().equals(square[i][j])){
                    square[i][j]=isX ? "X" : "O";
                    return true;
                }

        return false;

    }

    private int checkWinner(){
        if (square[0][0] == square[0][1] && square[0][1] == square[0][2]) //Fila 0

            return 1;
        else if (square[1][0] == square[1][1] && square[1][1] == square[1][2]) //Fila 1

            return 1;
        else if (square[2][0] == square[2][1] && square[2][1] == square[2][2]) // Fila 2

            return 1;

        else if (square[0][0] == square[1][0] && square[1][0] == square[2][0]) // col 0

            return 1;
        else if (square[0][1] == square[1][1] && square[1][1] == square[2][1]) // col 0

            return 1;
        else if (square[0][2] == square[1][2] && square[1][2] == square[2][2]) // col 0

            return 1;
        else if (square[0][0] == square[1][1] && square[1][1] == square[2][2]) // col 0

            return 1;
        else if (square[0][2] == square[1][1] && square[1][1] == square[2][0]) // col 0

            return 1;
        else if (square[0][0] != "0" && square[0][1] != "1" && square[0][2] != "2" //No winner
                && square[1][0] != "3" && square[1][1] != "4" && square[1][2] != "5"
                && square[2][0] != "6" && square[2][1] != "7" && square[2][2] != "8")

            return 0;
        else
            return -1;
    }
    public void resetGame(View vw)
    {
        statusGame = -1;
        String m2 [][]=
        {
            {"0","1","2"},
            {"3","4","5"},
            {"6","7","8"}

        };
        square = m2;

        for(int i=0; i < table.getChildCount(); i++)
        {
            View child = table.getChildAt(i);
            if(child != null && child instanceof TableRow)
            {
            TableRow row = (TableRow) child;
            for(int j = 0; j < row.getChildCount(); j++)
            {
                View v = row.getChildAt(j);
                if(v != null & v instanceof ImageButton)
                {
                    ImageButton img =(ImageButton)v;
                    img.setImageResource(R.mipmap.squere);
                }
            }
            }
        }
    }
}