package com.camarederic.entertainingmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result2 extends AppCompatActivity {

    TextView result2;
    Button play_again2;
    Button exit2;

    int score2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);

        result2 = findViewById(R.id.text_view_result_2);
        play_again2 = findViewById(R.id.button_play_again_2);
        exit2 = findViewById(R.id.button_exit_2);

        Intent intent = getIntent();
        score2 = intent.getIntExtra("score_2", 0);
        String userScore2 = String.valueOf(score2);
        result2.setText(getString(R.string.text_your_score_2) + " " + userScore2);

        play_again2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Result2.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        exit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });


    }
}