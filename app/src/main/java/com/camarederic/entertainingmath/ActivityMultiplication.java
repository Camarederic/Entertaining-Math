package com.camarederic.entertainingmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class ActivityMultiplication extends AppCompatActivity {


    TextView score3, life3, time3;
    TextView question3;
    EditText answer3;
    Button buttonOk3;
    Button buttonNextQuestion3;


    Random random = new Random();

    int number1;
    int number2;
    int userAnswer3;
    int realAnswer3;
    int userScore3 = 0;
    int userLife3 = 3;

    CountDownTimer timer3;
    private static final long START_TIMER_IN_MILIS_3 = 60000;
    Boolean timer_running3;
    long time_left_in_milis3 = START_TIMER_IN_MILIS_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);


        score3 = findViewById(R.id.text_view_score_3);
        life3 = findViewById(R.id.text_view_life_3);
        time3 = findViewById(R.id.text_view_time_3);

        question3 = findViewById(R.id.text_view_question_3);

        answer3 = findViewById(R.id.edit_text_answer_3);

        buttonOk3 = findViewById(R.id.button_ok_3);
        buttonNextQuestion3 = findViewById(R.id.button_question_3);

        gameContinue();


        buttonOk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userAnswer3 = Integer.valueOf(answer3.getText().toString());

                pauseTimer();

                if (userAnswer3 == realAnswer3) {

                    userScore3 = userScore3 + 10;
                    score3.setText("" + userScore3);
                    question3.setText(R.string.text_congratulations_3);

                } else {
                    userLife3 = userLife3 - 1;
                    life3.setText("" + userLife3);
                    question3.setText(R.string.text_sorry_3);

                }

            }
        });


        buttonNextQuestion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answer3.setText("");
                resetTimer();

                if (userLife3 <= 0) {

                    Toast.makeText(getApplicationContext(), R.string.toast_multiplication_message, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(ActivityMultiplication.this, Result3.class);
                    intent.putExtra("score_3", userScore3);
                    startActivity(intent);
                    finish();

                } else {

                    gameContinue();

                }

            }
        });
    }


    public void gameContinue() {
        number1 = random.nextInt(50);
        number2 = random.nextInt(50);

        realAnswer3 = number1 * number2;

        question3.setText(number1 + " × " + number2);
        startTimer();

    }

    public void startTimer() {
        timer3 = new CountDownTimer(time_left_in_milis3, 1000) {
            @Override
            public void onTick(long millisUntiFinished) {

                time_left_in_milis3 = millisUntiFinished;
                updateText();

            }

            @Override
            public void onFinish() {

                timer_running3 = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife3 = userLife3 - 1;
                life3.setText("" + userLife3);
                question3.setText(R.string.text_time_is_up_3);

            }
        }.start();

        timer_running3 = true;
    }

    public void updateText() {
        int second = (int) ((time_left_in_milis3 / 1000) % 60);
        String time_left = String.format(Locale.getDefault(), "%02d", second);
        time3.setText(time_left);

    }

    public void pauseTimer() {
        timer3.cancel();
        timer_running3 = false;
    }

    public void resetTimer() {
        time_left_in_milis3 = START_TIMER_IN_MILIS_3;
        updateText();

    }


}