package com.camarederic.entertainingmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result3 extends AppCompatActivity {

    TextView result3;
    Button play_again3;
    Button exit3;

    int score3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result3);

        result3 = findViewById(R.id.text_view_result_3);
        play_again3 = findViewById(R.id.button_play_again_3);
        exit3 = findViewById(R.id.button_exit_3);

        Intent intent = getIntent();
        score3 = intent.getIntExtra("score_3", 0);
        String userScore3 = String.valueOf(score3);
        result3.setText(getString(R.string.text_your_score_3) + " " + userScore3);

        play_again3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Result3.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        exit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });


    }
}